package com.wildrimak.yourest.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "subscriptions")
public class Subscription {

	@Id
	@Column(name = "id_subscription")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_my_channel", columnDefinition = "id_my_channel")
	private Channel myChannel;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_other_channel", columnDefinition = "id_other_channel")
	private Channel otherChannel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Channel getMyChannel() {
		return myChannel;
	}

	public void setMyChannel(Channel myChannel) {
		this.myChannel = myChannel;
	}

	public Channel getOtherChannel() {
		return otherChannel;
	}

	public void setOtherChannel(Channel otherChannel) {
		this.otherChannel = otherChannel;
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
		Subscription other = (Subscription) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
