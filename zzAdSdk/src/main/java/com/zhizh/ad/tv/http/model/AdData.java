package com.zhizh.ad.tv.http.model;

import java.util.List;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class AdData {
    private String msg;
    private int status;
    private String requestId;
    private List<AdOffer> offers;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<AdOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<AdOffer> offers) {
        this.offers = offers;
    }
}
