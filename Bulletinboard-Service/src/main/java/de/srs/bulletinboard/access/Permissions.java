package de.srs.bulletinboard.access;
import org.springframework.web.util.UriTemplate;

import de.srs.bulletinboard.model.Pair;
import de.srs.bulletinboard.util.Constants;


public class Permissions extends Constants {
	
	final protected Pair ADMIN_PERMISSIONS = 	


			new Pair(ADMIN,
					new Pair[]{
							new Pair(new UriTemplate(Endpoint_Bulletinboard_Announcement_Template),
									new Pair[]{
											new Pair(Method_Get,true),
											new Pair(Method_Post,true)
									}
							),
							new Pair(new UriTemplate(Endpoint_Bulletinboard_Announcement_By_Id_Template),
									new Pair[]{
											new Pair(Method_Get,true)
									}
							),
							new Pair(new UriTemplate(Endpoint_Bulletinboard_Reply_Template),
									new Pair[]{
											new Pair(Method_Get,true),
											new Pair(Method_Post,true)
									}
							)
							
					}
			);
	
	
	final protected Pair STUDENT_PERMISSIONS = 
		
		new Pair(STUDENT,
				new Pair[]{
						new Pair(new UriTemplate(Endpoint_Bulletinboard_Announcement_Template),
								new Pair[]{
										new Pair(Method_Get,true),
										new Pair(Method_Post,false)
								}
						),
						new Pair(new UriTemplate(Endpoint_Bulletinboard_Announcement_By_Id_Template),
								new Pair[]{
										new Pair(Method_Get,true)
								}
						),
						new Pair(new UriTemplate(Endpoint_Bulletinboard_Reply_Template),
								new Pair[]{
										new Pair(Method_Get,true),
										new Pair(Method_Post,true)
								}
						)
						
				}
		);
	
	
}

