package com.uniquedu.myumeng;

import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by ZhongHang on 2016/5/18.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getActivity().getComponentName().getClassName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getActivity().getComponentName().getClassName());
    }
}
