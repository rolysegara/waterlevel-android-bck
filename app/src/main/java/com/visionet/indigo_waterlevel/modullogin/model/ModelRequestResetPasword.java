package com.visionet.indigo_waterlevel.modullogin.model;

public class ModelRequestResetPasword {

    /**
     * emailAddress : klentingr@gmail.com
     */

    private String emailAddress;

    public ModelRequestResetPasword(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
