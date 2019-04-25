package com.example.artidemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "registrationIds", "platform", "sso", "collapseKey", "notificationId", "dataId" })

@Entity
@Table(name = "deviceinformation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformation {

	@Id
	@JsonProperty("registrationIds")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String registrationIds = null;
	@Column(name = "id")
	@JsonProperty("id")
	private long id;
	@Column(name = "platform")
	@JsonProperty("platform")
	private String platform;
	@Column(name = "collapseKey")
	@JsonProperty("collapseKey")
	private String collapseKey;

	@ManyToOne
	@JoinColumn(name = "notification_id",nullable=false)
	private Notification notification;

	@ManyToOne
	@JoinColumn(name = "data_id",nullable=false)
	private NotificationData data;

	@JsonProperty("registrationIds")
	public String getRegistrationIds() {
		return registrationIds;
	}

	@JsonProperty("registrationIds")
	public void setRegistrationIds(String registrationIds) {
		this.registrationIds = registrationIds;
	}

	@JsonProperty("collapseKey")
	public String getCollapseKey() {
		return collapseKey;
	}

	@JsonProperty("collapseKey")
	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}

	@ManyToOne
	@JoinColumn(name = "notification_id",nullable=false)
	public Notification getNotification() {
		return notification;
	}

	@ManyToOne
	@JoinColumn(name = "notification_id",nullable=false)
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@ManyToOne
	@JoinColumn(name = "data_id",nullable=false)
	public NotificationData getData() {
		return data;
	}

	@ManyToOne
	@JoinColumn(name = "data_id",nullable=false)
	public void setData(NotificationData data) {
		this.data = data;
	}

	@JsonProperty("id")
	public long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("platform")
	public String getPlatform() {
		return platform;
	}

	@JsonProperty("platform")
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}