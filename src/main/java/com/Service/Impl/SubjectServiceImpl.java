package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.SubjectInfo;
import com.Entity.UserInfo;
import com.Service.SubjectService;
import com.Service.UserService;
@Service("subjectService")
public class SubjectServiceImpl extends BaseServiceImpl<SubjectInfo> implements SubjectService{
	@Autowired 
	private BaseDaoI<SubjectInfo> dao;
	private List<SubjectInfo> list = new ArrayList<SubjectInfo>();
	
	@Override
	public List<SubjectInfo> getSubjectBySubjectName(String subjectName) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("subjectName", subjectName);
        
        String hql = "from SubjectInfo t where subjectName =:subjectName";
        list = this.dao.find(hql, params);
		return list;
	}
	
	/*@Override
	public List<AppointInfo> getAppointListByDateAndDoctorId(int doctorId,
			Date selectDate) {
		Date startDate = new Date(selectDate.getYear(), selectDate.getMonth(), selectDate.getDate(),7,0,0);
		System.out.println("开始时间"+startDate.toGMTString());
		Date stopDate = new Date(selectDate.getYear(), selectDate.getMonth(), selectDate.getDate(),30,0,0);
		System.out.println("开始时间"+stopDate.toGMTString());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doctorId", doctorId);
	    params.put("startDate", startDate);
		params.put("stopDate", stopDate);
		String hql = "from AppointInfo t where t.doctorId = :doctorId and t.diagnosisTime between :startDate and :stopDate";
		//String hql = "from AppointInfo t where t.doctorId = :doctorId and t.diagnosisTime BETWEEN '2016-4-10 00:00:15' AND '2016-4-10 14:12:11'";
		appointList = this.apDao.find(hql,params);
		System.out.println("列表长度为"+this.appointList.size());
		return appointList;
	}
	 @Override
	    public List<AppointInfo> findByPatientId(int patientId) {

	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("patientId", patientId);

	        String hql = "from AppointInfo t where patientId =:patientId order by regTime desc";
	        appointList = this.apDao.find(hql, params);
	        return appointList;

	    }*/

}
