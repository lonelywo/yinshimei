package com.example.enticement.plate.mall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cuci.enticement.R;
import com.example.enticement.bean.MallSourceBean;
import com.example.enticement.utils.ImageLoader;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NineAdapter extends RecyclerView.Adapter<NineAdapter.ViewHolder> {

    private static final int VIEW_TYPE_V = 1;
    private static final int VIEW_TYPE_H = 2;


    List<MallSourceBean.DataBean.ListBean> mList;

    private StaggeredGridLayoutManager mLayoutManager;

    public NineAdapter(StaggeredGridLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
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
        ImageLoader.loadPlaceholder(bean.getImage(),holder.img_source);

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

            itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
