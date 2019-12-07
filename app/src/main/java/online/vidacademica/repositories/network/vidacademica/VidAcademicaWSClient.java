package online.vidacademica.repositories.network.vidacademica;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
        return new Retrofit.Builder().baseUrl(VidAcademicaWSConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build()).build();
    }

    private static final Retrofit retrofit = getRetroInstance();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}