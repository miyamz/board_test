package com.miyam.mBoarder.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@Alias("board_user")
public class BoardUserDto {
	private long idx;
	private String user_id;
	private String password;
	private String name;
	private int grade;
	private Date update_date;
	
	private boolean useCookie;
	
	public boolean isUseCookie() {
		return useCookie;
	}
	
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Override
	public String toString() {
		// toString을 json형태로 내보낸다
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String retJson = objectMapper.writeValueAsString(this);
			
			return retJson;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
