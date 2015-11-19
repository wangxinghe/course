package me.wangxinghe.courses.rest.model;

import org.json.JSONObject;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class SessionList {
    private Session[] elements;

    public static class Session {
        private int id;

        private String homeLink;

        private JSONObject links;
    }
}
