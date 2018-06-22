package com.sinova.timecapsule.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 邮件信息
 * @Author hukui
 * @Date 2018/03/02 上午11:22
 */
@Entity
@Table(name = "mail_info")
public class MailInfo {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 邮件标题
     */
    @Column(length = 30)
    private String title;

    /**
     * 邮件正文
     */
    @Column(length = 1000)
    private String content;

    /**
     * 收件邮箱地址
     */
    @Column(length = 50)
    private String receivingMail;

    /**
     * 发送日期
     */
    @Column
    private Date sendTime;

    /**
     * 创建日期
     */
    @Column
    private Date createTime;

    /**
     * 访问秘钥
     */
    @Column(length = 32)
    private String visitKey;

    /**
     * 邮件发送状态
     * 0：未发送
     * 1：已发送
     */
    @Column(length = 2)
    private String status;

    public MailInfo() {
    }

    public MailInfo(String title, String content, String receivingMail, Date sendTime, Date createTime, String visitKey, String status) {
        this.title = title;
        this.content = content;
        this.receivingMail = receivingMail;
        this.sendTime = sendTime;
        this.createTime = createTime;
        this.visitKey = visitKey;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceivingMail() {
        return receivingMail;
    }

    public void setReceivingMail(String receivingMail) {
        this.receivingMail = receivingMail;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(String visitKey) {
        this.visitKey = visitKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



