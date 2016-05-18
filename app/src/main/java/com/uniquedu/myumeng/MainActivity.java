package com.uniquedu.myumeng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Iterator;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.button_share)
    Button buttonShare;
    @InjectView(R.id.button_login)
    Button buttonLogin;
    UMShareAPI shareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        shareAPI = UMShareAPI.get(this);
    }


    public void share() {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.DOUBAN
                };
        new ShareAction(this).setDisplayList(displaylist)
                .withText("呵呵")
                .withTitle("title")
                .withTargetUrl("https://github.com/android-cn/android-discuss/issues/450#event-663841383")
                .withMedia(new UMImage(this, "https://cloud.githubusercontent.com/assets/5943707/14880622/7bcf030a-0d62-11e6-9ded-4da04062f526.png"))
                .setListenerList()
                .open();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.button_share, R.id.button_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_share:
                share();
                break;
            case R.id.button_login:
                sanfang();
                break;
        }
    }

    /**
     * 第三方授权登陆，获取信息后在自己服务器注册账号
     */
    private void sanfang() {
        UMAuthListener umAuthListener = new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                String content = "";
                Iterator<String> iterator = data.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    content += " " + key + ":" + data.get(key);
                }
                Toast.makeText(getApplicationContext(), "Authorize succeed " + content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
            }
        };
        SHARE_MEDIA platform = SHARE_MEDIA.QQ;
        shareAPI.doOauthVerify(this, platform, umAuthListener);
    }
}
