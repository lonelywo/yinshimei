package com.cuci.enticement.plate.mine.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.MyTeamlbBean;
import com.cuci.enticement.plate.mine.activity.MyTeamTwoActivity;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemMyTeamTwoViewBinder extends ItemViewBinder<MyTeamlbBean.DataBean.ListBean, ItemMyTeamTwoViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(MyTeamlbBean.DataBean.ListBean item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemMyTeamTwoViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_team, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MyTeamlbBean.DataBean.ListBean item) {

        ImageLoader.loadNoPlaceholder(item.getHeadimg(),holder.imgTuxiang);
        holder.textTime.setText(item.getCreate_at());
        holder.textWenzi1.setText(item.getNickname());
        holder.text_shuliang.setText(item.getTeams());


      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });


    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_shuliang)
        TextView text_shuliang;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}