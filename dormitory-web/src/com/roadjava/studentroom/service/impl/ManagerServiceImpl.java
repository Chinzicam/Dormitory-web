package com.roadjava.studentroom.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roadjava.studentroom.bean.entity.ManagerDO;
import com.roadjava.studentroom.service.ManagerService;
import com.roadjava.studentroom.util.DBUtil;

/**
 *
  * 
 * 管理员和数据库交互的类
 *
 */
public class ManagerServiceImpl implements ManagerService {

//    校验管理员信息，查询管理员
	@Override
	public boolean validateManagerInfo(ManagerDO managerDO) {
		String sql = "select pwd from manager where user_name = ? ";
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            if (conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,managerDO.getUserName());
            resultSet = ps.executeQuery();
            boolean validateOk = false;
            while (resultSet.next()) {
                String pwd = resultSet.getString(1);
                if (managerDO.getPwd().equals(pwd)) {
                    validateOk = true;
                }
            }
            return validateOk;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePs(ps);
           DBUtil.closeConn(conn);
        }
        return false;
	}

}
