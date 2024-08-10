package com.sparta.msa_exam.auth.application.port.in;

public record SignUpRequest(
	String userId,
	String username,
	String password
) {
}
