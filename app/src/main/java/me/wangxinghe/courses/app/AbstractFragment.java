package me.wangxinghe.courses.app;

import android.support.v4.app.Fragment;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class AbstractFragment extends Fragment implements IComponent {
    private BusinessContext mBusinessContext;

    @Override
    public BusinessContext getBusinessContext() {
        return mBusinessContext;
    }

    @Override
    public void setBusinessContext(BusinessContext businessContext) {
        mBusinessContext = businessContext;
    }

}
