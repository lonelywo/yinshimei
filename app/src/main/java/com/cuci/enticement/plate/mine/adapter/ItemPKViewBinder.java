package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.MyTeamlbBean;
import com.cuci.enticement.bean.PKbean1;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.ItemViewBinder;

public class ItemPKViewBinder extends ItemViewBinder<PKbean1.DataBean.ListBean, ItemPKViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(PKbean1.DataBean.ListBean item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemPKViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_pk, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PKbean1.DataBean.ListBean item) {
        int position = getPosition(holder);

        ImageLoader.loadPlaceholder1(item.getHeadimg(),holder.imgTuxiang);
        holder.textNum.setText(String.valueOf(1+position));
        holder.textWenzi1.setText(item.getNickname());
        holder.textShuliang.setText("¥"+item.getTeams());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              mOnProdClickListener.onProdClick(item);
          }
      });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_num)
        TextView textNum;
        @BindView(R.id.img_huangguan)
        ImageView imgHuangguan;
        @BindView(R.id.img_tuxiang)
        CircleImageView imgTuxiang;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_shuliang)
        TextView textShuliang;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
