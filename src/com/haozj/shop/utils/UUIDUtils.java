package com.haozj.shop.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getCode(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
