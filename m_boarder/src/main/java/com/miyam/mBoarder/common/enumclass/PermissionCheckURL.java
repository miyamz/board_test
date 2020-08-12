package com.miyam.mBoarder.common.enumclass;

import java.util.ArrayList;
import java.util.List;

public enum PermissionCheckURL {
	LOGIN_URL("login.do", "logout.do"),
	REGIST_URL("regist.do"),
	ERROR_URL(),
	VIEWPAGE_URL("index.do", "pwCheckPopup.do", "boardList.do", "boardWrite.do", "boardView.do", "boardDelete.do"),
	RESOURCE_PATH("img", "css", "js", "scss", "vendor"),
	SOCIAL_URL("oauth2", "social");
	
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
