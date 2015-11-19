package me.wangxinghe.courses.ctrl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.app.AbstractFragment;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class CollectFragment extends AbstractFragment {
    private TextView mBackBtn;
    private TextView mTitileTv;
    private ListView mCollectLv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_collect, container, false);
        initView(contentView);

        return contentView;
    }

    private void initView(View contentView) {
        mBackBtn = (TextView)contentView.findViewById(R.id.btn_back);
        mTitileTv = (TextView)contentView.findViewById(R.id.tv_title);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBusinessContext().getNavigation().popBackStack();
            }
        });
        mTitileTv.setText(R.string.collect);
    }
}
