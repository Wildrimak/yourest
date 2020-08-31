package com.wildrimak.yourest.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wildrimak.yourest.domain.exceptions.BusinessException;
import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.repositories.UserRepository;

public class UserServiceTest {

	private UserRepository userRepository;

	private UserService userService;

	@BeforeEach
	public void setup() {

		this.userRepository = mock(UserRepository.class);
		enableUserRepositoryFindByEmail();
		enableUserRepositorySave();
		this.userService = new UserService(userRepository);

	}
	
	@Test
	public void userMustHaveAChannelWhenItHasBeenCreated() {

		User user = new User();
		user.setName("Ana");
		userService.save(user);
		Integer amount = user.getChannels().size();
		assertEquals(1, amount);

	}

	@Test
	public void shouldBeThrowBusinessExceptionIfEmailAlredyExists() {
	
		User user = new User();
		user.setEmail("default@gmail.com"); // equals to definition

		BusinessException exception = assertThrows(BusinessException.class, 
				() -> userService.save(user));
		
		assertEquals(exception.getMessage(), "This email has alredy been registered!");
	
	}

	private void enableUserRepositorySave() {
		User registred = new User();
		registred.setId(1);
		registred.setEmail("default@gmail.com");

		when(userRepository.save(any(User.class))).thenReturn(registred);

	}

	private void enableUserRepositoryFindByEmail() {

		User registred = new User();
		registred.setId(1);
		registred.setEmail("default@gmail.com");

		when(userRepository.findByEmail(any(String.class))).thenReturn(registred);
	}

}
