package org.wenzhuo4657.webhook.enmu;

import org.wenzhuo4657.webhook.enmu.Constant;

/**
 * @author: wenzhuo4657
 * @date: 2025/3/31
 * @description:
 */

public enum GitHubHeaderConstant {

    RunTheWorkflow_Accept("Accept", "application/vnd.github+json", "客户端期望接收json参数"),
    RunTheWorkflow_Authorization("Authorization", Constant.getToken(), "令牌，用于校验");
;



    GitHubHeaderConstant(String header, String value, String des) {
        this.header = header;
        this.value = value;
        this.des = des;
    }

    private String header;
    private String value;
    private String des;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
