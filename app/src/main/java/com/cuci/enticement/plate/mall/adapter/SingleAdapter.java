package com.cuci.enticement.plate.mall.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SingleAdapter extends RecyclerView.Adapter {


    private List<KaQuanListBean.DataBean.ListBean> datas;

    private int selected = 0;

    public SingleAdapter(List<KaQuanListBean.DataBean.ListBean> datas) {
        this.datas = datas;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setSelection(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_check_kaquan, parent, false);

        return new SingleViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SingleViewHolder) {
            final SingleViewHolder viewHolder = (SingleViewHolder) holder;
            KaQuanListBean.DataBean.ListBean listBean = datas.get(position);

            if(listBean.isIsshow()){
                if(TextUtils.equals(listBean.getUsed_at(),"未知")){
                    viewHolder.mTvName.setText("不使用代金券");
                    viewHolder.mTvName.setTextColor(Color.parseColor("#333333"));
                }else {
                    String amount = listBean.getCoupon().getAmount();
                    String moveone_amount = MathExtend.moveone(amount);
                    viewHolder.mTvName.setText("省"+moveone_amount+"元，"+listBean.getCoupon().getAmount_desc());
                    viewHolder.mTvName.setTextColor(Color.parseColor("#333333"));
                }
                ViewUtils.showView(viewHolder.mCheckBox);
                viewHolder.con_fangshi1.setEnabled(true);
            }else {
                String amount = listBean.getCoupon().getAmount();
                String moveone_amount = MathExtend.moveone(amount);
                viewHolder.mTvName.setText("省"+moveone_amount+"元，"+listBean.getCoupon().getAmount_desc()+"(不可用)");
                viewHolder.mTvName.setTextColor(Color.parseColor("#666666"));
                ViewUtils.hideView(viewHolder.mCheckBox);
                viewHolder.con_fangshi1.setEnabled(false);
            }

            if (selected == position) {
                viewHolder.mCheckBox.setImageResource(R.drawable.xuanzhong);
            } else {
                viewHolder.mCheckBox.setImageResource(R.drawable.noxuanzhong);
            }
            if (mOnItemClickLitener != null) {
                    viewHolder.con_fangshi1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(UtilsForClick.isFastClick()){

                                mOnItemClickLitener.onItemClick(listBean, viewHolder.getAdapterPosition());

                            }
                            }
                    });
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        ImageView mCheckBox;
        ConstraintLayout con_fangshi1;

        public SingleViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.text_name);
            mCheckBox = (ImageView) itemView.findViewById(R.id.img_check);
            con_fangshi1 = (ConstraintLayout) itemView.findViewById(R.id.con_fangshi1);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(KaQuanListBean.DataBean.ListBean datas, int position);

    }
}
