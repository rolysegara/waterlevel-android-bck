package com.visionet.indigo_waterlevel.modullogin.model;

public class ModelResponseUserProfile {

	/**
	 * result : {"name":"klenting","surname":"klenting","userName":"klenting","emailAddress":"klentingr@gmail.com","phoneNumber":"","isPhoneNumberConfirmed":false,"timezone":"","qrCodeSetupImageUrl":"","isGoogleAuthenticatorEnabled":false}
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
		 * name : klenting
		 * surname : klenting
		 * userName : klenting
		 * emailAddress : klentingr@gmail.com
		 * phoneNumber :
		 * isPhoneNumberConfirmed : false
		 * timezone :
		 * qrCodeSetupImageUrl :
		 * isGoogleAuthenticatorEnabled : false
		 */

		private String name;
		private String surname;
		private String userName;
		private String emailAddress;
		private String phoneNumber;
		private boolean isPhoneNumberConfirmed;
		private String timezone;
		private String qrCodeSetupImageUrl;
		private boolean isGoogleAuthenticatorEnabled;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public boolean isIsPhoneNumberConfirmed() {
			return isPhoneNumberConfirmed;
		}

		public void setIsPhoneNumberConfirmed(boolean isPhoneNumberConfirmed) {
			this.isPhoneNumberConfirmed = isPhoneNumberConfirmed;
		}

		public String getTimezone() {
			return timezone;
		}

		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}

		public String getQrCodeSetupImageUrl() {
			return qrCodeSetupImageUrl;
		}

		public void setQrCodeSetupImageUrl(String qrCodeSetupImageUrl) {
			this.qrCodeSetupImageUrl = qrCodeSetupImageUrl;
		}

		public boolean isIsGoogleAuthenticatorEnabled() {
			return isGoogleAuthenticatorEnabled;
		}

		public void setIsGoogleAuthenticatorEnabled(boolean isGoogleAuthenticatorEnabled) {
			this.isGoogleAuthenticatorEnabled = isGoogleAuthenticatorEnabled;
		}
	}
}