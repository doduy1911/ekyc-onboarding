import React, { useState } from "react";
import Login from "../components/Auth/Login";
import Register from "../components/Auth/Register";
import "../components/Auth/Auth.css";

const AuthPage: React.FC = () => {
  const [isLogin, setIsLogin] = useState(true);

  return (
    <div className="auth-container">
      <div className="auth-box">
        {isLogin ? <Login /> : <Register />}
        <p onClick={() => setIsLogin(!isLogin)} className="switch">
          {isLogin ? "Chưa có tài khoản? Đăng ký" : "Đã có tài khoản? Đăng nhập"}
        </p>
      </div>
    </div>
  );
};

export default AuthPage;
