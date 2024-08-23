package com.sparta.msa_exam.auth.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.msa_exam.auth.application.port.out.UserPersistencePort;
import com.sparta.msa_exam.auth.core.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Repository
public class UserRepository implements UserPersistencePort {

	private final UserJpaRepository userJpaRepository;

	@Override
	public User read(String userId) {
		return UserMapper.entityToDomain(userJpaRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다. id: $id")));
	}

	@Override
	public void create(User user) {
		userJpaRepository.save(UserMapper.domainToEntity(user));
	}
}
