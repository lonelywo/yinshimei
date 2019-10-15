package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.YsmBean;
import com.cuci.enticement.utils.UtilsForClick;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.ItemViewBinder;

public class ItemYsmViewBinder extends ItemViewBinder<YsmBean.DataBean, ItemYsmViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(YsmBean.DataBean item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemYsmViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.activity_ysmitem, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull YsmBean.DataBean item) {

        holder.textXieyi.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UtilsForClick.isFastClick()) {

                    mOnProdClickListener.onProdClick(item);
                }

            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_xieyi)
        TextView textXieyi;
        @BindView(R.id.img_youjiantou)
        ImageView imgYoujiantou;
        @BindView(R.id.con_zhongjian)
        ConstraintLayout conZhongjian;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
