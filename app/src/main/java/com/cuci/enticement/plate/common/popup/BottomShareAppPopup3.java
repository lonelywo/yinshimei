package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.CardBean;
import com.cuci.enticement.bean.CardMaker;
import com.cuci.enticement.bean.QianDaoBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.CardAdapter;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.cuci.enticement.widget.QrCodeProdView;
import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;
import com.lxj.xpopup.core.BottomPopupView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomShareAppPopup3 extends BottomPopupView {


    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.icon_share_save)
    TextView iconShareSave;
    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.tv_share_moment)
    TextView tvShareMoment;
    @BindView(R.id.ll_tupian)
    LinearLayout llTupian;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private UserInfo mUserInfo;
    private Bitmap bitmap;
    QianDaoBean.DataBean.ShareInfoBean mshare_info;
    private Context mContext;
    private ReItemTouchHelper mReItemTouchHelper;
    private QrCodeProdView mQrCodeProdView;
    private int i=0;

    public BottomShareAppPopup3(@NonNull Context context, UserInfo userInfo, QianDaoBean.DataBean.ShareInfoBean share_info) {
        super(context);
        mContext = context;
        mUserInfo = userInfo;
        mshare_info = share_info;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_qiandao_list_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mQrCodeProdView = new QrCodeProdView(BasicApp.getContext());
        mQrCodeProdView.setDesc(mshare_info.getNickname());
        mQrCodeProdView.setDesc1(mshare_info.getSlogan());
        mQrCodeProdView.setImageMain(mshare_info.getPoster().get(i%7));
        mQrCodeProdView.setImageQrCode(mshare_info.getQrcode());
        List<CardBean> list = CardMaker.initCards(mshare_info);
        CardSetting setting = new CardSetting();
        setting.setSwipeListener(new OnSwipeCardListener<CardBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float dx, float dy, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Log.e("aaa", "swiping direction=down");
                        break;
                    case ReItemTouchHelper.UP:
                        Log.e("aaa", "swiping direction=up");
                        break;
                    case ReItemTouchHelper.LEFT:
                        Log.e("aaa", "swiping direction=left");
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Log.e("aaa", "swiping direction=right");
                        break;
                }
            }

            @Override
            public void onSwipedOut(RecyclerView.ViewHolder viewHolder, CardBean o, int direction) {
                i++;
                mQrCodeProdView = new QrCodeProdView(BasicApp.getContext());
                mQrCodeProdView.setDesc(mshare_info.getNickname());
                mQrCodeProdView.setDesc1(mshare_info.getSlogan());
                mQrCodeProdView.setImageMain(mshare_info.getPoster().get(i%7));
                mQrCodeProdView.setImageQrCode(mshare_info.getQrcode());
                switch (direction) {
                    case ReItemTouchHelper.DOWN:

                        break;
                    case ReItemTouchHelper.UP:

                        break;
                    case ReItemTouchHelper.LEFT:

                        break;
                    case ReItemTouchHelper.RIGHT:

                        break;
                }
            }

            @Override
            public void onSwipedClear() {
              FToast.success(""+(i++));
            }
        });
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(mRecyclerView, list, setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        mRecyclerView.setLayoutManager(layoutManager);
        CardAdapter cardAdapter = new CardAdapter(list);
        mRecyclerView.setAdapter(cardAdapter);

    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
            R.id.icon_share_save})
    public void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.tv_share_wx:
                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                dismiss();
                break;
            case R.id.tv_share_moment:
                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                dismiss();
                break;
            case R.id.icon_share_save:
                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                        FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bitmap, true);
                if (file != null) {
                    FToast.success("图片成功保存到：" + file.getAbsolutePath());
                }
                dismiss();
                break;


        }
    }
}




