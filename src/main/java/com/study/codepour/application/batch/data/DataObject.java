package com.study.codepour.application.batch.data;

import lombok.Getter;

/**
 * 아래와 같은 코딩은 지양할 것
 * 임시 데이터를 Serialize하기 위한 클래스로 그 외 용도로는 사용하지 않음
 */
@Getter
public class DataObject {
	private Integer size = 10;
	private Integer time = 1;
	private String value = "Bad";
	private String status = "Good";
	private Integer measureValue = 75;
	private Float test = 10.5f;
}
