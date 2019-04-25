
package com.example.artidemo.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"notificationId",
"title",
"body"
})

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@JsonProperty("notificationId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer notificationId;
@Column(name = "title")
@JsonProperty("title")
private String title;
@Column(name = "body")
@JsonProperty("body")
private String body;


@JsonProperty("notificationId")
public Integer getNotificationId() {
return notificationId;
}

@JsonProperty("notificationId")
public void setNotificationId(Integer notificationId) {
this.notificationId = notificationId;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("body")
public String getBody() {
return body;
}

@JsonProperty("body")
public void setBody(String body) {
this.body = body;
}



}