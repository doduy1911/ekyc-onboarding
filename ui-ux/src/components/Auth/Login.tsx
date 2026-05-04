import React, { useState } from "react";
import Input from "../Input";

const Login: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Login:", { email, password });
  };

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      <h2>Đăng nhập</h2>
      <Input label="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <Input label="Mật khẩu" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button type="submit">Đăng nhập</button>
    </form>
  );
};

export default Login;
