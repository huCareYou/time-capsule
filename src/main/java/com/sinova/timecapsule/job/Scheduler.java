package com.sinova.timecapsule.job;

import com.alibaba.fastjson.JSON;
import com.sinova.timecapsule.config.SchedulerConfig;
import com.sinova.timecapsule.entity.MailInfo;
import com.sinova.timecapsule.services.MailInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 定时器
 * @Author hukui
 * @Date 2018/03/07 上午11:26
 */
@Component
public class Scheduler {

    private Logger log =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SchedulerConfig schedulerConfig;

    @Autowired
    private MailInfoService mailInfoService;

    @Autowired
    private JavaMailSender javaMailSender;


    @Scheduled(cron = "${scheduler.cronExpression}")
    public void sendMail(){
        Long afterSceonds = Long.valueOf(schedulerConfig.getAfterSceonds());
        List<MailInfo> mailInfoList = mailInfoService.findWithinTime(afterSceonds);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hu_care_you@163.com");

        for (MailInfo mailInfo : mailInfoList) {
            message.setTo(mailInfo.getReceivingMail());
            message.setSubject(mailInfo.getTitle());
            message.setText(mailInfo.getContent());
            try{
                javaMailSender.send(message);
                log.info("邮件信息"+ JSON .toJSONString(mailInfo)+"发送成功。");
                mailInfo.setStatus("1");
                mailInfoService.saveOrUpdate(mailInfo);
            } catch (Exception e){
                log.info("邮件信息"+ JSON .toJSONString(mailInfo)+"发送失败",e);
            }
        }

    }

}
