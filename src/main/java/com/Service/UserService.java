package com.Service;

import java.util.List;

import com.Entity.UserInfo;

public interface UserService extends BaseServiceI<UserInfo>{
	public  List<UserInfo>  getUserByUserName(String userName);
}
