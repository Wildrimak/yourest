package com.wildrimak.yourest.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildrimak.yourest.domain.models.Channel;
import com.wildrimak.yourest.domain.repositories.ChannelRepository;

@Service
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	public Channel save(Channel channel) {
		return channelRepository.save(channel);
	}

}
