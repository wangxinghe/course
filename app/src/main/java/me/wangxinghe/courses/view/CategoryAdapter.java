package me.wangxinghe.courses.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.rest.model.CategoryList;

/**
 * Created by wangxinghe on 11/11/15.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
//    private CategoryList mCategoryList;
    private List<CategoryList.Category> mList;

    public CategoryAdapter(Context context, List<CategoryList.Category> list) {
        mContext = context;
//        mCategoryList = categoryList;
        mList = list;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return -1;
        }
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_category, null);

            viewHolder = new ViewHolder();
            viewHolder.mNameTv = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.mShortNameTv = (TextView)convertView.findViewById(R.id.tv_shortName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.mNameTv.setText(mList.get(position).name);
        viewHolder.mShortNameTv.setText(mList.get(position).shortName);

        return convertView;
    }

    private class ViewHolder {
        private TextView mNameTv;
        private TextView mShortNameTv;
    }
}
