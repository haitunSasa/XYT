package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.TeacherClass;
import com.Service.TCService;
import com.Service.TSService;

@Service("tcService")
public class TCServiceImpl extends BaseServiceImpl<TeacherClass> implements TCService{
	@Autowired 
	private BaseDaoI<TeacherClass> dao;
	private List<TeacherClass> list = new ArrayList<TeacherClass>();

	@Override
	public List<TeacherClass> getTCByTeacherId(int teacherId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("teacherId", teacherId);
        
        String hql = "from TeacherClass t where userId =:teacherId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<TeacherClass> getTCByClassId(int classId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("classId", classId);
        
        String hql = "from TeacherClass t where classId =:classId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public TeacherClass getTCByTIdAndCId(int teacherId, int classId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classId", classId);
		params.put("teacherId", teacherId);

		String hql = "from TeacherClass t where classId =:classId and userId =:teacherId";
		return this.dao.getByHql(hql,params);
	}


}
