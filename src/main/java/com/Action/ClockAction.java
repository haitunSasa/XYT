package com.Action;

import com.Entity.ClockInfo;
import com.Service.ClockService;
import com.alibaba.fastjson.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tan on 2017/3/7.
 * Clock_addClockAction:studentId&content&clockTime&clockDay
 * Clock_updateClockAction:clockId&studentId&content&clockTime&clockDay
 * Clock_getClockBySAction:studentId
 * Clock_deleteClockBySkAction:clockId
 */
public class ClockAction extends BaseAction {
    private HttpServletResponse response    = ServletActionContext.getResponse();

    private int        flag        = 0;
    private JSONObject returnjson  = new JSONObject();
    @Autowired
    private ClockService clockService;

    public void addClock() throws Exception{
        try {
            int studentId = getIntFromGet("studentId");
            String content = getStringFromGet("content");
            String dateStr = getStringFromGet("clockTime");
            String clockDay = getStringFromGet("clockDay");
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date clockTime = sdf.parse(dateStr);
            ClockInfo clockInfo = new ClockInfo();
            clockInfo.setClockContent(content);
            clockInfo.setClockTime(clockTime);
            clockInfo.setStudentId(studentId);
            clockInfo.setClockDay(clockDay);
            this.clockService.save(clockInfo);
            flag = 1;
            List<ClockInfo> clockInfos = this.clockService.getByTagId(studentId,"studentId");
            returnjson.put("clockInfos",clockInfos);
        }catch (Exception e){
            flag = 2;
            e.printStackTrace();
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }
    public void updateClock() throws Exception{
        try {
            int clockId = getIntFromGet("clockId");
            int studentId = getIntFromGet("studentId");
            String content = getStringFromGet("content");
            String dateStr = getStringFromGet("clockTime");
            String clockDay = getStringFromGet("clockDay");
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date clockTime = sdf.parse(dateStr);
            ClockInfo clockInfo = this.clockService.getById(clockId);
            clockInfo.setClockContent(content);
            clockInfo.setClockTime(clockTime);
            clockInfo.setStudentId(studentId);
            clockInfo.setClockDay(clockDay);
            this.clockService.update(clockInfo);
            flag = 1;
            List<ClockInfo> clockInfos = this.clockService.getByTagId(studentId,"studentId");
            returnjson.put("clockInfos",clockInfos);
        }catch (Exception e){
            flag = 2;
            e.printStackTrace();
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag",flag);
            writeJson(returnjson);
        }
    }

    public void getClockByS() throws Exception{
        try {
            int studentId = getIntFromGet("studentId");
            List<ClockInfo> clockInfos = this.clockService.getByTagId(studentId,"studentId");
            flag = 1;
            returnjson.put("clockInfos",clockInfos);
        }catch (Exception e){
            flag = 2;
            e.printStackTrace();
            returnjson.put("error",e.toString());
        }finally {
            returnjson.put("flag", flag);
            writeJson(returnjson);
        }
    }
    public void deleteClock() throws Exception{
        try {
            int clockId = getIntFromGet("clockId");
            ClockInfo clockInfo = this.clockService.getById(clockId);
            if(clockInfo == null){
                flag = 3;
                returnjson.put("error","clockId下不存在");
            }else {
                this.clockService.delete(clockInfo);
                flag = 1;
                returnjson.put("success","成功删除闹钟");
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

}
