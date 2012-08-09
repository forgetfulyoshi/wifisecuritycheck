package com.routerraiders.wifisecuritycheck;

public class SecurityType {

    static class Type {
	public static final int AES = 0;
	public static final int TKIP = 1;
	public static final int WEP = 2;
	public static final int OPEN = 3;
	public static final int WPA = 4;
	public static final int ERROR = 5;
	public static final int WPS = 6;
	public static final int WPA2 = 7;
    }

    static class Name {
	public static final String AES = "AES/CCMP";
	public static final String TKIP = "TKIP";
	public static final String WEP = "WEP";
	public static final String OPEN = "Open Network";
	public static final String WPA = "WPA";
	public static final String WPA2 = "WPA2";
	public static final String ERROR = "Scan Error";
	public static final String WPS = "WPS";
    }

    public int type;
    public String name;

    SecurityType(int type, String name) {
	this.type = type;
	this.name = name;
    }

}
