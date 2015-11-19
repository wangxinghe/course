package me.wangxinghe.courses.rest.api;

import me.wangxinghe.courses.rest.model.CategoryList;
import me.wangxinghe.courses.rest.model.CourseList;
import me.wangxinghe.courses.rest.model.InstructorList;
import me.wangxinghe.courses.rest.model.SessionList;
import me.wangxinghe.courses.rest.model.UniversityList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by wangxinghe on 11/11/15.
 */
public interface CourseAPI {
    /**
     * base url
     */
    public static final String BASE_URL = "https://api.coursera.org/api/catalog.v1/";

    public static final String BASE_URL1 = "https://api.coursera.org";

    /**
     * course base url
     */
    public static final String COURSE_BASE_URL = BASE_URL + "/courses";

    /**
     * university base url
     */
    public static final String UNIVERSITY_BASE_URL = BASE_URL + "/universities";

    /**
     * category base url
     */
    public static final String CATEGORY_BASE_URL = BASE_URL + "/categories";

    /**
     * instructor base url
     */
    public static final String INSTRUCTOR_BASE_URL = BASE_URL + "/instructors";

    /**
     * session base url
     */
    public static final String SESSION_BASE_URL = BASE_URL + "/sessions";

    //获取所有课程列表
    @GET("courses")
    Call<CourseList> getCourseList(@Query("fields") String fields,
                                   @Query("includes") String includes);

    //获取指定id的课程
    @GET("courses")
    Call<CourseList> getCourseList(@Query("id") int id,
                                   @Query("fields") String fields,
                                   @Query("includes") String includes);
    //https://api.coursera.org/api/catalog.v1/courses?id=15&fields=aboutTheCourse

    @GET("universities")
    Call<UniversityList> getUniversityList();

    //获取所有分类列表
    @GET("categories")
    Call<CategoryList> getCategoryList(@Query("fields") String fields,
                                       @Query("includes") String includes);

    //获取指定id的分类课程
    @GET("categories")
    Call<CategoryList> getCategoryList(@Query("id") int id,
                                       @Query("fields") String fields,
                                       @Query("includes") String includes);

    //https://api.coursera.org/api/catalog.v1/categories?id=5&fields=description&includes=courses

    @GET("instructors")
    Call<InstructorList> getInstructorList();

    @GET("sessions")
    Call<SessionList> getSessionList();

}
