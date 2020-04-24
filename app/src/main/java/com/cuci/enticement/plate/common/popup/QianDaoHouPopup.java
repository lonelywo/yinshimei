package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.plate.common.LauncherActivity;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QianDaoHouPopup extends CenterPopupView {


    @BindView(R.id.mContent)
    TextView mContent;
    private Context mContext;
    private String mcontent;
    private String mposter;
    private String mqrcode;

    public QianDaoHouPopup(@NonNull Context context,String content,String poster,String qrcode,
                           OnQDHListener listener) {
        super(context);
        mContext = context;
        mQDHListener = listener;
        mcontent=content;
        mposter=poster;
        mqrcode=qrcode;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_qiandaohou_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        UserInfo mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mContent.setText(mcontent);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //延迟再启动
                new XPopup.Builder(mContext)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(true)
                        .asCustom(new BottomShareAppPopup2(mContext, mUserInfo, mposter, mqrcode))
                        .show();
                dismiss();
            }
        }, 500);


    }



    private OnQDHListener mQDHListener;

    public interface OnQDHListener {
        void QDHNow();
    }

}
