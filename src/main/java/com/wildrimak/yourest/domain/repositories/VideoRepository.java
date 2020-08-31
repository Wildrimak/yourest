package com.wildrimak.yourest.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildrimak.yourest.domain.models.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {

}
