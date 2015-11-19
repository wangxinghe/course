package me.wangxinghe.courses.app;

import android.content.Context;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class BusinessContext {
    private Context mContext;
    private INavigation mNavigation;

    public void assemble(Context context, INavigation navigation) {
        mContext = context;
        mNavigation = navigation;
    }

    public Context getContext() {
        return mContext;
    }

    public INavigation getNavigation() {
        return mNavigation;
    }

}
