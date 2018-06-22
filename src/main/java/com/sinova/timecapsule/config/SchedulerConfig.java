package com.sinova.timecapsule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 定时器的配置
 * @Author hukui
 * @Date 2018/03/07 下午3:34
 */
@Component
@ConfigurationProperties(prefix="scheduler") //接收application.yml中的myProps下面的属性
public class SchedulerConfig {

    private String cronExpression;

    private String afterSceonds;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getAfterSceonds() {
        return afterSceonds;
    }

    public void setAfterSceonds(String afterSceonds) {
        this.afterSceonds = afterSceonds;
    }
}
