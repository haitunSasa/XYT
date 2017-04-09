package com.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Entity.*;
import com.Service.StudentService;
import com.alibaba.fastjson.JSONObject;
import com.util.base.DateUtil;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.Service.HomeworkService;
import com.Service.HomeworkStudentService;

/**
 * Homework_*Action
 * 教师添加作业：Homework_addHomeworkAction(POST)
 * 教师修改作业：Homework_updateHomeworkAction
 * 教师删除作业：Homework_deleteHomeworkAction
 * 学生查看作业：Homework_sgetHomeworkAction
 * 教师查看作业：Homework_tgetHomeworkAction
 * 查看班级作业：Homework_getByClassIdAction
 * 学生完成作业：Homework_doHomeworkAction
 * 家长修改作业时间：Homework_changeHTimeAction
 * 家长签字：Homework_signHomeworkAction
 * 根据时间查找作业:Homework_getHByDateAndTAction
 */
public class HomeworkAction extends BaseAction implements ServletResponseAware {

    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int                flag = 0;
    private JSONObject  returnjson  = new JSONObject();

    @Autowired
    private HomeworkService  homeworkService;
    @Autowired
    private HomeworkStudentService homeworkStudentService;
    @Autowired
	private StudentService studentService;
	
    private List<HomeworkInfo>   list = new ArrayList<HomeworkInfo>();
    //添加作业
    public void addHomework() throws Exception{
    	try{
    		HomeworkInfo homeworkInfo = new HomeworkInfo();
    		homeworkInfo.setAssignTime(new Date());
    		homeworkInfo.setClassId(getIntFromPost("classId"));
    		homeworkInfo.setContent(getStringFromPost("content"));
    		homeworkInfo.setPlantime(getIntFromPost("plantime"));
    		homeworkInfo.setSubjectId(getIntFromPost("subjectId"));
    		homeworkInfo.setTeacherId(getIntFromPost("teacherId"));
    		homeworkInfo.setTitle(getStringFromPost("title"));
    		this.homeworkService.save(homeworkInfo);
    		flag = 1;
			returnjson.put("success", "添加作业成功！");
    		returnjson.put("flag", flag);
			writeJson(returnjson);
    	}catch (Exception e) {
				e.printStackTrace();
		}
    		
   }
    
    public void updateHomework() throws Exception{
    	try{
    		
    		int homeworkId = getIntFromPost("homeworkId");
    		HomeworkInfo oldHomework = this.homeworkService.getById(homeworkId);
    		
    		if (oldHomework == null) {
    			flag = 2;
    			returnjson.put("error","修改作业不存在");
    		} else {
    			oldHomework.setAssignTime(new Date());
				oldHomework.setClassId(getIntFromPost("classId"));
				oldHomework.setContent(getStringFromPost("content"));
				oldHomework.setPlantime(getIntFromPost("plantime"));
				oldHomework.setSubjectId(getIntFromPost("subjectId"));
				oldHomework.setTeacherId(getIntFromPost("teacherId"));
				oldHomework.setTitle(getStringFromPost("title"));
    			this.homeworkService.update(oldHomework);
    			flag = 1;
				returnjson.put("success", "修改作业成功");
    		}
    		
    		returnjson.put("flag", flag);
			writeJson(returnjson);
    	} catch (Exception e) {
				e.printStackTrace();
		}
    }
    
    public void deleteHomework() throws Exception{
    	
    	int homeworkId = getIntFromGet("homeworkId");
		HomeworkInfo oldHomework = this.homeworkService.getById(homeworkId);
    	try{
    		
	    	if (oldHomework == null) {
	    		flag = 2;//要删除的作业不存在
				returnjson.put("error","删除作业不存在");
	    	} else {
	    		this.homeworkService.delete(oldHomework);
	    		flag = 1;
				returnjson.put("success","删除作业不存在");
	    	}
	    	
	    	returnjson.put("flag", flag);
			writeJson(returnjson);
    	} catch (Exception e) {
			e.printStackTrace();
	}
  }
    
    public void tgetHomework() throws Exception{
    	
    	int teacherId = getIntFromGet("teacherId");
		List<HomeworkInfo> list = this.homeworkService.getByTagId(teacherId,"teacherId") ;
    	try{
    		flag= 1;
			returnjson.put("homeworkList", list);
			returnjson.put("flag", flag);
			writeJson(returnjson);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sgetHomework() throws Exception{
		int studentId = getIntFromGet("studentId");
		try{
			StudentInfo studentInfo = this.studentService.getById(studentId);
			if(studentInfo == null){
				flag = 3;
				returnjson.put("error","该学生不存在");
			}else {
				int classId = studentInfo.getClassId();
				List<HomeworkInfo> homeworkInfos = this.homeworkService.getByTagId(classId, "classId");
				List<HomeworkStudent> homeworkStudents = this.homeworkStudentService.getByTagId(studentId, "studentId");
				List<HHS> hhsList = new ArrayList<>();
				//查看家长是否修改完成作业时间
				for (HomeworkInfo h : homeworkInfos) {
					HHS hhs = new HHS();
					hhs.setHomeworkId(h.getHomeworkId());
					hhs.setPlantime(h.getPlantime());
					hhs.setClassId(h.getClassId());
					hhs.setContent(h.getContent());
					hhs.setTitle(h.getTitle());
					hhs.setTeacherId(h.getTeacherId());
					hhs.setAssignTime(h.getAssignTime());
					hhs.setSubjectId(h.getSubjectId());
					for (HomeworkStudent hs : homeworkStudents) {
						if (hs.getHomeworkId() == h.getHomeworkId()) {
							hhs.setChangeTime(hs.getChangeTime());
							hhs.setFinishTime(hs.getFinishTime());
							hhs.setIsChangeTime(hs.getIsChangeTime());
							hhs.setIsFinishied(hs.getIsFinishied());
							hhs.setIsSignend(hs.getIsSignend());
							hhs.setSignParent(hs.getSignParent());
							hhs.setStudentId(studentId);
						}
					}
					hhsList.add(hhs);

				}

				flag = 1;
				returnjson.put("homeworkList", hhsList);
			}
		} catch (Exception e) {
			flag = 2;
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			returnjson.put("flag", flag);
			writeJson(returnjson);
		}
	}
   

    public void getByClassId() throws Exception {
    	
    	int classId = getIntFromGet("classId");
    	List<HomeworkInfo> homeworkList = this.homeworkService.getHomeworkByClassId(classId);
    	flag = 1;
		returnjson.put("homeworkList", homeworkList);
		returnjson.put("flag", flag);
		writeJson(returnjson);
    }
    
    public void changeHTime() throws Exception {

    	int homeworkId   = getIntFromGet("homeworkId");
    	int changeTime   = getIntFromGet("changeTime");
    	int studentId = getIntFromGet("studentId");
		try {
			HomeworkStudent hs = this.homeworkStudentService.getHSByHSid(studentId,homeworkId);
			if(hs != null){
				hs.setChangeTime(changeTime);
				hs.setIsChangeTime(1);
				this.homeworkStudentService.update(hs);
			}else {
				hs = new HomeworkStudent();
				hs.setStudentId(studentId);
				hs.setHomeworkId(homeworkId);
				hs.setChangeTime(changeTime);
				hs.setIsChangeTime(1);
				this.homeworkStudentService.save(hs);
			}
			flag = 1;
			returnjson.put("flag", flag);
			returnjson.put("success", "修改作业完成时间成功");
			writeJson(returnjson);

		}catch (Exception e){
			e.printStackTrace();
		}

    }

	public void doHomework() throws Exception {

		int homeworkId   = getIntFromGet("homeworkId");
		int studentId        = getIntFromGet("studentId");
		int finishTime       = getIntFromGet("finishTime");
		try {
			HomeworkStudent hs = this.homeworkStudentService.getHSByHSid(studentId,homeworkId);
			if(hs != null){
				hs.setFinishTime(finishTime);
				hs.setIsFinishied(1);
				this.homeworkStudentService.update(hs);
			}else {
				hs = new HomeworkStudent();
				hs.setStudentId(studentId);
				hs.setHomeworkId(homeworkId);
				hs.setFinishTime(finishTime);
				hs.setIsFinishied(1);
				this.homeworkStudentService.save(hs);
			}
			flag = 1;
			returnjson.put("flag", flag);
			returnjson.put("success", "学生完成作业成功");
			writeJson(returnjson);

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void signHomework() throws Exception {

		int homeworkId   = getIntFromGet("homeworkId");
		int studentId        = getIntFromGet("studentId");
		int parentId       = getIntFromGet("parentId");
		try {
			HomeworkStudent hs = this.homeworkStudentService.getHSByHSid(studentId,homeworkId);
			if(hs != null){
				hs.setSignParent(parentId);
				hs.setIsSignend(1);
				this.homeworkStudentService.update(hs);
			}else {
				hs = new HomeworkStudent();
				hs.setStudentId(studentId);
				hs.setHomeworkId(homeworkId);
				hs.setSignParent(parentId);
				hs.setIsSignend(1);
				this.homeworkStudentService.save(hs);
			}
			flag = 1;
			returnjson.put("flag", flag);
			returnjson.put("success", "签字作业成功");
			writeJson(returnjson);

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void getHByDateAndT() throws Exception{
    	try {
    		String dateStr = getStringFromGet("date");
    		int dateC = DateUtil.isValidDate(dateStr,"-");
    		if(dateC>0){
				int teacherId = getIntFromGet("teacherId");
				List<HomeworkInfo> hList = this.homeworkService.getHomeworkByDate(dateStr,teacherId,dateC);
				returnjson.put("homeworkList",hList);
				flag = 1;
			}else {
    			flag = 2;
    			returnjson.put("error","日期格式不正确");
			}


		}catch (Exception e){
    		flag = 2;
			returnjson.put("error",e.toString());
		}finally {
			returnjson.put("flag", flag);
			writeJson(returnjson);
		}
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
    }
}
