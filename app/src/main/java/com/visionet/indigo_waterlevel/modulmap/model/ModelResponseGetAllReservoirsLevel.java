package com.visionet.indigo_waterlevel.modulmap.model;

import java.util.List;

public class ModelResponseGetAllReservoirsLevel {

    /**
     * result : [{"id":27,"name":"Tsunami","description":"1855 - 10000","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":1855,"levelMax":2000,"creationTime":"2018-09-14T02:36:08.854329","modificationTime":"2018-10-04T01:37:59.482765"},{"id":26,"name":"dfasersa","description":"fsadfasegs","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":100,"levelMax":500,"creationTime":"2018-09-14T02:35:46.781738","modificationTime":""},{"id":25,"name":"indo mie","description":"ksladnfklser","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":1,"levelMax":33,"creationTime":"2018-09-14T02:35:28.819723","modificationTime":""},{"id":24,"name":"aman","description":"asdfsdflkjaklsdf","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":10,"levelMax":33,"creationTime":"2018-09-14T02:35:09.027377","modificationTime":""},{"id":23,"name":"Bahaya sekaliゴゴゴ","description":"sgt bhy","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":522.66,"levelMax":1855,"creationTime":"2018-09-01T19:23:03.719624","modificationTime":""},{"id":22,"name":"dsgsdf","description":"werf","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":41,"levelMax":100,"creationTime":"2018-08-25T23:35:33.730407","modificationTime":"2018-08-27T09:31:12.93072"},{"id":20,"name":"dfs","description":"dffd","reservoirId":6,"reservoirName":"Kolam air visionet","levelMin":33,"levelMax":50,"creationTime":"2018-08-24T16:07:33.630662","modificationTime":""},{"id":17,"name":"waspadalah","description":"asdflka;jser","reservoirId":7,"reservoirName":"Kolam Cupang","levelMin":33,"levelMax":40,"creationTime":"2018-08-24T15:56:13.398974","modificationTime":"2018-08-24T16:56:12.142454"},{"id":15,"name":"Waspada","description":"Waduk dalam keadaan waspada","reservoirId":1,"reservoirName":"Waduk Jatiluhur","levelMin":251,"levelMax":350,"creationTime":"2018-08-14T17:35:24.210565","modificationTime":""},{"id":14,"name":"Siaga","description":"Waduk dalam keadaan siaga","reservoirId":1,"reservoirName":"Waduk Jatiluhur","levelMin":151,"levelMax":250,"creationTime":"2018-08-14T17:35:11.230941","modificationTime":""},{"id":13,"name":"Normal","description":"Waduk dalam keadaan normal","reservoirId":1,"reservoirName":"Waduk Jatiluhur","levelMin":0,"levelMax":150,"creationTime":"2018-08-14T17:34:55.767443","modificationTime":""},{"id":12,"name":"Siaga III","description":"Waduk dalam keadaan siaga","reservoirId":4,"reservoirName":"Waduk Ir. Sutami","levelMin":501,"levelMax":600,"creationTime":"2018-08-14T15:14:24.210727","modificationTime":""},{"id":11,"name":"Siaga II","description":"Waduk dalam keadaan siaga","reservoirId":4,"reservoirName":"Waduk Ir. Sutami","levelMin":401,"levelMax":500,"creationTime":"2018-08-14T15:13:47.182609","modificationTime":""},{"id":10,"name":"Siaga","description":"Waduk dalam keadaan siaga","reservoirId":4,"reservoirName":"Waduk Ir. Sutami","levelMin":201,"levelMax":400,"creationTime":"2018-08-14T15:12:15.323355","modificationTime":""},{"id":9,"name":"Normal","description":"Waduk dalam keadaan normal","reservoirId":4,"reservoirName":"Waduk Ir. Sutami","levelMin":0,"levelMax":200,"creationTime":"2018-08-14T15:12:01.690575","modificationTime":""},{"id":8,"name":"Awas","description":"Waduk dalam keadaan awas","reservoirId":2,"reservoirName":"Waduk Cirata","levelMin":301,"levelMax":5000,"creationTime":"2018-07-31T10:48:22.095441","modificationTime":""},{"id":7,"name":"Waspada","description":"Waduk dalam keadaan waspada","reservoirId":2,"reservoirName":"Waduk Cirata","levelMin":201,"levelMax":300,"creationTime":"2018-07-31T10:48:08.935035","modificationTime":""},{"id":6,"name":"Siaga","description":"Waduk dalam keadaan siaga","reservoirId":2,"reservoirName":"Waduk Cirata","levelMin":101,"levelMax":200,"creationTime":"2018-07-31T10:47:50.346107","modificationTime":""},{"id":5,"name":"Normal","description":"Waduk dalam keadaan normal","reservoirId":2,"reservoirName":"Waduk Cirata","levelMin":0,"levelMax":100,"creationTime":"2018-07-31T10:47:37.808764","modificationTime":""},{"id":4,"name":"Awas","description":"Waduk dalam keadaan awas","reservoirId":3,"reservoirName":"Kolam Segaran","levelMin":201,"levelMax":5000,"creationTime":"2018-07-31T10:35:22.114937","modificationTime":""},{"id":3,"name":"Waspada","description":"Waduk dalam keadaan waspada","reservoirId":3,"reservoirName":"Kolam Segaran","levelMin":151,"levelMax":200,"creationTime":"2018-07-31T10:34:50.953951","modificationTime":""},{"id":2,"name":"Siaga","description":"Waduk dalam keadaan siaga","reservoirId":3,"reservoirName":"Kolam Segaran","levelMin":101,"levelMax":150,"creationTime":"2018-07-31T10:34:29.336855","modificationTime":""},{"id":1,"name":"Normal","description":"Waduk dalam keadaan normal","reservoirId":3,"reservoirName":"Kolam Segaran","levelMin":0,"levelMax":100,"creationTime":"2018-07-31T10:33:05.514367","modificationTime":""}]
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
         * id : 27
         * name : Tsunami
         * description : 1855 - 10000
         * reservoirId : 7
         * reservoirName : Kolam Cupang
         * levelMin : 1855
         * levelMax : 2000
         * creationTime : 2018-09-14T02:36:08.854329
         * modificationTime : 2018-10-04T01:37:59.482765
         */

        private int id;
        private String name;
        private String description;
        private int reservoirId;
        private String reservoirName;
        private double levelMin;
        private double levelMax;
        private String creationTime;
        private String modificationTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(int reservoirId) {
            this.reservoirId = reservoirId;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public double getLevelMin() {
            return levelMin;
        }

        public void setLevelMin(double levelMin) {
            this.levelMin = levelMin;
        }

        public double getLevelMax() {
            return levelMax;
        }

        public void setLevelMax(double levelMax) {
            this.levelMax = levelMax;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getModificationTime() {
            return modificationTime;
        }

        public void setModificationTime(String modificationTime) {
            this.modificationTime = modificationTime;
        }
    }
}
