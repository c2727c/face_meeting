package cn.drrs.face_meeting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Meter;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.MeterService;

@Controller
@RequestMapping("/meter")
public class MeterController {
	@Resource
    private MeterService meterService;

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public void addBathMeter(){//方法参数必须可以获取到表单数据
        System.out.println("进入addBathMeter的controll层方法**********************************************************************************");
        List<Meter> meterList= new ArrayList<Meter>();
        /*try {
            meterList= MyQueryDeviceHistoryData.fun();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        meterService.addBathMeter(meterList);
    }

    @RequestMapping(value="/query.do",method = RequestMethod.GET)
    @ResponseBody
    //ResponseData是符合Layui table 格式的返回消息
    public ResponseData queryData(int page,int limit){//方法参数必须可以获取到表单数据

        System.out.println("进入controller层的meter/query方法********************************************************************************************");
        return meterService.queryData(page,limit);

    }
}
