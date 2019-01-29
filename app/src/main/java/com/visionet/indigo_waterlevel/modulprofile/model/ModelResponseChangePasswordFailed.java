package com.visionet.indigo_waterlevel.modulprofile.model;

public class ModelResponseChangePasswordFailed {

    /**
     * result : null
     * targetUrl :
     * success : false
     * error : {"code":0,"message":"Incorrect password.","details":null,"validationErrors":null}
     * unAuthorizedRequest : false
     * __abp : true
     */

    private Object result;
    private String targetUrl;
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
         * message : Incorrect password.
         * details : null
         * validationErrors : null
         */

        private int code;
        private String message;
        private Object details;
        private Object validationErrors;

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

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }

        public Object getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(Object validationErrors) {
            this.validationErrors = validationErrors;
        }
    }
}
