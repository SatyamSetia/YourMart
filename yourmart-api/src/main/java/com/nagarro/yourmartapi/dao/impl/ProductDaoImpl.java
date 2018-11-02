package com.nagarro.yourmartapi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.HqlQueries;
import com.nagarro.yourmartapi.constants.ResponseCodesAndMessages;
import com.nagarro.yourmartapi.constants.Status;
import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.dto.ProductResp;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.models.SellerDetails;
import com.nagarro.yourmartapi.utils.HibernateUtils;

@Component
public class ProductDaoImpl implements ProductDao {
	
private Session session;
	
	public ProductDaoImpl() {
		this.session = HibernateUtils.createSession();
	}

	public void createNewProduct(ProductDetails productDetails) {
		
		Seller seller = new Seller();
		seller.setSellerId(productDetails.getSellertId());
		
		Product product = new Product();
		product.setSeller(seller);
		product.setComments(productDetails.getComments());
		product.setDimensions(productDetails.getDimensions());
		product.setLongDesc(productDetails.getLongDesc());
		product.setShortDesc(productDetails.getShortDesc());
		product.setMrp(productDetails.getMrp());
		product.setSsp(productDetails.getSsp());
		product.setYmp(productDetails.getYmp());
		product.setName(productDetails.getName());
		product.setPrimaryImage(productDetails.getPrimaryImage());
		product.setProdAttr(productDetails.getProdAttr());
		product.setStatus(Status.NEED_APPROVAL_STATUS);
		product.setSellerProdCode(productDetails.getSellerProdCode());
		product.setUsageInstructions(productDetails.getUsageInstructions());
		
		this.session.beginTransaction();
		
		session.save(product);
		
		this.session.getTransaction().commit();
			
	}

	public Response<List<ProductResp>> getAllProducts(List<String> sortBy, String status, String searchKeyword,
			String searchType) {
		
		String whereClause = "";
		String sortOrder = "";
		
		if(!Objects.isNull(status)) {
			whereClause = " WHERE dbProduct.status = '" + status + "'";
		}
		
		if(!Objects.isNull(sortBy)) {
			for(String column: sortBy) {
				sortOrder +=", dbProduct."+column;
			}
		}
		
		if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
			System.out.println("hii");
			sortOrder = "";
			whereClause = " WHERE dbSellerDetails."+searchType+" LIKE '"+searchKeyword+"'";
		}
		
		Query query = this.session.createQuery(HqlQueries.SELECT_PRODUCTS_FROM_TABLE + whereClause + sortOrder);
		
		List<Product> list = query.list();
		
		List<ProductResp> productList = new ArrayList<ProductResp>();
		Response<List<ProductResp>> response;
		
		for(Product product: list) {
			
			ProductResp prod = new ProductResp();
			
			prod.setName(product.getName());
			prod.setComments(product.getComments());
			prod.setDimensions(product.getDimensions());
			prod.setMrp(product.getMrp());
			prod.setProdAttr(product.getProdAttr());
			prod.setPrimaryImage(product.getPrimaryImage());
			prod.setLongDesc(product.getLongDesc());
			prod.setProductId(product.getProductId());
			prod.setSellerProdCode(product.getSellerProdCode());
			prod.setStatus(product.getStatus());
			prod.setShortDesc(product.getShortDesc());
			prod.setUsageInstructions(product.getUsageInstructions());
			prod.setYmp(product.getYmp());
			prod.setSsp(product.getSsp());
			prod.setSellerId(product.getSeller().getSellerId());
			
			productList.add(prod);
		}
		
		response = new Response<List<ProductResp>>(ResponseCodesAndMessages.SUCCESS, productList, null);
		
		return response;
	}

}
