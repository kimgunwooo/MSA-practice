package com.sparta.msa_exam.auth.application.port.in;

import com.sparta.msa_exam.auth.application.port.out.SignInResponse;

public interface AuthUseCase {

	void signUp(SignUpRequest signUpRequest);

	SignInResponse signIn(SignInRequest signInRequest);
}
