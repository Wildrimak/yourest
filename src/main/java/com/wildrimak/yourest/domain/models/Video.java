package com.wildrimak.yourest.domain.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Video {

	@Id
	@Column(name = "id_video")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_channel")
	private Channel channel;

	private String title;
	private String description;
	private Long views;
	private Long likes;
	private Long deslikes;
	private Boolean isVisibleReactions;
	private Integer durationSeconds;
	private String contentUrl;
	private Date published;

	public void incrementView() {
		this.views += 1;
	}
}
