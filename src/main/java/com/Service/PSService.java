package com.Service;

import com.Entity.ParentStudent;

import java.util.List;

public interface PSService extends BaseServiceI<ParentStudent>{
//	public  List<ParentStudent>  getUserByUserName(String userName);
    public ParentStudent getByPIdAndSId(int parentId , int studentId);
}
