package com.sunyubo.mvp.model.bean;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2017/2/21.
 */

public class HttpResult<T> {
    public int error_code;
    public String reason;
    public T result;
}
