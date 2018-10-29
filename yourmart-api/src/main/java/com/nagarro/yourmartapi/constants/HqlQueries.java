package com.nagarro.yourmartapi.constants;

public class HqlQueries {
	
	private HqlQueries() {

	}

	public static final String SELECT_ADMIN_FROM_TABLE = "SELECT dbAdmin.adminId, dbAdmin.username FROM "+ Models.ADMIN_MODEL +" as dbAdmin WHERE dbAdmin.username= :username AND dbAdmin.password= :password";
	
	public static final String SELECT_SELLER_FROM_TABLE = "SELECT dbSeller.sellerId, dbSeller.username, dbSeller.status FROM "+ Models.SELLER_MODEL + " as dbSeller where dbSeller.username= :username AND dbSeller.password= :password";
	
}
