package com.test.pos;

public class ThirdPartyConsoleDevice implements Device {

	@Override
	public void write(String data) {
		System.out.println(data);
	}

}
