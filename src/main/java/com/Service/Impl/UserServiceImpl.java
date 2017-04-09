package com.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BaseDaoI;
import com.Entity.UserInfo;
import com.Service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserInfo> implements UserService{
	@Autowired 
	private BaseDaoI<UserInfo> dao;
	private List<UserInfo> list = new ArrayList<UserInfo>();
	@Override
	public List<UserInfo> getUserByUserName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", userName);
        
        String hql = "from UserInfo t where userName =:userName";
        list = this.dao.find(hql, params);
		return list;
	}
	

}
