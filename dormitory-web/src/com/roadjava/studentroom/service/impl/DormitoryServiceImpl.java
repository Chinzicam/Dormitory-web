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
import com.roadjava.studentroom.bean.entity.DormitoryDO;
import com.roadjava.studentroom.service.BaseServiceSupport;
import com.roadjava.studentroom.service.DormitoryService;
import com.roadjava.studentroom.util.Constants;
import com.roadjava.studentroom.util.DBUtil;
import com.roadjava.studentroom.req.DormitoryRequest;
import com.roadjava.studentroom.res.TableResult;

/**
 *
  * 
  * 宿舍业务类实现
 */
public class DormitoryServiceImpl  extends BaseServiceSupport implements DormitoryService {
//添加宿舍
	@Override
	public boolean add(DormitoryDO dormitoryDO) {
		String sql = "insert into dormitory(no,dormitory_building_id) values(?,?)";
		return doAdd(sql, dormitoryDO.getNo(),dormitoryDO.getDormitoryBuildingId());
	}
//删除宿舍
	@Override
	public boolean delete(Long[] ids) {
		List<DeleteParam> conditionList = new ArrayList<DeleteParam>();
		// 删除学生的条件
		StringBuilder studentDeleteSql = new StringBuilder("delete from student where dormitory_id in (");
		DeleteParam studentDeleteParam = new DeleteParam();
		studentDeleteParam.setSql(studentDeleteSql);
		studentDeleteParam.setIds(ids);
		conditionList.add(studentDeleteParam);
		// 删除宿舍的条件
        StringBuilder sql = new StringBuilder("delete from dormitory where id in ( ");
     	DeleteParam deleteParam = new DeleteParam();
		deleteParam.setSql(sql);
		deleteParam.setIds(ids);
		conditionList.add(deleteParam);
        return doDelete(conditionList);
	}
//更新宿舍
	@Override
	public boolean update(DormitoryDO dormitoryDO) {
		String sql = "update dormitory set no = ?,dormitory_building_id=? "
        		+ "  where id = ? ";
		return doUpdate(sql,dormitoryDO.getNo(),dormitoryDO.getDormitoryBuildingId(),
				dormitoryDO.getId());
	}
//获取所有宿舍 按照条件
	@Override
	public TableResult<DormitoryDO> retrieveList(DormitoryRequest req) {
        TableResult<DormitoryDO> retDTO = new TableResult<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select a.id,a.no,b.no as build_no,b.type from dormitory a ");
        sql.append(" left join dormitory_building b  on a.dormitory_building_id = b.id ");
        sql.append(" where 1=1 ");
        if (req.getNo() != null && !"".equals(req.getNo().trim())) {
            sql.append(" and a.no like '%");
            sql.append(req.getNo());
            sql.append("%'");
        }
        if (req.getDormitoryBuildingId() != null) {
			sql.append(" and a.dormitory_building_id = " + req.getDormitoryBuildingId());
		}
        sql.append(" order by a.id desc  ");
        if (!req.selectAll()) {
            sql.append(" limit ");
            sql.append(req.getStart()).append(",").append(req.getPageSize());
		}
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            if (conn == null) {
                return null;
            }
            ps = conn.prepareStatement(sql.toString());
            resultSet = ps.executeQuery();
            Vector<DormitoryDO> data = new Vector<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String no = resultSet.getString("no");
                String buildingNo = resultSet.getString("build_no");
                String type = resultSet.getString("type");
                DormitoryDO oneVector = new DormitoryDO();
                oneVector.setId(id);
                oneVector.setNo(no);
                oneVector.setBuildingUniqueNo(BuildingDO.format(type,buildingNo));
                data.addElement(oneVector);
            }
            // 总数
            sql.setLength(0);
            sql.append("select count(*) from dormitory where 1=1 ");
            if (req.getNo() != null && !"".equals(req.getNo().trim())) {
                sql.append("and no like '%");
                sql.append(req.getNo());
                sql.append("%'");
            }
            ps = conn.prepareStatement(sql.toString());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int totalCount = resultSet.getInt(1);
                retDTO.setTotalCount(totalCount);
            }
            retDTO.setData(data);
            retDTO.setPageNow(req.getpageNow());
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
//根据id查询宿舍
	@Override
	public DormitoryDO retrieveOneById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,no,dormitory_building_id from dormitory where id =? ");
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
            DormitoryDO resultDO = new DormitoryDO();
            while (resultSet.next()) {
                String no = resultSet.getString(2);
                resultDO.setId(id);
                resultDO.setNo(no);
                resultDO.setDormitoryBuildingId(resultSet.getLong(3));
            }
            return resultDO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
	}

	@Override
	public Vector<DormitoryDO> getVector(DormitoryRequest req) {
        TableResult<DormitoryDO> result = this.retrieveList(req);

        return result.getData();
	}


}
