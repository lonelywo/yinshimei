package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissionjlBean;
import com.cuci.enticement.bean.CommissiontjBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemCommissionJLViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class CommissionActivity extends BaseActivity implements OnRefreshLoadMoreListener {

    @BindView(R.id.img_back)
    ImageView imageBack;
    @BindView(R.id.button_tixian)
    Button buttonTixian;
    @BindView(R.id.text_wenzi)
    TextView textWenzi;
    @BindView(R.id.text_yongjing)
    TextView textYongjing;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.text_yitixian)
    TextView textYitixian;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.text_ketixian)
    TextView textKetixian;
    @BindView(R.id.con_huang)
    ConstraintLayout conHuang;
    @BindView(R.id.text_shanggeyue)
    TextView textShanggeyue;
    @BindView(R.id.text_rqi)
    TextView textRqi;
    @BindView(R.id.text_xiageyue)
    TextView textXiageyue;
    @BindView(R.id.con_hui)
    ConstraintLayout conHui;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.civ_tuxiang)
    CircleImageView civTuxiang;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.con_money)
    ConstraintLayout conMoney;
    @BindView(R.id.view_beijing1)
    View viewBeijing1;
    @BindView(R.id.view_beijing2)
    View viewBeijing2;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private Items mItems;
    private MultiTypeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<CommissionjlBean.DataBean.ListBean> mDatas = new ArrayList<>();
    private boolean mCanLoadMore = true;
    private String format;
    private Date d;
    private TimePickerView pvTime;
    private FrameLayout mFrameLayout;
    private String formatStart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commisson;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), civTuxiang);
        textName.setText(mUserInfo.getNickname());
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(CommissionjlBean.DataBean.ListBean.class, new ItemCommissionJLViewBinder());
        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 10);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                .observe(this, mObserver);

        Date a = new Date();

        d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        formatStart = sdf.format(a);
        format = sdf.format(d);
        textRqi.setText(format);
        String nian = format.split("-")[0];
        String yue = format.split("-")[1];

        refreshLayout.autoRefresh();
        if (d.equals(a)) {
            textXiageyue.setEnabled(false);
        } else {
            textXiageyue.setEnabled(true);
        }
        textShanggeyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);
                calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
                Date time1 = calendar.getTime();
                String format2 = sdf.format(time1);
                textRqi.setText(format2);
                format = format2;
                d = time1;
                if (d.equals(a)) {
                    textXiageyue.setEnabled(false);
                } else {
                    textXiageyue.setEnabled(true);
                    mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", format, Status.LOAD_REFRESH)
                            .observe(CommissionActivity.this, mObserver1);
                    ViewUtils.showView(progressBar);
                }

            }
        });
        textXiageyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);
                calendar.add(Calendar.MONTH, +1);//当前时间前去一个月，即一个月前的时间
                Date time2 = calendar.getTime();
                String format3 = sdf.format(time2);
                textRqi.setText(format3);
                format = format3;
                d = time2;
                if (format3.equals(formatStart)) {
                    textXiageyue.setEnabled(false);
                } else {
                    textXiageyue.setEnabled(true);
                }
                mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", format, Status.LOAD_REFRESH)
                        .observe(CommissionActivity.this, mObserver1);
                ViewUtils.showView(progressBar);
            }
        });
        buttonTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(CommissionActivity.this, CashActivity.class);
                intentProd.putExtra("Data", "");
                startActivity(intentProd);
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //初始化时间选择器
        initTimePicker();

        textRqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvTime.show(view, false);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
            }
        });

    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 11, 28);
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                d = date;
                textRqi.setText(getTime(date));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                format = sdf.format(d);

                if (format.equals(formatStart)) {
                    textXiageyue.setEnabled(false);
                } else {
                    textXiageyue.setEnabled(true);
                }


                pvTime.dismiss();
                mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", format, Status.LOAD_REFRESH)
                        .observe(CommissionActivity.this, mObserver1);
                ViewUtils.showView(progressBar);

            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();

                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.BLACK)
                .setContentTextSize(20)
                .setLineSpacingMultiplier((float) 2.0)
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                // .setDecorView(mFrameLayout)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false)
                .build();

        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉
    }


    private String getTime(Date date) {
        //"yyyy-MM-dd  HH:mm"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    private void load() {
        if (mUserInfo == null) {
            refreshLayout.finishRefresh();
            return;
        }
        mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", format, Status.LOAD_REFRESH)
                .observe(this, mObserver1);
    }

    private Observer<Status<ResponseBody>> mObserver1 = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ViewUtils.hideView(progressBar);
                statusView.showContent();
                //  dismissLoading();
                ResponseBody body = status.content;
                opera1(body, status);
                break;
            case Status.ERROR:
                ViewUtils.hideView(progressBar);
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishRefresh();
                }
                //   dismissLoading();
                FToast.error("网络错误");
                break;
            case Status.LOADING:
                //showLoading();
                break;
        }

    };

    private void opera1(ResponseBody body, Status status) {
        try {
            String b = body.string();
            CommissionjlBean mCommissionjlBean = new Gson().fromJson(b, CommissionjlBean.class);
            List<CommissionjlBean.DataBean.ListBean> item = mCommissionjlBean.getData().getList();
            if (item == null || item.size() == 0) {

                if (status.loadType == Status.LOAD_REFRESH) {
                    statusView.showEmpty();
                    refreshLayout.finishRefresh();
                } else {

                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mCommissionjlBean.getCode() == 1) {
                mCanLoadMore = true;
                if (status.loadType == Status.LOAD_REFRESH) {
                    mItems.clear();
                    mItems.addAll(item);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh();
                } else {
                    mItems.addAll(item);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore();
                }
            } else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.error(mCommissionjlBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            CommissiontjBean mCommissiontjBean = new Gson().fromJson(b, CommissiontjBean.class);
            if (mCommissiontjBean.getCode() == 1) {
                String subtract = MathExtend.subtract(String.valueOf(mCommissiontjBean.getData().getTotal()), String.valueOf(mCommissiontjBean.getData().getUsed()));
                textYongjing.setText("¥" + mCommissiontjBean.getData().getTotal());
                textYitixian.setText("¥" + mCommissiontjBean.getData().getUsed());
                textKetixian.setText("¥" + subtract);
            } else {
                FToast.error(mCommissiontjBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        load();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
