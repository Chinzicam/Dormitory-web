package com.roadjava.studentroom.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import com.roadjava.studentroom.bean.dto.DeleteParam;
import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.bean.entity.DormitoryDO;
import com.roadjava.studentroom.service.BaseServiceSupport;
import com.roadjava.studentroom.service.DormitoryService;
import com.roadjava.studentroom.util.DBUtil;
import com.roadjava.studentroom.req.DormitoryBuildingRequest;
import com.roadjava.studentroom.req.DormitoryRequest;
import com.roadjava.studentroom.res.TableResult;
import com.roadjava.studentroom.service.BuildingService;

/**
 *
  * 
  * 宿舍楼业务实现
 */
public class BuildingServiceImpl extends BaseServiceSupport implements BuildingService {
//添加宿舍楼
	@Override
	public boolean add(BuildingDO buildingDO) {
		String sql = "insert into dormitory_building(no,completed_date,type) values(?,?,?)";
        return doAdd(sql, buildingDO.getNo(),buildingDO.getCompletedDate(),buildingDO.getType());
	}
//删除宿舍楼
	@Override
	public boolean delete(Long[] ids) {
		List<DeleteParam> conditionList = new ArrayList<DeleteParam>();
		// 先通过宿舍楼id查出关联的宿舍id集合,用于删除关联的学生
		List<Long> dormitoryIds = new ArrayList<Long>();
		DormitoryService dormitoryService = new DormitoryServiceImpl();
		for (Long buildingId : ids) {
			DormitoryRequest dormitoryRequest = new DormitoryRequest();
			dormitoryRequest.setPageSize(-1);//查所有
			dormitoryRequest.setDormitoryBuildingId(buildingId);
			Vector<DormitoryDO> vector = dormitoryService.getVector(dormitoryRequest);
			List<Long> list = vector.stream().
					map(DormitoryDO::getId).collect(Collectors.toList());
			dormitoryIds.addAll(list);
		}
		if (!dormitoryIds.isEmpty()) {
            // 删除学生的条件
            StringBuilder studentDeleteSql = new StringBuilder("delete from student where dormitory_id in (");
            DeleteParam studentDeleteParam = new DeleteParam();
            studentDeleteParam.setSql(studentDeleteSql);
            studentDeleteParam.setIds(dormitoryIds.toArray(new Long[dormitoryIds.size()]));
            conditionList.add(studentDeleteParam);
        }
		// 删除宿舍的条件
		StringBuilder dormitoryDelsql = new StringBuilder("delete from dormitory where dormitory_building_id in ( ");
		DeleteParam dormitoryDeleteParam = new DeleteParam();
		dormitoryDeleteParam.setSql(dormitoryDelsql);
		dormitoryDeleteParam.setIds(ids);
		conditionList.add(dormitoryDeleteParam);
		// 删除宿舍楼的条件
        StringBuilder buildingDelSql = new StringBuilder("delete from dormitory_building where id in ( ");
        DeleteParam buildingDeleteParam = new DeleteParam();
        buildingDeleteParam.setSql(buildingDelSql);
        buildingDeleteParam.setIds(ids);
		conditionList.add(buildingDeleteParam);
		// 在一个事务中删除
        return doDelete(conditionList);
	}
//更新宿舍楼
	@Override
	public boolean update(BuildingDO buildingDO) {
		String sql = "update dormitory_building set no = ?,completed_date = ? "
        		+ "  ,type = ? where id = ? ";
        return doUpdate(sql, buildingDO.getNo(),buildingDO.getCompletedDate()
                ,buildingDO.getType(), buildingDO.getId());
	}
//查询宿舍楼按照条件
	@Override
	public TableResult<BuildingDO> retrieveList(DormitoryBuildingRequest req) {
        TableResult<BuildingDO> retDTO = new TableResult<BuildingDO>();
        StringBuilder sql = new StringBuilder("select id,no" +
                ",date_format(completed_date,'%Y-%m-%d') as completed_date,type  " +
                "from dormitory_building where 1=1 ");
        if (req.getNo() != null && !"".equals(req.getNo().trim())) {
            sql.append("and no like '%");
            sql.append(req.getNo());
            sql.append("%'");
        }
        sql.append(" order by id desc  ");
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
            Vector<BuildingDO> data = new Vector<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String no = resultSet.getString("no");
                String completed_date = resultSet.getString("completed_date");
                String type = resultSet.getString("type");
                BuildingDO oneVector = new BuildingDO();
                oneVector.setId(id);
                oneVector.setNo(no);
                oneVector.setCompletedDate(completed_date);
                oneVector.setType(type);
                data.addElement(oneVector);
            }
            // 总数
            sql.setLength(0);
            sql.append("select count(*) from dormitory_building where 1=1 ");
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
//根据id查询宿舍楼
	@Override
	public BuildingDO retrieveOneById(Long id) {
		StringBuilder sql = new StringBuilder("select id,no," +
                " date_format(completed_date,'%Y-%m-%d') as completed_date, " +
                " type " +
                " from dormitory_building where id =? ");
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
            BuildingDO resultDO = new BuildingDO();
            while (resultSet.next()) {
                String no = resultSet.getString("no");
                String completed_date = resultSet.getString("completed_date");
                String type = resultSet.getString("type");
                resultDO.setId(id);
                resultDO.setNo(no);
                resultDO.setCompletedDate(completed_date);
                resultDO.setType(type);
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
	public Vector<BuildingDO> getVector(DormitoryBuildingRequest req) {
        TableResult<BuildingDO> result = this.retrieveList(req);
        return result.getData();
	}

}
