package com.Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.Entity.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.util.base.getChinaString;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.Entity.SubjectInfo;
import com.Entity.TeacherSubject;
import com.Entity.Page.Json;
import com.Service.SubjectService;
import com.Service.TSService;
import com.Service.UserService;

/**
 * Subject_*Action
 * 查看科目列表：Subject_getSubjectsAction
 * 添加科目：Subject_addSubjectAction
 * 删除科目：Subject_deleteSubjectAction
 * 添加老师教授科目：Subject_addSOfTAction
 * 查看老师教授科目：Subject_getSOfTAction
 * 删除老师教授科目：Subject_deleteSOfTAction
 * 查看教授某科目的老师：Subject_getTOfSAction
 */

public class SubjectAction extends BaseAction implements ServletResponseAware {

    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int                 flag        = 0;
    private JSONObject          returnjson  = new JSONObject();

    @Autowired
    private SubjectService  subjectService;
    
    @Autowired
    private TSService tSService;

    @Autowired
	private UserService userService;
	
    private List<SubjectInfo>   list = new ArrayList<SubjectInfo>();

    public void getSubjects() throws Exception{
    	try {
    		returnjson.put("flag",1);
    		returnjson.put("subjectList",this.subjectService.find());
		}catch (Exception e){
    		returnjson.put("error",e.toString());
    		e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void addSubject() throws Exception{
		try {
			String subjectName = getStringFromGet("subjectName");
			List<SubjectInfo> subjectInfos = this.subjectService.getByTagString(subjectName,"subjectName");
			if(subjectInfos != null && subjectInfos.size()>0){
				returnjson.put("flag",2);
				returnjson.put("error","科目名不可重复");
			}else{
				SubjectInfo subjectInfo = new SubjectInfo();
				subjectInfo.setSubjectName(subjectName);
				this.subjectService.save(subjectInfo);
				returnjson.put("flag",1);
				returnjson.put("success","添加科目成功");
			}

		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void deleteSubject() throws Exception{
		try {
			int subjectId = getIntFromGet("subjectId");
			SubjectInfo subjectInfo = this.subjectService.getById(subjectId);
			if(subjectInfo == null){
				returnjson.put("flag",2);
				returnjson.put("error","科目不存在");
			}else {
				this.subjectService.delete(subjectInfo);
				returnjson.put("flag",1);
				returnjson.put("success","删除科目成功");
			}
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void addSOfT() throws Exception{
		try {
			int teacherId = getIntFromGet("teacherId");
			int subjectId = getIntFromGet("subjectId");
			SubjectInfo subjectInfo = this.subjectService.getById(subjectId);
			if(subjectInfo == null){
				returnjson.put("flag",2);
				returnjson.put("error","科目不存在");
			}else {
				List<TeacherSubject> teacherSubjects = this.tSService.getTSByTidAndSid(teacherId,subjectId);
				if(teacherSubjects != null && teacherSubjects.size()>0){
					returnjson.put("flag",2);
					returnjson.put("error","关系已存在");
				}else {
					TeacherSubject teacherSubject = new TeacherSubject();
					teacherSubject.setUserId(teacherId);
					teacherSubject.setSubjectId(subjectId);
					this.tSService.save(teacherSubject);
					returnjson.put("flag",1);
					returnjson.put("success","添加关系成功");
				}
			}

		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void deleteSOfT() throws Exception{
		try {
			int teacherId = getIntFromGet("teacherId");
			int subjectId = getIntFromGet("subjectId");
			SubjectInfo subjectInfo = this.subjectService.getById(subjectId);
			List<TeacherSubject> teacherSubjects = this.tSService.getTSByTidAndSid(teacherId,subjectId);
			if(teacherSubjects == null && teacherSubjects.size()==0){
				returnjson.put("flag",2);
				returnjson.put("error","关系不存在");
			}else {
				this.tSService.delete(teacherSubjects.get(0));
				returnjson.put("flag",1);
				returnjson.put("success","删除关系成功");
			}
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void getSOfT() throws Exception{
		try {
			int teacherId = getIntFromGet("teacherId");
			List<TeacherSubject> teacherSubjects = this.tSService.getTSByTeacherId(teacherId);
			if(teacherSubjects == null && teacherSubjects.size() == 0){
				returnjson.put("flag",2);
				returnjson.put("error","关系不存在");
			}else {
				returnjson.put("flag",1);
				List<SubjectInfo> subjectInfos = new ArrayList<>();
				for(TeacherSubject ts : teacherSubjects){
					SubjectInfo subjectInfo = this.subjectService.getById(ts.getSubjectId());
					if(subjectInfo!=null){
						subjectInfos.add(subjectInfo);
					}
				}
				returnjson.put("subjectList",subjectInfos);
			}
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void getTOfS() throws Exception{
		try {
			int subjectId = getIntFromGet("subjectId");
			List<TeacherSubject> teacherSubjects = this.tSService.getByTagId(subjectId,"subjectId");
			if(teacherSubjects == null && teacherSubjects.size()==0){
				returnjson.put("flag",2);
				returnjson.put("error","关系不存在");
			}else {
				returnjson.put("flag",1);
				List<UserInfo> userInfos = new ArrayList<>();
				for(TeacherSubject ts : teacherSubjects){
					UserInfo userInfo = this.userService.getById(ts.getUserId());
					if(userInfo != null){
						userInfos.add(userInfo);
					}
				}
				returnjson.put("userList",userInfos);
			}
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}

    

    public void setServletResponse(HttpServletResponse response) {

        // TODO Auto-generated method stub
        this.response = response;
    }
    
    

}
