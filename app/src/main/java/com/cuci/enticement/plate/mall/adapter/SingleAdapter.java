package com.cuci.enticement.plate.mall.adapter;

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

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SingleAdapter extends RecyclerView.Adapter {


    private List<KaQuanListBean.DataBean.ListBean> datas;

    private int selected = -1;

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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SingleViewHolder) {
            final SingleViewHolder viewHolder = (SingleViewHolder) holder;
            KaQuanListBean.DataBean.ListBean listBean = datas.get(position);

            if(TextUtils.equals(listBean.getUsed_at(),"未知")){
                viewHolder.mTvName.setText("不使用优惠券");
            }else {
                String amount = listBean.getCoupon().getAmount();
                String moveone_amount = MathExtend.moveone(amount);
                viewHolder.mTvName.setText("省"+moveone_amount+"元，"+listBean.getCoupon().getAmount_desc()+"优惠券");
            }

            if (selected == position) {
                viewHolder.mCheckBox.setImageResource(R.drawable.xuanzhong);
            } else {
                viewHolder.mCheckBox.setImageResource(R.drawable.noxuanzhong);
            }
            if (mOnItemClickLitener != null) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

        public SingleViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.text_name);
            mCheckBox = (ImageView) itemView.findViewById(R.id.img_check);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(KaQuanListBean.DataBean.ListBean datas, int position);

    }
}
