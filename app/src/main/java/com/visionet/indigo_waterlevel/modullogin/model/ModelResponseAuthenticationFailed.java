package com.visionet.indigo_waterlevel.modullogin.model;

public class ModelResponseAuthenticationFailed {

	/**
	 * result : null
	 * targetUrl : null
	 * success : false
	 * error : {"code":0,"message":"Login failed!","details":"Invalid user name or password","validationErrors":null}
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
		 * message : Login failed!
		 * details : Invalid user name or password
		 * validationErrors : null
		 */

		private int code;
		private String message;
		private String details;
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

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
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