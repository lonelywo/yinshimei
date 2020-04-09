package com.cuci.enticement.plate.mine.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.YsmBean;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi1;
import com.cuci.enticement.plate.mine.adapter.ItemYsmViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import java.io.IOException;
import java.util.List;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class YinshimeiActivity extends BaseActivity implements ItemYsmViewBinder.OnProdClickListener {

    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.text_banbenhao)
    TextView textBanbenhao;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_2)
    TextView text2;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private String title;
    private String url;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yinshimei;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        String versionName = getVerName(this);
        textBanbenhao.setText("版本号v" + versionName);

        mViewModel.ysm("2", "" + mUserInfo.getId(), mUserInfo.getToken(),""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mjiebindwxObserver);


        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(YsmBean.DataBean.class, new ItemYsmViewBinder(this));
        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 0,4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


    @OnClick({R.id.image_back})
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private Observer<Status<ResponseBody>> mjiebindwxObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                operajieBindWxInfo(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void operajieBindWxInfo(ResponseBody body) {
        try {
            String b = body.string();
            YsmBean mModifyInfo = new Gson().fromJson(b, YsmBean.class);
            if (mModifyInfo.getCode() == 1) {
            //    title = mModifyInfo.getData().get(0).getTitle();
           //     url = mModifyInfo.getData().get(0).getUrl();
                List<YsmBean.DataBean> data = mModifyInfo.getData();
                mItems.clear();
                mItems.addAll(data);
                mAdapter.notifyDataSetChanged();
            }else if(mModifyInfo.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mModifyInfo.getInfo());
            } else {
                FToast.error(mModifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }



    @Override
    public void onProdClick(YsmBean.DataBean item) {
        title = item.getTitle();
        url = item.getUrl();
        if (!TextUtils.isEmpty(url)) {

            new XPopup.Builder(YinshimeiActivity.this)
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asCustom(new TipsPopupxieyi1(YinshimeiActivity.this,
                            url, title, () -> {

                    }))
                    .show();

        }
    }
}
