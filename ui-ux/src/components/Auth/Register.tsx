import React, { useState } from "react";
import Input from "../Input";

const Register: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (password !== confirm) {
      alert("Mật khẩu không khớp");
      return;
    }
    console.log("Register:", { email, password });
  };

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      <h2>Đăng ký</h2>
      <Input label="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <Input label="Mật khẩu" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <Input label="Nhập lại mật khẩu" type="password" value={confirm} onChange={(e) => setConfirm(e.target.value)} />
      <button type="submit">Đăng ký</button>
    </form>
  );
};

export default Register;
