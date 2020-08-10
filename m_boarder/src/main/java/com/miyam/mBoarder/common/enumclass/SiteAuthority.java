package com.miyam.mBoarder.common.enumclass;

public enum SiteAuthority {
	MANAGER(10),
	GUEST(90),
	ALL(100);
	
	int value;
	
	private SiteAuthority(int value) {
		this.value = value;
	}
	
	public int getAuthValue() {
		return value;
	}
	
	public static SiteAuthority valueOf(int value) {
		for (SiteAuthority auth : SiteAuthority.values()) {
			if (auth.value == value) {
				return auth;
			}
		}
		
		return null;
	}
}
