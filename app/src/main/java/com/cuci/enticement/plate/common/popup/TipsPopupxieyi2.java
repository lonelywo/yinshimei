package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cuci.enticement.R;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.lxj.xpopup.core.CenterPopupView;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsPopupxieyi2 extends CenterPopupView {


    @BindView(R.id.web_context)
    WebView webContext;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.text_xieyi)
    TextView textXieyi;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;
    private String murl;
    private String mtext;

    public TipsPopupxieyi2(@NonNull Context context, String url, String text, OnExitListener listener) {
        super(context);
        mOnExitListener = listener;
        murl = url;
        mtext = text;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tips_view_xieyi2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        textXieyi.setText(mtext);
        webContext.loadUrl(murl);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefUtils.saveFirstTime(false);
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
