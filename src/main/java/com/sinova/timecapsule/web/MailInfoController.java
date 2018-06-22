package com.sinova.timecapsule.web;

import com.alibaba.fastjson.JSON;
import com.sinova.timecapsule.entity.MailInfo;
import com.sinova.timecapsule.services.MailInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description 邮件controller
 * @Author hukui
 * @Date 2018/03/02 下午4:07
 */
@Controller
public class MailInfoController {


    private Logger log =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailInfoService mailInfoService;

    @PostMapping("/saveMailInfo")
    @ResponseBody
    public String save(MailInfo mailInfo){
        mailInfo.setCreateTime(new Date());
        String visitKey = UUID.randomUUID().toString().replace("-","");
        mailInfo.setVisitKey(visitKey);
        mailInfo.setStatus("0");
        mailInfo = mailInfoService.saveOrUpdate(mailInfo);
        String sendTime = new SimpleDateFormat("YYYY年MM月dd日 HH时mm分ss秒").format(mailInfo.getSendTime());
        return JSON.toJSONString("邮件保存成功，将会在：【"+ sendTime+"】发送，您这封邮件的访问码为：【"+visitKey+"】。可通过该访问码随时查看。");
    }

    @PostMapping("/findMailInfo")
    @ResponseBody
    public String find(String visitKey){
        MailInfo mailInfo = mailInfoService.findByVisitKey(visitKey);
        return JSON.toJSONString(mailInfo);
    }


   /* @GetMapping("/testFind")
    @ResponseBody
    public String find(){
        List<MailInfo> list = mailInfoService.findWithinTime(600000L);
        return JSON.toJSONString(list);
    }*/

    @GetMapping("/")
    public String index(){
        log.info("--------跳转首页--------");
        return "/index";
    }

    @GetMapping("/saveUI")
    public String saveUI(){
        log.info("--------跳转新建邮件页面--------");
        return "/saveMailInfo";
    }

    @GetMapping("/findUI")
    public String findUI(){
        log.info("--------跳转查看邮件页面--------");
        return "/findMailInfo";
    }
}
