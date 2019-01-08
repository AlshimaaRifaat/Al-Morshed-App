package com.example.shosho.almorshed.api;

import com.example.shosho.almorshed.model.ContactResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {
    @POST("api/contact")
    Call<ContactResponse> getContactData(@Body Map<String,String> map);
}
