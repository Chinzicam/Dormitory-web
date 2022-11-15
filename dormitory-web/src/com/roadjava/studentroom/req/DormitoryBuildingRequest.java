package com.roadjava.studentroom.req;
/**
 *
  * 
  * 宿舍楼查询条件
 */
public class DormitoryBuildingRequest extends BaseRequest{
	private String no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public boolean selectAll() {
		// 约定-1时表示查询全部
		return this.getPageSize() == -1;
	}
	
}
