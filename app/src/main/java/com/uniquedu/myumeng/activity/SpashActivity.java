package com.uniquedu.myumeng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.uniquedu.myumeng.BaseActivity;
import com.uniquedu.myumeng.MainActivity;
import com.uniquedu.myumeng.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SpashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        ButterKnife.inject(this);
        //开启推送
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.enable();
//        mPushAgent.enable(new IUmengRegisterCallback() {
//            @Override
//            public void onRegistered(String s) {
//                Toast.makeText(SpashActivity.this, "注册返回" + s, Toast.LENGTH_SHORT).show();
//            }
//        });
//        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(SpashActivity.this, "device_id  " + UmengRegistrar.getRegistrationId(SpashActivity.this), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 1000);
    }

}
