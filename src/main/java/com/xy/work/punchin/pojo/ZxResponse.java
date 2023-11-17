package com.xy.work.punchin.pojo;

import lombok.Data;

@Data
public class ZxResponse<T> {
	private String msg;
	private String code;
	private T data;
	private String requestID;
	private long ctime;

}
