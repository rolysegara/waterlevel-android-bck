package com.visionet.indigo_waterlevel.modulmap.model;

import java.util.List;

public class ModelResponseGetAllReservoirsLevelFailed {
    /**
     * result : null
     * targetUrl : null
     * success : false
     * error : {"code":0,"message":"Your request is not valid!","details":"The following errors were detected during validation.\r\n - The value 'as' is not valid for MaxResultCount.\r\n","validationErrors":[{"message":"The value 'as' is not valid for MaxResultCount.","members":["maxResultCount"]}]}
     * unAuthorizedRequest : false
     * __abp : true
     */

    private Object result;
    private Object targetUrl;
    private boolean success;
    private ErrorBean error;
    private boolean unAuthorizedRequest;
    private boolean __abp;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

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

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
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

    public static class ErrorBean {
        /**
         * code : 0
         * message : Your request is not valid!
         * details : The following errors were detected during validation.
         - The value 'as' is not valid for MaxResultCount.

         * validationErrors : [{"message":"The value 'as' is not valid for MaxResultCount.","members":["maxResultCount"]}]
         */

        private int code;
        private String message;
        private String details;
        private List<ValidationErrorsBean> validationErrors;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public List<ValidationErrorsBean> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(List<ValidationErrorsBean> validationErrors) {
            this.validationErrors = validationErrors;
        }

        public static class ValidationErrorsBean {
            /**
             * message : The value 'as' is not valid for MaxResultCount.
             * members : ["maxResultCount"]
             */

            private String message;
            private List<String> members;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public List<String> getMembers() {
                return members;
            }

            public void setMembers(List<String> members) {
                this.members = members;
            }
        }
    }
}
