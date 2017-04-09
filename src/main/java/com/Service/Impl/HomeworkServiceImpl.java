package com.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.HomeworkInfo;
import com.Entity.UserInfo;
import com.Service.HomeworkService;
import com.Service.SubjectService;
import com.Service.UserService;

@Service("homeworkService")
public class HomeworkServiceImpl extends BaseServiceImpl<HomeworkInfo> implements HomeworkService{
	@Autowired 
	private BaseDaoI<HomeworkInfo> dao;
	private List<HomeworkInfo> list = new ArrayList<HomeworkInfo>();

	@Override
	public List<HomeworkInfo> getHomeworkByTeacherId(int teacherId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("teacherId", teacherId);
        
        String hql = "from HomeworkInfo t where teacherId =:teacherId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<HomeworkInfo> getHomeworkByClassId(int classId) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("classId", classId);
        
        String hql = "from HomeworkInfo t where classId =:classId";
        list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<HomeworkInfo> getHomeworkByDate(String date, int teacherId, int dateC) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("assignTime", date);
		params.put("teacherId", teacherId);
		System.out.println(date);
		String dateHql = "";
		switch (dateC){
			case 1:dateHql = "date_format(assignTime,'%Y')";break;
			case 2:dateHql = "date_format(assignTime,'%Y-%m')";break;
			case 3:dateHql = "date_format(assignTime,'%Y-%m-%d')";break;
		}
		String hql = "from HomeworkInfo t where "+dateHql+" =:assignTime and teacherId =:teacherId order by assignTime";
		list = this.dao.find(hql, params);
		return list;
	}

	@Override
	public List<HomeworkInfo> getHomeworkByDateForC(String date, int classId, int dateC) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("assignTime", date);
		params.put("classId", classId);
		System.out.println(date);
		String dateHql = "";
		switch (dateC){
			case 1:dateHql = "date_format(assignTime,'%Y')";break;
			case 2:dateHql = "date_format(assignTime,'%Y-%m')";break;
			case 3:dateHql = "date_format(assignTime,'%Y-%m-%d')";break;
		}
		String hql = "from HomeworkInfo t where "+dateHql+" =:assignTime and classId =:classId order by assignTime";
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
