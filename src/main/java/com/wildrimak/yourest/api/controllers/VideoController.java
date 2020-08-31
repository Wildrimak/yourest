package com.wildrimak.yourest.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildrimak.yourest.api.models.VideoInputModel;
import com.wildrimak.yourest.domain.exceptions.BusinessException;
import com.wildrimak.yourest.domain.exceptions.EntityNotFoundException;
import com.wildrimak.yourest.domain.models.Channel;
import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.models.Video;
import com.wildrimak.yourest.domain.repositories.ChannelRepository;
import com.wildrimak.yourest.domain.repositories.UserRepository;
import com.wildrimak.yourest.domain.repositories.VideoRepository;
import com.wildrimak.yourest.domain.services.VideoBusinessService;

@RestController
@RequestMapping("users/{idUser}/channels/{idChannel}/videos")
public class VideoController {

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private VideoBusinessService videoBusinessService;

	@GetMapping
	private List<Video> getVideos(@PathVariable Integer idChannel) {

		Channel channel = channelRepository.findById(idChannel)
				.orElseThrow(() -> new EntityNotFoundException("Channel not found"));
		return channel.getVideos();

	}

	@PostMapping
	public ResponseEntity<Video> postVideo(@PathVariable Integer idUser, @PathVariable Integer idChannel,
			@Valid @RequestBody VideoInputModel videoInputModel) {

		User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));

		Channel channel = channelRepository.findById(idChannel)
				.orElseThrow(() -> new EntityNotFoundException("Channel not found"));

		Optional<Video> videoOptional = videoBusinessService.canCreateVideo(user, channel, videoInputModel);

		Video video = videoOptional
				.orElseThrow(() -> new BusinessException("You can not post video in a channel that's not yours"));

		return ResponseEntity.status(HttpStatus.CREATED).body(video);

	}

	// update to response entity
	@GetMapping("/{idVideo}")
	public Video getVideo(@PathVariable Integer idUser, @PathVariable Integer idVideo) {

		Video video = videoRepository.findById(idVideo)
				.orElseThrow(() -> new EntityNotFoundException("Video not found"));

		return videoBusinessService.updateViewInVideo(video);

	}

}
