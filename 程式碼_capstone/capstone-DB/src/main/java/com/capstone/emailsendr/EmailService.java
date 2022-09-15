package com.capstone.emailsendr;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.capstone.model.MessageHubModel;
import com.capstone.model.MessageModel;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;

	final private static String mailFrom = "ludougan123234@gmail.com";
	
	public void sendEmail(MessageHubModel messageModel) throws MessagingException, IOException{
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
        
        // 初始化context方便將email訊息主體傳至thymeleaf template engine做處理
        Context context = new Context();
        // context.setVariables(messageModel.getProps());
        context.setVariable("MessageBody", messageModel.getMessage());
        
        String html = templateEngine.process("message.html", context);
        
        // Create the plain TEXT body using Thymeleaf
        message.setTo(messageModel.getPat_email());
        message.setText(html, true);
        message.setSubject("國泰醫院通知 Notification from GuoTai Hospital");
        message.setFrom(mailFrom);
        
        // Send email
        this.mailSender.send(mimeMessage);
	}
}
