package com.capstone.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.capstone.model.MessageHubModel;
import com.capstone.model.MessageModel;

public interface MessageHubRepository extends CrudRepository<MessageHubModel, Integer>{
	
	/*
	Query寫法：
	===
	@Query(parameter:JPA query -> String)
	List<Object[]> find___(@Param("String") String variable)
	===
	*/

	//因為目前都是以String從資料庫收資料 後端要再做parsing
	@Query("SELECT c FROM MessageHubModel c WHERE (c.phone_num = :phone_num)")
	List<MessageHubModel> findByPhone(@Param("phone_num") String phone_num);

	// TODO find message by date
	@Query("SELECT d From MessageHubModel d WHERE (d.createDate = :date)")
	List<MessageHubModel> findByDate(@Param("date") String date);
	
	//@Query("SELECT t From MessageHubModel t WHERE (t.time = :time)")
	//List<MessageHubModel> findByTime(@Param("time") String time);
	
	// TODO find message by send status
	@Query("SELECT c From MessageHubModel c WHERE (c.receive_status = :receive_status)")
	List<MessageHubModel> findByStatus(@Param("receive_status") String receive_status);
	
	//@Query("SELECT c FROM MessageHubModel c WHERE c.phone_num = :phone_num and c.sendTime = :sendTime")
	//List<MessageHubModel> findByPhoneAndTime(@Param("phone_num") String phone_num, @Param("sendTime") String sendTime);
	
	//@Query("UPDATE MessageHubModel c SET c.receive_status = :receive_status WHERE c.receive_status = :not_yet_received")
	//List
}
