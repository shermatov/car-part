import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ResetPassword from "./pages/ResetPassword";
import ForgotPassword from "./pages/ForgotPassword";
import ShopPage from "./pages/ShopPage";
import ProductPage from "./pages/ProductPage";
import AdminUserPage from "./pages/AdminUserPage";

import ProtectedRoute from "./routes/ProtectedRoute";
import AuthLayout from "./components/layout/AuthLayout";
import HomeLayout from "./components/layout/HomeLayout";
import HomePage from "./pages/HomePage.jsx";


function App() {
  return (
    <BrowserRouter> 
      <Routes>

          <Route path="/" element={
              <AuthLayout>
                <HomePage />
              </AuthLayout>
          }
          />
          <Route path="/shops/my" element={<ShopPage />} />
          <Route path="/products/shop/shopId" element={<ShopPage />} />
        <Route path="/" element={<Navigate to="/login" replace />} />

        <Route
          path="/login"
          element={
            <AuthLayout>
              <LoginPage />
            </AuthLayout>
          }
        />
        <Route
          path="/register"
          element={
            <AuthLayout>
              <RegisterPage />
            </AuthLayout>
          }
        />

        <Route
           path="/resetpassword"
           element={
             <AuthLayout>
               <ResetPassword />
             </AuthLayout>
           }
        />

        <Route
            path="/forgot-password"
            element={
                <AuthLayout>
                    <ForgotPassword />
                </AuthLayout>
            }
        />

        {/* HOME */}
        <Route
          path="/shops"
          element={
            <ProtectedRoute>
              <HomeLayout>
                <ShopPage />
              </HomeLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/products/shop/:shopId"
          element={
            <ProtectedRoute>
              <HomeLayout>
                <ProductPage />
              </HomeLayout>
            </ProtectedRoute>
          }
        />

        {/* Admin User Page */}
        <Route
          path="/users"
          element={
            <ProtectedRoute requireRole="ADMIN">
              <HomeLayout>
                <AdminUserPage />
              </HomeLayout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
