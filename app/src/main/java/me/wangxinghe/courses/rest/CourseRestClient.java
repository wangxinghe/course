package me.wangxinghe.courses.rest;

import com.squareup.okhttp.OkHttpClient;

import me.wangxinghe.courses.rest.api.CourseAPI;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wangxinghe on 12/11/15.
 */
public class CourseRestClient {
    private static CourseRestClient sInstance;
    private CourseAPI mAPI;

    public static CourseRestClient getInstance() {
        if (sInstance == null) {
            synchronized (CourseRestClient.class) {
                if (sInstance == null) {
                    sInstance = new CourseRestClient();
                }
            }
        }
        return sInstance;
    }

    private CourseRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CourseAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient())
                .build();
        mAPI = retrofit.create(CourseAPI.class);
    }

    public CourseAPI getCourseAPI() {
        return mAPI;
    }

}
