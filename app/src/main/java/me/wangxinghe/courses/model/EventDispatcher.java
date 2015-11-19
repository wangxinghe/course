package me.wangxinghe.courses.model;

/**
 * Created by wangxinghe on 17/11/15.
 */
public interface EventDispatcher<T> {
    void onResponseCallback(T t);
}