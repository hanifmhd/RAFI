package its.rafi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import its.rafi.model.Posko;
import its.rafi.model.ResponseApi;
import its.rafi.model.Summary;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ASUS on 6/9/2017.
 */


public interface API {
    //variable base URL
    public static String baseURL = "http://163.53.193.92/rafi/api/";
    //public static String baseURL = "http://192.168.43.92/nami/android/api/";
    //public static String baseURL = "http://10.65.2.16/nami/android/api/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    Retrofit client = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @FormUrlEncoded
    @POST("login.php")
    public Call<ResponseApi> login(@Field("user_id") String uid,
                                   @Field("passcode") String password,
                                   @Field("latitude") String latitude,
                                   @Field("longitude") String longitude
                                   );

    @GET("getUser.php")
    public Call<ResponseApi> getUser(@Query("user_id") String user_id);

    @GET("nearbylocation.php")
    public Call<List<Posko>> getNearbyLocation(@Query("latitude") String lat,
                                                @Query("longitude")String longi,
                                                @Query("distance")String distance);

    @GET("achievement.php")
    public Call<List<Summary>> getAchievement(@Query("user_id") String user_id);

    @GET("lastupdate.php")
    public Call<List<Summary>> getLastUpdate();

    @GET("notif.php")
    public Call<List<Posko>> getnotif(@Query("user_id") String user_id);

    @GET("detailachievement.php")
    public Call<List<Summary>> getDetail(@Query("user_id") String user_id,
                                         @Query("report") String report);

    @FormUrlEncoded
    @POST("user_data/insertndu.php")
    public Call<ResponseApi> insertUserData(
            @Field("poi_name") String poi_name,
            @Field("msisdn") String msisdn,
            @Field("user_id") String user_id,
            @Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("user_data/checkin.php")
    public Call<ResponseApi> checkin(@Field("location_name") String location_name,
                                     @Field("user_id") String user_id,
                                     @Field("user_name") String user_name,
                                     @Field("checkin_latitude") String checkin_latitude,
                                     @Field("checkin_longitude") String checkin_longitude
    );

    @FormUrlEncoded
    @POST("user_data/checkout.php")
    public Call<ResponseApi> checkout(@Field("id") String id,
                                      @Field("checkout_latitude") String checkout_latitude,
                                      @Field("checkout_longitude") String checkout_longitude
    );

}

