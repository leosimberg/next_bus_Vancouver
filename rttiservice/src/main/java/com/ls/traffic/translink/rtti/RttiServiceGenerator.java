/**
 * Created by Leonardo Simberg on 4/11/2016.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ls.traffic.translink.rtti;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * Creates the RttiService with the RTTI API endpoints
 */
public class RttiServiceGenerator {
    private static final String API_URL = "http://api.translink.ca/";

    /**
     * Creates a new RttiService to acess the RTTI API endpoints;
     *
     * @param apiKey User api Key provided by Translink.*
     * @return RttiService object used to call the RTTI api
     */
    public static RttiService createService(final String apiKey) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url().newBuilder()
                                .addEncodedQueryParameter("apikey", apiKey)
                                .build();

                        Request request = original.newBuilder()
                                .header("Accept", "application/JSON")
                                .method(original.method(), original.body())
                                .url(httpUrl)
                                .build();
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(RttiService.class);
    }
}
