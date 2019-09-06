package com.example.enticement.bean;

import androidx.annotation.NonNull;

public class Status<T> {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int LOADING = 2;

    public static final int LOAD_REFRESH = 3;
    public static final int LOAD_MORE = 4;

    public int status;
    public T content;
    public String message;

    public int loadType;

    public Status(int status, T content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }

    public Status(int status, T content, String message, int loadType) {
        this.status = status;
        this.content = content;
        this.message = message;
        this.loadType = loadType;
    }

    public static <T> Status<T> success(T content) {
        return new Status<>(SUCCESS, content, null);
    }

    public static <T> Status<T> refreshSuccess(T content) {
        return new Status<>(SUCCESS, content, null, LOAD_REFRESH);
    }

    public static <T> Status<T> moreSuccess(T content) {
        return new Status<>(SUCCESS, content, null, LOAD_MORE);
    }

    public static <T> Status<T> error(T content, String message) {
        return new Status<>(ERROR, content, message);
    }

    public static <T> Status<T> refreshError(T content, String message) {
        return new Status<>(ERROR, content, message, LOAD_REFRESH);
    }

    public static <T> Status<T> moreError(T content, String message) {
        return new Status<>(ERROR, content, message, LOAD_MORE);
    }

    public static <T> Status<T> loading(T content) {
        return new Status<>(LOADING, content, null);
    }

    @NonNull
    @Override
    public String toString() {
        return "Status{" +
                "status=" + status +
                ", content=" + content +
                ", message='" + message + '\'' +
                '}';
    }
}
