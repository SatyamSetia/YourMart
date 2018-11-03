export interface LoginSignupResponse {
  status: number,
  payload: {
    id: number,
    username: String,
    token: String
  },
  error: String
}
