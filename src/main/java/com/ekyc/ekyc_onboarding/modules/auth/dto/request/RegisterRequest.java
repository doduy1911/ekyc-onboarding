package com.ekyc.ekyc_onboarding.modules.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username không được để trống")
    @Size(min = 5, max = 50, message = "Username phải từ 6 -> 50 kí tự ")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username chỉ chứa chữ, số, dấu gạch dưới")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email Không hợp lệ ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống ")
    @Size(min = 8, message = "Mật khẩu tối thiếu 8 kí tự ")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).+$", message = "Mật khẩu phải có chữ hoa , số và ký tự đặt biệt ")
    private String password;

    @NotBlank(message = "Họ Tên Không được để trống ")
    @Size(max = 100)
    private String fullName;

    @Pattern(regexp = "^(\\+84|0)[0-9]{9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
}
