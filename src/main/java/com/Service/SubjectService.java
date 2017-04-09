package com.Service;

import java.util.List;

import com.Entity.SubjectInfo;

public interface SubjectService extends BaseServiceI<SubjectInfo>{
	public List<SubjectInfo> getSubjectBySubjectName(String subjectName);
}