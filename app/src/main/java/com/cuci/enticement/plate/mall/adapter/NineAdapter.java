package com.cuci.enticement.plate.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.MallSourceBean;
import com.cuci.enticement.plate.common.popup.ImageViewerPopup;
import com.cuci.enticement.utils.ImageLoader;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NineAdapter extends RecyclerView.Adapter<NineAdapter.ViewHolder> {

    private static final int VIEW_TYPE_V = 1;
    private static final int VIEW_TYPE_H = 2;


    List<MallSourceBean.DataBean.ListBean> mList;

    private GridLayoutManager mLayoutManager;
    private Context mcontext;
    public NineAdapter(Context context,GridLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mcontext = context;
    }

    public void setList(List<MallSourceBean.DataBean.ListBean> list) {
        mList = list;
    }

    public void addList(List<MallSourceBean.DataBean.ListBean> list) {
        int oldCount = mList.size();
        mList.addAll(list);
        int newCount = mList.size();
        notifyItemRangeInserted(oldCount, newCount);
    }

    public List<MallSourceBean.DataBean.ListBean> getList() {
        return mList;
    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root;

        if (viewType == VIEW_TYPE_V) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_mall_fragment01, parent, false);
        } else {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_mall_fragment01, parent, false);
        }

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         if(mList==null){
             return;
         }
        MallSourceBean.DataBean.ListBean bean = mList.get(position);
        String type = bean.getType();
        ImageLoader.loadPlaceholder(bean.getImage()+"-dealwith",holder.img_source);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mList,position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mLayoutManager.getSpanCount() == 1) {
            return VIEW_TYPE_V;
        } else if (mLayoutManager.getSpanCount() == 2) {
            return VIEW_TYPE_H;
        } else {
            return super.getItemViewType(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_source)
        ImageView img_source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

           /* itemView.setOnClickListener(v -> {

            });*/
        }
    }

    public interface OnItemClickListener {
        void onItemClick( List<MallSourceBean.DataBean.ListBean> mList,int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
