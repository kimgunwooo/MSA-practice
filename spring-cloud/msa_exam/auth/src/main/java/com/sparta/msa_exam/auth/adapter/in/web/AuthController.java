package com.sparta.msa_exam.auth.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.auth.application.port.in.AuthUseCase;
import com.sparta.msa_exam.auth.application.port.in.SignInRequest;
import com.sparta.msa_exam.auth.application.port.in.SignUpRequest;
import com.sparta.msa_exam.auth.application.port.out.SignInResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        authUseCase.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> createAuthenticationToken(@RequestBody SignInRequest signInRequest){
        SignInResponse response = authUseCase.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }
}




