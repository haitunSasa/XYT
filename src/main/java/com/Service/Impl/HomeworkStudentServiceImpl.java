package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.HomeworkStudent;
import com.Entity.UserInfo;
import com.Service.HomeworkService;
import com.Service.HomeworkStudentService;
import com.Service.SubjectService;
import com.Service.UserService;

@Service("homeworkStudentService")
public class HomeworkStudentServiceImpl extends BaseServiceImpl<HomeworkStudent> implements HomeworkStudentService{
	@Autowired 
	private BaseDaoI<HomeworkStudent> dao;
	private List<HomeworkStudent> list = new ArrayList<HomeworkStudent>();

	@Override
	public List<HomeworkStudent> getHomeworkByStudentId(int studentId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentId", studentId);
        
        String hql = "from HomeworkStudent t where studentId =:studentId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<HomeworkStudent> getHomeworkByHomeworkId(int homeworkId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("homeworkId", homeworkId);
        
        String hql = "from HomeworkStudent t where homeworkId =:homeworkId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public HomeworkStudent getHSByHSid(int studentId, int homeworkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("homeworkId", homeworkId);
		params.put("studentId", studentId);

		String hql = "from HomeworkStudent t where homeworkId =:homeworkId and userId =:studentId ";
		HomeworkStudent hs = this.dao.getByHql(hql, params);
		return hs;
	}
}
