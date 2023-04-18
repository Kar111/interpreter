package org.example.editor;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.editor.EditorSplitPane.createSplitPane;
import static org.example.editor.InterpreterService.get_response;
import static org.example.editor.KeywordStyles.KEYWORD_STYLES;


public class EditorApplication extends Application {
    private static final int LOG_DELAY = 250;
    private static final int SYNTAX_DECORATION_DELAY = 50;
    private static final double DIVIDER_POSITION = 0.6;
    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 500;
    private static final String SCENE_TITLE = "Interpreter";

    private static final Pattern KEYWORD_PATTERN = Pattern.compile(
            "\\b(" + String.join("|", KEYWORD_STYLES.keySet()) + ")\\b" + "|(\"[^\"]+\")"
    );

    private ExecutorService interpreterExecutor = Executors.newSingleThreadExecutor();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EditorApplication.class.getResource("/org/example/editor/editor-view.fxml"));
        VBox root = fxmlLoader.load();

        StyleClassedTextArea editorTextArea = new StyleClassedTextArea();
        StyleClassedTextArea logTextArea = new StyleClassedTextArea();
        logTextArea.setEditable(false);
        logTextArea.setId("logTextArea");

        // Set the LineNumberFactory to display line numbers
        editorTextArea.setParagraphGraphicFactory(new CustomLineNumberFactory());

        // Wrap the StyleClassedTextArea in a VirtualizedScrollPane
        VirtualizedScrollPane<StyleClassedTextArea> editorScrollPan = new VirtualizedScrollPane<>(editorTextArea);
        VirtualizedScrollPane<StyleClassedTextArea> logScrollPan = new VirtualizedScrollPane<>(logTextArea);

        // Create SplitPane
        SplitPane splitPane = createSplitPane(root, editorScrollPan, logScrollPan, DIVIDER_POSITION);

        // Add the SplitPane to the VBox
        root.getChildren().add(0, splitPane);

        PauseTransition pause = new PauseTransition(Duration.millis(LOG_DELAY));
        PauseTransition pauseSyntaxDecoration = new PauseTransition(Duration.millis(SYNTAX_DECORATION_DELAY));

        editorTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            pauseSyntaxDecoration.setOnFinished(event -> {
                Platform.runLater(() -> {
                    editorTextArea.setStyleSpans(0, computeKeywordHighlighting(newValue));
                });
            });
            if (pauseSyntaxDecoration.getStatus() == Animation.Status.RUNNING) {
                pauseSyntaxDecoration.stop();
            }
            pauseSyntaxDecoration.playFromStart();

        });

        editorTextArea.textProperty().addListener((observable, prefText, currentText) -> {
            pause.setOnFinished(event -> {
                Task<InterpreterResponse[]> task = new Task<>() {
                    @Override
                    protected InterpreterResponse[] call(){
                        return get_response(currentText);
                    }
                };

                task.setOnSucceeded(event1 -> {
                    InterpreterResponse[] response = task.getValue();

                    Platform.runLater(() -> {
                        logTextArea.replaceText("");
                        for (InterpreterResponse resp : response) {
                            appendResponseToLog(resp, logTextArea);
                        }
                    });
                });

                // Cancel the previous task if it's still running
                interpreterExecutor.shutdownNow();
                // Create a new single-threaded executor
                interpreterExecutor = Executors.newSingleThreadExecutor();
                // Execute the new task
                new Thread(task).start();
                interpreterExecutor.submit(task);
            });

            // If the pause is still running, stop it and restart
            if (pause.getStatus() == Animation.Status.RUNNING) {
                pause.stop();
            }
            pause.playFromStart();

        });

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle(SCENE_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    private void appendResponseToLog(InterpreterResponse response, StyleClassedTextArea logTextArea) {

        int start = logTextArea.getLength();
        logTextArea.appendText(response.message() + "\n");
        int end = logTextArea.getLength();

        if (response.status() == ResponseStatus.SUCCESS) {
            logTextArea.setStyleClass(start, end, "success-text");
        } else {
            logTextArea.setStyleClass(start, end, "error-text");
        }
    }

    private static StyleSpans<Collection<String>> computeKeywordHighlighting(String text) {
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastKeywordEnd = 0;

        Matcher matcher = KEYWORD_PATTERN.matcher(text);

        while (matcher.find()) {
            String style = KEYWORD_STYLES.getOrDefault(matcher.group(), "quoted-text");
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeywordEnd);
            spansBuilder.add(Collections.singleton(style), matcher.end() - matcher.start());
            lastKeywordEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKeywordEnd);
        return spansBuilder.create();
    }

    @Override
    public void stop() {
        interpreterExecutor.shutdownNow(); // Forcefully cancel running tasks after app exit
    }

}
