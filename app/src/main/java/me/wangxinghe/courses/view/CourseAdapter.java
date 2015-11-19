package me.wangxinghe.courses.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.rest.model.CourseList;

/**
 * Created by wangxinghe on 17/11/15.
 */
public class CourseAdapter extends BaseAdapter {
    private Context mContext;
    private List<CourseList.Course> mList;

    public CourseAdapter(Context context, List<CourseList.Course> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_course, null);
            viewHolder = new ViewHolder();
            viewHolder.mNameTv = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.mIconIv = (ImageView)convertView.findViewById(R.id.iv_icon);
            viewHolder.mUniversityNameTv = (TextView)convertView.findViewById(R.id.tv_universityName);
            viewHolder.mWorkloadTv = (TextView)convertView.findViewById(R.id.tv_workload);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.mNameTv.setText(mList.get(position).name);
//        viewHolder.mIconIv.setImageDrawable(mList.get(position).);
//        viewHolder.mUniversityNameTv.setText();
        viewHolder.mWorkloadTv.setText(mList.get(position).workload);

        return convertView;
    }

    private static class ViewHolder {
        private TextView mNameTv;
        private ImageView mIconIv;
        private TextView mUniversityNameTv;
        private TextView mWorkloadTv;
    }
}
