package me.wangxinghe.courses.ctrl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.app.AbstractFragment;
import me.wangxinghe.courses.model.EventDispatcher;
import me.wangxinghe.courses.model.Model;
import me.wangxinghe.courses.rest.model.CategoryList;
import me.wangxinghe.courses.rest.model.CourseList;
import me.wangxinghe.courses.view.CourseAdapter;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class CategoryFragment extends AbstractFragment {
    private TextView mBackBtn;
    private TextView mTitleTv;

    private TextView mDescriptionTv;

    private ListView mCourseLv;

    private CategoryList.Category mCategory;
    private List<CourseList.Course> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCategory = (CategoryList.Category)bundle.getSerializable("category");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_category, container, false);
        initView(contentView);

        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initBusiness();
    }

    private void initView (View contentView) {
        mBackBtn = (TextView)contentView.findViewById(R.id.btn_back);
        mTitleTv = (TextView)contentView.findViewById(R.id.tv_title);
        mDescriptionTv = (TextView)contentView.findViewById(R.id.tv_category_description);
        mCourseLv = (ListView)contentView.findViewById(R.id.lv_course);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBusinessContext().getNavigation().popBackStack();
            }
        });
        mTitleTv.setText(mCategory.name);
        mDescriptionTv.setText(mCategory.description);

        mCourseLv.setOnItemClickListener(mOnItemClickListener);
        mCourseLv.setAdapter(new CourseAdapter(getContext(), mList));
    }

    private void initBusiness() {
        Model.getInstance().getCategoryModel().fetchCategoryCourses(mCategory.id, new EventDispatcher<CategoryList>() {
            @Override
            public void onResponseCallback(CategoryList categoryList) {
                mList.clear();

                JSONObject linkedJO = categoryList.linked;
                JSONArray coursesJA = linkedJO.optJSONArray("courses");
                if (coursesJA == null) return;

                for (int i = 0; i < coursesJA.length(); i++) {
                    CourseList.Course course = new Gson().fromJson(coursesJA.optString(i), CourseList.Course.class);
                    mList.add(course);
                }
                ((BaseAdapter)mCourseLv.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            trans2Detail(mList.get(position));
        }
    };

    private void trans2Detail(CourseList.Course course) {
        Intent intent = new Intent(getContext(), DetailFragment.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("course", course);
        intent.putExtras(bundle);
        getBusinessContext().getNavigation().transition(getBusinessContext(), intent);
    }

}
