package de.srs.appliance.access;
import org.springframework.web.util.UriTemplate;

import de.srs.appliance.model.Pair;
import de.srs.appliance.util.Constants;


public class Permissions extends Constants {
	
//	final protected Pair ADMIN_PERMISSIONS = 	
//		new Pair(ADMIN,
//				new Pair[]{
//						new Pair(new UriTemplate(Endpoint_Bulletinboard_Get_id_Template),
//								new Pair[]{
//										new Pair(Method_Get,true),
//										new Pair(Method_Put,true)
//								}
//						)
//						
//				}
//		);
	
	
	final protected Pair STUDENT_PERMISSIONS = 
		
		new Pair(STUDENT,
				new Pair[]{
						new Pair(new UriTemplate(Endpoint_Appliance_Template),
								new Pair[]{
										new Pair(Method_Get,true),
										new Pair(Method_Post,false)
								}
						),
						new Pair(new UriTemplate(Endpoint_Appliance_By_Id_Template),
								new Pair[]{
										new Pair(Method_Get,true),
										new Pair(Method_Put,false),
										new Pair(Method_Delete,false)
								}
						),
						new Pair(new UriTemplate(Endpoint_Appliance_Rent_Template),
								new Pair[]{
										new Pair(Method_Get,true)
										
								}
						),
						new Pair(new UriTemplate(Endpoint_Appliance_Id_Rent_Template),
								new Pair[]{
										new Pair(Method_Get,false),
										new Pair(Method_Post,false)
										
								}
						),
						new Pair(new UriTemplate(Endpoint_Appliance_Id_Rent_Id_Template),
								new Pair[]{
										new Pair(Method_Get,true),
										new Pair(Method_Put,true)
										
										
								}
						)
						
				}
		);
	
	
}

