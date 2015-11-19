package me.wangxinghe.courses.rest.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wangxinghe on 11/11/15.
 */
public class CourseList implements Serializable {
    @SerializedName("elements")
    public Course[] elements;

    @SerializedName("paging")
    public Paging paging;
    @SerializedName("linked")
    public Linked linked;

    public static class Course implements Serializable {
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("description")
        public String description;

        public String slug;

        public String courseType;

        public String workload;

        public String photoUrl;

        public DomainType[] domainTypes;

        public String[] partnerIds;

        public String[] primaryLanguages;
    }

    public static class Paging {
        public int next;
        public int total;
    }

    public static class Linked {
        @SerializedName("partners.v1")
        public PartnerV1[] partners_v1;
    }

    public static class PartnerV1 {
        public int id;
        public String name;
        public String shortName;
    }

    public static class DomainType {
        public String subdomainId;
        public String domainId;
    }
}
