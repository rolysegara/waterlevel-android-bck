package com.visionet.indigo_waterlevel.modulreservoir.model;

import java.util.List;

public class ModelResponseGetBookmarkedReservoirs {

    /**
     * result : {"totalCount":1,"items":[{"id":1,"code":"SKS187XIU1","name":"Waduk Jatiluhur","address":"Kutamanah, Sukasari, Kabupaten Purwakarta, Jawa Barat","description":"Waduk Jatiluhur adalah sebuah waduk yang terletak di Kecamatan Jatiluhur, Kabupaten Purwakarta, Provinsi Jawa Barat (±9 km dari pusat Kota Purwakarta). Waduk yang dinamakan oleh pemerintah Waduk Ir. H. Juanda ini merupakan waduk terbesar di Indonesia. Bendungan Waduk Jatiluhur mulai dibangun sejak tahun 1957 oleh kontraktor asal Perancis Compagnie française d'entreprise, dengan potensi air yang tersedia sebesar 12,9 miliar m3 / tahun dan merupakan waduk serbaguna pertama di Indonesia. Waduk Jatiluhur dapat dikunjungi melalui Jalan Tol Purbaleunyi (Purwakarta-Bandung-Cileunyi), keluar di Gerbang Tol Jatiluhur","depth":0,"volume":0,"areaId":13,"areaName":"Sukasari","latitude":-6.523611,"longitude":107.38833299999999,"photo":[{"photoId":2,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180802120956940.jpg"}],"creationTime":"2018-07-09T11:08:26.238336","modificationTime":""}]}
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
         * totalCount : 1
         * items : [{"id":1,"code":"SKS187XIU1","name":"Waduk Jatiluhur","address":"Kutamanah, Sukasari, Kabupaten Purwakarta, Jawa Barat","description":"Waduk Jatiluhur adalah sebuah waduk yang terletak di Kecamatan Jatiluhur, Kabupaten Purwakarta, Provinsi Jawa Barat (±9 km dari pusat Kota Purwakarta). Waduk yang dinamakan oleh pemerintah Waduk Ir. H. Juanda ini merupakan waduk terbesar di Indonesia. Bendungan Waduk Jatiluhur mulai dibangun sejak tahun 1957 oleh kontraktor asal Perancis Compagnie française d'entreprise, dengan potensi air yang tersedia sebesar 12,9 miliar m3 / tahun dan merupakan waduk serbaguna pertama di Indonesia. Waduk Jatiluhur dapat dikunjungi melalui Jalan Tol Purbaleunyi (Purwakarta-Bandung-Cileunyi), keluar di Gerbang Tol Jatiluhur","depth":0,"volume":0,"areaId":13,"areaName":"Sukasari","latitude":-6.523611,"longitude":107.38833299999999,"photo":[{"photoId":2,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180802120956940.jpg"}],"creationTime":"2018-07-09T11:08:26.238336","modificationTime":""}]
         */

        private int totalCount;
        private List<ItemsBean> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 1
             * code : SKS187XIU1
             * name : Waduk Jatiluhur
             * address : Kutamanah, Sukasari, Kabupaten Purwakarta, Jawa Barat
             * description : Waduk Jatiluhur adalah sebuah waduk yang terletak di Kecamatan Jatiluhur, Kabupaten Purwakarta, Provinsi Jawa Barat (±9 km dari pusat Kota Purwakarta). Waduk yang dinamakan oleh pemerintah Waduk Ir. H. Juanda ini merupakan waduk terbesar di Indonesia. Bendungan Waduk Jatiluhur mulai dibangun sejak tahun 1957 oleh kontraktor asal Perancis Compagnie française d'entreprise, dengan potensi air yang tersedia sebesar 12,9 miliar m3 / tahun dan merupakan waduk serbaguna pertama di Indonesia. Waduk Jatiluhur dapat dikunjungi melalui Jalan Tol Purbaleunyi (Purwakarta-Bandung-Cileunyi), keluar di Gerbang Tol Jatiluhur
             * depth : 0
             * volume : 0
             * areaId : 13
             * areaName : Sukasari
             * latitude : -6.523611
             * longitude : 107.38833299999999
             * photo : [{"photoId":2,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180802120956940.jpg"}]
             * creationTime : 2018-07-09T11:08:26.238336
             * modificationTime :
             */

            private int id;
            private String code;
            private String name;
            private String address;
            private String description;
            private int depth;
            private int volume;
            private int areaId;
            private String areaName;
            private double latitude;
            private double longitude;
            private String creationTime;
            private String modificationTime;
            private List<PhotoBean> photo;

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getDepth() {
                return depth;
            }

            public void setDepth(int depth) {
                this.depth = depth;
            }

            public int getVolume() {
                return volume;
            }

            public void setVolume(int volume) {
                this.volume = volume;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
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

            public List<PhotoBean> getPhoto() {
                return photo;
            }

            public void setPhoto(List<PhotoBean> photo) {
                this.photo = photo;
            }

            public static class PhotoBean {
                /**
                 * photoId : 2
                 * photo : http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180802120956940.jpg
                 */

                private int photoId;
                private String photo;

                public int getPhotoId() {
                    return photoId;
                }

                public void setPhotoId(int photoId) {
                    this.photoId = photoId;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }
            }
        }
    }
}
