package com.roadjava.studentroom.res;

import com.roadjava.studentroom.util.Constants;

import java.util.Vector;

/**
 *
  * 
  * 表格响应数据的封装
 */
public class TableDataDTO {
    private Vector<Vector<Object>> data;
    private int pageNow; // 透传
    private int totalCount; // 总共多少条
    private String searchWord; // 透传

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    /*
        总页数
         */
    public int getPageCount() {
        int pageCount = 0;
        if (totalCount % Constants.PAGE_SIZE == 0) {
            pageCount = totalCount / Constants.PAGE_SIZE;
        } else {
            pageCount = totalCount / Constants.PAGE_SIZE + 1;
        }
        return pageCount;
    }
}
