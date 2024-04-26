package com.example.blog.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "이메일 형태로 입력해주세요.")
	private String email;
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickName;
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 6, message = "6자리 이상의 비밀번호를 입력해주세요")
	private String password;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	private String confirmPassword;
	private String salt;
	private String blockYn;
	private String loginCnt;
	private String latestLoginSuccessDate;
	private String latestLoginFailDate;
	private String registDate;
}
