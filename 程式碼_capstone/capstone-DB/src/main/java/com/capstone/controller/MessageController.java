package com.capstone.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.emailsendr.EmailService;
import com.capstone.model.MessageHubModel;
import com.capstone.model.MessageModel;
import com.capstone.repository.MessageHubRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Controller
public class MessageController {
	@Autowired
	MessageModel messageModel;
	
	@Autowired 
	EmailService emailservice;
	
	@Autowired
	MessageHubRepository messageHubRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// 資料庫中的sendstatus一律都是大寫的 Y/N，這邊先定義
	private final static String SENDSTATUS_YES = "Y";
	private final static String SENDSTATUS_NO = "N";
	public String messageBody;
	
	// 定義收件者email，為符合demo需求這邊先硬編碼一個隨機email
	private String userEmail = "randy19981223@gmail.com";
	
    @GetMapping(value = "/send")
    @HystrixCommand(fallbackMethod = "EmailFallback",
	commandProperties = {@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100")})
    public void sendemail(String userEmail, String messageBody) throws IOException, MessagingException {
    	MessageHubModel messageModel = new MessageHubModel();
    	
    	messageModel.setPat_email(userEmail);
    	messageModel.setMessage(messageBody);
    	
    	try {
			emailservice.sendEmail(messageModel);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    @GetMapping(path="/all")
    //@HystrixCommand(fallbackMethod = "dbAllFallback",
   	//commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public @ResponseBody Iterable<MessageHubModel> getAllMessages() {
      // This returns a JSON or XML with the users
    	return messageHubRepository.findAll();
    }
    
    @GetMapping(path="/phone")
    @Scheduled(fixedRate = 20000)
//    @HystrixCommand(fallbackMethod = "dbPhoneFallback",
//   	commandProperties = {@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100"),
//   			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")})
    public @ResponseBody Iterable<MessageHubModel> getAllMatchingPhone() {
      // This returns a JSON or XML with the users
    	String query_phoneNum = "0960616768"; 
    	// TODO For each 迴圈
    	List<MessageHubModel> query = messageHubRepository.findByPhone(query_phoneNum);
    	return messageHubRepository.findByPhone(query_phoneNum);
    }
    
    @GetMapping(path = "/demo")
    @Scheduled(fixedRate = 300000)
//    @HystrixCommand(fallbackMethod = "demoFallback",
//	commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
    public void getAllMatchingDate() throws IOException, MessagingException {
        // 這行定義要查詢的日期（有好幾筆資料都是這個日期的）
      	String query_phone = "0939194736"; 
      	
      	// 拿List來放query的結果
      	List<MessageHubModel> query = messageHubRepository.findByPhone(query_phone);
      	for(int i = 0; i <= query.size(); i++) {
      		try {
      			addNewMsg(query.get(i).getMessage_id(),query.get(i).getPatNo(),query.get(i).getPatName(),
      	      			query.get(i).getUser_pref(),query.get(i).getSendSystemType(),query.get(i).getPat_email(),
      	      			query.get(i).getCreateDate(),query.get(i).getCreateTime(),query.get(i).getMessage(),
      	      			"Y",query.get(i).getPhone_num());
      			System.out.println(query.get(i).getMessage() + "\n" + query.get(i).getPhone_num());
      			sendemail(query.get(i).getPat_email(), query.get(i).getMessage());
      		} catch(IndexOutOfBoundsException e) {
      			break;
      		}
      	}
      	// return messageHubRepository.findByDate(query_date);
      }
    
    @GetMapping(path = "/fallback")
    @HystrixCommand(fallbackMethod = "demoFallback",
   	commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public String fallbackDemo() throws InterruptedException {
    	Thread.sleep(3000);
    	return "string"; 
    }
    
    
//    @HystrixCommand(fallbackMethod = "dbAddFallback",
//   	commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public @ResponseBody String addNewMsg (@RequestParam String message_id,@RequestParam String PatNo,
    		@RequestParam String patName,@RequestParam String sendSystemType,@RequestParam String user_pref,
    		@RequestParam String pat_email,@RequestParam String createDate,@RequestParam String CreateTime,
    		@RequestParam String message , @RequestParam String receive_status,@RequestParam String phone_num) {
    	jdbcTemplate.update("UPDATE hello SET sendstatus = ? WHERE sendstatus = 'N' LIMIT 1","Y");
    	jdbcTemplate.update("INSERT INTO goodbye(MSGSEQNO,CHARTNO,CHARTNAME,SYSTEMNAME,SENDTYPE,MAIL,CREATEDATE,"
        		+ "CREATETIME,MSGTEXT,TELCOMM, SENDSTATUS, SHIPPINGDATE, SHIPPINGTIME) "
        		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW(), NOW())", 
        		message_id,PatNo,patName,sendSystemType,user_pref,pat_email,createDate,CreateTime,
        		message, phone_num, 
        		receive_status);
      
        return "saved";
     }
    
    
    public void EmailFallback(String userEmail, String messageBody) {
    	System.out.println("Email service encountered an error");
    }
    
    public String dbAllFallback() {
    	return "Microservice Encountered an error. Please try to restart the service";
    }
    
    public String demoFallback() throws IOException, MessagingException {
    	return "service_down";
    }
//    @GetMapping(path = "/timing")
//    @Scheduled(fixedRate = 20000)
//    public void timing() {
//    	Date date = new Date();
//    	long now = date.getTime();
//    	System.out.println("the time is: " + now);
//    }
    
    
    // TODO Job scheduling: https://www.baeldung.com/spring-scheduled-tasks
    // TODO Thymeleaf 對接message text
    // TODO Implement other micro-service functions
    // TODO Implement chron job 
    // TODO SQL時間格式（有時間再做）
}
