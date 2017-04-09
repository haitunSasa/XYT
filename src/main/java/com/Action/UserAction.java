package com.Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.Entity.ParentStudent;
import com.Service.PSService;
import com.alibaba.fastjson.JSONObject;
import com.util.base.getChinaString;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.Entity.UserInfo;
import com.Entity.Page.Json;
import com.Service.UserService;

/**
 * User_*Action:用户模块
 * 获取用户信息：User_getUserAction?userId=
 * 添加用户：User_addUserAction（POST:userName,userRole,password）
 * 更新用户信息：User_updateUserAction（POST:userName,userRole）
 * 删除用户信息：User_deleteUserAction?userId=
 * 由用户名查询用户信息：User_getByUserNameAction
 * 添加父子关系：User_addPSAction
 * 删除父子关系：User_deletePSAction
 * 查看孩子的父母：User_getPOfSAction
 * 查看父母的孩子：User_getSOfPAction
 */
public class UserAction extends BaseAction implements ServletResponseAware {

    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int                 flag        = 0;
    private JSONObject          returnjson  = new JSONObject();

    @Autowired
    private UserService  userService;
	@Autowired
	private PSService psService;
    private List<UserInfo>   list = new ArrayList<UserInfo>();


    //登陆
	public void login() throws Exception{
		System.out.println("登陆");
		String phoneNumber,password;
		try{
			JSONObject jsonObject = getJSONObjectFromJson();
			if(jsonObject == null){
				phoneNumber = getStringFromPost("phone");
				password = getStringFromPost("password");
			}else {
				phoneNumber = jsonObject.getString("phone");
				password = jsonObject.getString("password");
			}
			if(checkString(phoneNumber,"phone")&&checkString(password,"password")){
				List<UserInfo> userInfoList = this.userService.getByTagString(phoneNumber,"phone");
				if(userInfoList != null && userInfoList.size()>0){
					System.out.println(userInfoList.get(0).getPhone());
					if(userInfoList.get(0).getPassword().equals(password)){
						returnjson.put("success","登陆成功");
						returnjson.put("userInfo",userInfoList.get(0));
						flag = 1;
					}else {
						flag = 3;
						returnjson.put("error","密码错误");
					}
				}else {
					flag = 3;
					returnjson.put("error","用户不存在");
				}

			}else{
				flag = 2;
				returnjson.put("error","手机号或密码不能为空");
			}

		}catch (Exception e){
			flag = 2;
			e.printStackTrace();
			returnjson.put("error",e.toString());
		}finally {
			returnjson.put("flag", flag);
			writeJson(returnjson);
		}
	}
    //注册
	public void register() throws Exception{
		System.out.println("注册用户");
		String phoneNumber,userName,password;
		int userRole;
		try{
			JSONObject jsonObject = getJSONObjectFromJson();
			if(jsonObject == null){
				phoneNumber = getStringFromPost("phone");
				userName = getStringFromPost("userName");
				password = getStringFromPost("password");
				userRole = getIntFromPost("userRole");
			}else {
				phoneNumber = jsonObject.getString("phone");
				userName = jsonObject.getString("userName");
				password = jsonObject.getString("password");
				userRole = jsonObject.getInteger("userRole");
			}
			String phoneRegEx = "^1[3|4|5|7|8][0-9]\\d{8}$";
			Pattern pattern1 = Pattern.compile(phoneRegEx);
			Matcher phoneMatcher = pattern1.matcher(phoneNumber);
			String passwordRegEx = "^[a-zA-Z][\\w/^]{7,31}$";
			Pattern pattern2 = Pattern.compile(passwordRegEx);
			Matcher passwordMatcher = pattern2.matcher(password);
			System.out.println("手机号匹配"+phoneMatcher.matches());
			System.out.println("密码匹配"+passwordMatcher.matches());
			if(phoneMatcher.matches() && passwordMatcher.matches()){
				List<UserInfo> checkUser = this.userService.getByTagString(phoneNumber,"phone");
				if(checkUser!=null&&checkUser.size()>0){
					flag = 3;
					returnjson.put("error","该手机号已被注册");
				}else {
					UserInfo userInfo = new UserInfo();
					userInfo.setPassword(password);
					userInfo.setPhone(phoneNumber);
					userInfo.setRegTime(new Date());
					userInfo.setUserName(userName);
					userInfo.setUserRole(userRole);
					this.userService.save(userInfo);
					UserInfo saveU = this.userService.getByTagString(phoneNumber,"phone").get(0);
					returnjson.put("userInfo",saveU);
					returnjson.put("success","注册成功");
					flag = 1;
				}
			}else{
				flag = 2;
				returnjson.put("error","手机号或密码格式不正确");
			}

		}catch (Exception e){
			flag = 2;
			e.printStackTrace();
			returnjson.put("error",e.toString());
		}finally {
			returnjson.put("flag", flag);
			writeJson(returnjson);
		}
	}
    //添加用户
    public void addUser() throws Exception{
		System.out.println("添加用户");
		try{
    		String userName = getStringFromPost("userName");
    		String password = getStringFromPost("password");
    		int userRole = getIntFromPost("userRole");
    		UserInfo newUser = new UserInfo();
    		List<UserInfo> checkUser = this.userService.getUserByUserName(userName); //用户名不可重复
    		if(checkUser.size()>0){
    			flag = 2; //用户已存在
				returnjson.put("error", "用户已存在，用户名不能重复");
    		}else{

    			newUser.setUserName(userName);
    			newUser.setUserRole(userRole);
    			newUser.setPassword(password);
    			newUser.setRegTime(new Date());
    			this.userService.save(newUser);
    			flag = 1;
				returnjson.put("user", newUser);
				returnjson.put("success", "添加用户成功");
    		}
    		returnjson.put("flag", flag);
			writeJson(returnjson);
    	}catch (Exception e) {
				e.printStackTrace();
		}
    		
   }
    //更新用户
    public void updateUser() throws Exception{
		System.out.println("更新用户信息");
		try{
			String userName = getStringFromPost("userName");
			int userRole = getIntFromPost("userRole");
			List<UserInfo> checkUser = this.userService.getUserByUserName(userName); //用户名不可重复
			if(checkUser.size()== 0){
				flag = 2;
				returnjson.put("erro", "用户不存在");
			}else{
				checkUser.get(0).setUserName(userName);
				checkUser.get(0).setUserRole(userRole);
				this.userService.update(checkUser.get(0));
				flag = 1;
				returnjson.put("success", "更新用户成功");
			}
			returnjson.put("flag", flag);
			writeJson(returnjson);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
    //删除用户
    public void deleteUser() throws Exception{
		System.out.println("删除用户信息");
    	int userId = getIntFromGet("userId");
    	try{
	    	UserInfo deleteUser = this.userService.getById(userId);
	    	if(deleteUser == null){
	    		flag = 2;//删除用户不存在
				returnjson.put("error", "删除用户不存在");
	    	}else{
	    		this.userService.delete(deleteUser);
	    		flag = 1;
				returnjson.put("success", "删除用户成功！");
	    	}
	    	returnjson.put("flag", flag);
			writeJson(returnjson);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
    	
  }
	//获取用户
    public void getUser() throws Exception{
		System.out.println("查询用户信息");
    	int userId = getIntFromGet("useId");
    	try{
	    	UserInfo getUser = this.userService.getById(userId);
	    	if(getUser == null){
	    		flag = 2;
	    		returnjson.put("error","用户不存在");
	    	}else{
	    		flag = 1;
	    	}
	    	returnjson.put("flag", flag);
	    	returnjson.put("user", getUser);
			writeJson(returnjson);
    	}catch (Exception e) {
			e.printStackTrace();
	}
    }
	//添加父子关系
	public void  addPS() throws Exception{
		try {
			int parentId = getIntFromGet("parentId");
			int studentId = getIntFromGet("studentId");
			UserInfo parent = this.userService.getById(parentId);
			UserInfo student = this.userService.getById(studentId);
			if(parent.getUserRole() != 2 || student.getUserRole() != 3){
				returnjson.put("flag",2);
				returnjson.put("error","用户角色不符");
			}else {
				ParentStudent parentStudent = this.psService.getByPIdAndSId(parentId,studentId);
				if(parentStudent != null){
					returnjson.put("flag",2);
					returnjson.put("error","关系已存在");
				}else{
					parentStudent = new ParentStudent();
					parentStudent.setParentId(parentId);
					parentStudent.setStudentId(studentId);
					this.psService.save(parentStudent);
					returnjson.put("flag",1);
					returnjson.put("success","关系新建成功");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			returnjson.put("flag",2);
			returnjson.put("error",e.toString());
		}finally {
			writeJson(returnjson);
		}

	}
	public void  deletePS() throws Exception{
		try {
			int parentId = getIntFromGet("parentId");
			int studentId = getIntFromGet("studentId");
			UserInfo parent = this.userService.getById(parentId);
			UserInfo student = this.userService.getById(studentId);
			if(parent.getUserRole() != 2 || student.getUserRole() != 3){
				returnjson.put("flag",2);
				returnjson.put("error","用户角色不符");
			}else {
				ParentStudent parentStudent = this.psService.getByPIdAndSId(parentId,studentId);
				if(parentStudent == null){
					returnjson.put("flag",2);
					returnjson.put("error","关系不存在");
				}else{
					this.psService.delete(parentStudent);
					returnjson.put("flag",1);
					returnjson.put("success","删除关系成功");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			returnjson.put("flag",2);
			returnjson.put("error",e.toString());
		}finally {
			writeJson(returnjson);
		}

	}

	public void getSOfP() throws Exception{
    	try {
    		int parentId = getIntFromGet("parentId");
    		List<ParentStudent> parentStudents = this.psService.getByTagId(parentId,"parentId");
    		if(parentStudents == null ){
				returnjson.put("flag",2);
				returnjson.put("error","关系不存在");
			}else {
    			List<UserInfo> students = new ArrayList<>();
    			for(ParentStudent ps :parentStudents){
    				if(ps != null){
    					UserInfo student = this.userService.getById(ps.getStudentId());
    					if(student != null){
    						students.add(student);
						}
					}
				}
				returnjson.put("flag",1);
    			returnjson.put("students",students);
			}

		}catch (Exception e){
			e.printStackTrace();
			returnjson.put("flag",2);
			returnjson.put("error",e.toString());
		}finally {
			writeJson(returnjson);
		}
	}
	public void getPOfS() throws Exception{
		try {
			int studentId = getIntFromGet("studentId");
			List<ParentStudent> parentStudents = this.psService.getByTagId(studentId,"studentId");
			if(parentStudents == null ){
				returnjson.put("flag",2);
				returnjson.put("error","关系不存在");
			}else {
				List<UserInfo> parents = new ArrayList<>();
				for(ParentStudent ps :parentStudents){
					if(ps != null){
						UserInfo parent = this.userService.getById(ps.getParentId());
						if(parent != null){
							parents.add(parent);
						}
					}
				}
				returnjson.put("flag",1);
				returnjson.put("parents",parents);
			}

		}catch (Exception e){
			e.printStackTrace();
			returnjson.put("flag",2);
			returnjson.put("error",e.toString());
		}finally {
			writeJson(returnjson);
		}
	}
	public void getByUserName() throws Exception{
		try {
			String userName = getStringFromGet("userName");
			List<UserInfo> userInfos = this.userService.getByTagString(userName,"userName");
			if(userInfos ==null && userInfos.size()== 0){
				returnjson.put("flag",2);
				returnjson.put("error","用户不存在");
			}else {
				returnjson.put("flag",1);
				returnjson.put("userInfo",userInfos.get(0));
			}
		}catch (Exception e){
			e.printStackTrace();
			returnjson.put("flag",2);
			returnjson.put("error",e.toString());
		}finally {
			writeJson(returnjson);
		}
	}





    public void setServletResponse(HttpServletResponse response) {

        // TODO Auto-generated method stub
        this.response = response;
    }
    
    

}
