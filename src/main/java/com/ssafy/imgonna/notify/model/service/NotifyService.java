package com.ssafy.imgonna.notify.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.notify.model.dto.Notify;

public interface NotifyService {
	// 알림 개수
	int getNotifyCount(Map<String, String> map);
	
	// 알림 목록 가져오기
	List<Notify> getNotifyList(Map<String, String> map);
	
	// 알림 보내기
	void createNotify(Notify notify);
	
	// 알림 읽음처리
	void updateNotify(int notifyNo);

	// 알림 모두 읽음처리
	void updateNotifyAll(String id);
	
	// 알림 삭제하기
	void deleteNotify(int notifyNo);
}
