import api from "./axios";

const shopApi = {
  getAllShops() {
    return api.get("/shops");
  },

  getMyShop() {
    return api.get("/shops/my");
  },

  getShopById(shopId) {
    return api.get(`/shops/${shopId}`);
  },

  createShop(data) {
    return api.post("/shops", data);
  },

  updateShop(shopId, data) {
    return api.put(`/shops/${shopId}`, data);
  },

  deleteShop(shopId) {
    return api.delete(`/shops/${shopId}`);
  }
};

export default shopApi;