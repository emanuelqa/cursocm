package com.emanuel.cursocm.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	
	public static List<Integer> converterStingForIdsInteger(String string){
		return Arrays.asList(string.split(",")).stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList());
	}

}
