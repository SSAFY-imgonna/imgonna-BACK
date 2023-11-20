package com.ssafy.imgonna.notify.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.imgonna.notify.model.dto.Notify;
import com.ssafy.imgonna.notify.model.mapper.NotifyMapper;

@Service
public class NotifyServiceImpl implements NotifyService {

	private NotifyMapper notifyMapper;
	
	public NotifyServiceImpl(NotifyMapper notifyMapper) {
		super();
		this.notifyMapper = notifyMapper;
	}

	
	// 알림 개수 가져오기
	@Override
	public int getNotifyCount(Map<String, String> map) {
		return notifyMapper.getNotifyCount(map);
	}
	
	// 알림 목록 가져오기
	@Override
	public List<Notify> getNotifyList(Map<String, String> map) {
		return notifyMapper.getNotifyList(map);
	}

	// 알림 보내기
	@Override
	public void createNotify(Notify notify) {
		notifyMapper.createNotify(notify);
	}

	// 알림 읽음처리
	@Override
	public void updateNotify(int notifyNo) {
		notifyMapper.updateNotify(notifyNo);
	}
	
	// 알림 모두 읽음처리
	@Override
	public void updateNotifyAll(String id) {
		notifyMapper.updateNotifyAll(id);
	}
	
	// 알림 삭제하기
	@Override
	public void deleteNotify(int notifyNo) {
		notifyMapper.deleteNotify(notifyNo);
	}

	

}
