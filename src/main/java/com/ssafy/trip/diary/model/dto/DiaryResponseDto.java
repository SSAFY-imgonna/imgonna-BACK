package com.ssafy.trip.diary.model.dto;

public class DiaryResponseDto {
	private int diaryNo;
	private String travelTime;
	private String id;
	private int contentId;
	private String title;
	private String content;
	private String createdTime;
	private int weather;
	private int emotion;
	private int isPublic;
	private String latitude;
	private String longitude;
	private int contentTypeId;
	
	
	public int getDiaryNo() {
		return diaryNo;
	}
	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public int getWeather() {
		return weather;
	}
	public void setWeather(int weather) {
		this.weather = weather;
	}
	public int getEmotion() {
		return emotion;
	}
	public void setEmotion(int emotion) {
		this.emotion = emotion;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	
	
	@Override
	public String toString() {
		return "DiaryResponseDto [diaryNo=" + diaryNo + ", travelTime=" + travelTime + ", id=" + id + ", contentId="
				+ contentId + ", title=" + title + ", content=" + content + ", createdTime=" + createdTime
				+ ", weather=" + weather + ", emotion=" + emotion + ", isPublic=" + isPublic + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", contentTypeId=" + contentTypeId + "]";
	}
	
}
