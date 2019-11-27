package online.vidacademica.repositories.network.vidacademica;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import online.vidacademica.helpers.LocalDateAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class VidAcademicaWSClient {

    private static final HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl url = request.url().newBuilder().build();
                            request = request.newBuilder().url(url).build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .callTimeout(60, TimeUnit.SECONDS);

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Retrofit getRetroInstance() {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return new Retrofit.Builder().baseUrl(VidAcademicaWSConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp.build()).build();
    }

    private static final Retrofit retrofit = getRetroInstance();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }

}
