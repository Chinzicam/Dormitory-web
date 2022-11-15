package com.roadjava.studentroom.req;

import com.roadjava.studentroom.util.Constants;

/**
 *
  * 
  * 查询时都需要的参数
 */
public class BaseRequest {
	/**
	 * 每页显示多少条
	 */
    private int pageSize = Constants.PAGE_SIZE;
    /**
           * 要显示第几页
     */
    private int pageNow;

    public int getStart() {
        return (pageNow - 1) * pageSize;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getpageNow() {
        return pageNow;
    }
}
