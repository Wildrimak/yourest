package com.wildrimak.yourest.domain.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildrimak.yourest.api.models.VideoInputModel;
import com.wildrimak.yourest.domain.models.Channel;
import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.models.Video;
import com.wildrimak.yourest.domain.repositories.VideoRepository;

@Service
public class VideoBusinessService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	VideoCrudService videoCrudService;

	public Video updateViewInVideo(Video video) {
		video.incrementView();
		return videoRepository.save(video);
	}

	public Optional<Video> canCreateVideo(User user, Channel channel, VideoInputModel videoInputModel) {

		if (!user.getId().equals(channel.getUser().getId())) {
			return Optional.empty();
		}

		Video videoModel = toModel(videoInputModel, channel);
		Video video = videoCrudService.save(videoModel);
		Optional<Video> optional = Optional.of(video);

		return optional;

	}

	public Video toModel(VideoInputModel videoInputModel, Channel channel) {

		Video video = new Video();

		String contentUrl = videoInputModel.getContentUrl();
		String description = videoInputModel.getDescription();
		Integer durationSeconds = videoInputModel.getDurationSeconds();
		Boolean isVisibleReactions = videoInputModel.getIsVisibleReactions();
		String title = videoInputModel.getTitle();

		video.setChannel(channel);
		video.setContentUrl(contentUrl);
		video.setDescription(description);
		video.setDeslikes(0L);
		video.setDurationSeconds(durationSeconds);
		video.setIsVisibleReactions(isVisibleReactions);
		video.setLikes(0L);
		video.setPublished(new Date());
		video.setTitle(title);
		video.setViews(0L);

		return video;
	}

}
