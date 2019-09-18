package com.example.enticement.plate.common.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.example.enticement.bean.HomeDetailsBean;
import com.example.enticement.plate.mine.vm.OrderViewModel;
import com.example.enticement.utils.DimensionUtils;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareBottom2TopProdPopup extends BottomPopupView {

    private static final String TAG = ShareBottom2TopProdPopup.class.getSimpleName();
    @BindView(R.id.img_tuxiang)
    ImageView imgTuxiang;
    @BindView(R.id.text_fenzu)
    TextView textFenzu;
    @BindView(R.id.rgp_button)
    RadioGroup radioGroup;
    @BindView(R.id.text_shuliang)
    TextView textShuliang;

    @BindView(R.id.text_shuzi)
    TextView textShuzi;
    @BindView(R.id.stock_tv)
    TextView stockTv;
    @BindView(R.id.selected_tv)
    TextView selectedTv;
    @BindView(R.id.img_guanbi)
    ImageView imgGuanbi;
    private OrderViewModel mViewModel;
    private HomeDetailsBean.DataBean mItem;

    private int mScreenWidth;
    private Context mContext;
    //code用来区分是购物车还是立即购买
    private int mCode;

    @BindView(R.id.container)
    ConstraintLayout mContainer;


    private int num;
    private String mSpec;
    private int mID;

    public ShareBottom2TopProdPopup(@NonNull Context context, HomeDetailsBean.DataBean item, int code, OnCommitClickListener OnCommitClickListener) {
        super(context);
        mContext = context;
        mItem = item;
        mScreenWidth = DimensionUtils.getScreenWidth(context);
        mCode = code;
        mOnCommitClickListener = OnCommitClickListener;
    }


    @Override
    protected int getImplLayoutId() {
        //todo 这里把你写的布局填在里面
        return R.layout.popup_share_bottom_to_top_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        //todo 这里把最外层控件换成你布局里面的

        ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
        layoutParams.width = mScreenWidth;
        mContainer.setLayoutParams(layoutParams);
        List<HomeDetailsBean.DataBean.SpecsBean> specs = mItem.getSpecs();
        HomeDetailsBean.DataBean.SpecsBean specsBean = specs.get(0);
        List<HomeDetailsBean.DataBean.SpecsBean.ListBean> list = specsBean.getList();

        textFenzu.setText(specsBean.getName());
        addview(radioGroup, list);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    int id = radioGroup.getChildAt(j).getId();
                    if (id == i) {
                        selectedTv.setText(list.get(j).getName());
                        mSpec = specsBean.getName() + ":" + list.get(j).getName();
                        mID = id;
                        break;
                    }
                }
            }
        });
        // this.setFinishOnTouchOutside(true);
        imgGuanbi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void addview(RadioGroup radiogroup, List<HomeDetailsBean.DataBean.SpecsBean.ListBean> skuList) {

        int index = 0;
        for (int i = 0; i < skuList.size(); i++) {


            HomeDetailsBean.DataBean.SpecsBean.ListBean sku = skuList.get(i);
            RadioButton button = new RadioButton(getContext());
            setRaidBtnAttribute(button, sku.getName(), index);

            radiogroup.addView(button);
            button.setChecked(sku.isCheck());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            button.setPadding(20,10,20,10);
            button.setGravity(Gravity.LEFT);
            layoutParams.setMargins(DimensionUtils.dp2px(getContext(), 10), 0, DimensionUtils.dp2px(getContext(), 10), 0);//4个参数按顺序分别是左上右下
            button.setLayoutParams(layoutParams);
            index++;
        }


    }


    private void setRaidBtnAttribute(final RadioButton codeBtn, String btnContent, int id) {
        if (null == codeBtn) {
            return;
        }
        codeBtn.setBackgroundResource(R.drawable.radio_group_selector);
        codeBtn.setTextColor(this.getResources().getColorStateList(R.color.radio_group_text_slt));
        codeBtn.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        //codeBtn.setTextSize( ( textSize > 16 )?textSize:24 );
        codeBtn.setId(id);
        codeBtn.setText(btnContent);
        //codeBtn.setPadding(2, 0, 2, 0);

        codeBtn.setGravity(Gravity.LEFT);

        //DensityUtilHelps.Dp2Px(this,40)
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, DimensionUtils.dp2px(getContext(), 25));

        codeBtn.setLayoutParams(rlp);

    }


    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        if (mOnCommitClickListener != null) {
            mOnCommitClickListener.onCommitClick(mID, mSpec, num, mCode);
            dismiss();
        }
    }

    @OnClick({R.id.img_jia, R.id.img_jian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_jia:
                break;
            case R.id.img_jian:
                break;
        }
    }


    public interface OnCommitClickListener {

        void onCommitClick(int id, String spec, int num, int code);

    }

    private OnCommitClickListener mOnCommitClickListener;


}
