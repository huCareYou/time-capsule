package com.sinova.timecapsule.repository;

import com.sinova.timecapsule.entity.MailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MailInfoRepository extends JpaRepository<MailInfo, Integer>{

    MailInfo findByVisitKey(String visitKey);

    /**
     * 查询 邮件发送时间在指定时间区间内的邮件
     * @param now   当前时间
     * @param afterTime
     * @return
     */
    @Query("SELECT mailInfo from MailInfo  mailInfo WHERE send_time BETWEEN ?1 and ?2 ")
    List<MailInfo> findBySendTimeBetween(Date now, Date afterTime);
}
