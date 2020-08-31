package com.wildrimak.yourest.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wildrimak.yourest.domain.models.Video;
import com.wildrimak.yourest.domain.repositories.VideoRepository;

@Service
public class VideoCrudService {

	@Autowired
	private VideoRepository videoRepository;

	public List<Video> getAllUnsorted() {
		return videoRepository.findAll(Sort.unsorted());
	}

	public Video save(Video video) {
		return videoRepository.save(video);
	}

	public Video update(Video video) {
		return videoRepository.save(video);
	}

	public void delete(Integer idVideo) {
		videoRepository.deleteById(idVideo);
	}

}
