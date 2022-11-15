package com.roadjava.studentroom.service;

import java.util.Vector;

import com.roadjava.studentroom.bean.entity.DormitoryDO;
import com.roadjava.studentroom.req.DormitoryRequest;
import com.roadjava.studentroom.res.TableResult;

/**
 *
  * 
 */
public interface DormitoryService {
	/**
	 * 添加宿舍
	 * @param studentDO
	 * @return
	 */
    boolean add(DormitoryDO studentDO);
    /**
     * 删除宿舍
     * @param ids:宿舍id组成的数组
     * @return
     */
    boolean delete(Long[] ids);
    /**
     * 更新宿舍信息
     * @param studentDO
     * @return
     */
    boolean update(DormitoryDO studentDO);
    TableResult<DormitoryDO> retrieveList(DormitoryRequest request);
    DormitoryDO retrieveOneById(Long id);
	Vector<DormitoryDO> getVector(DormitoryRequest dormitoryRequest);
}
