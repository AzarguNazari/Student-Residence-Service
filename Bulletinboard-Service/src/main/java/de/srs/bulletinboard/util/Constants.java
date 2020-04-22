package de.srs.bulletinboard.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	

	/*-----------------------------------------REQUEST ENDPOINTS------------------------------------------------*/
	public static final String Endpoint_Bulletinboard_Announcement_Template = "/v1/bulletinboard";
	
	public static final String Endpoint_Bulletinboard_Announcement_By_Id_Template = "/v1/bulletinboard/{id}";
	
	public static final String Endpoint_Bulletinboard_Reply_Template = "/v1/bulletinboard/{id}/reply";
	
	/*-----------------------------------------REQUEST METHODS------------------------------------------------*/
	
	public static final String Method_Get = "GET";
	public static final String Method_Post = "POST";
	
	/*-----------------------------------------USER ROLES------------------------------------------------*/
	
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String STUDENT = "ROLE_STUDENT";
	
	
	public static final Map<String, String> claimsToUserObjectMap = new HashMap<String, String>(){

	{
		put("first_name","firstName");
		put("last_name","lastName");
		put("id","id");
	}};
	
	public static final int PAGE_SIZE = 5;

}
