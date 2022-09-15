package com.capstone.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capstone.model.MessageHubOutModel;

@Repository
public class MessageHubOutRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public void addMessage(MessageHubOutModel messagehuboutmodel) {	
		//Update structure: INSERT INTO table(columns) VALUES(vals)
		jdbcTemplate.update("INSERT INTO goodbye(COL1,"
				+ "MESSAGEQNO, SENDSTATUS, SENDTYPE, TELCOMM"
				+ "SYSTEMNAME, CHARTNAME, CHARTNO, MSGTEXT, MAIL,"
				+ "CREATEDATE, CREATETIME, SHIPPINGDATE, SHIPPINGTIME) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(), NOW())", 
				messagehuboutmodel.getMessage_type(), 
				messagehuboutmodel.getMessage_id(),
				messagehuboutmodel.getReceive_status(),
				messagehuboutmodel.getUser_pref(),
				messagehuboutmodel.getPhone_num(),
				messagehuboutmodel.getSendSystemType(),
				messagehuboutmodel.getPatName(),
				messagehuboutmodel.getPatNo(),
				messagehuboutmodel.getMessage(),
				messagehuboutmodel.getPat_email(),
				messagehuboutmodel.getCreateDate(),
				messagehuboutmodel.getCreateTime());
	}
}
