package com.wildrimak.yourest.api.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VideoInputModel {

	@NotBlank
	@Size(min = 3, max = 127)
	private String title;

	@NotBlank
	@Size(min = 3, max = 1023)
	private String description;

	private Boolean isVisibleReactions;

	@Min(value = 1)
	private Integer durationSeconds;

	@NotBlank
	private String contentUrl;

}
