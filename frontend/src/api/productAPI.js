import api from "./axios";

const productApi = {
  getAllProducts() {
    return api.get("/products");
  },

  getProductById(productId) {
    return api.get(`/products/${productId}`);
  },

  getProductsByShop(shopId) {
    return api.get(`/products/shop/${shopId}`);
  },

  createProduct(shopId, data) {
    return api.post(`/products/shop/${shopId}`, data);
  },

  updateProduct(productId, data) {
    return api.put(`/products/${productId}`, data);
  },

  deleteProduct(productId) {
    return api.delete(`/products/${productId}`);
  }
};

export default productApi;