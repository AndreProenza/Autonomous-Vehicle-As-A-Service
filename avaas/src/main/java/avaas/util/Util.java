package avaas.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	
	public static List<String> StringToList(String s) {
    	String replace = s.replace("[","");
    	String replace1 = replace.replace("]","");
    	List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
    	return myList;
    }
}
