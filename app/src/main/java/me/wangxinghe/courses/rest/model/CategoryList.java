package me.wangxinghe.courses.rest.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class CategoryList implements Serializable {
    public Category[] elements;

    @SerializedName("linked")
    public JSONObject linked;

    public static class Category implements Serializable {

        public int id;

        public String name;

        public String shortName;

        public String description;

        public Links links;

        public static class Links {
            public int[] courses;
        }
    }
}
