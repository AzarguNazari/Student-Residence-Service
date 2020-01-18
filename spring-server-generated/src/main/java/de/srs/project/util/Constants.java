package de.srs.project.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	public static final Map<String, String> claimsToUserObjectMap = new HashMap<String, String>(){

	{
		put("first_name","firstName");
		put("last_name","lastName");
		put("id","id");
	}};
	
	public static final int PAGE_SIZE = 5;

}
