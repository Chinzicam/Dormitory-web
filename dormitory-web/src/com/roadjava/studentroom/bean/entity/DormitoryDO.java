package com.roadjava.studentroom.bean.entity;

import java.util.Date;

/**
 *
  * 
 * 和宿舍表dormitory对应的实体类
 */
public class DormitoryDO {
	private Long id;
	// 宿舍编号
	private String no;
	// 宿舍所属宿舍楼的id，驼峰法命名
	private Long dormitoryBuildingId;
	/**
	 * 宿舍楼唯一编号:type-no
	 */
	private String buildingUniqueNo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Long getDormitoryBuildingId() {
		return dormitoryBuildingId;
	}
	public void setDormitoryBuildingId(Long dormitoryBuildingId) {
		this.dormitoryBuildingId = dormitoryBuildingId;
	}

	public String getBuildingUniqueNo() {
		return buildingUniqueNo;
	}

	public void setBuildingUniqueNo(String buildingUniqueNo) {
		this.buildingUniqueNo = buildingUniqueNo;
	}
}
