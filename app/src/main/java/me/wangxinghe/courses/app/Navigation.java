package me.wangxinghe.courses.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by wangxinghe on 13/11/15.
 */
public class Navigation implements INavigation {
    private FragmentManager mFragmentMgr;
    private int mContainerViewId;

    public Navigation(FragmentManager fragmentMgr, int containerViewId) {
        mFragmentMgr = fragmentMgr;
        mContainerViewId = containerViewId;
    }

    @Override
    public void transition(BusinessContext context, Intent intent) {
        ComponentName componentName = intent.getComponent();
        if (isActivity(componentName)) {
            startActivity(context, intent);
        } else {
            startPage(context, intent);
        }
    }

    @Override
    public void popBackStack() {
        mFragmentMgr.popBackStack();
    }

    /**
     * 是否Activity
     * @param componentName 组件名
     * @return
     */
    private boolean isActivity(ComponentName componentName) {
        if (componentName == null)
            return false;

        Class cls = componentName.getClass();

        return Activity.class.isAssignableFrom(cls);
    }

    /**
     * 动画跳转到指定Page
     *
     * @param context
     * @param intent
     */
    private void startPage(BusinessContext context, Intent intent) {
        // 匹配 Fragment
        Fragment page = matchPage(context, intent);
        if (page != null) {
            // 切换
            transitionImpl(mContainerViewId, page, intent);
        }
    }

    /**
     * 跳转到指定Activity
     *
     * @param businessContext
     * @param intent
     */
    private void startActivity(BusinessContext businessContext, Intent intent) {
        businessContext.getContext().startActivity(intent);
    }

    private Fragment matchPage(BusinessContext businessContext, Intent intent) {
        Fragment page = null;
        ComponentName componentName = intent.getComponent();
        if (componentName != null) {
            String componentClassName = componentName.getClassName();
            try {
                Class pageClass = Class.forName(componentClassName);

                // component是否从Page派生
                boolean isAssignable = Fragment.class.isAssignableFrom(pageClass);
                page = (Fragment)pageClass.newInstance();

                // 如果是component 则传递上下文过去
                if (page instanceof IComponent) {
                    // 传递上下文
                    ((IComponent) page).setBusinessContext(businessContext);
                    page.setArguments(intent.getExtras());
                }

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return page;
    }

    private void transitionImpl(int containerViewId, Fragment newPage, Intent intent) {
        // Create new fragment and transaction
        final FragmentTransaction transaction = mFragmentMgr.beginTransaction();

        // 获取fragment name
        String fragmentName = newPage.getClass().getName();

        // add fragment
        transaction.add(containerViewId, newPage, fragmentName);

        // and add the transaction to the popBackStack stack
        transaction.addToBackStack(fragmentName);

        transaction.commit();
    }

}
