package com.visionet.indigo_waterlevel.modullogin.model;

public class ModelRequestAuthentication {

    /**
     * userNameOrEmailAddress : admin
     * password : Password1!
     * twoFactorVerificationCode : string
     * rememberClient : true
     * twoFactorRememberClientToken : string
     * singleSignIn : true
     * returnUrl : string
     */

    private String userNameOrEmailAddress;
    private String password;
    private String twoFactorVerificationCode;
    private boolean rememberClient;
    private String twoFactorRememberClientToken;
    private boolean singleSignIn;
    private String returnUrl;

    public ModelRequestAuthentication(String userNameOrEmailAddress, String password, String twoFactorVerificationCode, boolean rememberClient, String twoFactorRememberClientToken, boolean singleSignIn, String returnUrl) {
        this.userNameOrEmailAddress = userNameOrEmailAddress;
        this.password = password;
        this.twoFactorVerificationCode = twoFactorVerificationCode;
        this.rememberClient = rememberClient;
        this.twoFactorRememberClientToken = twoFactorRememberClientToken;
        this.singleSignIn = singleSignIn;
        this.returnUrl = returnUrl;
    }

    public String getUserNameOrEmailAddress() {
        return userNameOrEmailAddress;
    }

    public void setUserNameOrEmailAddress(String userNameOrEmailAddress) {
        this.userNameOrEmailAddress = userNameOrEmailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwoFactorVerificationCode() {
        return twoFactorVerificationCode;
    }

    public void setTwoFactorVerificationCode(String twoFactorVerificationCode) {
        this.twoFactorVerificationCode = twoFactorVerificationCode;
    }

    public boolean isRememberClient() {
        return rememberClient;
    }

    public void setRememberClient(boolean rememberClient) {
        this.rememberClient = rememberClient;
    }

    public String getTwoFactorRememberClientToken() {
        return twoFactorRememberClientToken;
    }

    public void setTwoFactorRememberClientToken(String twoFactorRememberClientToken) {
        this.twoFactorRememberClientToken = twoFactorRememberClientToken;
    }

    public boolean isSingleSignIn() {
        return singleSignIn;
    }

    public void setSingleSignIn(boolean singleSignIn) {
        this.singleSignIn = singleSignIn;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
