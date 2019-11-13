package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.utils.FToast;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuiDetailsYesActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_2)
    TextView text2;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.text_3)
    TextView text3;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;

    @Override
    public int getLayoutId() {
        return R.layout.tui_details_yes;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_left:
                startActivity(new Intent(this, TuiLogisticsActivity.class));
                break;
            case R.id.tv_right:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    Intent intent = new IntentBuilder(this)
                            .setServiceIMNumber("kefuchannelimid_269943")
                            .setTitleName("美美")
                            .build();
                    startActivity(intent);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    FToast.error("环信登录失败");
                }
                break;
        }
    }
}
