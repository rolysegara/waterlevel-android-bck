package com.visionet.indigo_waterlevel.modullogin.model;

public class ModelResponseAuthentication {

	/**
	 * result : {"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ijg5IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImtsZW50aW5nIiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiI5ZTI5MWE0ZC0xNDhkLTQ1MGItYWUyMC01NmI3MjI1N2YxZmIiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiI1YzBjNDkyYjNlZTI0M2RmYTY2ZTRjYjdhNWM5YzM1OCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI4OSIsImp0aSI6Ijc2NTQ1ZGUzLTNkYmEtNDk5OS05OGU0LTU1MTVjZGM2ZTU1OSIsImlhdCI6MTUzOTk0MTQ1NSwibmJmIjoxNTM5OTQxNDU1LCJleHAiOjE1NDAwMjc4NTUsImlzcyI6IkRlbW8iLCJhdWQiOiJEZW1vIn0.1JpQwehiXdHkZvmU3c9dxEIcfDJInpVActuy3C_E1jA","encryptedAccessToken":"wNYmO41/48SHNstaLVXxHCCre29BZQl1NhC6NM3R3rzpXtPQxVzH6jEzA/QhXFN5tu6Fk7pO53uppm1mVXMZgxbyRVz26dnepi/FyB6axBY+6gq1GL+uRQgoiFUCjRN2p8w6LevViwKlHyWZZJZO1DGVSjAi1m2U+og9pkHw9/Qw84tFX9DWrjU3E9WR6/skXaVyDdKlXGmRIpCZg/mrlIr92Lk12hsMsSE7xn6XFvQLjxdIY+Gk2i/Ff+aBjK+J6PR3KG90GhFmlm2kt1caZrY8TzubGQnwOOXlJKTfUaJuySNuulCRmdvNmagmKyYZXcdbFOTphzbdzL6Ma9vtJFfZBMjo7xh6FDINMY5hC7R0ER5xqJHWCgofJAO0oEayWD82niNvIVbO1Wcg5rqNZ9oBy3Hp0GWZ/0DFSntjoanH+af9Hgh3xUZIqwfC9X2HqXrQ/Wshvyxe2BeNRHaPf4cnhcY+vmIxgoEjSO1/LPy5HERFh0zHqGPU8Mpw13qNg/VaQBVZDJVCFystHF6up4VLzfeMEwPvUFA05d0EcwrITTwcma37JgGzmwdgi/mgVPp/HjMnWUDg6HWc3oNvjpKb9P5j656DY9bTGQBzNi20pKuher7f/ybC5hmRuKSn3A6DJlYrRz9ds6NRhJIaBKUtn2EzgPFzpzxdOlT+6tdLrwCPX93XLaedQarwyyGNsyjtV8g6hN/4t1ZmipkC8fXJkorlpfIQvVO3vHIpKQLFPySGE2Z9u76YwwI+B7KfEQnBuHcHEpJ/nO6e+I4sECNU/GB9Xg6dUYQqwabrUEnH9RPPSlFabs5gZbfZEZMR3KDikD/XQWvgnU7+x0lXE5zqu5FlqVi7VFGs64rsPD/hYYouJWSQhOqWnQ39jQRZHbYuLhfxMDpz/QMXJVRDSJJSV37dwxug561DTqX6fM0dCzsykO89iLt4YhteM8RnMm2kFMX+xFwJKng/mXZdpj7CQl30WZ/VAESWcz2pJz8mcoUdXNfaIhHUgbUe4Uby1dmPblh/ciwECUh0VHb8RQ==","expireInSeconds":86400,"shouldResetPassword":false,"passwordResetCode":null,"userId":89,"requiresTwoFactorVerification":false,"twoFactorAuthProviders":null,"twoFactorRememberClientToken":null,"returnUrl":"string?accessToken=f6d31596-27cd-4125-8592-360158a3bdcf&userId=ODk=&tenantId=MQ=="}
	 * targetUrl : null
	 * success : true
	 * error : {"code":0,"message":"Login failed!","details":"Invalid user name or password","validationErrors":null}
	 * unAuthorizedRequest : false
	 * __abp : true
	 */

	private ResultBean result;
	private Object targetUrl;
	private boolean success;
	private ErrorBean error;
	private boolean unAuthorizedRequest;
	private boolean __abp;

	public ResultBean getResult() {
		return result;
	}

	public void setResult(ResultBean result) {
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

	public static class ResultBean {
		/**
		 * accessToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ijg5IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImtsZW50aW5nIiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiI5ZTI5MWE0ZC0xNDhkLTQ1MGItYWUyMC01NmI3MjI1N2YxZmIiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiI1YzBjNDkyYjNlZTI0M2RmYTY2ZTRjYjdhNWM5YzM1OCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI4OSIsImp0aSI6Ijc2NTQ1ZGUzLTNkYmEtNDk5OS05OGU0LTU1MTVjZGM2ZTU1OSIsImlhdCI6MTUzOTk0MTQ1NSwibmJmIjoxNTM5OTQxNDU1LCJleHAiOjE1NDAwMjc4NTUsImlzcyI6IkRlbW8iLCJhdWQiOiJEZW1vIn0.1JpQwehiXdHkZvmU3c9dxEIcfDJInpVActuy3C_E1jA
		 * encryptedAccessToken : wNYmO41/48SHNstaLVXxHCCre29BZQl1NhC6NM3R3rzpXtPQxVzH6jEzA/QhXFN5tu6Fk7pO53uppm1mVXMZgxbyRVz26dnepi/FyB6axBY+6gq1GL+uRQgoiFUCjRN2p8w6LevViwKlHyWZZJZO1DGVSjAi1m2U+og9pkHw9/Qw84tFX9DWrjU3E9WR6/skXaVyDdKlXGmRIpCZg/mrlIr92Lk12hsMsSE7xn6XFvQLjxdIY+Gk2i/Ff+aBjK+J6PR3KG90GhFmlm2kt1caZrY8TzubGQnwOOXlJKTfUaJuySNuulCRmdvNmagmKyYZXcdbFOTphzbdzL6Ma9vtJFfZBMjo7xh6FDINMY5hC7R0ER5xqJHWCgofJAO0oEayWD82niNvIVbO1Wcg5rqNZ9oBy3Hp0GWZ/0DFSntjoanH+af9Hgh3xUZIqwfC9X2HqXrQ/Wshvyxe2BeNRHaPf4cnhcY+vmIxgoEjSO1/LPy5HERFh0zHqGPU8Mpw13qNg/VaQBVZDJVCFystHF6up4VLzfeMEwPvUFA05d0EcwrITTwcma37JgGzmwdgi/mgVPp/HjMnWUDg6HWc3oNvjpKb9P5j656DY9bTGQBzNi20pKuher7f/ybC5hmRuKSn3A6DJlYrRz9ds6NRhJIaBKUtn2EzgPFzpzxdOlT+6tdLrwCPX93XLaedQarwyyGNsyjtV8g6hN/4t1ZmipkC8fXJkorlpfIQvVO3vHIpKQLFPySGE2Z9u76YwwI+B7KfEQnBuHcHEpJ/nO6e+I4sECNU/GB9Xg6dUYQqwabrUEnH9RPPSlFabs5gZbfZEZMR3KDikD/XQWvgnU7+x0lXE5zqu5FlqVi7VFGs64rsPD/hYYouJWSQhOqWnQ39jQRZHbYuLhfxMDpz/QMXJVRDSJJSV37dwxug561DTqX6fM0dCzsykO89iLt4YhteM8RnMm2kFMX+xFwJKng/mXZdpj7CQl30WZ/VAESWcz2pJz8mcoUdXNfaIhHUgbUe4Uby1dmPblh/ciwECUh0VHb8RQ==
		 * expireInSeconds : 86400
		 * shouldResetPassword : false
		 * passwordResetCode : null
		 * userId : 89
		 * requiresTwoFactorVerification : false
		 * twoFactorAuthProviders : null
		 * twoFactorRememberClientToken : null
		 * returnUrl : string?accessToken=f6d31596-27cd-4125-8592-360158a3bdcf&userId=ODk=&tenantId=MQ==
		 */

		private String accessToken;
		private String encryptedAccessToken;
		private int expireInSeconds;
		private boolean shouldResetPassword;
		private Object passwordResetCode;
		private int userId;
		private boolean requiresTwoFactorVerification;
		private Object twoFactorAuthProviders;
		private Object twoFactorRememberClientToken;
		private String returnUrl;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getEncryptedAccessToken() {
			return encryptedAccessToken;
		}

		public void setEncryptedAccessToken(String encryptedAccessToken) {
			this.encryptedAccessToken = encryptedAccessToken;
		}

		public int getExpireInSeconds() {
			return expireInSeconds;
		}

		public void setExpireInSeconds(int expireInSeconds) {
			this.expireInSeconds = expireInSeconds;
		}

		public boolean isShouldResetPassword() {
			return shouldResetPassword;
		}

		public void setShouldResetPassword(boolean shouldResetPassword) {
			this.shouldResetPassword = shouldResetPassword;
		}

		public Object getPasswordResetCode() {
			return passwordResetCode;
		}

		public void setPasswordResetCode(Object passwordResetCode) {
			this.passwordResetCode = passwordResetCode;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public boolean isRequiresTwoFactorVerification() {
			return requiresTwoFactorVerification;
		}

		public void setRequiresTwoFactorVerification(boolean requiresTwoFactorVerification) {
			this.requiresTwoFactorVerification = requiresTwoFactorVerification;
		}

		public Object getTwoFactorAuthProviders() {
			return twoFactorAuthProviders;
		}

		public void setTwoFactorAuthProviders(Object twoFactorAuthProviders) {
			this.twoFactorAuthProviders = twoFactorAuthProviders;
		}

		public Object getTwoFactorRememberClientToken() {
			return twoFactorRememberClientToken;
		}

		public void setTwoFactorRememberClientToken(Object twoFactorRememberClientToken) {
			this.twoFactorRememberClientToken = twoFactorRememberClientToken;
		}

		public String getReturnUrl() {
			return returnUrl;
		}

		public void setReturnUrl(String returnUrl) {
			this.returnUrl = returnUrl;
		}
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