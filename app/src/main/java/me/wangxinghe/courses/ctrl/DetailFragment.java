package me.wangxinghe.courses.ctrl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.app.AbstractFragment;
import me.wangxinghe.courses.model.EventDispatcher;
import me.wangxinghe.courses.model.Model;
import me.wangxinghe.courses.rest.model.CourseList;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class DetailFragment extends AbstractFragment {
    private Button mBackBtn;
    private TextView mTitleTv;

    private TextView mNameTv;
    private TextView mUniversityNameTv;
    private ImageView mIconIv;
    private TextView mWorkloadTv;
    private TextView mLanguageTv;
    private TextView mAboutTheCourseTv;

    private CourseList.Course mCourse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourse = (CourseList.Course)getArguments().getSerializable("course");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_detail, container, false);
        initView(contentView);

        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initBusiness();
    }

    private void initView(View contentView) {
        mBackBtn = (Button)contentView.findViewById(R.id.btn_back);
        mTitleTv = (TextView)contentView.findViewById(R.id.tv_title);

        mNameTv = (TextView)contentView.findViewById(R.id.tv_name);
        mUniversityNameTv = (TextView)contentView.findViewById(R.id.tv_universityName);
        mWorkloadTv = (TextView)contentView.findViewById(R.id.tv_workload);
        mLanguageTv = (TextView)contentView.findViewById(R.id.tv_language);
        mAboutTheCourseTv = (TextView)contentView.findViewById(R.id.tv_aboutTheCourse);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBusinessContext().getNavigation().popBackStack();
            }
        });

        mTitleTv.setText(mCourse.name);
    }

    private void initBusiness() {
        Model.getInstance().getCourseModel().fetchCourseDetail(mCourse.id, new EventDispatcher<CourseList.Course>() {
            @Override
            public void onResponseCallback(CourseList.Course course) {
                fillView(course);
            }
        });
    }

    private void fillView(CourseList.Course course) {
        mNameTv.setText(course.name);
//        mUniversityNameTv.setText();
//        mIconIv.setImageDrawable();
        mWorkloadTv.setText(course.estimatedClassWorkload);
        mLanguageTv.setText(course.language);
        mAboutTheCourseTv.setText(course.aboutTheCourse);
    }

}
