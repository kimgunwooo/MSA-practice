package com.sparta.msa_exam.auth.adapter.out.persistence;

import com.sparta.msa_exam.auth.core.domain.User;

public class UserMapper {

	public static User entityToDomain(UserJpaEntity userJpaEntity) {
		return User.builder()
			.userId(userJpaEntity.getUserId())
			.username(userJpaEntity.getUsername())
			.password(userJpaEntity.getPassword())
			.role(userJpaEntity.getRole())
			.build();
	}

	public static UserJpaEntity domainToEntity(User user) {
		return UserJpaEntity.builder()
			.userId(user.getUserId())
			.username(user.getUsername())
			.password(user.getPassword())
			.role(user.getRole())
			.build();
	}
}
