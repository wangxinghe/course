package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class CourseGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "me.wangxinghe.greendao");

        addTable(schema);

        final String outDir = "/Users/wangxinghe/Work_Didi/work_space/Courses/app/src/main/java-gen";
        new DaoGenerator().generateAll(schema, outDir);
    }

    private static void addTable(Schema schema) {
        Entity course = schema.addEntity("Course");
        course.addIdProperty();
        course.addStringProperty("id").notNull();
        course.addStringProperty("name");
        course.addStringProperty("description");
        course.addStringProperty("slug");
        course.addStringProperty("courseType");
        course.addStringProperty("workload");
        course.addShortProperty("photoUrl");
    }
}
