package cn.drrs.face_meeting.entity.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EChart {
	private List<String> dataAxis;
	private List<Integer> data;
	
	public EChart(){
	}
	public EChart(Map<Time, Map<String, Integer>> map){
		dataAxis = new ArrayList<String> ();
		data= new ArrayList<Integer> ();
		Iterator<Time> iterator = map.keySet().iterator();
		Time key;
        while (iterator.hasNext()) {
            key = iterator.next();
            dataAxis.add(key.toString());
            data.add(map.get(key).get("avilable_count"));
        }
	}
	public EChart(List<AvilableCount> lav) {
		dataAxis = new ArrayList<String> ();
		data= new ArrayList<Integer> ();
		for(AvilableCount ac  : lav) {
			dataAxis.add(ac.getCount_point().toString());
			data.add(ac.getAvilable_count());
		}
	}
	public List<String> getDataAxis() {
		return dataAxis;
	}

	public void setDataAxis(List<String> dataAxis) {
		this.dataAxis = dataAxis;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer>data) {
		this.data = data;
	}
	
}
