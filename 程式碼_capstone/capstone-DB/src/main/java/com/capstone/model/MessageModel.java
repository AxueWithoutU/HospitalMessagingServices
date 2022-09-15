package com.capstone.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
public class MessageModel {
	
	// private String id;
	// receive_date, receive_time, send_date, send_time
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer message_id;  //MSGSEQNO
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	private boolean receive_status; //SENDSTATUS
	private String user_pref; //SENDTYPE
	private String phone_num; //TELCOMM
	private String email;
	private String sendSystemType; //SYSTEMNAME
	private String patName; //CHARTNAME
	private String patNo; //CHARTNO
	private String message_type; //COLUMN_1
	private String message; //MSGTEXT
	
	@Column(name = "MAIL")
	private String pat_email; //MAIL
	private String createDate;
	private String CreateTime;
	private String ShippingDate;
	private String ShippingTime; 
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getShippingDate() {
		return ShippingDate;
	}

	public void setShippingDate(String shippingDate) {
		ShippingDate = shippingDate;
	}

	public String getShippingTime() {
		return ShippingTime;
	}

	public void setShippingTime(String shippingTime) {
		ShippingTime = shippingTime;
	}
	
	@ElementCollection
	private Map<String, Object> props;

	public MessageModel() {}
		
	public String getPat_email() {
			return pat_email;
		}
		public void setPat_email(String pat_email) {
			this.pat_email = pat_email;
		}
	public boolean isReceive_status() {
		return receive_status;
	}
	public void setReceive_status(boolean receive_status) {
		this.receive_status = receive_status;
	}
	public String getUser_pref() {
		return user_pref;
	}
	public void setUser_pref(String user_pref) {
		this.user_pref = user_pref;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatNo() {
		return patNo;
	}
	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getProps() {
		return props;
	}
	public void setProps(Map<String, Object> props) {
		this.props = props;
	}
	
	public String getSendSystemType() {
		return sendSystemType;
	}

	public void setSendSystemType(String sendSystemType) {
		this.sendSystemType = sendSystemType;
	}
}
