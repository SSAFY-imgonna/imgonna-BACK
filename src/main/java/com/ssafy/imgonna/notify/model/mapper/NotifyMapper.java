package com.ssafy.imgonna.notify.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.imgonna.notify.model.dto.Notify;

@Mapper
public interface NotifyMapper {
	// 알림 개수
	int getNotifyCount(Map<String, String> map);
	
	// 알림 목록 가져오기
	List<Notify> getNotifyList(Map<String, String> map);
	
	// 알림 목록 가져오기(읽음+안읽음)
	List<Notify> getNotifyListAll(Map<String, String> map);
	
	// 알림 보내기
	void createNotify(Notify notify);
	
	// 알림 읽음처리
	void updateNotify(int notifyNo);

	// 알림 모두 읽음처리
	void updateNotifyAll(String id);
	
	// 알림 삭제하기
	void deleteNotifyList(List<String> list);

}
