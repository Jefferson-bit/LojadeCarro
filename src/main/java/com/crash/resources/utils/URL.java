package com.crash.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeItens(String s) {	
		String vect[] = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i<vect.length; i++) {
			list.add(Integer.parseInt(vect[i]));
		}
		return list;
		
//		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}
