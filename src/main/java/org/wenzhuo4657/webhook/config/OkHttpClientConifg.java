package org.wenzhuo4657.webhook.config;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wenzhuo4657.webhook.interceptor.GitHubApiInterceptor;

/**
 * @author: wenzhuo4657
 * @date: 2025/3/31
 * @description:
 */
@Configuration
public class OkHttpClientConifg {


//    可复用的客户端，通过命令区分

    @Bean
    public OkHttpClient GitHubClient(){
           return new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(new GitHubApiInterceptor())
                    .build();
    }

    @Bean
    public RequestBody emptyBody(){
        String json = "{\"ref\": \"main\", \"inputs\": {}}";
        MediaType JSON = MediaType.parse("application/json");

       return RequestBody.create(json, JSON);
    }


}
