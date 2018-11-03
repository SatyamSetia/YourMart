package com.nagarro.yourmartapi.constants;

public class HqlQueries {
	
	private HqlQueries() {

	}

	public static final String SELECT_ADMIN_FROM_TABLE = "SELECT dbAdmin.adminId, dbAdmin.username FROM "+ Models.ADMIN_MODEL +" as dbAdmin WHERE dbAdmin.username= :username AND dbAdmin.password= :password";
	
	public static final String SELECT_SELLER_FROM_TABLE = "SELECT dbSeller.sellerId, dbSeller.username, dbSeller.status FROM "+ Models.SELLER_MODEL + " as dbSeller where dbSeller.username= :username AND dbSeller.password= :password";
	
	public static final String SELECT_SELLERS_FROM_TABLE = "FROM " + Models.SELLER_DETAILS_MODEL + " as dbSellerDetails";
	
	public static final String SELECT_SELLER_BY_ID_FROM_TABLE = "FROM " + Models.SELLER_DETAILS_MODEL + " as dbSellerDetails WHERE dbSellerDetails.seller.sellerId = :sellerId";

	public static final String SELECT_PRODUCTS_FROM_TABLE = "FROM " + Models.PRODUCT_MODEL + " as dbProduct";
	
	public static final String SELECT_PRODUCT_BY_ID_FROM_TABLE = "FROM "+Models.PRODUCT_MODEL +" as dbProduct WHERE dbProduct.productId = :productId";

	public static final String SELECT_PRODUCT_BY_SELLER_ID_FROM_TABLE = "FROM "+Models.PRODUCT_MODEL +" as dbProduct WHERE dbProduct.seller.sellerId = :sellerId";

}
