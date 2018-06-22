package com.sinova.timecapsule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeCapsuleApplicationTests {

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void contextLoads() {

	}

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("hu_care_you@163.com");
		message.setTo("592992388@qq.com");
		message.setSubject("主题：测试端口");
		message.setText("测试465端口");

		javaMailSender.send(message);
	}


}
