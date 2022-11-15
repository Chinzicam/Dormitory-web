package com.roadjava.studentroom.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.roadjava.studentroom.bean.dto.DeleteParam;
import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.bean.entity.StudentDO;
import com.roadjava.studentroom.service.BaseServiceSupport;
import com.roadjava.studentroom.service.StudentService;
import com.roadjava.studentroom.util.DBUtil;
import com.roadjava.studentroom.req.StudentRequest;
import com.roadjava.studentroom.res.TableResult;

/**
 *
  * 
 * 学生与数据库交互的类
 */
public class StudentServiceImpl extends BaseServiceSupport implements StudentService {
//	添加学生
    @Override
    public boolean add(StudentDO studentDO) {
    	if (checkHasExist(studentDO.getNo())) {
			throw new RuntimeException("学号已存在");
		}
        String sql = "insert into student(name,gender,no,dept,grade,dormitory_id) values(?,?,?,?,?,?)";
        return doAdd(sql, studentDO.getName(),studentDO.getGender(),studentDO.getNo(),
                studentDO.getDept(),
        		studentDO.getGrade(),studentDO.getDormitoryId());
    }
    // 检测学号是否存在
    private boolean checkHasExist(String no) {
    	String sql = "select count(1) from student where no = ? ";
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            if (conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            resultSet = ps.executeQuery();
            boolean hasExist = false;
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count != 0) {
                	hasExist = true;
                	return hasExist;
                }
            }
            return hasExist;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePs(ps);
           DBUtil.closeConn(conn);
        }
        return false;
    }
//删除学生
    @Override
    public boolean delete(Long[] ids) {
        StringBuilder sqlBuilder = new StringBuilder("delete from student where id in (");
    	DeleteParam deleteParam = new DeleteParam();
		deleteParam.setSql(sqlBuilder);
		deleteParam.setIds(ids);
		List<DeleteParam> list = new ArrayList<DeleteParam>();
		list.add(deleteParam);
        return doDelete(list);
    }
//更新学生
    @Override
    public boolean update(StudentDO studentDO) {
        String sql = "update student set name = ?,no = ?,dept = ?,grade = ?,"
        		+ " gender = ?, "
        		+ "dormitory_id = ?  where id = ?";
        return doUpdate(sql, studentDO.getName(),studentDO.getNo(),studentDO.getDept(),
        		studentDO.getGrade(),studentDO.getGender(),
                studentDO.getDormitoryId(),studentDO.getId());
    }
//获取学生所有信息
    @Override
    public TableResult<StudentDO> retrieveList(StudentRequest studentRequest) {
        TableResult<StudentDO> retDTO = new TableResult<StudentDO>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select s.id,s.name,s.gender,s.no,s.dept,s.grade," +
                " d.no as dormitoryNo,b.no as buildingNo,b.type ");
        appendFrom(sql);
        appendWhere(studentRequest, sql);
        sql.append(" order by s.id desc limit ");
        sql.append(studentRequest.getStart()).append(",").append(studentRequest.getPageSize());
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql.toString());
            resultSet = ps.executeQuery();
            Vector<StudentDO> data = new Vector<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String no = resultSet.getString("no");
                String dept = resultSet.getString("dept");
                String grade = resultSet.getString("grade");
                String dormitoryNo = resultSet.getString("dormitoryNo");
                String type = resultSet.getString("type");
                String buildingNo = resultSet.getString("buildingNo");
                StudentDO studentDO = new StudentDO();
                studentDO.setId(id);
                studentDO.setName(name);
                studentDO.setGender(gender);
                studentDO.setNo(no);
                studentDO.setDept(dept);
                studentDO.setGrade(grade);
                studentDO.setDormitoryNo(dormitoryNo);
                studentDO.setBuildingUniqueNo(BuildingDO.format(type,buildingNo));
                data.addElement(studentDO);
            }
            // 总数
            sql.setLength(0);
            sql.append("select count(*) ");
            appendFrom(sql);
            appendWhere(studentRequest, sql);
            ps = conn.prepareStatement(sql.toString());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int totalCount = resultSet.getInt(1);
                retDTO.setTotalCount(totalCount);
            }
            retDTO.setData(data);
            retDTO.setPageNow(studentRequest.getpageNow());
            return retDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }
//根据id查询学生
    @Override
    public StudentDO retrieveOneById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select s.id,s.name,s.gender,s.no,s.dept,s.grade,s.dormitory_id," +
                " d.dormitory_building_id  ");
        appendFrom(sql);
        sql.append(" where s.id =? ");
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(1,id);
            resultSet = ps.executeQuery();
            StudentDO studentDO = new StudentDO();
            while (resultSet.next()) {
                String no = resultSet.getString("no");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String dept = resultSet.getString("dept");
                String grade = resultSet.getString("grade");
                long dormitoryId = resultSet.getLong("dormitory_id");
                long dormitoryBuildingId = resultSet.getLong("dormitory_building_id");
                studentDO.setId(id);
                studentDO.setName(name);
                studentDO.setGender(gender);
                studentDO.setNo(no);
                studentDO.setDept(dept);
                studentDO.setGrade(grade);
                studentDO.setDormitoryId(dormitoryId);
                studentDO.setBuildingId(dormitoryBuildingId);
            }
            return studentDO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    private void appendFrom(StringBuilder sql) {
        sql.append(" from student s  ");
        sql.append(" left join dormitory d on s.dormitory_id = d.id  ");
        sql.append(" left join dormitory_building b on d.dormitory_building_id = b.id  ");
    }

    private void appendWhere(StudentRequest studentRequest, StringBuilder sql) {
        sql.append(" where 1=1 ");
        if (studentRequest.getStudentNo() != null && !"".equals(studentRequest.getStudentNo().trim())) {
            sql.append(" and s.no like '%");
            sql.append(studentRequest.getStudentNo());
            sql.append("%'");
        }
    }
}
