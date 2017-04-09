package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.ClassInfo;
import com.Entity.UserInfo;
import com.Service.ClassService;
import com.Service.UserService;
@Service("classService")
public class ClassServiceImpl extends BaseServiceImpl<ClassInfo> implements ClassService{
	@Autowired 
	private BaseDaoI<ClassInfo> dao;
	private List<ClassInfo> list = new ArrayList<ClassInfo>();
	
	@Override
	public List<ClassInfo> getClassByClassName(String className) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("className", className);
        
        String hql = "from ClassInfo t where className =:className";
        list = this.dao.find(hql, params);
		return list;
	}
}
