module org.example.editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires flowless;
    requires org.antlr.antlr4.runtime;

    exports org.example.testLang;
    exports org.example.interpreter;
    opens org.example.editor to javafx.fxml;
    exports org.example.editor;
}