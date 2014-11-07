package org.swz.com.family.repository.mybatis;

import java.util.List;

import org.swz.com.family.entity.Area;

@MyBatisRepository
public interface AreaDao {
	List<Area> getAreaByParentId(int parentId);
	List<Area> searchAll();
	List<Area> searchAreaByName(String areaName);
}
