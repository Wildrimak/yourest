package com.wildrimak.yourest.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildrimak.yourest.domain.exceptions.BusinessException;
import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		validateUserEmail(user);
		return userRepository.save(user);
	}

	public void delete(Integer userId) {
		userRepository.deleteById(userId);
	}

	public void validateUserEmail(User user) {

		String myEmail = user.getEmail();

		User found = userRepository.findByEmail(myEmail);

		if (found != null) {

			if (newest(user)) {
				throw new BusinessException("This email has alredy been registered!");
			}
			if (!myEmail(user, found)) {
				throw new BusinessException("This email has alredy been registered!");
			}
		}

	}

	private boolean myEmail(User user, User found) {
		return user.getId().equals(found.getId());
	}

	private boolean newest(User user) {
		return user.getId() == null;
	}

}
