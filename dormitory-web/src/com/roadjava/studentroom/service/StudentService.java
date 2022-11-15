package com.roadjava.studentroom.service;

import com.roadjava.studentroom.bean.entity.StudentDO;
import com.roadjava.studentroom.req.StudentRequest;
import com.roadjava.studentroom.res.TableResult;

/**
 *
  * 
 */
public interface StudentService {
    boolean add(StudentDO studentDO);
    boolean delete(Long[] ids);
    boolean update(StudentDO studentDO);

    /**
     * 查询学生列表，用于表格展示
     */
    TableResult<StudentDO> retrieveList(StudentRequest studentRequest);

    /**
     * 通过学生id查询一个学生,用于更新时的回显
     */
    StudentDO retrieveOneById(Long id);
}
