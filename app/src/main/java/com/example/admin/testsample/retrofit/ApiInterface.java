package com.example.admin.testsample.retrofit;


import com.example.admin.testsample.CategoriesModel;
import com.example.admin.testsample.nottification.NottificationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /* @FormUrlEncoded
     @POST("main/forgot-password")
     Call<LoginResponse> ForgotPasscode(@Field("username") String username);


     @GET("vehicle")
     Call<VechicleListResponse> VehicleList();

     @GET("service-categories")
     Call<CatagoriesListresponse> CatagoriesList(@Query("expand") String services);*/
    //http://dev.bteem.com/ICH/Menu_api/menu?category_id=15
    @GET("Category_api/category")
    Call<CategoriesModel> CategoriesList();

    @GET("notification")
    Call<NottificationResponse> NottificationList(@Query("page") String acces_token, @Query("page") int page);


/*
    @FormUrlEncoded
    @POST("Registration_api/user_logout")
    Call<SignupResponse> Logout(@Field("customer_id") String customer_id, @Field("email") String email, @Field("device_token") String device);

*/


}
