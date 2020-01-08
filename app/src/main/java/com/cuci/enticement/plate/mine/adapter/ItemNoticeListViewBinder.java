package com.cuci.enticement.plate.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.NoticeListBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemNoticeListViewBinder extends ItemViewBinder<NoticeListBean.DataBean.ListBean, ItemNoticeListViewBinder.ViewHolder> {


    public interface OnProdClickListener {

        void onProdClick(int id);

    }
    private OnProdClickListener mOnProdClickListener;

    public ItemNoticeListViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_notice, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NoticeListBean.DataBean.ListBean item) {
        int adapterPosition = holder.getAdapterPosition();
       /* if(adapterPosition==0){
            ViewUtils.showView(holder.textDian);
        }else {
            ViewUtils.hideView(holder.textDian);
        }*/
        int id = item.getId();
        ViewUtils.hideView(holder.textDian);
        holder.textBiaoti.setText(item.getTitle());
        holder.textTime.setText(item.getCreate_at());
        holder.textNeirong.setText(item.getBrief());
        holder.textQian.setText(item.getCatetitle());
        ImageLoader.loadPlaceholder1(item.getLogo(),holder.imgTupian);
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(id);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_dian)
        TextView textDian;
        @BindView(R.id.text_biaoti)
        TextView textBiaoti;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.img_tupian)
        ImageView imgTupian;
        @BindView(R.id.text_neirong)
        TextView textNeirong;
        @BindView(R.id.text_qian)
        TextView textQian;
        @BindView(R.id.img_num)
        ImageView imgNum;
        @BindView(R.id.con_buju)
        ConstraintLayout conBuju;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
