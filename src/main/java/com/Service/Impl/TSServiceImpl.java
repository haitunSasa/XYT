package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.TeacherSubject;
import com.Service.TSService;
@Service("tSService")
public class TSServiceImpl extends BaseServiceImpl<TeacherSubject> implements TSService{
	@Autowired 
	private BaseDaoI<TeacherSubject> dao;
	private List<TeacherSubject> list = new ArrayList<TeacherSubject>();
	
	@Override
	public List<TeacherSubject> getTSByTeacherId(int teacherId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("teacherId", teacherId);
        
        String hql = "from TeacherSubject t where userId =:teacherId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<TeacherSubject> getTSByTidAndSid(int teacherId, int subjectId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("teacherId", teacherId);
        params.put("subjectId", subjectId);
        String hql = "from TeacherSubject t where userId =:teacherId and subjectId=:subjectId";
        list = this.dao.find(hql, params);
		return list;
	}
	
	
}
