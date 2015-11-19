package me.wangxinghe.courses.rest.model;

import org.json.JSONObject;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class InstructorList {
    private Instructor[] elements;

    public static class Instructor {
        private int id;

        private String firstName;

        private String lastName;

        private JSONObject links;
    }
}
