package com.wildrimak.yourest.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildrimak.yourest.domain.models.Channel;

public interface  ChannelRepository extends  JpaRepository<Channel, Integer> {

}
