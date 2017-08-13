package com.example.a111.ibsweather.retrofit.api;

import android.app.Activity;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WeatherRetrofitCallback<S> implements Callback {
    Activity activity;
    Context context;

    public WeatherRetrofitCallback(Activity activity) {
        this.activity = activity;
    }

    public WeatherRetrofitCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call call, Response response) {
        common();
        onResponseWeatherResponse(call, response);

        Object obj = response.body();
        if (obj != null) {
            S objectResponse = (S) obj;
            onResponseWeatherObject(call, objectResponse);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        common();
        //onFailureWeather(call, t);
    }


    protected abstract void onResponseWeatherResponse(Call call, Response response);

    /**
     * Invoked for a received HTTP response.
     * <p/>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     */
    protected abstract void onResponseWeatherObject(Call call, S response);


    protected abstract void common();
}
