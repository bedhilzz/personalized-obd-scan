package id.ac.ui.ft.personalizedobdscan.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://automobile-dev.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
}
