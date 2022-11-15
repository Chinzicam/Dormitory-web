package com.roadjava.studentroom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.roadjava.studentroom.bean.dto.DeleteParam;
import com.roadjava.studentroom.util.DBUtil;

/**
 *
  * 
 * 抽取service里面公共的部分，不然每个service里面
 *
 */
public class BaseServiceSupport {
//	添加方法
	protected boolean doAdd(String sql,Object...args) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        try {
            if (conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            populatePs(ps, args);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
	}

	/**
	 * 如果入参不为空,则设置参数
	 * @param ps
	 * @param args
	 * @throws SQLException
	 */
	private void populatePs(PreparedStatement ps, Object... args) throws SQLException {
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1,args[i]);
			}
		}
	}
//	删除方法
	protected boolean doDelete(List<DeleteParam> deleteParams) {
		Connection conn = DBUtil.getConn();
		boolean result = true;
		try {
			conn.setAutoCommit(false);// 默认是自动提交的
			for (DeleteParam deleteParam : deleteParams) {
				StringBuilder sql = deleteParam.getSql();
				Long[] ids = deleteParam.getIds();
				int length = ids.length;
		        // 传过来的sql类似:delete from student where id in (,这里根据ids来补全
		        for (int i=0;i<length;i++) {
		            if (i == (length - 1)) {
		                sql.append(" ?) ");
		            }else {
		                sql.append("?,");
		            }
		        }
		        PreparedStatement ps = null;
		        try {
		            ps = conn.prepareStatement(sql.toString());
		            // 设置参数的值
		            for (int i=0;i<length;i++) {
		                // 从1开始
		                ps.setLong(i+1, ids[i]);
		            }
					System.out.println("ps:"+ps.toString());
		            ps.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		            conn.rollback();
		            result = false;
		            break;
		        }finally {
		            DBUtil.closePs(ps);
		        }
			}
			// 都执行完后提交事务
			if (result) {
				conn.commit();
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				result = false;
			}
		}finally {
			 DBUtil.closeConn(conn);
		}
        return result;
    }
	
//	更新方法
	protected boolean doUpdate(String sql,Object...args) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        try {
            if (conn == null) {
                return false;
            }
            ps = conn.prepareStatement(sql);
            populatePs(ps, args);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
	}
	
}
