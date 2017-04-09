package com.Service;

import java.util.Date;
import java.util.List;

import com.Entity.HomeworkInfo;
import com.Entity.TeacherSubject;
import com.Entity.UserInfo;

public interface HomeworkService extends BaseServiceI<HomeworkInfo> {
	
    public  List<HomeworkInfo>  getHomeworkByTeacherId(int teacherId);
    public  List<HomeworkInfo>  getHomeworkByClassId(int classId);
    public  List<HomeworkInfo>  getHomeworkByDate(String date, int teacherId, int dateC);
    public  List<HomeworkInfo>  getHomeworkByDateForC(String date, int classId, int dateC);


}