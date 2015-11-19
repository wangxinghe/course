package me.wangxinghe.courses.rest.model;

import org.json.JSONObject;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class UniversityList {
    private University[] elements;

    public static class University {

        private int id;

        private String shortName;

        private JSONObject links;
    }
}
