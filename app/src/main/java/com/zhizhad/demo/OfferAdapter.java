package com.zhizhad.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhizh.ad.tv.ZzAdSdk;
import com.zhizh.ad.tv.http.model.AdOffer;
import com.zhizh.ad.tv.http.model.ZzNativeAd;

import java.util.List;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {
    private List<ZzNativeAd> mData;

    public OfferAdapter(List<ZzNativeAd> data) {
        this.mData = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ZzNativeAd adOffer = mData.get(position);
        holder.tvName.setText(adOffer.getTitle());
        holder.tvDesc.setText(adOffer.getDesc());
        Glide.with(holder.itemView.getContext())
                .load(adOffer.getIcon())
                .into(holder.ivIcon);

        ZzAdSdk.getInstance().setAdShow(adOffer.getAppid());//当广告展示在视图上时调用该方法
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
