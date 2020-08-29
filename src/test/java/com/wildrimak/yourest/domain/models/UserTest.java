package com.wildrimak.yourest.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	public void userMustHaveAChannelWhenItHasBeenCreated() {

		User user = new User();
		Integer amount = user.getChannels().size();
		assertEquals(1, amount);

	}

}
