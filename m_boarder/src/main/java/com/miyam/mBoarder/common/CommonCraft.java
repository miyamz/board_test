package com.miyam.mBoarder.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonCraft {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static CommonCraft instance = new CommonCraft();
	
	public static CommonCraft getInstance() {
		return instance;
	}
	
	public <T> T getSessionObj(String sessionValue, Class<T> getClass) {
		T sessionObj = null;
		try {
			ObjectMapper objMapper = new ObjectMapper();
			sessionObj = objMapper.readValue(sessionValue, getClass);
			
		} catch (Exception ex) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("%s \n", ex.getMessage()));
			sb.append(String.format("%s \n", ex.getStackTrace().toString()));
			logger.error(sb.toString());
		}

		return sessionObj;
	}
}
