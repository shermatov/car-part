const API_BASE_URL = "http://localhost:8080";

if (!API_BASE_URL) {
  console.error("VITE_API_URL is not defined. Check your .env file.");
}

export { API_BASE_URL };
