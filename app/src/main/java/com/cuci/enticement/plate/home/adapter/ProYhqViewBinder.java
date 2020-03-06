package com.cuci.enticement.plate.home.adapter;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ProYhqBean;
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

public class ProYhqViewBinder extends ItemViewBinder<ProYhqBean.DataBean, ProYhqViewBinder.ViewHolder> {


    private boolean type = true;
    private Boolean misckeck = false;

    public interface OnProdYhqClickListener {

        void onProdClick(ProYhqBean.DataBean item);

    }


    private OnProdYhqClickListener mOnProdYhqClickListener;

    public ProYhqViewBinder(OnProdYhqClickListener OnProdYhqClickListener) {
        mOnProdYhqClickListener = OnProdYhqClickListener;

    }

    public void setdata(Boolean isckeck) {
        misckeck = isckeck;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_pro_lingqu_yhq, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ProYhqBean.DataBean item) {
        String replace = item.getRule_desc().replace("\\n", "\n");
        String moveone_amount = MathExtend.moveone(item.getAmount());
        String strMsg = "<font >" + "¥" + "<big><big><big><big>" + moveone_amount + "</big></big></big></big>" + "</font>";
        holder.tvMoneyTou.setText(Html.fromHtml(strMsg));
        holder.tvMoneyWei.setText(item.getDiscount());
        holder.tvName.setText(item.getTitle());
        holder.tvTime.setText(item.getDate_range());


        holder.tvQushiyong.setOnClickListener(position -> {

            if (mOnProdYhqClickListener != null) {
                if (UtilsForClick.isFastClick()) {
                    mOnProdYhqClickListener.onProdClick(item);
                    item.setIscheck(!item.getIscheck());

                }

            }
        });


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

        if (item.getIscheck()) {
            holder.tvQushiyong.setClickable(false);
            holder.tvQushiyong.setText("领取成功");
            holder.tvQushiyong.setBackgroundResource(R.drawable.shape_pro_yhq_anniu_hui);
        } else {
            holder.tvQushiyong.setClickable(true);
            holder.tvQushiyong.setText("点击领取");
            holder.tvQushiyong.setBackgroundResource(R.drawable.shape_pro_yhq_anniu);
        }
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
