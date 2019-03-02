package cn.drrs.face_meeting.service.imple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.GroupDao;
import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.GroupService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("groupService")
public class GroupServiceImple implements GroupService {
	@Resource
	private GroupDao dao;
	//·建立分组
	public NoteResult<Object> insert(Group g){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "新建工作组成功", dao.insert(g));
		return nr;
		
	}
	//·删除分组
	public NoteResult<Object> delete(String gName){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "删除工作组成功", dao.delete(gName));
		return nr;
	}
		
	//·按组名查询分组
	public NoteResult<Group> findBygId(String gName){
		NoteResult<Group> nr=new NoteResult<Group>();
		Group g = dao.findBygId(gName);
		if(g!=null) {
			nr.setAll(0, "查询得到工作组", g);
		}else {
			nr.setAll(1, "查询工作组无结果", g);
		}
		return nr;
	}
	//·修改分组信息
	public NoteResult<Object> update(Group g){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "更新工作组", dao.update(g));
		return nr;
		
	}
	public ResponseData getPageofGroup(int page, int limit) {
		// TODO Auto-generated method stub
		System.out.println("进入GroupService层的getPageofGroup方法**************************************************************************************************");
        ResponseData rd=new ResponseData();
        List<Group> groupList;

        System.out.println("page的值："+page+"****************************************************************************************************************");
        page=(page-1)*limit;
        System.out.println("page变换之后的值："+page+"****************************************************************************************************************");
        System.out.println("limit的值："+limit+"***************************************************************************************************************");
        try {
            rd.setCode("0");
            int num = dao.queryGroupCount();
            System.out.println("num的值为：" + num + "*****************************************************************************************************");
            String snum=num+"";
            rd.setCount(snum);//获取记录总数
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put("page",page);//从第几页开始
            map.put("limit",limit);//每页显示多少条记录
            groupList = dao.getPageofGroup(map);
            rd.setData(groupList);
            rd.setMsg("请求成功");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rd;
	}


}
