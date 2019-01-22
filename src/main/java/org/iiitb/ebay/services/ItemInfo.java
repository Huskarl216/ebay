package org.iiitb.ebay.services;

import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Iteminfo")
public class ItemInfo {

	
	//API to return the Information of item given an Id
	
	@POST
	@Path("/getItemInfo")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getItemInfo(String id)
	{
		String info="Hello World!";
		
		//Search for item in the database using id.
		
		return info;
	}
}
