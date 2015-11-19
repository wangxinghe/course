package me.wangxinghe.courses.model;

import android.util.Log;

import me.wangxinghe.courses.rest.CourseRestClient;
import me.wangxinghe.courses.rest.api.CourseAPI;
import me.wangxinghe.courses.rest.model.CourseList;
import me.wangxinghe.courses.rest.model.InstructorList;
import me.wangxinghe.courses.rest.model.SessionList;
import me.wangxinghe.courses.rest.model.UniversityList;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class CourseModel {
    private static final String TAG = "Course";
    private CourseAPI mCourseAPI;

    public CourseModel() {
        mCourseAPI = CourseRestClient.getInstance().getCourseAPI();
    }

    public void fetchCourses() {
        mCourseAPI.getCourseList("", "").enqueue(new Callback<CourseList>() {
            @Override
            public void onResponse(Response<CourseList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    CourseList courseList = response.body();
                } else {
                    Log.d(TAG, "onResponse fail" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure " + t);
            }
        });
    }

    public void fetchCourseDetail(int courseId, final EventDispatcher<CourseList.Course> dispatcher) {
        mCourseAPI.getCourseList(courseId, "aboutTheCourse", "universities").enqueue(new Callback<CourseList>() {
            @Override
            public void onResponse(Response<CourseList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    CourseList courseList = response.body();
                    CourseList.Course course = courseList.elements[0];
                    dispatcher.onResponseCallback(course);
                } else {
                    Log.d(TAG, "onResponse fail" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void fetchUniversities() {
        mCourseAPI.getUniversityList().enqueue(new Callback<UniversityList>() {
            @Override
            public void onResponse(Response<UniversityList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    UniversityList universityList = response.body();
                } else {
                    Log.d(TAG, "onResponse fail" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure " + t);
            }
        });
    }

    public void fetchCategories() {
//        mCourseAPI.getCategoryList().enqueue(new Callback<CategoryList>() {
//            @Override
//            public void onResponse(Response<CategoryList> response, Retrofit retrofit) {
//                if (response.isSuccess()) {
//                    Log.d(TAG, "onResponse " + response.body());
//                    CategoryList categoryList = response.body();
//                } else {
//                    Log.d(TAG, "onResponse fail" + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure " + t);
//            }
//        });
    }

    public void fetchInstructors() {
        mCourseAPI.getInstructorList().enqueue(new Callback<InstructorList>() {
            @Override
            public void onResponse(Response<InstructorList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    InstructorList instructorList = response.body();
                } else {
                    Log.d(TAG, "onResponse fail" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure " + t);
            }
        });
    }

    public void fetchSessions() {
        mCourseAPI.getSessionList().enqueue(new Callback<SessionList>() {
            @Override
            public void onResponse(Response<SessionList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    SessionList sessionList = response.body();
                } else {
                    Log.d(TAG, "onResponse fail" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure " + t);
            }
        });
    }

}
