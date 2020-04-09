package com.cuci.enticement.plate.mine.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TagBean;
import com.cuci.enticement.bean.TuikuanSQBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.TuiKuanDetailsActivity;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.StringUtils;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.ResponseBody;


public class DaiFaHuoTuiKuanActivity extends BaseActivity {


    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.id_editor_detail)
    EditText idEditorDetail;
    @BindView(R.id.id_editor_detail_font_count)
    TextView idEditorDetailFontCount;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    List<TagBean> list;
    TagAdapter mAdapter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.con_edt)
    ConstraintLayout conEdt;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private String tag;
    private boolean islMaxCount = false;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private OrderGoods mItem;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private List<OrderGoods> items;
    private long order_no;
    //商品id
    List<String> mlist = new ArrayList<>();
    private String join;
    private String refund_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_daifahuo;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mItem = (OrderGoods) intent.getSerializableExtra("intentItem");
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        if (mInfo != null) {
            items = mInfo.getList();
            mlist.clear();
            order_no = mInfo.getOrder_no();
            for (int i = 0; i < items.size(); i++) {
                mlist.add(String.valueOf(items.get(i).getId()));
            }
            join = StringUtils.join(mlist);
        } else {
            order_no = mItem.getOrder_no();
            join = String.valueOf(mItem.getId());
        }
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        init();
        String strMsg = "申请换货/退款/退货退款服务需签署" + "<font color=\"#e1ad73\">" + "《退款协议》" + "</font>" + "，点击提交则默认您已查阅并同意退款协议所有内容";
        tvShuoming.setText(Html.fromHtml(strMsg));
        tvShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(DaiFaHuoTuiKuanActivity.this, TuiAgreementActivity.class);
                intentProd.putExtra("bannerData", "http://web.enticementchina.com/appweb/refundAgreement.html");
                startActivity(intentProd);
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = idEditorDetail.getText().toString().trim();
                if (mUserInfo != null) {
                    mViewModel.SQtuikuan(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", "" + order_no, join, "1", "", tag, trim, "", "" + AppUtils.getVersionCode(DaiFaHuoTuiKuanActivity.this))
                            .observe(DaiFaHuoTuiKuanActivity.this, mCommitObserver);
                }

            }
        });
    }


    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    TuikuanSQBean mbean = new Gson().fromJson(result, TuikuanSQBean.class);
                    if (mbean.getCode() == 1) {
                        //退款申请成功后，刷新个人中心状态
                        Intent intent2 = new Intent(_MineFragment.ACTION_REFRESH_STATUS);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                        refund_id = mbean.getData().getRefund_id();
                        Intent intent = new Intent(this, TuiKuanDetailsActivity.class);
                        intent.putExtra("refund_id", refund_id);
                        intent.putExtra("back","1");
                        startActivity(intent);
                        finish();
                        FToast.success(mbean.getInfo());

                    } else if (mbean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        FToast.error(mbean.getInfo());
                    } else {
                        FToast.error(mbean.getInfo());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Status.LOADING:
                break;
            case Status.ERROR:
                FToast.error(status.message);
                break;
        }
    };

    @OnTextChanged(value = R.id.id_editor_detail, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void editTextDetailChange(Editable editable) {
        int detailLength = editable.length();
        idEditorDetailFontCount.setText(detailLength + "/200");
        if (detailLength == 199) {
            islMaxCount = true;
        }
        // 不知道为什么执行俩次，所以增加一个标识符去标识
        if (detailLength == 200 && islMaxCount) {
            FToast.warning("您已达到输入字数上限");
            islMaxCount = false;
        }
    }

    private void init() {
        tag = "商品选错了";
        final LayoutInflater mInflater = LayoutInflater.from(this);
        list = new ArrayList<>();
        list.add(new TagBean("商品选错了"));
        list.add(new TagBean("商品发货时间久"));
        list.add(new TagBean("收货信息填写错误"));
        list.add(new TagBean("优惠券忘记使用"));
        list.add(new TagBean("其它原因"));
        /**
         * 点击某个 Tag 返回
         */

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                tag = list.get(position).getName();
                mAdapter.setSelectedList(position);
                mAdapter.notifyDataChanged();
              //  FToast.success(tag);
                return true;
            }
        });
        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

               /* String s = selectPosSet.toString();
                String quStr = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                if (TextUtils.equals(s, "[]")) {
                    tag = "";
                    FToast.success(tag);
                } else {
                    Integer integer = Integer.valueOf(quStr);
                    tag = list.get(integer).getName();
                    FToast.success(tag);
                }*/

            }
        });

        idFlowlayout.setAdapter(mAdapter = new TagAdapter<TagBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, TagBean user) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(user.getName());
                return tv;
            }


        });
        //预先设置选中
        mAdapter.setSelectedList(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
