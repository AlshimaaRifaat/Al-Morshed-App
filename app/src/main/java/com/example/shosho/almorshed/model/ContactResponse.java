package com.example.shosho.almorshed.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactResponse {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}