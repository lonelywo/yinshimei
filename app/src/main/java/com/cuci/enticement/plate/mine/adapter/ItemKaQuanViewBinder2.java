package com.cuci.enticement.plate.mine.adapter;


import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemKaQuanViewBinder2 extends ItemViewBinder<KaQuanListBean.DataBean.ListBean, ItemKaQuanViewBinder2.ViewHolder> {

    private boolean type = true;

    public interface OnPKClickListener {

        void onProdClick(KaQuanListBean.DataBean.ListBean item);


    }

    private ItemKaQuanViewBinder2.OnPKClickListener mOnProdClickListener;

    public ItemKaQuanViewBinder2(ItemKaQuanViewBinder2.OnPKClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ItemKaQuanViewBinder2.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_kaquan21, parent, false);
        return new ItemKaQuanViewBinder2.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemKaQuanViewBinder2.ViewHolder holder, @NonNull KaQuanListBean.DataBean.ListBean item) {
        final int position = getPosition(holder);
        String amount = item.getCoupon().getAmount();
        String limit_amount = item.getCoupon().getLimit_amount();
        String title = item.getCoupon().getTitle();
        String rule_desc = item.getCoupon().getRule_desc();
        String use_goods = item.getCoupon().getUse_goods();
        String startstime = item.getStartstime();
        String endtime = item.getEndtime();
        String date_range = item.getDate_range();
        String amount_desc = item.getCoupon().getAmount_desc();
        String moveone_amount = MathExtend.moveone(amount);
        String moveone_limit_amount = MathExtend.moveone(limit_amount);
        String replace = item.getCoupon().getRule_desc().replace("\\n", "\n");
        String strMsg = "<font >" + "¥" + "<big><big><big><big>" + moveone_amount + "</big></big></big></big>" + "</font>";
        holder.tvMoneyTou.setText(Html.fromHtml(strMsg));
        holder.tvMoneyWei.setText(amount_desc);
        holder.tvName.setText(title);
        holder.tvTime.setText(date_range);
        holder.conShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UtilsForClick.isFastClick()) {
                    if (type) {
                        type = false;
                        holder.imgJiantou.setImageResource(R.drawable.sanjiao_shang);
                        ViewUtils.showView(holder.conDibu);
                        holder.tvContent.setText(replace);
                    } else {
                        type = true;
                        holder.imgJiantou.setImageResource(R.drawable.jiantou_xia);
                        ViewUtils.hideView(holder.conDibu);
                    }
                }
            }
        });

        holder.tvQushiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnProdClickListener.onProdClick(item);
            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guideline)
        Guideline guideline;
        @BindView(R.id.img_tupian)
        ImageView imgTupian;
        @BindView(R.id.con_buju)
        ConstraintLayout conBuju;
        @BindView(R.id.tv_money_tou)
        TextView tvMoneyTou;
        @BindView(R.id.tv_money_wei)
        TextView tvMoneyWei;
        @BindView(R.id.guideline1)
        Guideline guideline1;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_qushiyong)
        TextView tvQushiyong;
        @BindView(R.id.tv_shuoming)
        TextView tvShuoming;
        @BindView(R.id.img_jiantou)
        ImageView imgJiantou;
        @BindView(R.id.con_shuoming)
        LinearLayout conShuoming;
        @BindView(R.id.con_toubu)
        ConstraintLayout conToubu;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.con_dibu)
        ConstraintLayout conDibu;
        @BindView(R.id.container)
        ConstraintLayout container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
