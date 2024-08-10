package com.sparta.msa_exam.auth.application.port.out;

import com.sparta.msa_exam.auth.core.domain.User;

public interface UserPersistencePort {

	User read(String userId);

	void create(User user);
}
