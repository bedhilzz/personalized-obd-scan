package id.ac.ui.ft.personalizedobdscan.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://automobile-dev.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
}
