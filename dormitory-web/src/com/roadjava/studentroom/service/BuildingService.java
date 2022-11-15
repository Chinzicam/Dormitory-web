package com.roadjava.studentroom.service;

import java.util.Vector;

import com.roadjava.studentroom.bean.entity.BuildingDO;
import com.roadjava.studentroom.req.DormitoryBuildingRequest;
import com.roadjava.studentroom.res.TableResult;

/**
 *
  * 
 */
public interface BuildingService {
	/**
	 * 添加宿舍楼到数据库
	 * @param buildingDO
	 * @return
	 */
    boolean add(BuildingDO buildingDO);
    /**
     * 使用宿舍楼的id组成的数组来删除宿舍楼记录
     * @param ids
     * @return
     */
    boolean delete(Long[] ids);
    /**
     * 更新宿舍楼记录
     * @param buildingDO
     * @return
     */
    boolean update(BuildingDO buildingDO);
    /**
     * 查询宿舍楼表格数据
     * @param req
     * @return
     */
    TableResult<BuildingDO> retrieveList(DormitoryBuildingRequest req);
    /**
     * 通过宿舍楼的id查询一条
     * @param id
     * @return
     */
    BuildingDO retrieveOneById(Long id);
    /**
     * 查询宿舍楼列表数据
     * @param req
     * @return
     */
    Vector<BuildingDO> getVector(DormitoryBuildingRequest req);
}
