package com.cuci.enticement.plate.cart.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.UtilsForClick;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemCartViewBinder extends ItemViewBinder<OrderGoods, ItemCartViewBinder.ViewHolder> {

    public interface OnItemClickListener {

        void onAddClick(OrderGoods bean,int position);

        void onMinusClick(OrderGoods bean,int position);
        void onCheckedChange(int positon);
        void onDelete(OrderGoods bean,int position);
    }

    private ItemCartViewBinder.OnItemClickListener mOnItemClickListener;

    public ItemCartViewBinder(ItemCartViewBinder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_cart, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OrderGoods item) {
        int position = holder.getPosition();

       if (item.isCheck()) {
            holder.mImageCheck.setImageResource(R.drawable.xuanzhong);
        } else {
            holder.mImageCheck.setImageResource(R.drawable.noxuanzhong);
        }

        holder.mImageCheck.setOnClickListener(v -> {
            item.setCheck(!item.isCheck());
            if (item.isCheck()) {
                holder.mImageCheck.setImageResource(R.drawable.xuanzhong);
            } else {
                holder.mImageCheck.setImageResource(R.drawable.noxuanzhong);
            }
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onCheckedChange(position);
            }
        });


        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textJiage.setText(String.format(Locale.CHINA,"%s",item.getGoods_price_selling()));

        holder.tvNum.setText(String.valueOf(item.getGoods_num()));
        ImageLoader.loadPlaceholder(item.getGoods_logo(),holder.imgTuxiang);



        holder.ivJia.setOnClickListener(v -> {
            if (UtilsForClick.isFastClick()) {
                int num = item.getGoods_num() + 1;
                item.setGoods_num(num);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onAddClick(item, position);
                }
            }


        });

        holder.ivJian.setOnClickListener(v -> {
            if (UtilsForClick.isFastClick()) {
                if (item.getGoods_num() <= 1) {
                    FToast.warning("不能再少了");
                    return;
                }
                int num = item.getGoods_num() - 1;
                item.setGoods_num(num);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onMinusClick(item, position);
                }
            }
        });



        holder.itemView.setOnClickListener(v -> {
                //这里不用写，但是必须加这个监听事件才能滑动
        });

       if (!holder.tvDelete.hasOnClickListeners()) {
            holder.tvDelete.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onDelete(item, position);
                }

            });
        }


       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {

               if (mOnItemClickListener != null) {
                   mOnItemClickListener.onDelete(item, position);
               }

               return false;
           }
       });

    }



    static class ViewHolder extends RecyclerView.ViewHolder {

       @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;

        @BindView(R.id.image_check)
        ImageView mImageCheck;
        @BindView(R.id.text_biaoti)
        TextView textBiaoti;
        @BindView(R.id.text_neirong)
        TextView textNeirong;
        @BindView(R.id.text_jiage)
        TextView textJiage;
        @BindView(R.id.text_shuzi)
        TextView tvNum;
        @BindView(R.id.img_jia)
        ImageView ivJia;
        @BindView(R.id.img_jian)
        ImageView ivJian;

        @BindView(R.id.tv_delete)
        TextView tvDelete;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
