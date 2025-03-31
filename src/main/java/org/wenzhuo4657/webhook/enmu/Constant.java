package org.wenzhuo4657.webhook.enmu;

/**
 * @author: wenzhuo4657
 * @date: 2025/3/29
 * @description:
 */

public class Constant {

    public    static String  secret;

    public     static String token;

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Constant.secret = secret;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Constant.token = token;
    }
}
