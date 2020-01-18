package de.srs.appliance.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	

	/*-----------------------------------------REQUEST ENDPOINTS------------------------------------------------*/
	public static final String Endpoint_Appliance_Template = "/v1/appliances";
	
	public static final String Endpoint_Appliance_By_Id_Template = "/v1/appliances/{appliance-id}";
	
	public static final String Endpoint_Appliance_Rent_Template = "/v1/appliances/rent";
	
	public static final String Endpoint_Appliance_Id_Rent_Template = "/v1/appliances/{appliance-id}/rent";
	
	public static final String Endpoint_Appliance_Id_Rent_Id_Template = "/v1/appliances/{appliance-id}/rent/{rent-id}";
	
	/*-----------------------------------------REQUEST METHODS------------------------------------------------*/
	
	public static final String Method_Get = "GET";
	public static final String Method_Put = "PUT";
	public static final String Method_Post = "POST";
	public static final String Method_Delete = "DELETE";
	
	/*-----------------------------------------USER ROLES------------------------------------------------*/
	
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String STUDENT = "ROLE_STUDENT";
	
	
	public static final Map<String, String> claimsToUserObjectMap = new HashMap<String, String>(){

	/**
		 * 
		 */
		private static final long serialVersionUID = -2306637273251490988L;

	{
		put("first_name","firstName");
		put("last_name","lastName");
		put("id","id");
	}};
	
	public static final int PAGE_SIZE = 2;

}
