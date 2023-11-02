package com.ssafy.trip.util;

import com.ssafy.trip.accompany.model.AccompanyDto;

import java.util.List;


public class QuickSort {
	public static void quickSort(List<AccompanyDto> list, int low, int high, String keyfield1) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, keyfield1);
            quickSort(list, low, pivotIndex - 1, keyfield1);
            quickSort(list, pivotIndex + 1, high, keyfield1);
        }
	}

	public static int partition(List<AccompanyDto> list, int low, int high, String keyfield1) {
		long pivot = -1;
		// 조회수 정렬일경우
		if(keyfield1.equals("hit")) {
			pivot = list.get(high).getHit(); // 피봇을 배열의 마지막 요소로 선택
			
			int i = low - 1; // 큰 요소들을 추적하기 위한 인덱스
			
			for (int j = low; j < high; j++) {
				if (list.get(j).getHit() > pivot) {
					i++;
					// list[i]와 list[j]를 교환
					AccompanyDto temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
			
			// 피봇을 올바른 위치로 이동
			AccompanyDto temp = list.get(i+1);
			list.set(i+1, list.get(high));
			list.set(high, temp);
			
			return i+1;
		}
		
		// 작성일순 정렬일 경우
		else if(keyfield1.equals("regDate")) {
			String date = list.get(high).getRegDate();
			date = date.replace("-", "").replace(" ", "").replace(":", "");
			System.out.println(date);
			
			pivot = Long.parseLong(date); // 피봇을 배열의 마지막 요소로 선택
			
			int i = low - 1; // 큰 요소들을 추적하기 위한 인덱스
			
			for (int j = low; j < high; j++) {
				String tempDate = list.get(j).getRegDate();
				tempDate = tempDate.replace("-", "").replace(" ", "").replace(":", "");
				if (Long.parseLong(tempDate) > pivot) {
					i++;
					// list[i]와 list[j]를 교환
					AccompanyDto temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
			
			// 피봇을 올바른 위치로 이동
			AccompanyDto temp = list.get(i+1);
			list.set(i+1, list.get(high));
			list.set(high, temp);
			
			return i+1;	
		}		
      
		return 0;
	}
}
