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
import com.nagarro.yourmartapi.dto.ProductStatus;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.SellerResp;
import com.nagarro.yourmartapi.models.Gallery;
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

	/**
	 * @see com.nagarro.yourmartapi.dao.ProductDao#createNewProduct(com.nagarro.yourmartapi.dto.ProductDetails)
	 * @description Creating new product
	 */
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
		
		for(String image: productDetails.getGallery()) {
			Gallery gallery = new Gallery();
			gallery.setImage(image);
			gallery.setProduct(product);
			session.save(gallery);
		}
		
		this.session.getTransaction().commit();
			
	}

	/** 
	 * @description Fetch all products
	 */
	public Response<List<ProductResp>> getAllProducts(String sortBy, List<String> status, String searchKeyword,
			String searchType) {
		
		String whereClause = "";
		String sortOrder = "";
		
		if(!Objects.isNull(status)) {
			whereClause = " WHERE dbProduct.status IN (";
			
			int count=0;
			for(String eachStatus: status) {
				System.out.println(eachStatus);
				if(count!=0) {
					whereClause += ", ";
				}
				whereClause += "'"+eachStatus+"'";
				count++;
			}
			
			whereClause += ")";
		}
		
		if(!Objects.isNull(sortBy)) {
			sortOrder = " ORDER BY dbProduct."+sortBy;
		}
		
		if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
			sortOrder = "";
			whereClause = " WHERE dbProduct."+searchType+" LIKE '"+searchKeyword+"'";
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
			prod.setGallery(getAllImages(product.getProductId()));
			
			productList.add(prod);
		}
		
		response = new Response<List<ProductResp>>(ResponseCodesAndMessages.SUCCESS, productList, null);
		
		return response;
	}

	public Response<ProductResp> getProductById(Integer productId) {
		
		Query query = this.session.createQuery(HqlQueries.SELECT_PRODUCT_BY_ID_FROM_TABLE);
		query.setParameter("productId", productId);
		
		List<Product> productList = new ArrayList<Product>();
		
		try {
			productList = query.list();
		} catch(Exception exp) {
			Response<ProductResp> response = new Response<>(ResponseCodesAndMessages.UNAUTHORIZED,null,exp.getMessage());
			return response;
		}
		
		if(productList.isEmpty()) {
			Response<ProductResp> response = new Response<>(ResponseCodesAndMessages.NOT_FOUND,null,"Product does not exists.");
			return response;
		}
		
		Product product = productList.get(0);
		
		ProductResp productResponse = new ProductResp();
		
		productResponse.setComments(product.getComments());
		productResponse.setDimensions(product.getDimensions());
		productResponse.setLongDesc(product.getLongDesc());
		productResponse.setMrp(product.getMrp());
		productResponse.setName(product.getName());
		productResponse.setPrimaryImage(product.getPrimaryImage());
		productResponse.setProdAttr(product.getProdAttr());
		productResponse.setProductId(product.getProductId());
		productResponse.setSellerId(product.getSeller().getSellerId());
		productResponse.setSellerProdCode(product.getSellerProdCode());
		productResponse.setShortDesc(product.getShortDesc());
		productResponse.setSsp(product.getSsp());
		productResponse.setStatus(product.getStatus());
		productResponse.setUsageInstructions(product.getUsageInstructions());
		productResponse.setYmp(product.getYmp());
		productResponse.setGallery(getAllImages(product.getProductId()));
		
		Response<ProductResp> response = new Response<ProductResp>(200,productResponse,null);
		
		return response;
	}

	public Response<List<ProductResp>> getAllProductsOfSeller(Integer sellerId, String sortBy, List<String> status, Integer offset) {
		
		String whereClause = "";
		String sortOrder = "";
		
		if(!Objects.isNull(status)) {
			whereClause = " AND dbProduct.status IN (";
			
			int count=0;
			for(String eachStatus: status) {
				System.out.println(eachStatus);
				if(count!=0) {
					whereClause += ", ";
				}
				whereClause += "'"+eachStatus+"'";
				count++;
			}
			
			whereClause += ")";
		}
		
		if(!Objects.isNull(sortBy)) {
			sortOrder = " ORDER BY dbProduct."+sortBy;
		}
		
		Query query = this.session.createQuery(HqlQueries.SELECT_PRODUCT_BY_SELLER_ID_FROM_TABLE + whereClause + sortOrder);
		query.setParameter("sellerId", sellerId);
		if(!Objects.isNull(offset)) {
			query.setFirstResult(offset);
			query.setMaxResults(2);
		}
		
		List<Product> productList = new ArrayList<Product>();
		
		try {
			productList = query.list();
		} catch(Exception exp) {
			Response<List<ProductResp>> response = new Response<>(ResponseCodesAndMessages.UNAUTHORIZED,null,exp.getMessage());
			return response;
		}
		
		if(productList.isEmpty()) {
			Response<List<ProductResp>> response = new Response<>(ResponseCodesAndMessages.NOT_FOUND,null,"Product list empty");
			return response;
		}
		
		List<ProductResp> list = new ArrayList<ProductResp>();
		
		Response<List<ProductResp>> response;
		
		for(Product product: productList) {
			
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
			prod.setGallery(getAllImages(product.getProductId()));
			
			list.add(prod);
		}
		
		response = new Response<List<ProductResp>>(ResponseCodesAndMessages.SUCCESS, list, null);
		
		return response;
	}
	
	public List<String> getAllImages(Integer productId) {
		
		Query query = this.session.createQuery(HqlQueries.SELECT_IMAGES_FROM_GALLERY);
		query.setParameter("productId", productId);
		
		List<String> images = query.list();
		
		return images;
	}

	public void updateProduct(Integer productId, ProductDetails productDetails) {
		
		this.session.beginTransaction();
		
		Product product = (Product) this.session.load(Product.class, productId);
		
		product.setComments("");
		product.setDimensions(productDetails.getDimensions());
		product.setLongDesc(productDetails.getLongDesc());
		product.setMrp(productDetails.getMrp());
		product.setName(productDetails.getName());
		product.setPrimaryImage(product.getPrimaryImage());
		product.setProdAttr(productDetails.getProdAttr());
		product.setSellerProdCode(productDetails.getSellerProdCode());
		product.setSsp(productDetails.getSsp());
		product.setYmp(productDetails.getYmp());
		product.setShortDesc(productDetails.getShortDesc());
		product.setStatus("REVIEW");
		
		this.session.getTransaction().commit();
		
	}

	public Response<Product> updateProductStatus(Integer productId, ProductStatus productStatus) {
		
		if(productStatus.getStatus().equals("REJECTED") && productStatus.getComment().equals("")) {
			return new Response<Product>(401, null,"Comment can not be empty");
		}
		this.session.beginTransaction();
		
		Product product = (Product) this.session.load(Product.class, productId);
		
		product.setComments(productStatus.getComment());
		product.setStatus(productStatus.getStatus());
		try {
			this.session.getTransaction().commit();
		} catch(Exception exp) {
			return new Response<Product>(500, null, exp.getMessage());
		}
		Response<Product> response = new Response<>(200, product, null);
		return response;
	}

}
