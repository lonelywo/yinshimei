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
import de.hdodenhof.circleimageview.CircleImageView;
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

        ImageLoader.loadPlaceholder1(item.getHeadimg(),holder.imgTuxiang);
        holder.textTime.setText(item.getCreate_at());
        holder.textWenzi1.setText(item.getNickname());
        if(item.getId()==2){
            holder.textShuliang.setText(item.getTeams_total()+"人");
            holder.textRzeng.setText("日新增：" + item.getDaily_teams_total()+"人");
        }else {
            holder.textShuliang.setText(item.getTeams()+"人");
            holder.textRzeng.setText("日新增：" + item.getDaily_teams()+"人");
        }


      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });


    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tuxiang)
        CircleImageView imgTuxiang;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_rzeng)
        TextView textRzeng;
        @BindView(R.id.text_shuliang)
        TextView textShuliang;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
