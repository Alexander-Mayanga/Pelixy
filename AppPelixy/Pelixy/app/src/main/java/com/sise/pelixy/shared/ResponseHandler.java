package com.sise.pelixy.shared;

public interface ResponseHandler<T> {
    void onSuccess(T response);
    void onError(String message);
}
