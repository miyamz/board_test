package com.miyam.mBoarder.common.enumclass;

import java.util.ArrayList;
import java.util.List;

public enum PermissionCheckURL {
	LOGIN_URL("login.do", "logout.do"),
	REGIST_URL("regist.do", "modifyPass.do", "resetRequest.do"),
	ERROR_URL(),
	VIEWPAGE_URL("index.do");
	
	List<String> urls;
	
	private PermissionCheckURL(String... urls) {
		List<String> ret = new ArrayList<String>();
		for (String url : urls) {
			ret.add(url);
		}
		this.urls = ret;
	}
	
	public List<String> GetCheckUrlList() {
		return urls;
	}
}
