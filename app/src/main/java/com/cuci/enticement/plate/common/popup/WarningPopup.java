package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.lxj.xpopup.core.CenterPopupView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningPopup extends CenterPopupView {


    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.ok)
    TextView ok;
    private String mDesc;

    private String mok ;

    public WarningPopup(@NonNull Context context, String desc,  String ok) {
        super(context);

        mDesc = desc;

        mok = ok;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_warn_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        desc.setText(mDesc);
        ok.setText(mok);
    }

    @OnClick({R.id.ok})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ok:

                dismiss();
                break;


        }
    }



}
