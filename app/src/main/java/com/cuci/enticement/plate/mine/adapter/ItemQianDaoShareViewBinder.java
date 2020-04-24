package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.QianDaoBean;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemQianDaoShareViewBinder extends ItemViewBinder<QianDaoBean.DataBean.SigninTaskBean, ItemQianDaoShareViewBinder.ViewHolder> {


    public interface OnProdClickListener {
       //分享朋友圈
        void onProdClick(QianDaoBean.DataBean.SigninTaskBean item);
       //去购物
        void onProdClick2(QianDaoBean.DataBean.SigninTaskBean item);
    }
    private OnProdClickListener mOnProdClickListener;

    public ItemQianDaoShareViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_qiandao_share, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QianDaoBean.DataBean.SigninTaskBean item) {

      ImageLoader.loadPlaceholder(item.getImg(),holder.imgTupian);
      holder.textTime.setText("+"+item.getGet_integral()+"积分");
        holder.textWenzi1.setText(item.getTitle());
        if(item.getIs_finish()==0){
            holder.textMoney.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.shape_qiandao_share_anniu_bg));
            holder.textMoney.setText("立即前往");
          if(item.getType()==1){
              holder.textMoney.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (mOnProdClickListener != null) {
                          mOnProdClickListener.onProdClick(item);
                          holder.textMoney.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.shape_qiandao_share_anniu_bg1));
                          holder.textMoney.setTextColor(BasicApp.getContext().getResources().getColor(R.color.white));
                          holder.textMoney.setText("已完成");
                      }
                  }
              });

          }  else if(item.getType()==2){
              holder.textMoney.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (mOnProdClickListener != null) {
                          mOnProdClickListener.onProdClick2(item);
                      }
                  }
              });
          }

        }else {
            holder.textMoney.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.shape_qiandao_share_anniu_bg1));
            holder.textMoney.setTextColor(BasicApp.getContext().getResources().getColor(R.color.white));
            holder.textMoney.setText("已完成");
        }
/*


        holder.itemView.setOnClickListener(position -> {

        });*/

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tupian)
        ImageView imgTupian;
        @BindView(R.id.line)
        Guideline line;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_money)
        TextView textMoney;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
