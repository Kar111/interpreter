package org.example.editor;

public enum ResponseStatus {
    ERROR("error"),
    SUCCESS("success");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
