package org.example.ast;

public class PrintStringNode extends StmtNode{
    private final String stringValue;

    public PrintStringNode(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
