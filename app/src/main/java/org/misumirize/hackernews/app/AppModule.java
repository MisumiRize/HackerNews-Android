package org.misumirize.hackernews.app;

import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;

@Module
public class AppModule {

    @Provides
    OkHttpClient provideHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    Client provideRetrofitClient(OkHttpClient httpClient) {
        return new OkClient(httpClient);
    }

    @Provides
    HackerNewsService provideHackerNewsService(Client client) {
        return new HackerNewsService(client);
    }
}
