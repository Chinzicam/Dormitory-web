package com.roadjava.studentroom.bean.entity;

import com.roadjava.studentroom.util.Constants;

import java.util.Date;

/**
 *
  * 
 * 和宿舍楼表dormitory_building对应的实体类
 */
public class BuildingDO {
	private Long id;
	/**
	 * 宿舍楼编号
 	 */
	private String no;
	/**
	 * 宿舍楼落成时间
	 */
	private String completedDate;
	/**
	 * 宿舍楼类型,M:男生宿舍,F:女生宿舍
	 */
	private String type;
	/**
	 * 页面上显示的宿舍楼编号:type-no
	 */
	private String uniqueNo;

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

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUniqueNo() {
		return format(type,no);
	}
	public static String format(String type,String no) {
		if ("M".equals(type)) {
			type = "男";
		} else {
			type ="女";
		}
		return type + Constants.MIDDLE_LINE + no;
	}
}
