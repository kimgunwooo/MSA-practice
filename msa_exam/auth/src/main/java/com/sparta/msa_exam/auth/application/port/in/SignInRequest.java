package com.sparta.msa_exam.auth.application.port.in;

public record SignInRequest(
	String userId,
	String password
) {
}
