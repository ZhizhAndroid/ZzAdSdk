package com.zhizh.ad.tv.http.exception;

/**
 * @author lihui
 * @date 2024/9/6
 * @desc
 */
public class OfferException extends Exception {
    private final int errorCode;
    private final String errorMessage;

    /**
     * 构造函数
     *
     * @param errorCode 错误代码
     * @param errorMessage 错误信息
     */
    public OfferException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "OfferException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
