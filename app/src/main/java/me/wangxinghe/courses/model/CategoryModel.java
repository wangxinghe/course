package me.wangxinghe.courses.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import me.wangxinghe.courses.rest.CourseRestClient;
import me.wangxinghe.courses.rest.model.CategoryList;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class CategoryModel {
    private static final String TAG = "Category";
    private static final String SP_CATEGORY = "CATEGORY";
    private static final String SP_KEY_CATEGORY = "course_category";

    public CategoryModel() {
    }

    public void fetchCategoryCourses(int categoryId, final EventDispatcher eventDispatcher) {
        CourseRestClient.getInstance().getCourseAPI()
                .getCategoryList(categoryId, "description", "courses").enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Response<CategoryList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    eventDispatcher.onResponseCallback(response.body());
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

    public CategoryList getCategoryList(Context context, EventDispatcher<CategoryList> eventDispatcher) {
        SharedPreferences sp = context.getSharedPreferences(SP_CATEGORY, Context.MODE_PRIVATE);
        String categoryListStr = sp.getString(SP_KEY_CATEGORY, "");
        CategoryList categoryList = new Gson().fromJson(categoryListStr, CategoryList.class);

        if (categoryList != null) {
            eventDispatcher.onResponseCallback(categoryList);
            return categoryList;
        }

        String assetsCategoryListStr = getAssetsFile(context, "category.txt");
        CategoryList assetsCategoryList = new Gson().fromJson(assetsCategoryListStr, CategoryList.class);
        eventDispatcher.onResponseCallback(assetsCategoryList);

        fetchCategories(context, eventDispatcher);

        return assetsCategoryList;
    }

    private void fetchCategories(final Context context, final EventDispatcher eventDispatcher) {
        CourseRestClient.getInstance().getCourseAPI()
                .getCategoryList("description", "courses").enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Response<CategoryList> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d(TAG, "onResponse " + response.body());
                    saveCategoryList(context, response);
                    eventDispatcher.onResponseCallback(response.body());
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

    private void saveCategoryList(Context context, Response<CategoryList> response) {
        CategoryList categoryList = response.body();
        SharedPreferences sp = context.getSharedPreferences(SP_CATEGORY, Context.MODE_PRIVATE);
        sp.edit().putString(SP_KEY_CATEGORY, new Gson().toJson(categoryList));
    }

    private static String getAssetsFile(Context context, String fileName) {
        String result = "";
        InputStream in = null;
        try {
            in = context.getAssets().open(fileName);
            //获取文件的字节数
            int length = in.available();
            //创建byte数组
            byte[] buffer = new byte[length];
            //将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer, "utf-8");
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return result;
    }

}
