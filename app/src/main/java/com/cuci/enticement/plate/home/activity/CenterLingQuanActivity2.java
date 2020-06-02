package com.cuci.enticement.plate.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CardBean;
import com.cuci.enticement.bean.CardMaker;
import com.cuci.enticement.bean.CardMaker2;
import com.cuci.enticement.bean.ShareHomeImgBean;
import com.cuci.enticement.bean.ShareimgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.CardAdapter;
import com.cuci.enticement.plate.mine.adapter.CardAdapter2;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.cuci.enticement.widget.QrCodeProdView;
import com.cuci.enticement.widget.QrCodeProdView2;
import com.google.gson.Gson;
import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


public class CenterLingQuanActivity2 extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.icon_share_save)
    TextView iconShareSave;
    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.tv_share_moment)
    TextView tvShareMoment;
    @BindView(R.id.ll_tupian)
    LinearLayout llTupian;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private Bitmap bitmap;
    private ProgressDialog mProgressDialog;
    private ShareHomeImgBean.DataBean data;
    private QrCodeProdView2 mQrCodeProdView;
    private ReItemTouchHelper mReItemTouchHelper;
    private int i = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_center_lingquan_new;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        data = (ShareHomeImgBean.DataBean) intent.getSerializableExtra("Data");
        List<ShareHomeImgBean.DataBean.GoodsListBean> goods_list = data.getGoods_list();
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        List<CardBean> list = CardMaker2.initCards(data);
        CardSetting setting = new CardSetting();
        mQrCodeProdView = new QrCodeProdView2(BasicApp.getContext());
        mQrCodeProdView.setDesc(data.getNickname());
        mQrCodeProdView.setImageMain(goods_list.get(i % goods_list.size()).getPoster());
        mQrCodeProdView.setImageQrCode(goods_list.get(i % goods_list.size()).getQrcode());
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
                mQrCodeProdView = new QrCodeProdView2(BasicApp.getContext());
                mQrCodeProdView.setDesc(data.getNickname());
                mQrCodeProdView.setImageMain(goods_list.get(i % goods_list.size()).getPoster());
                mQrCodeProdView.setImageQrCode(goods_list.get(i % goods_list.size()).getQrcode());
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
                FToast.success("" + (i++));
            }
        });
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(mRecyclerView, list, setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        mRecyclerView.setLayoutManager(layoutManager);
        CardAdapter2 cardAdapter = new CardAdapter2(list);
        mRecyclerView.setAdapter(cardAdapter);

    }

    public void load() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment, R.id.icon_share_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share_wx:

                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 720, 1280);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                break;
            case R.id.tv_share_moment:
                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 720, 1280);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }

                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                break;
            case R.id.icon_share_save:
                bitmap = ImageUtils.getViewBitmap(mQrCodeProdView, 720, 1280);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }
                File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                        FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bitmap, true);
                if (file != null) {
                    FToast.success("图片成功保存到：" + file.getAbsolutePath());
                }
                break;
        }
    }



}