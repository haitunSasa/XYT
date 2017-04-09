package com.Service;

import java.util.List;

import com.Entity.ClassInfo;
import com.Entity.HomeworkInfo;
import com.Entity.SubjectInfo;
import com.Entity.TeacherSubject;
import com.Entity.UserInfo;

public interface ClassService extends BaseServiceI<ClassInfo> {
	public List<ClassInfo> getClassByClassName(String className);
}