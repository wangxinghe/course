package me.wangxinghe.courses.app;

import android.content.Intent;

/**
 * Created by wangxinghe on 13/11/15.
 */
public interface INavigation {

    void transition(BusinessContext context, Intent intent);

    void popBackStack();
}
