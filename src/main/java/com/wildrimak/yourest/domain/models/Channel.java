package com.wildrimak.yourest.domain.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Channel {

	@Id
	@Column(name = "id_channel")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_user")
	private User user;

	@NotNull
	private String name;

	private String description;

	@NotNull
	@DateTimeFormat
	@CreationTimestamp
	private Date joined;

	private String location;

	private String art;

	private Long views;

	@Column(name = "active_membership")
	private Boolean activeMembership;

	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Video> videos;

	@OneToMany(mappedBy = "myChannel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subscription> subscriptions; // Channels that's I like

	@OneToMany(mappedBy = "otherChannel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subscription> subscribers; // Other channels that's like to me

	public Channel() {
	}

	public Channel(String name) {
		this();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Boolean getActiveMembership() {
		return activeMembership;
	}

	public void setActiveMembership(Boolean activeMembership) {
		this.activeMembership = activeMembership;
	}

	public List<Video> getVideos() {
		return Collections.unmodifiableList(videos);
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public void addVideo(Video video) {
		this.videos.add(video);
	}

	public List<Subscription> getSubscriptions() {
		return Collections.unmodifiableList(subscriptions);
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public void addSubscription(Subscription subscription) {
		this.subscriptions.add(subscription);
	}

	public List<Subscription> getSubscribers() {
		return Collections.unmodifiableList(subscribers);
	}

	public void setSubscribers(List<Subscription> subscribers) {
		this.subscribers = subscribers;
	}

	public void addSubscriber(Subscription subscriber) {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
