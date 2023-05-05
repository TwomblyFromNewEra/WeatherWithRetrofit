package ah.hathi.weatherv4;
import ah.hathi.
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "41e9d5063e0f92cba9e17f3f32f092b4";

    private static WeatherClient instance;
    private Retrofit retrofit;

    private WeatherClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WeatherClient getInstance() {
        if (instance == null) {
            instance = new WeatherClient();
        }
        return instance;
    }

    public void getWeather(String cityName, Callback<WeatherResponse> callback) {
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getWeather(cityName, API_KEY);
        call.enqueue(callback);
    }
}
