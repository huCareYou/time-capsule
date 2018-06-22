package com.sinova.timecapsule.services;

import com.alibaba.fastjson.JSON;
import com.sinova.timecapsule.entity.MailInfo;
import com.sinova.timecapsule.repository.MailInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description 邮件service
 * @Author hukui
 * @Date 2018/03/02 下午3:14
 */
@Service
public class MailInfoService {

    @Autowired
    private MailInfoRepository mailInfoRepository;

    private Logger log =  LoggerFactory.getLogger(this.getClass());

    @Transactional
    public MailInfo saveOrUpdate(MailInfo mailInfo){
        mailInfo = mailInfoRepository.saveAndFlush(mailInfo);
        log.info("保存或修改邮件信息："+ JSON.toJSONString(mailInfo));
        return mailInfo;

    }

    @Transactional
    public void deleteById(String id){
        if(StringUtils.isNotBlank(id)){
            mailInfoRepository.deleteById(Integer.valueOf(id));
            log.info("删除id为【"+id+"】的邮件信息");
        }else{
            log.info("删除操作，所传id为空。");
        }
    }

    public MailInfo findById(String id){
        MailInfo mailInfo = null;
        if(StringUtils.isNotBlank(id)){
            mailInfo = mailInfoRepository.getOne(Integer.valueOf(id));
        }
        log.info("根据id【"+id+"】查找到邮件信息："+JSON.toJSONString(mailInfo));
        return mailInfo;
    }

    public MailInfo findByVisitKey(String visitKey){
        MailInfo mailInfo = mailInfoRepository.findByVisitKey(visitKey);
        log.info("根据访问码【"+visitKey+"】查找到邮件信息："+JSON.toJSONString(mailInfo));
        return mailInfo;
    }

    public List<MailInfo> findWithinTime(long afterSeconds){
        Date date = new Date();
        Date afterTime = new Date(System.currentTimeMillis()+afterSeconds);
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        List<MailInfo> bySendTimeBetweenAnd = mailInfoRepository.findBySendTimeBetween(date, afterTime);
        log.info("查询发送时间在【"+dateFormat.format(date)+"】至【"+dateFormat.format(afterTime)+"】的邮件信息为："+JSON.toJSONString(bySendTimeBetweenAnd));
        return bySendTimeBetweenAnd;
    }
    


}

