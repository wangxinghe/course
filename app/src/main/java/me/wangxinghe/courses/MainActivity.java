package me.wangxinghe.courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.wangxinghe.courses.app.BusinessContext;
import me.wangxinghe.courses.app.INavigation;
import me.wangxinghe.courses.app.Navigation;
import me.wangxinghe.courses.ctrl.CategoryFragment;
import me.wangxinghe.courses.ctrl.CollectFragment;
import me.wangxinghe.courses.ctrl.SearchFragment;
import me.wangxinghe.courses.model.CategoryModel;
import me.wangxinghe.courses.model.CourseModel;
import me.wangxinghe.courses.model.EventDispatcher;
import me.wangxinghe.courses.model.Model;
import me.wangxinghe.courses.rest.model.CategoryList;
import me.wangxinghe.courses.view.CategoryAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private INavigation mNavigation;
    private BusinessContext mBusinessContext;

    private ImageView mSearchBtn;
    private ImageView mMoreBtn;
    private GridView mCategoryGv;

    private CourseModel mModel;
    private CategoryModel mCategoryModel;
    private CategoryList mCategoryList = new CategoryList();
    private List<CategoryList.Category> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initModel();
        initComponent();
    }

    private void initView() {
        //init title bar
        mSearchBtn = (ImageView)findViewById(R.id.btn_search);
        mMoreBtn = (ImageView)findViewById(R.id.btn_favorite);
        mCategoryGv = (GridView)findViewById(R.id.gv_category);

        mSearchBtn.setOnClickListener(this);
        mMoreBtn.setOnClickListener(this);
        mCategoryGv.setAdapter(new CategoryAdapter(this, mList));
        mCategoryGv.setOnItemClickListener(mOnItemClickListener);
    }

    private void initModel() {
        mModel = Model.getInstance().getCourseModel();
        mCategoryModel = Model.getInstance().getCategoryModel();

        fetchCategories();
    }

    private void initComponent() {
        mNavigation = new Navigation(getSupportFragmentManager(), R.id.container);

        mBusinessContext = new BusinessContext();
        mBusinessContext.assemble(this, mNavigation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                trans2Search();
                break;
            case R.id.btn_favorite:
                trans2Collect();
                break;
        }
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CategoryList.Category category = mList.get(position);
            trans2Category(category);
        }
    };

    private void trans2Search() {
        Intent intent = new Intent(getApplicationContext(), SearchFragment.class);
        mNavigation.transition(mBusinessContext, intent);
    }

    private void trans2Collect() {
        Intent intent = new Intent(getApplicationContext(), CollectFragment.class);
        mNavigation.transition(mBusinessContext, intent);
    }

    private void trans2Category(CategoryList.Category category) {
        Intent intent = new Intent(getApplicationContext(), CategoryFragment.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", category);
        intent.putExtras(bundle);
        mNavigation.transition(mBusinessContext, intent);
    }

    private void fetchCourses() {
        mModel.fetchCourses();
    }

    private void fetchUniversities() {
        mModel.fetchUniversities();
    }

    private void fetchCategories() {
        mCategoryModel.getCategoryList(this, new EventDispatcher<CategoryList>() {
            @Override
            public void onResponseCallback(CategoryList categoryList) {
                mList.clear();
                mList.addAll(Arrays.asList(categoryList.elements));
                ((BaseAdapter)mCategoryGv.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    private void fetchInstructors() {
        mModel.fetchInstructors();
    }

    private void fetchSessions() {
        mModel.fetchSessions();
    }

}
