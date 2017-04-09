package com.Service;

import java.util.List;

import com.Entity.TeacherClass;
import com.Entity.TeacherSubject;
import com.Entity.UserInfo;

public interface TCService extends BaseServiceI<TeacherClass>{
	public List<TeacherClass> getTCByTeacherId(int teacherId);
	public List<TeacherClass> getTCByClassId(int classId);
	TeacherClass getTCByTIdAndCId(int teacherId, int classId);
}
