package com.sparta.msa_exam.auth.core.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.auth.application.port.in.AuthUseCase;
import com.sparta.msa_exam.auth.application.port.in.SignInRequest;
import com.sparta.msa_exam.auth.application.port.in.SignUpRequest;
import com.sparta.msa_exam.auth.application.port.out.SignInResponse;
import com.sparta.msa_exam.auth.application.port.out.UserPersistencePort;
import com.sparta.msa_exam.auth.core.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService implements AuthUseCase {

	private final UserPersistencePort userPersistencePort;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	@Override
	public void signUp(SignUpRequest signUpRequest) {
		User user = User.builder()
			.userId(signUpRequest.userId())
			.username(signUpRequest.username())
			.password(passwordEncoder.encode(signUpRequest.password()))
			.role("ROLE_USER") // 간단하게 string
			.build();

		userPersistencePort.create(user);
	}

	@Override
	public SignInResponse signIn(SignInRequest signInRequest) {
		User user = userPersistencePort.read(signInRequest.userId());

		if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
			throw new IllegalArgumentException("Invalid user ID or password");
		}

		String accessToken = tokenProvider.createAccessToken(user.getUserId(), user.getRole());

		return new SignInResponse(accessToken);
	}
}
