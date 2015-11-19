package me.wangxinghe.courses.ctrl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.wangxinghe.courses.R;
import me.wangxinghe.courses.app.AbstractFragment;
import me.wangxinghe.courses.model.Model;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class SearchFragment extends AbstractFragment {
    private EditText mInputEt;
    private TextView mCancelTv;
    private RelativeLayout mLoadingRl;
    private ListView mResultLv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_search, container, false);
        initView(contentView);

        return contentView;
    }

    private void initView(View contentView) {
        mInputEt = (EditText)contentView.findViewById(R.id.et_input);
        mCancelTv = (TextView)contentView.findViewById(R.id.tv_search_cancel);
        mLoadingRl = (RelativeLayout)contentView.findViewById(R.id.search_load_layout);
        mResultLv = (ListView)contentView.findViewById(R.id.search_result_list_view);

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBusinessContext().getNavigation().popBackStack();
            }
        });

        mInputEt.addTextChangedListener(mTextWatcher);
        mLoadingRl.setVisibility(View.GONE);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fetchSearchList(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void fetchSearchList(CharSequence query) {
        mLoadingRl.setVisibility(View.VISIBLE);
    }

}
