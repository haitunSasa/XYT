package com.Action;

import com.Entity.*;
import com.Service.*;
import com.alibaba.fastjson.JSONObject;
import com.util.base.DateUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tan on 2017/3/7.
 * Data_getDataForTAction?teacherId=1&&date=2017-01-01
 * Data_getDataForPAction?parentId=2&&date=2017-01-01
 */
public class DataAction extends BaseAction {
    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int                 flag        = 0;
    private JSONObject returnjson  = new JSONObject();
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private ClockService clockService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PSService psService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private NCService ncService;
    @Autowired
    private HomeworkStudentService homeworkStudentService;
    @Autowired
    private DeviceService deviceService;
    public void getDataForT() throws Exception{
        try {
            int teacherId = getIntFromGet("teacherId");
            String dateStr = getStringFromGet("date");
            int dateC = DateUtil.isValidDate(dateStr,"-");
            if(dateC>0){
                List<HomeworkInfo> homeworkInfos = this.homeworkService.getHomeworkByDate(dateStr,teacherId,dateC);
                returnjson.put("homeworkInfos", homeworkInfos);
                List<NotificationInfo> notificationInfos = this.notificationService.getNotyByDateForT(teacherId,dateStr,dateC);
                returnjson.put("notificationInfos",notificationInfos);
                flag = 1;
            }else {
                flag = 2;
                returnjson.put("error","日期格式不正确");
            }
        }catch (Exception e){
            flag = 2;
            e.printStackTrace();
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }

    public void getDataForP() throws Exception{
        try {
            int parentId = getIntFromGet("parentId");
            String dateStr = getStringFromGet("date");
            int dateC = DateUtil.isValidDate(dateStr,"-");
            List<ParentStudent> ps = this.psService.getByTagId(parentId,"parentId");
            if(ps.size()>0){
                int studentId = ps.get(0).getStudentId();
                int classId = this.studentService.getById(studentId).getClassId();
                if(dateC>0){
                    DateFormat sdf = new SimpleDateFormat("YYYY-MM-HH");
                    Date date =  sdf.parse(dateStr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
                    List<ClockInfo> findClockInfo = this.clockService.getByTagId(studentId,"studentId");
                    List<ClockInfo> clockInfos = new ArrayList<>();
                    for(ClockInfo clockInfo : clockInfos){
                        String[] hasCl = clockInfo.getClockDay().split("");
                        if(hasCl[w].equals("1"))clockInfos.add(clockInfo);
                    }
                    returnjson.put("clockInfos",clockInfos);
                    List<NotificationClass> ncs = this.ncService.getByTagId(classId,"classId");
                    List<NotificationInfo> notificationInfos = new ArrayList<>();
                    if(notificationInfos.size()>0){
                        for(NotificationClass nc : ncs){
                            NotificationInfo n = this.notificationService.getById(nc.getNotificationId());
                            notificationInfos.add(n);
                        }
                    }
                    returnjson.put("notificationInfos", notificationInfos);
                    List<HomeworkInfo> homeworkInfos = this.homeworkService.getHomeworkByDateForC(dateStr,classId,dateC);
                    List<HHS> hhsList = new ArrayList<>() ;
                    //查看家长是否修改完成作业时间
                    if(homeworkInfos.size()>0){
                        for (HomeworkInfo h : homeworkInfos){
                            HHS hhs = new HHS();
                            hhs.setHomeworkId(h.getHomeworkId());
                            hhs.setPlantime(h.getPlantime());
                            hhs.setClassId(h.getClassId());
                            hhs.setContent(h.getContent());
                            hhs.setTitle(h.getTitle());
                            hhs.setTeacherId(h.getTeacherId());
                            hhs.setAssignTime(h.getAssignTime());
                            hhs.setSubjectId(h.getSubjectId());
                            List<HomeworkStudent> hs = this.homeworkStudentService.getHomeworkByHomeworkId(h.getHomeworkId());
                            if(hs.size()>0){
                                hhs.setFinishTime(hs.get(0).getFinishTime());
                                hhs.setChangeTime(hs.get(0).getChangeTime());
                                hhs.setIsChangeTime(hs.get(0).getIsChangeTime());
                                hhs.setIsFinishied(hs.get(0).getIsFinishied());
                                hhs.setIsSignend(hs.get(0).getIsSignend());
                                hhs.setSignParent(hs.get(0).getSignParent());
                                hhs.setStudentId(studentId);
                            }
                            hhsList.add(hhs);

                        }
                    }
                    returnjson.put("homeworkInfos",hhsList);

                }else {
                    flag = 2;
                    returnjson.put("error","日期格式不正确");
                }



            }else {
                returnjson.put("error","未绑定学生账号");
                flag = 3;
            }

        }catch (Exception e){
            flag = 2;
            e.printStackTrace();
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }
    public JSONObject getHomeworksToH(String macAddress){
        //int studentId = this.deviceService.getById(Integer.macAddress)
        return null;
    }

}
