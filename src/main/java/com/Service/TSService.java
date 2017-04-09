package com.Service;

import java.util.List;

import com.Entity.TeacherSubject;
import com.Entity.UserInfo;

public interface TSService extends BaseServiceI<TeacherSubject>{
	public  List<TeacherSubject>  getTSByTeacherId(int teacherId);
	public List<TeacherSubject> getTSByTidAndSid(int teacherId, int subjectId);
}
