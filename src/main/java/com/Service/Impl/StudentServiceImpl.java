package com.Service.Impl;

import com.Entity.StudentInfo;
import com.Service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Created by tan on 2017/3/7.
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<StudentInfo> implements StudentService {
}
