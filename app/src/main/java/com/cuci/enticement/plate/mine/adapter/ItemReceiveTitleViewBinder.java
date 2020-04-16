package com.cuci.enticement.plate.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemReceiveTitle;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemReceiveTitleViewBinder extends ItemViewBinder<ItemReceiveTitle, ItemReceiveTitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_lipingcenter_title, parent, false);
        return new ViewHolder(root);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemReceiveTitle itemOrderTitle) {
        holder.textBianhao.setText(String.format(Locale.CHINA,"%s",itemOrderTitle.getCreate_at()));
       switch (itemOrderTitle.status){
           case "0":
               holder.textZhuangtai.setText("未领取");
               break;
           case "3":
               holder.textZhuangtai.setText("待发货");
               break;
           case "4":
               holder.textZhuangtai.setText("已发货");
               break;
           case "5":
               holder.textZhuangtai.setText("已收货");
               break;
           case "6":
               holder.textZhuangtai.setText("已取消");
               break;
           default:
               holder.textZhuangtai.setText("");
            break;
       }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_bianhao)
        TextView textBianhao;
        @BindView(R.id.text_zhuangtai)
        TextView textZhuangtai;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
