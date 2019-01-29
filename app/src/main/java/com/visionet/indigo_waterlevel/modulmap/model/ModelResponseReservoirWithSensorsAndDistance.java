package com.visionet.indigo_waterlevel.modulmap.model;

import java.util.List;

public class ModelResponseReservoirWithSensorsAndDistance {

    /**
     * result : {"totalCount":7,"items":[{"id":2,"code":"PLD187XI6C","name":"Waduk Cirata","address":"Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat","description":"Pembangkit Listrik Tenaga Air (PLTA) Cirata merupakan PLTA terbesar di Asia Tenggara. PLTA ini memiliki konstruksi power house di bawah tanah dengan kapasitas 8x126 Megawatt (MW) sehingga total kapasitas terpasang 1.008 Megawatt (MW) dengan produksi energi listrik rata-rata 1.428 Giga Watthour (GWh) pertahun. PLTA Cirata terletak di daerah aliran sungai (DAS) Citarum di Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat. Sedangkaln luas Waduk Cirata, dari ujung selatan kecamatan Cipeundeuy kabupaten Bandung barat, dan terbendung di desa Ciroyom, kecamatan Cipeundeuy kabupaten Bandung barat, yang berbatasan langsung dengan maniis kabupaten Purwakarta. Latar belakang pendirian PLTA ini, dengan letak sungai Citarum yang subur, bergunung-gunung dan dianugerahi curah hujan yang tinggi. Pembangunan proyek PLTA Cirata merupakan salah satu cara pemanfaatan potensi tenaga air di Sungai Citarum yang letaknya di wilayah kabupaten Bandung, kurang lebih 60 km sebelah barat laut kota Bandung atau 100 km dari Jakarta melalui jalan Purwakarta","depth":0,"volume":0,"areaId":5,"areaName":"Plered","latitude":-6.739620200000001,"longitude":107.28437259999998,"distance":11915.210048096058,"distanceDescription":"11915.21 km","lastLevelAverage":56,"lastStatusAverage":"Normal","photo":[{"photoId":1,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180730173400.jpg"}],"sensor":[{"sensorId":6,"sensorCode":"SEN-2-0003","sensorName":"Sensor 3","lastLevel":87,"lastStatus":"Normal"},{"sensorId":4,"sensorCode":"SEN-2-0001","sensorName":"Sensor 1","lastLevel":54,"lastStatus":"Normal"},{"sensorId":5,"sensorCode":"SEN-2-0002","sensorName":"Sensor 2","lastLevel":27,"lastStatus":"Normal"}],"creationTime":"2018-07-09T11:14:32.976312","modificationTime":""},{"id":3,"code":"TRW187XIGP","name":"Kolam Segaran","address":"Dukuh Trowulan, Desa Trowulan, Kecamatan Trowulan, Kabupaten Mojokerto, Jawa Timur","description":"Kolam Segaran ditemukan pada tahun 1926, dalam keadaan teruruk tanah. Pada tahun 1966 kolam ini mengalami pemugaran sekedarnya. Baru pada tahun 1974 dimulai pelaksanaan pemugaran yang lebih terencana dan menyeluruh, yang memakan waktu sepuluh tahun. Fungsi Kolam Segaran belum diketahui secara pasti, tetapi menurut masyarakat di sekitarnya, kolam tersebut digunakan keluarga Kerajaan Majapahit untuk berekreasi dan menjamu tamu dari luar negeri. Kolam ini merupakan satu-satunya bangunan kolam kuno terbesar yang pernah ditemukan di Indonesia. Kolam yang luas keseluruhannya kurang lebih 6,5 hektar, membujur ke arah utara-selatan sepanjang 375 m dengan lebar 175 m. Sekeliling tepi kolam dilapisi dinding setebal 1,60 m dengan kedalaman 2,88 m","depth":0,"volume":0,"areaId":14,"areaName":"Trowulan","latitude":-7.5580556,"longitude":112.38277779999999,"distance":12473.016910435606,"distanceDescription":"12473.02 km","lastLevelAverage":146,"lastStatusAverage":"Siaga","photo":[{"photoId":47,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180810060716772.jpg"}],"sensor":[{"sensorId":1,"sensorCode":"SEN-3-0001","sensorName":"Sensor 1","lastLevel":122,"lastStatus":"Siaga"},{"sensorId":2,"sensorCode":"SEN-3-0002","sensorName":"Sensor 2","lastLevel":122,"lastStatus":"Siaga"},{"sensorId":3,"sensorCode":"SEN-3-0003","sensorName":"Sensor 3","lastLevel":194,"lastStatus":"Waspada"}],"creationTime":"2018-07-09T11:25:42.769622","modificationTime":"2018-08-10T06:07:16.315291"},{"id":6,"code":"BGS188XBZ2","name":"Kolam air visionet","address":"Visionet Blimbing","description":"kolam air paling jernih didunia","depth":3343221,"volume":233,"areaId":2,"areaName":"Bungursari","latitude":-8.698944053099034,"longitude":119.75631245624515,"distance":13273.835822412479,"distanceDescription":"13273.84 km","lastLevelAverage":0,"lastStatusAverage":"Not available","photo":[{"photoId":49,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180810061050757.jpg"}],"sensor":[{"sensorId":9,"sensorCode":"SSSSS","sensorName":"SENSOR II","lastLevel":0,"lastStatus":"dfs"},{"sensorId":7,"sensorCode":"SENSOR-1","sensorName":"sensor gerak","lastLevel":0,"lastStatus":"dfs"}],"creationTime":"2018-08-02T14:38:55.975136","modificationTime":"2018-09-04T14:57:34.549823"}]}
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
         * totalCount : 7
         * items : [{"id":2,"code":"PLD187XI6C","name":"Waduk Cirata","address":"Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat","description":"Pembangkit Listrik Tenaga Air (PLTA) Cirata merupakan PLTA terbesar di Asia Tenggara. PLTA ini memiliki konstruksi power house di bawah tanah dengan kapasitas 8x126 Megawatt (MW) sehingga total kapasitas terpasang 1.008 Megawatt (MW) dengan produksi energi listrik rata-rata 1.428 Giga Watthour (GWh) pertahun. PLTA Cirata terletak di daerah aliran sungai (DAS) Citarum di Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat. Sedangkaln luas Waduk Cirata, dari ujung selatan kecamatan Cipeundeuy kabupaten Bandung barat, dan terbendung di desa Ciroyom, kecamatan Cipeundeuy kabupaten Bandung barat, yang berbatasan langsung dengan maniis kabupaten Purwakarta. Latar belakang pendirian PLTA ini, dengan letak sungai Citarum yang subur, bergunung-gunung dan dianugerahi curah hujan yang tinggi. Pembangunan proyek PLTA Cirata merupakan salah satu cara pemanfaatan potensi tenaga air di Sungai Citarum yang letaknya di wilayah kabupaten Bandung, kurang lebih 60 km sebelah barat laut kota Bandung atau 100 km dari Jakarta melalui jalan Purwakarta","depth":0,"volume":0,"areaId":5,"areaName":"Plered","latitude":-6.739620200000001,"longitude":107.28437259999998,"distance":11915.210048096058,"distanceDescription":"11915.21 km","lastLevelAverage":56,"lastStatusAverage":"Normal","photo":[{"photoId":1,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180730173400.jpg"}],"sensor":[{"sensorId":6,"sensorCode":"SEN-2-0003","sensorName":"Sensor 3","lastLevel":87,"lastStatus":"Normal"},{"sensorId":4,"sensorCode":"SEN-2-0001","sensorName":"Sensor 1","lastLevel":54,"lastStatus":"Normal"},{"sensorId":5,"sensorCode":"SEN-2-0002","sensorName":"Sensor 2","lastLevel":27,"lastStatus":"Normal"}],"creationTime":"2018-07-09T11:14:32.976312","modificationTime":""},{"id":3,"code":"TRW187XIGP","name":"Kolam Segaran","address":"Dukuh Trowulan, Desa Trowulan, Kecamatan Trowulan, Kabupaten Mojokerto, Jawa Timur","description":"Kolam Segaran ditemukan pada tahun 1926, dalam keadaan teruruk tanah. Pada tahun 1966 kolam ini mengalami pemugaran sekedarnya. Baru pada tahun 1974 dimulai pelaksanaan pemugaran yang lebih terencana dan menyeluruh, yang memakan waktu sepuluh tahun. Fungsi Kolam Segaran belum diketahui secara pasti, tetapi menurut masyarakat di sekitarnya, kolam tersebut digunakan keluarga Kerajaan Majapahit untuk berekreasi dan menjamu tamu dari luar negeri. Kolam ini merupakan satu-satunya bangunan kolam kuno terbesar yang pernah ditemukan di Indonesia. Kolam yang luas keseluruhannya kurang lebih 6,5 hektar, membujur ke arah utara-selatan sepanjang 375 m dengan lebar 175 m. Sekeliling tepi kolam dilapisi dinding setebal 1,60 m dengan kedalaman 2,88 m","depth":0,"volume":0,"areaId":14,"areaName":"Trowulan","latitude":-7.5580556,"longitude":112.38277779999999,"distance":12473.016910435606,"distanceDescription":"12473.02 km","lastLevelAverage":146,"lastStatusAverage":"Siaga","photo":[{"photoId":47,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180810060716772.jpg"}],"sensor":[{"sensorId":1,"sensorCode":"SEN-3-0001","sensorName":"Sensor 1","lastLevel":122,"lastStatus":"Siaga"},{"sensorId":2,"sensorCode":"SEN-3-0002","sensorName":"Sensor 2","lastLevel":122,"lastStatus":"Siaga"},{"sensorId":3,"sensorCode":"SEN-3-0003","sensorName":"Sensor 3","lastLevel":194,"lastStatus":"Waspada"}],"creationTime":"2018-07-09T11:25:42.769622","modificationTime":"2018-08-10T06:07:16.315291"},{"id":6,"code":"BGS188XBZ2","name":"Kolam air visionet","address":"Visionet Blimbing","description":"kolam air paling jernih didunia","depth":3343221,"volume":233,"areaId":2,"areaName":"Bungursari","latitude":-8.698944053099034,"longitude":119.75631245624515,"distance":13273.835822412479,"distanceDescription":"13273.84 km","lastLevelAverage":0,"lastStatusAverage":"Not available","photo":[{"photoId":49,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180810061050757.jpg"}],"sensor":[{"sensorId":9,"sensorCode":"SSSSS","sensorName":"SENSOR II","lastLevel":0,"lastStatus":"dfs"},{"sensorId":7,"sensorCode":"SENSOR-1","sensorName":"sensor gerak","lastLevel":0,"lastStatus":"dfs"}],"creationTime":"2018-08-02T14:38:55.975136","modificationTime":"2018-09-04T14:57:34.549823"}]
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
             * id : 2
             * code : PLD187XI6C
             * name : Waduk Cirata
             * address : Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat
             * description : Pembangkit Listrik Tenaga Air (PLTA) Cirata merupakan PLTA terbesar di Asia Tenggara. PLTA ini memiliki konstruksi power house di bawah tanah dengan kapasitas 8x126 Megawatt (MW) sehingga total kapasitas terpasang 1.008 Megawatt (MW) dengan produksi energi listrik rata-rata 1.428 Giga Watthour (GWh) pertahun. PLTA Cirata terletak di daerah aliran sungai (DAS) Citarum di Desa Tegal Waru, Kecamatan Plered, Kabupaten Purwakarta, Jawa Barat. Sedangkaln luas Waduk Cirata, dari ujung selatan kecamatan Cipeundeuy kabupaten Bandung barat, dan terbendung di desa Ciroyom, kecamatan Cipeundeuy kabupaten Bandung barat, yang berbatasan langsung dengan maniis kabupaten Purwakarta. Latar belakang pendirian PLTA ini, dengan letak sungai Citarum yang subur, bergunung-gunung dan dianugerahi curah hujan yang tinggi. Pembangunan proyek PLTA Cirata merupakan salah satu cara pemanfaatan potensi tenaga air di Sungai Citarum yang letaknya di wilayah kabupaten Bandung, kurang lebih 60 km sebelah barat laut kota Bandung atau 100 km dari Jakarta melalui jalan Purwakarta
             * depth : 0
             * volume : 0
             * areaId : 5
             * areaName : Plered
             * latitude : -6.739620200000001
             * longitude : 107.28437259999998
             * distance : 11915.210048096058
             * distanceDescription : 11915.21 km
             * lastLevelAverage : 56
             * lastStatusAverage : Normal
             * photo : [{"photoId":1,"photo":"http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180730173400.jpg"}]
             * sensor : [{"sensorId":6,"sensorCode":"SEN-2-0003","sensorName":"Sensor 3","lastLevel":87,"lastStatus":"Normal"},{"sensorId":4,"sensorCode":"SEN-2-0001","sensorName":"Sensor 1","lastLevel":54,"lastStatus":"Normal"},{"sensorId":5,"sensorCode":"SEN-2-0002","sensorName":"Sensor 2","lastLevel":27,"lastStatus":"Normal"}]
             * creationTime : 2018-07-09T11:14:32.976312
             * modificationTime :
             */

            private int id;
            private String code;
            private String name;
            private String address;
            private String description;
            private double depth;
            private double volume;
            private int areaId;
            private String areaName;
            private double latitude;
            private double longitude;
            private double distance;
            private String distanceDescription;
            private double lastLevelAverage;
            private String lastStatusAverage;
            private String creationTime;
            private String modificationTime;
            private List<PhotoBean> photo;
            private List<SensorBean> sensor;

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

            public double getDepth() {
                return depth;
            }

            public void setDepth(double depth) {
                this.depth = depth;
            }

            public double getVolume() {
                return volume;
            }

            public void setVolume(double volume) {
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

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public String getDistanceDescription() {
                return distanceDescription;
            }

            public void setDistanceDescription(String distanceDescription) {
                this.distanceDescription = distanceDescription;
            }

            public double getLastLevelAverage() {
                return lastLevelAverage;
            }

            public void setLastLevelAverage(double lastLevelAverage) {
                this.lastLevelAverage = lastLevelAverage;
            }

            public String getLastStatusAverage() {
                return lastStatusAverage;
            }

            public void setLastStatusAverage(String lastStatusAverage) {
                this.lastStatusAverage = lastStatusAverage;
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

            public List<SensorBean> getSensor() {
                return sensor;
            }

            public void setSensor(List<SensorBean> sensor) {
                this.sensor = sensor;
            }

            public static class PhotoBean {
                /**
                 * photoId : 1
                 * photo : http://66.96.237.20:6017/Assets/Photo/ReservoirPhoto/RESERVOIR_20180730173400.jpg
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

            public static class SensorBean {
                /**
                 * sensorId : 6
                 * sensorCode : SEN-2-0003
                 * sensorName : Sensor 3
                 * lastLevel : 87
                 * lastStatus : Normal
                 */

                private int sensorId;
                private String sensorCode;
                private String sensorName;
                private int lastLevel;
                private String lastStatus;

                public int getSensorId() {
                    return sensorId;
                }

                public void setSensorId(int sensorId) {
                    this.sensorId = sensorId;
                }

                public String getSensorCode() {
                    return sensorCode;
                }

                public void setSensorCode(String sensorCode) {
                    this.sensorCode = sensorCode;
                }

                public String getSensorName() {
                    return sensorName;
                }

                public void setSensorName(String sensorName) {
                    this.sensorName = sensorName;
                }

                public int getLastLevel() {
                    return lastLevel;
                }

                public void setLastLevel(int lastLevel) {
                    this.lastLevel = lastLevel;
                }

                public String getLastStatus() {
                    return lastStatus;
                }

                public void setLastStatus(String lastStatus) {
                    this.lastStatus = lastStatus;
                }
            }
        }
    }
}
