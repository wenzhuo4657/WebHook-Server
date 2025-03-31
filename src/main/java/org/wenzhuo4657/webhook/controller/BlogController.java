package org.wenzhuo4657.webhook.controller;

import am.ik.webhook.HmacWebhookSigner;
import am.ik.webhook.WebhookHttpHeaders;
import am.ik.webhook.WebhookSigner;
import am.ik.webhook.WebhookVerifier;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenzhuo4657.webhook.enmu.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author: wenzhuo4657
 * @date: 2025/3/29
 * @description:
 */
@RestController
@RequestMapping("/api")
public class BlogController {



    Logger log= LoggerFactory.getLogger(BlogController.class);
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private OkHttpClient okHttpClient;
    @Resource
    private  RequestBody emptyBody;


    @PostMapping(value = "/deploy",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void second(HttpServletRequest request, String body, HttpServletResponse response){
        String header = request.getHeader("X-GitHub-Event");
        if (!header.equals("ping")){
            verify(request,body);
        }
        response.setStatus(200);
//        职责划分，webhook只要求通知到服务端，所以此处应当添加线程池任务执行发送url.
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .method(HttpMethod.POST.name(),emptyBody)
                        .url("https://api.github.com/repos/wenzhuo4657/wenzhuo4657.github.io/actions/workflows/static.yml/dispatches")
                        .build();
                try {
                    Response execute = okHttpClient.newCall(request).execute();
                    log.info("请求运行工作流响应结果 code={} ,body={}",execute.code(),execute.body());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void verify(HttpServletRequest request, String body) {
        if (!StringUtils.hasLength(body)){
            return;
        }
//        1,生成验证器
        HmacWebhookSigner webhookSigner = new HmacWebhookSigner("SHA256", Constant.secret);
        WebhookVerifier verifier = new WebhookVerifier(webhookSigner, WebhookSigner.Encoder.HEX);

//        2,获取请求表头内容
        String signature = request.getHeader(WebhookHttpHeaders.X_HUB_SIGNATURE_256);

//        3，验证密钥
        verifier.verify(body, signature);
    }


}
