package com.devil.restapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("https://reqres.in/api/users?page=2/")
    Call<Model> getAllData();

}
