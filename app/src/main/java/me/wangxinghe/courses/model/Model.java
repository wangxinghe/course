package me.wangxinghe.courses.model;

/**
 * Created by wangxinghe on 17/11/15.
 */
public class Model {
    private static Model sInstance;

    private CourseModel mCourseModel;
    private CategoryModel mCategoryModel;

    public static Model getInstance() {
        if (sInstance == null) {
            synchronized (Model.class) {
                if (sInstance == null) {
                    sInstance = new Model();
                }
            }
        }

        return sInstance;
    }

    private Model() {
        mCourseModel = new CourseModel();
        mCategoryModel = new CategoryModel();
    }

    public CourseModel getCourseModel() {
        return mCourseModel;
    }

    public CategoryModel getCategoryModel() {
        return mCategoryModel;
    }
}
