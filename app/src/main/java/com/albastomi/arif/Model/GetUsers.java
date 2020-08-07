package com.albastomi.arif.Model;

import com.google.gson.annotations.SerializedName;



public class GetUsers {

    @SerializedName("status_code")
    String status_code;

    @SerializedName("message")
    String message;

    public String getStatusCode() {
        return status_code;
    }
    public void setStatusCode(String status_code) {
        this.status_code = status_code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
