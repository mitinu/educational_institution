package org.example.backend.DTO;

public class Response<T> {
    private short status;
    private String message;
    private T data;

    public Response() {}

    public Response(short status) {
        this.status = status;
    }

    public Response(short status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
