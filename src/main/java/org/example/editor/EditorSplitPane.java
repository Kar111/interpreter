package org.example.editor;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;

public class EditorSplitPane {
    public static SplitPane createSplitPane(
            VBox root,
            VirtualizedScrollPane<StyleClassedTextArea>  editorScrollPan,
            VirtualizedScrollPane<StyleClassedTextArea> logScrollPan,
            double dividerPosition) {
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0, dividerPosition);
        splitPane.getItems().addAll(editorScrollPan, logScrollPan);
        splitPane.prefHeightProperty().bind(root.heightProperty());
        return splitPane;
    }
}
