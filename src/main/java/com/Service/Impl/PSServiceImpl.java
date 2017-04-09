package com.Service.Impl;

import com.Dao.BaseDaoI;
import com.Entity.HomeworkStudent;
import com.Entity.ParentStudent;
import com.Entity.UserInfo;
import com.Service.PSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("psService")
public class PSServiceImpl extends BaseServiceImpl<ParentStudent> implements PSService{
	@Autowired 
	private BaseDaoI<ParentStudent> dao;
	@Override
	public ParentStudent getByPIdAndSId(int parentId, int studentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("studentId", studentId);

		String hql = "from ParentStudent t where parentId =:parentId and studentId =:studentId ";
		return this.dao.getByHql(hql, params);
	}
}
