package com.roadjava.studentroom.res;


import com.roadjava.studentroom.util.Constants;

import java.util.Vector;

/**
 *
 *表格封装的结果
 */
public class TableResult<T> {
    private Vector<T> data;
    private int totalCount; // 总共多少条
    private int pageNow; // 透传
    private String searchWord; // 透传

    public Vector<T> getData() {
        return data;
    }

    public void setData(Vector<T> data) {
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
