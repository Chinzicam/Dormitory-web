package com.roadjava.studentroom.req;
/**
 *
  * 
  * 宿舍查询条件
 */
public class DormitoryRequest extends BaseRequest {
	private String no;
	private Long dormitoryBuildingId;

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
	
	public boolean selectAll() {
		// 约定-1时表示查询全部
		return this.getPageSize() == -1;
	}
}
