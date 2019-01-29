package com.visionet.indigo_waterlevel.modulreservoirdetail.model;

import java.util.List;

public class ModelResponseGetProgressAllReservoirs {

    /**
     * result : [{"id":1,"code":"SKS187XIU1","name":"Waduk Jatiluhur","level":0,"levelMax":251,"progress":0,"status":"Normal"},{"id":2,"code":"PLD187XI6C","name":"Waduk Cirata","level":99,"levelMax":301,"progress":32.89036544850498,"status":"Normal"},{"id":3,"code":"TRW187XIGP","name":"Kolam Segaran","level":32,"levelMax":201,"progress":15.92039800995025,"status":"Normal"},{"id":4,"code":"ARE188XBKS","name":"Waduk Ir. Sutami","level":0,"levelMax":501,"progress":0,"status":"Normal"},{"id":5,"code":"JTH188XBMT","name":"Visionet","level":0,"levelMax":0,"progress":0,"status":"Not available"},{"id":6,"code":"BGS188XBZ2","name":"Kolam air visionet","level":0,"levelMax":33,"progress":0,"status":"Not available"},{"id":7,"code":"DRM188XIXN","name":"Kolam Cupang","level":0,"levelMax":1855,"progress":0,"status":"Not available"}]
     * targetUrl : null
     * success : true
     * error : null
     * unAuthorizedRequest : false
     * __abp : true
     */

    private Object targetUrl;
    private boolean success;
    private Object error;
    private boolean unAuthorizedRequest;
    private boolean __abp;
    private List<ResultBean> result;

    public Object getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(Object targetUrl) {
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * code : SKS187XIU1
         * name : Waduk Jatiluhur
         * level : 0
         * levelMax : 251
         * progress : 0
         * status : Normal
         */

        private int id;
        private String code;
        private String name;
        private int level;
        private int levelMax;
        private int progress;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevelMax() {
            return levelMax;
        }

        public void setLevelMax(int levelMax) {
            this.levelMax = levelMax;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
