package com.Action;

import com.Entity.NotificationClass;
import com.Entity.NotificationInfo;
import com.Service.NCService;
import com.Service.NotificationService;
import com.Service.StudentService;
import com.alibaba.fastjson.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tan on 2017/3/7.
 */
public class NotificationAction extends BaseAction {
    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int                 flag        = 0;
    private JSONObject returnjson  = new JSONObject();
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private NCService ncService;


    public void addNotify() throws Exception{
        try {
            int teacherId = getIntFromGet("teacherId");
            String content = getStringFromGet("content");
            String classIdsStr = getStringFromGet("classId");
            String[] classIdStrs = classIdsStr.split("|");
            Date date = new Date();
            int notId = (int)date.getTime();

            NotificationInfo notificationInfo = new NotificationInfo();
            notificationInfo.setNotificationId(notId);
            notificationInfo.setContent(content);
            notificationInfo.setTeacherId(teacherId);
            notificationInfo.setAssignTime(date);
            this.notificationService.save(notificationInfo);
            if(classIdStrs!=null && classIdStrs.length>0){
                for(String classIdS : classIdStrs){
                    int classId = Integer.parseInt(classIdS);
                    NotificationClass notificationClass = new NotificationClass();
                    notificationClass.setNotificationId(notId);
                    notificationClass.setClassId(classId);
                    this.ncService.save(notificationClass);
                }
            }
            flag = 1;
            List<NotificationInfo> notificationInfos = this.notificationService.getByTagId(teacherId,"teacherId");
            returnjson.put("notificationInfos",notificationInfos);
        }catch (Exception e){
            flag = 2;
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag", flag);
            writeJson(returnjson);
        }
    }
    public void deleteNotify() throws Exception{
        try {
            int noId = getIntFromGet("notifyId");
            NotificationInfo no = this.notificationService.getById(noId);
            if(no == null){
                flag = 3;
                returnjson.put("error","此通知不存在");
            }else {
                this.notificationService.delete(no);
                flag = 1;
                returnjson.put("success","删除通知成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            flag = 2;
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }
    public void updateNotifyContent() throws Exception{
        try {
            int noId = getIntFromGet("notifyId");
            String content = getStringFromGet("content");
            NotificationInfo no = this.notificationService.getById(noId);
            if(content.equals("")){
                flag = 3;
                returnjson.put("error","通知内容不能为空");
            }else {
                no.setContent(content);
                flag = 1;
                returnjson.put("success","更新通知成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            flag = 2;
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }
    public void getNotifyByT() throws Exception{
        try {
            int teacherId = getIntFromGet("teacerId");
            List<NotificationInfo> notis = this.notificationService.getByTagId(teacherId,"teacherId");
            flag = 1;
            returnjson.put("notificationInfos",notis);

        }catch (Exception e){
            e.printStackTrace();
            flag = 2;
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }
    public void getNotifyByS() throws Exception{
        try {
            int studentId = getIntFromGet("studentId");
            int classId = this.studentService.getByTagId(studentId,"studentId").get(0).getClassId();
            List<NotificationClass> ncs = this.ncService.getByTagId(classId,"classId");
            List<NotificationInfo> notificationInfos = new ArrayList<>();
            for(NotificationClass nc : ncs){
                NotificationInfo n = this.notificationService.getByTagId(nc.getNotificationId(),"notificationId").get(0);
                notificationInfos.add(n);
            }
            flag = 1;
            returnjson.put("notificationInfos",notificationInfos);
            

        }catch (Exception e){
            flag = 2;
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag", flag);
            writeJson(returnjson);
        }
    }

}
