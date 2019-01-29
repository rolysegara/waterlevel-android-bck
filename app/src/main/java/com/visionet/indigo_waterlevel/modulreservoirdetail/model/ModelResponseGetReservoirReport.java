package com.visionet.indigo_waterlevel.modulreservoirdetail.model;

import java.util.List;

public class ModelResponseGetReservoirReport {

    /**
     * result : [{"reservoirId":2,"reservoirName":"Waduk Cirata","levelAverage":82.73568831103096,"reservoirData":[{"timestamp":"2018-11-23T00:00:00","label":"00:00","dataRAW":4714.095944609298,"dataLevel":86.21737828332785,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T01:00:00","label":"01:00","dataRAW":4990.166836734694,"dataLevel":79.54958414864116,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T02:00:00","label":"02:00","dataRAW":5120.249293785311,"dataLevel":79.15681497175142,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T03:00:00","label":"03:00","dataRAW":5329.167758369724,"dataLevel":86.7376698204755,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T04:00:00","label":"04:00","dataRAW":4101.150423728814,"dataLevel":82.01844005021972,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T05:00:00","label":"05:00","dataRAW":5219.498821310703,"dataLevel":81.09872603331762,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T06:00:00","label":"06:00","dataRAW":5732.550847457627,"dataLevel":84.12927259887005,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T07:00:00","label":"07:00","dataRAW":5123.09533898305,"dataLevel":83.32672237915882,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T08:00:00","label":"08:00","dataRAW":5194.7296676096175,"dataLevel":89.72514831840328,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T09:00:00","label":"09:00","dataRAW":4112.795438472418,"dataLevel":76.58294043690083,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T10:00:00","label":"10:00","dataRAW":5394.2705669224215,"dataLevel":85.85266114900067,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T11:00:00","label":"11:00","dataRAW":5686.583333333333,"dataLevel":81.28721359070934,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T12:00:00","label":"12:00","dataRAW":5163.32196969697,"dataLevel":79.88137626262626,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T13:00:00","label":"13:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T14:00:00","label":"14:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T15:00:00","label":"15:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T16:00:00","label":"16:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T17:00:00","label":"17:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T18:00:00","label":"18:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T19:00:00","label":"19:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T20:00:00","label":"20:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T21:00:00","label":"21:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T22:00:00","label":"22:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T23:00:00","label":"23:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"}],"statusCategory":[{"status":"Normal","levelMin":0,"levelMax":100,"color":"#BFFF7F"},{"status":"Siaga","levelMin":101,"levelMax":200,"color":"#FFFF7F"},{"status":"Waspada","levelMin":201,"levelMax":300,"color":"#FFBF7F"},{"status":"Awas","levelMin":301,"levelMax":5000,"color":"#FF7F7F"}]}]
     * targetUrl :
     * success : true
     * error : null
     * unAuthorizedRequest : false
     * __abp : true
     */

    private String targetUrl;
    private boolean success;
    private Object error;
    private boolean unAuthorizedRequest;
    private boolean __abp;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * reservoirId : 2
         * reservoirName : Waduk Cirata
         * levelAverage : 82.73568831103096
         * reservoirData : [{"timestamp":"2018-11-23T00:00:00","label":"00:00","dataRAW":4714.095944609298,"dataLevel":86.21737828332785,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T01:00:00","label":"01:00","dataRAW":4990.166836734694,"dataLevel":79.54958414864116,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T02:00:00","label":"02:00","dataRAW":5120.249293785311,"dataLevel":79.15681497175142,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T03:00:00","label":"03:00","dataRAW":5329.167758369724,"dataLevel":86.7376698204755,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T04:00:00","label":"04:00","dataRAW":4101.150423728814,"dataLevel":82.01844005021972,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T05:00:00","label":"05:00","dataRAW":5219.498821310703,"dataLevel":81.09872603331762,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T06:00:00","label":"06:00","dataRAW":5732.550847457627,"dataLevel":84.12927259887005,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T07:00:00","label":"07:00","dataRAW":5123.09533898305,"dataLevel":83.32672237915882,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T08:00:00","label":"08:00","dataRAW":5194.7296676096175,"dataLevel":89.72514831840328,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T09:00:00","label":"09:00","dataRAW":4112.795438472418,"dataLevel":76.58294043690083,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T10:00:00","label":"10:00","dataRAW":5394.2705669224215,"dataLevel":85.85266114900067,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T11:00:00","label":"11:00","dataRAW":5686.583333333333,"dataLevel":81.28721359070934,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T12:00:00","label":"12:00","dataRAW":5163.32196969697,"dataLevel":79.88137626262626,"status":"Normal","color":"#BFFF7F"},{"timestamp":"2018-11-23T13:00:00","label":"13:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T14:00:00","label":"14:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T15:00:00","label":"15:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T16:00:00","label":"16:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T17:00:00","label":"17:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T18:00:00","label":"18:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T19:00:00","label":"19:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T20:00:00","label":"20:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T21:00:00","label":"21:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T22:00:00","label":"22:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"},{"timestamp":"2018-11-23T23:00:00","label":"23:00","dataRAW":0,"dataLevel":0,"status":"N/A","color":"#FFFFFF"}]
         * statusCategory : [{"status":"Normal","levelMin":0,"levelMax":100,"color":"#BFFF7F"},{"status":"Siaga","levelMin":101,"levelMax":200,"color":"#FFFF7F"},{"status":"Waspada","levelMin":201,"levelMax":300,"color":"#FFBF7F"},{"status":"Awas","levelMin":301,"levelMax":5000,"color":"#FF7F7F"}]
         */

        private int reservoirId;
        private String reservoirName;
        private double levelAverage;
        private List<ReservoirDataBean> reservoirData;
        private List<StatusCategoryBean> statusCategory;

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

        public double getLevelAverage() {
            return levelAverage;
        }

        public void setLevelAverage(double levelAverage) {
            this.levelAverage = levelAverage;
        }

        public List<ReservoirDataBean> getReservoirData() {
            return reservoirData;
        }

        public void setReservoirData(List<ReservoirDataBean> reservoirData) {
            this.reservoirData = reservoirData;
        }

        public List<StatusCategoryBean> getStatusCategory() {
            return statusCategory;
        }

        public void setStatusCategory(List<StatusCategoryBean> statusCategory) {
            this.statusCategory = statusCategory;
        }

        public static class ReservoirDataBean {
            /**
             * timestamp : 2018-11-23T00:00:00
             * label : 00:00
             * dataRAW : 4714.095944609298
             * dataLevel : 86.21737828332785
             * status : Normal
             * color : #BFFF7F
             */

            private String timestamp;
            private String label;
            private double dataRAW;
            private double dataLevel;
            private String status;
            private String color;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public double getDataRAW() {
                return dataRAW;
            }

            public void setDataRAW(double dataRAW) {
                this.dataRAW = dataRAW;
            }

            public double getDataLevel() {
                return dataLevel;
            }

            public void setDataLevel(double dataLevel) {
                this.dataLevel = dataLevel;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class StatusCategoryBean {
            /**
             * status : Normal
             * levelMin : 0
             * levelMax : 100
             * color : #BFFF7F
             */

            private String status;
            private int levelMin;
            private int levelMax;
            private String color;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getLevelMin() {
                return levelMin;
            }

            public void setLevelMin(int levelMin) {
                this.levelMin = levelMin;
            }

            public int getLevelMax() {
                return levelMax;
            }

            public void setLevelMax(int levelMax) {
                this.levelMax = levelMax;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
