package com.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.Entity.*;
import com.Service.StudentService;
import com.alibaba.fastjson.JSONObject;
import com.util.base.ListSort;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.Service.ClassService;
import com.Service.TCService;
import com.Service.UserService;

/**
 * Class_*Action
 * 查看班级列表：Class_getClassesAction
 * 添加班级：Class_addClassAction
 * 删除班级：Class_deleteClassAction
 * 添加班级的学生：Class_addSToCAction
 * 删除班级的学生：Class_deleteSToCAction
 * 查看学生的班级：Class_getCofSAction
 * 查看班级的学生：Class_getSOfCAction
 * 查看老师的班级：Class_getCofTAction
 * 添加老师的班级：Class_addCofTAction
 * 删除老师的班级：Class_deleteCofTAction
 */
public class ClassAction extends BaseAction implements ServletResponseAware {

    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int               flag              = 0;
    private JSONObject returnjson  = new JSONObject();

    @Autowired
    private ClassService  classService;
    
    @Autowired
    private TCService tcService;
    
    @Autowired
    private StudentService studentService;

    @Autowired
	private UserService userService;
	
    private List<SubjectInfo>   list = new ArrayList<SubjectInfo>();

    public void getClasses() throws Exception{
    	try {
    		List<ClassInfo> classes = this.classService.find("from ClassInfo t");
    		flag = 1;
    		returnjson.put("flag",flag);
    		returnjson.put("classes",classes);
    		writeJson(returnjson);
		}catch (Exception e){
    		e.printStackTrace();
		}
	}
    
    public void addClass() throws Exception{
    	
    	String className = getStringFromGet("className");
    	try{
    		ClassInfo newClassInfo = new ClassInfo();
    		List<ClassInfo> checkUser = this.classService.getClassByClassName(className);
    		if (checkUser!= null && checkUser.size()>0) {
    			flag = 2;
    			returnjson.put("error","班级名称不可重复");
    		} else {
    			newClassInfo.setClassName(className);
    			this.classService.save(newClassInfo);
    			//ClassInfo classInfo = this.classService.getByTagString(className,"className").get(0);
    			returnjson.put("class",newClassInfo);
    			flag = 1;
    		}
    		returnjson.put("flag", flag);
			writeJson(returnjson);
    	} catch (Exception e) {
				e.printStackTrace();
		}
    		
    }
    
    public void deleteClass() throws Exception{
		int userId = getIntFromGet("classId");
    	try{
	    	ClassInfo deleteUser = this.classService.getById(userId);
	    	if(deleteUser == null){
	    		flag = 2;//删除用户不存在
				returnjson.put("error","删除班级不存在");
	    	}else{
	    		this.classService.delete(deleteUser);
	    		flag = 1;
				returnjson.put("success","删除班级成功");
	    	}
	    	returnjson.put("flag", flag);
			writeJson(returnjson);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
  }
  	public void addSToC() throws Exception{
		try {
			int studentId = getIntFromGet("studentId");
			int classId =getIntFromGet("classId");
			StudentInfo studentInfo = this.studentService.getById(studentId);
			ClassInfo classInfo = this.classService.getById(classId);
			if(classInfo == null){
				flag = 2;
				returnjson.put("error","此班级不存在");
			}else {
				studentInfo.setClassId(classId);
				this.studentService.update(studentInfo);
				flag = 1;
				returnjson.put("success","班级关系成功");
			}

		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			returnjson.put("flag",flag);
			writeJson(returnjson);
		}

	}

	public void getCOfS() throws Exception{
		try {
			int studentId = getIntFromGet("studentId");
			StudentInfo studentInfo = this.studentService.getById(studentId);
			if(studentInfo.getClassId() == null){
				flag = 3;
				returnjson.put("error","请绑定学生的班级");
			}else {
				ClassInfo classInfo = this.classService.getById(studentInfo.getClassId());
				flag = 1;
				returnjson.put("classInfo",classInfo);
			}

		}catch (Exception e){
			flag = 2;
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			returnjson.put("flag",flag);
			writeJson(returnjson);
		}
	}

	public void getSOfC() throws Exception{
		try {
			int classId =getIntFromGet("classId");
			List<StudentInfo> studentInfos = this.studentService.getByTagId(classId,"classId");
			ListSort listSort = new ListSort();
			Map<String,List> studentsMap = listSort.sort(studentInfos);
			flag = 1;
			returnjson.put("students",studentsMap);
			returnjson.put("flag",flag);
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}


	}
	public void getCofT() throws Exception{

		int tId = getIntFromGet("teacherId");

		try  {
			List<TeacherClass> tcs = this.tcService.getTCByTeacherId(tId);
			if( tcs== null){
				flag = 2;
				returnjson.put("error", "未查询到相关内容");
			}else{
				flag = 1;
				List<ClassInfo> classes = new ArrayList<>();
				for(TeacherClass tc : tcs){
					ClassInfo classInfo = this.classService.getById(tc.getClassId());
					if(classInfo!=null) classes.add(classInfo);
				}
				returnjson.put("flag", flag);
				returnjson.put("classList", classes);
			}

			writeJson(returnjson);
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void addCofT() throws Exception{

		int tId = getIntFromGet("teacherId");
		int classId = getIntFromGet("classId");

		try  {
			TeacherClass teacherClass = this.tcService.getTCByTIdAndCId(tId,classId);
			if( teacherClass != null){
				flag = 2;
				returnjson.put("error", "关系已存在");
			}else{
				flag = 1;
				teacherClass = new TeacherClass();
				teacherClass.setClassId(classId);
				teacherClass.setUserId(tId);
				this.tcService.save(teacherClass);
				returnjson.put("flag", flag);
				returnjson.put("success", "添加关系成功");
			}

			writeJson(returnjson);
		}catch (Exception e){
			returnjson.put("error",e.toString());
			e.printStackTrace();
		}finally {
			writeJson(returnjson);
		}
	}
	public void deleteCofT() throws Exception{

		int tId = getIntFromGet("teacherId");
		int classId = getIntFromGet("classId");

		try  {
			TeacherClass teacherClass = this.tcService.getTCByTIdAndCId(tId,classId);
			if( teacherClass == null){
				flag = 2;
				returnjson.put("error", "关系不存在");
			}else{
				flag = 1;
				this.tcService.delete(teacherClass);
				returnjson.put("flag", flag);
				returnjson.put("success", "删除关系成功");
			}

			writeJson(returnjson);
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
