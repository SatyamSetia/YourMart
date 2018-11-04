export interface Product {
  comments: String,
  createdAt: String,
  dimensions: String,
  gallery: Array<String>,
  longDesc: String,
  mrp: number,
  name: number,
  primaryImage: String,
  prodAttr: String,
  productId: number,
  sellerId: number,
  sellerProdCode: String,
  shortDesc: String,
  ssp: number,
  status: String,
  updatedAt: String,
  usageInstructions: String,
  ymp: number
}

export interface ProductResponse {
  status: number,
  payload: Product,
  error: String
}

export interface ProductListResponse {
  status: number,
  payload: [Product],
  error: String
}
