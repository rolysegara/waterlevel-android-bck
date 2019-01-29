package com.visionet.indigo_waterlevel.modulreservoir.model;

public class ModelResponseBookmarkReservoirOrClear {

    /**
     * result : {"isBookmarked":true,"message":"Successfully bookmark Waduk Jatiluhur"}
     * targetUrl :
     * success : true
     * error : null
     * unAuthorizedRequest : false
     * __abp : true
     */

    private ResultBean result;
    private String targetUrl;
    private boolean success;
    private Object error;
    private boolean unAuthorizedRequest;
    private boolean __abp;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public boolean isUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    public boolean is__abp() {
        return __abp;
    }

    public void set__abp(boolean __abp) {
        this.__abp = __abp;
    }

    public static class ResultBean {
        /**
         * isBookmarked : true
         * message : Successfully bookmark Waduk Jatiluhur
         */

        private boolean isBookmarked;
        private String message;

        public boolean isIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(boolean isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
