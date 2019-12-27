package net.themaven.sportscontrols.network;

import com.orhanobut.logger.Logger;

import net.themaven.sportscontrols.model.StatsApi;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class ApiClient {

    private static Retrofit retrofit = getRetrofitWithInterceptors();

    private static Retrofit getRetrofitWithInterceptors() {

        Retrofit retrofit = null;
        try {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://stats.api.si.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        } catch (Exception | OutOfMemoryError e) {
            Logger.e(e.getMessage());
        }
        return retrofit;
    }

    /////////////////////////////////SERVICES//////////////////////////////////////////

    public interface StatsService {
        @GET("{sportName}/events")
        Call<StatsApi> getEvents(@Path("sportName") String sportName,
                                 @Query("start_date") String startDate,
                                 @Query("end_date") String endDate);
    }

    /////////////////////////////////SERVICES//////////////////////////////////////////

    public static StatsService getStatsService() {
        return retrofit.create(StatsService.class);
    }
}
