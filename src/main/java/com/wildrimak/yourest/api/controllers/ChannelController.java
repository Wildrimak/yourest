package com.wildrimak.yourest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildrimak.yourest.api.models.ChannelInputModel;
import com.wildrimak.yourest.domain.exceptions.EntityNotFoundException;
import com.wildrimak.yourest.domain.models.Channel;
import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.repositories.UserRepository;
import com.wildrimak.yourest.domain.services.ChannelService;

@RestController
@RequestMapping("/users/{idUser}/channels")
public class ChannelController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChannelService channelService;

	@GetMapping
	private List<Channel> getChannels(@PathVariable Integer idUser) {

		User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));

		return user.getChannels();

	}

	@PostMapping
	private ResponseEntity<Channel> postChannel(@PathVariable Integer idUser,
			@RequestBody ChannelInputModel channelInputModel) {

		Channel channel = toDomain(channelInputModel);
		Channel channelSaved = channelService.save(channel);

		return ResponseEntity.status(HttpStatus.CREATED).body(channelSaved);
	}

	public Channel toDomain(ChannelInputModel channelInputModel) {

		String name = channelInputModel.getName();
		String description = channelInputModel.getDescription();
		String location = channelInputModel.getLocation();
		String art = channelInputModel.getArt();

		Channel channel = new Channel(name, description, location, art);

		return channel;
	}

}
