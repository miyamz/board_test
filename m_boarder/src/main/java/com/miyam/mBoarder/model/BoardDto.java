package com.miyam.mBoarder.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("board")
public class BoardDto {
	private long idx;
	private long parent_idx;
	private long writer_idx;
	private String title;
	private String body;
	private String password;
	private Date update_date;
	private String user_id;
	
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public long getParent_idx() {
		return parent_idx;
	}
	public void setParent_idx(long parent_idx) {
		this.parent_idx = parent_idx;
	}
	public long getWriter_idx() {
		return writer_idx;
	}
	public void setWriter_idx(long writer_idx) {
		this.writer_idx = writer_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getUserID() {
		return user_id;
	}
	
	public String getUpdateDateStr() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = transFormat.format(this.update_date);
		
		return dateStr;
	}
}
