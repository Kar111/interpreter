package org.example.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.function.IntFunction;

class CustomLineNumberFactory implements IntFunction<Node> {

    @Override
    public Node apply(int lineNumber) {
        Label label = new Label(lineNumber + " ");
        int fixedWidth = 40;
        label.setMinWidth(fixedWidth);
        label.setMaxWidth(fixedWidth);
        label.setPrefWidth(fixedWidth);
        return label;
    }
}
