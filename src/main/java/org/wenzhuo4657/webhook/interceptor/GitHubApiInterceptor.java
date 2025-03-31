package org.wenzhuo4657.webhook.interceptor;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.wenzhuo4657.webhook.enmu.GitHubHeaderConstant;

import java.io.IOException;

/**
 * @author: wenzhuo4657
 * @date: 2025/3/31
 * @description: okhttp的拦截器：对okhttp所发出的请求做拦截，进行统一处理
 */

public class GitHubApiInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request  raw = chain.request();
        Request newRquest = raw.newBuilder()
                //传递原始请求的rul、请求方法、请求体
                .url(raw.url())
                .method(raw.method(), raw.body())
//               添加验证参数
                .addHeader(GitHubHeaderConstant.RunTheWorkflow_Accept.getHeader(), GitHubHeaderConstant.RunTheWorkflow_Accept.getValue())
                .addHeader(GitHubHeaderConstant.RunTheWorkflow_Authorization.getHeader(), GitHubHeaderConstant.RunTheWorkflow_Authorization.getValue())
                .build();

        return chain.proceed(newRquest);
    }
}
