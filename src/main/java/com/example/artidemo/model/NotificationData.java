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
"dataId",
"refresh"
})

@Entity
@Table(name = "notificationdata")
public class NotificationData {
	@Id
	@JsonProperty("dataId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer dataId;
@Column(name = "refresh")
@JsonProperty("refresh")
private Boolean refresh;



@JsonProperty("dataId")
public Integer getDataId() {
return dataId;
}

@JsonProperty("dataId")
public void setDataId(Integer dataId) {
this.dataId = dataId;
}
@JsonProperty("refresh")
public Boolean getRefresh() {
return refresh;
}

@JsonProperty("refresh")
public void setRefresh(Boolean refresh) {
this.refresh = refresh;
}


}