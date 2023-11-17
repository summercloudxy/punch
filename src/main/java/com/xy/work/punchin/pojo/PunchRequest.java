package com.xy.work.punchin.pojo;

import lombok.Data;

@Data
public class PunchRequest{
	private String corpId = "6";
	private int signType;
	private String location = "天津市南开区时代奥城商业广场C6南楼";
	private double lon = 117.17593575131382;
	private String office = "1178669458475945985";
	private String userId;
	private double lat = 39.08206514129723;

}
