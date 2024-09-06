package com.zhizhad.demo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhizh.ad.tv.ZzAdSdk;
import com.zhizh.ad.tv.http.model.ZzNativeAd;
import com.zhizh.ad.tv.listener.OnLoadAdsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class AdListActivity extends Activity {
    private RecyclerView rvList;
    private OfferAdapter offerAdapter;
    private List<ZzNativeAd> zzNativeAds=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_list_activity);
        rvList=findViewById(R.id.rvList);
        offerAdapter=new OfferAdapter(zzNativeAds);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(offerAdapter);
        initData();
    }

    private void initData(){
        //获取原生广告
        ZzAdSdk.getInstance().getListOfAds(new OnLoadAdsListener() {
            @Override
            public void onLoadAdsSuccess(List<ZzNativeAd> offerList) {
                zzNativeAds.clear();
                zzNativeAds.addAll(offerList);
                offerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadAdsFail(Exception e) {

            }
        });
    }
}
