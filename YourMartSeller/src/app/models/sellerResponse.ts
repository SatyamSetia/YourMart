export interface Seller {
  address: String,
  company: String,
  email: String,
  gstNumber: String,
  ownerName: String,
  sellerId: number,
  status: String,
  telephone: String,
  username: String
}

export interface SellerResponse {
  status: number,
  payload: Seller,
  error: String
}
