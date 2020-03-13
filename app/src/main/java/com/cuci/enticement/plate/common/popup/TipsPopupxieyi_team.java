package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.cuci.enticement.R;
import com.lxj.xpopup.core.CenterPopupView;
import com.tencent.smtt.sdk.WebView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsPopupxieyi_team extends CenterPopupView {


    @BindView(R.id.web_context)
    WebView webContext;
    @BindView(R.id.cancel)
    ImageView cancel;

    public TipsPopupxieyi_team(@NonNull Context context, OnExitListener listener) {
        super(context);
        mOnExitListener = listener;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tips_view_xieyi_team;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
       webContext.loadUrl("http://web.enticementchina.com/appweb/fan_description.html");
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                //   mOnExitListener.onPositive();

            }
        });
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    private OnExitListener mOnExitListener;

    public interface OnExitListener {
        void onPositive();
    }

}