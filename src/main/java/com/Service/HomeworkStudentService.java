package com.Service;

import java.util.List;

import com.Entity.HomeworkInfo;
import com.Entity.HomeworkStudent;
import com.Entity.TeacherSubject;
import com.Entity.UserInfo;

public interface HomeworkStudentService extends BaseServiceI<HomeworkStudent> {
	
    public  List<HomeworkStudent>  getHomeworkByStudentId(int studentId);
    public  List<HomeworkStudent>  getHomeworkByHomeworkId(int homeworkId);
    public HomeworkStudent getHSByHSid(int studentId, int homeworkId);
    
}