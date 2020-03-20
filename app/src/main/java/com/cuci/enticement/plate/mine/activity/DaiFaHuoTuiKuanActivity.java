package com.cuci.enticement.plate.mine.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.TagBean;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.zxing.common.StringUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


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
    private String tag;
    private boolean islMaxCount=false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_daifahuo;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        init();

    }
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
        tag="商品选错了";
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
               // tag = list.get(position).getName();
               // FToast.success(tag);
                return true;
            }
        });
        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener(){
            @Override
            public void onSelected(Set<Integer> selectPosSet)
            {

                String s = selectPosSet.toString();
                String quStr=s.substring(s.indexOf("[")+1,s.indexOf("]"));
             if(TextUtils.equals(s,"[]")){
                 tag="请选择退款原因";
                 FToast.success(tag);
             }else {
                 Integer integer = Integer.valueOf(quStr);
                 tag=list.get(integer).getName();
                FToast.success(tag);
             }

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

}
