package com.nagarro.yourmartapi.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constants.Status;
import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.ProductDetails;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.models.Seller;
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

}
