package com.roadjava.studentroom.bean.entity;

import java.beans.Transient;
import java.util.Date;

/**
 *
  * 
 * 和学生表student对应的实体类
 */
public class StudentDO {
	private Long id;
	private String name;
	private String gender;
	/**
	 * 学号
	 */
	private String no;
	private String dept;// 学生所在系别，如计算机科学与技术系
	private String grade;// 班级
	// 学生所在宿舍的id
	private Long  dormitoryId;
	
	// 不是student表的字段，查出来为了计算
	private Long buildingId;

	/**
	 * 页面显示的宿舍编号
	 */
	private String dormitoryNo;
	/**
	 * 页面显示的宿舍楼编号
	 */
	private String buildingUniqueNo;

	public String getDormitoryNo() {
		return dormitoryNo;
	}

	public void setDormitoryNo(String dormitoryNo) {
		this.dormitoryNo = dormitoryNo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Long getDormitoryId() {
		return dormitoryId;
	}
	public void setDormitoryId(Long dormitoryId) {
		this.dormitoryId = dormitoryId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingUniqueNo() {
		return buildingUniqueNo;
	}

	public void setBuildingUniqueNo(String buildingUniqueNo) {
		this.buildingUniqueNo = buildingUniqueNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
