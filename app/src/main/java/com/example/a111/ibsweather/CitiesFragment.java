package com.example.a111.ibsweather;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a111.simpleweather.Helper.ForecastCallback;
import com.example.a111.simpleweather.Helper.TempUnitConverter;
import com.example.a111.simpleweather.Helper.WeatherCallback;
import com.example.a111.simpleweather.retrofit.models.ForecastResponseModel;
import com.example.a111.simpleweather.retrofit.models.Weather;
import com.example.a111.simpleweather.retrofit.models.WeatherResponseModel;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CitiesFragment extends FragmentActivity {

    public final String APP_ID = BuildConfig.OWM_API_KEY;
    String city = "Cairo";

    @Bind(R.id.weather_title)
    TextView weatherTitle;
    @Bind(R.id.refresh)
    ImageButton refresh;
    @Bind(R.id.weather_icon)
    ImageView weatherIcon;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.condition)
    TextView condition;
    @Bind(R.id.temp)
    TextView temp;
    @Bind(R.id.tvHumidity)
    TextView tvHumidity;
    @Bind(R.id.tvPressure)
    TextView tvPressure;
    @Bind(R.id.tvWind)
    TextView tvWind;
    @Bind(R.id.tvWindDeg)
    TextView tvWindDeg;
    @Bind(R.id.et_city)
    EditText etCity;
    @Bind(R.id.tv_go)
    TextView tvGo;
    @Bind(R.id.textLayout)
    LinearLayout textLayout;
    @Bind(R.id.humidity_desc)
    TextView humidityDesc;
    @Bind(R.id.pres_desc)
    TextView presDesc;
    @Bind(R.id.ws_desc)
    TextView wsDesc;
    @Bind(R.id.wd_desc)
    TextView wdDesc;
    @Bind(R.id.ll_extraWeather)
    LinearLayout llExtraWeather;
    @Bind(R.id.weatherCard)
    CardView weatherCard;

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        ButterKnife.bind(this);
        loadWeather(city);
    }

    @OnClick(R.id.refresh)
    public void refresh() {
        loadWeather(city);
    }

    private void loadWeather(String city) {
        WeatherMap weatherMap = new WeatherMap(this, APP_ID);
        weatherMap.getCityWeather(city, new WeatherCallback() {
            @Override
            public void success(WeatherResponseModel response) {
                populateWeather(response);
            }

            @Override
            public void failure(String message) {

            }
        });

        weatherMap.getCityForecast(city, new ForecastCallback() {
            @Override
            public void success(ForecastResponseModel response) {
                ForecastResponseModel responseModel = response;
            }

            @Override
            public void failure(String message) {

            }
        });
    }

    private void populateWeather(WeatherResponseModel response) {

        Weather weather[] = response.getWeather();
        condition.setText(weather[0].getMain());
        temp.setText(TempUnitConverter.convertToCelsius(response.getMain().getTemp()).intValue() + " °C");
        location.setText(response.getName());

        tvHumidity.setText(response.getMain().getHumidity() + "%");
        tvPressure.setText(response.getMain().getPressure() + " hPa");
        tvWind.setText(response.getWind().getSpeed() + "m/s");
        tvWindDeg.setText(response.getWind().getDeg() + "°");

        String link = weather[0].getIconLink();
        Picasso.with(this).load(link).into(weatherIcon);
    }

    @OnClick(R.id.tv_go)
    public void go() {
        city = etCity.getText().toString().trim();
        loadWeather(city);
    }
}