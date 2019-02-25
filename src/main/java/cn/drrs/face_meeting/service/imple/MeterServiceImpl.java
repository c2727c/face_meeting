package cn.drrs.face_meeting.service.imple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.drrs.face_meeting.dao.MeterMapper;
import cn.drrs.face_meeting.entity.Meter;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.MeterService;

@Service("meterService")
public class MeterServiceImpl implements MeterService{
	@Resource
    private MeterMapper meterMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.DEFAULT,rollbackFor=Exception.class)
    public void addBathMeter(List<Meter> meterList) {

        System.out.println("进入addBathMeter的service层方法*********************************************************************************");
        meterMapper.addBathClient(meterList);
    }

    @Transactional(readOnly = true)//page默认是从1开始的
    public ResponseData queryData(int page, int limit) {
        System.out.println("进入meterService层的queryData方法**************************************************************************************************");
        ResponseData rd=new ResponseData();
        List<Meter> meterList;

        System.out.println("page的值："+page+"****************************************************************************************************************");
        page=(page-1)*limit;
        System.out.println("page变换之后的值："+page+"****************************************************************************************************************");
        System.out.println("limit的值："+limit+"***************************************************************************************************************");
        try {
            rd.setCode("0");
            int num=meterMapper.queryCount();
            System.out.println("num的值为：" + num + "*****************************************************************************************************");
            String snum=num+"";
            rd.setCount(snum);//获取记录总数
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put("page",page);//从第几页开始
            map.put("limit",limit);//每页显示多少条记录
            meterList = meterMapper.queryData(map);
            rd.setData(meterList);
            rd.setMsg("请求成功");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rd;
    }
}
