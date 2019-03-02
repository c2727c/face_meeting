package cn.drrs.face_meeting.service.imple;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.usbrelay.UrDllBridge;

//@Service("usbService")
public class UsbRelayServiceImple {
	private static UrDllBridge Dev = null;
	private static int Hdl = -1;
	
	
	//连接继电器
	public boolean init() {
		Dev = new UrDllBridge();
        Hdl = Dev.USBRELAY_Open(1);
        System.out.printf("OpenPort result = %d, 0x%x\n", Hdl, Hdl);
        if (Hdl <= 0) {
            System.out.printf("OpenPort Error\n");
            return false;
        }else {//连接成功，现接通所有继电器
        	System.out.printf("OpenPort Success\n");
        	for(int doorNo = 1;doorNo<=2;++doorNo ) {
        		Dev.USBRELAY_SetRelay(Hdl, doorNo, 1);
                System.out.printf("Relay"+doorNo+" Connect\n");
        	}
        	return true;
        }
	}
	
	//打开指定会议室的门(因为只有两个继电器，奇数为2，偶数为1)
	public void openDoor(String rId) throws InterruptedException {
		Dev = new UrDllBridge();
        Hdl = Dev.USBRELAY_Open(1);
		int doorNo = Integer.parseInt(rId.substring(rId.length()-1))%2+1;
		//断电
		Dev.USBRELAY_SetRelay(Hdl, doorNo, 0);
        System.out.printf("Relay"+doorNo+" Disconnect\n");
        TimeUnit.SECONDS.sleep(6);
        System.out.printf("wait for 10 sec\n");
        Dev.USBRELAY_SetRelay(Hdl, doorNo, 1);
        System.out.printf("Relay"+doorNo+" Connect\n");
	}
	
	public void longOpenDoor(String rId) throws InterruptedException {
		Dev = new UrDllBridge();
        Hdl = Dev.USBRELAY_Open(1);
		int doorNo = Integer.parseInt(rId.substring(rId.length()-1))%2+1;
		//断电
		Dev.USBRELAY_SetRelay(Hdl, doorNo, 0);
	    System.out.printf("Relay1 Disconnect\n");
	}
	
	public void longCloseDoor(String rId) throws InterruptedException {
		Dev = new UrDllBridge();
        Hdl = Dev.USBRELAY_Open(1);
		int doorNo = Integer.parseInt(rId.substring(rId.length()-1))%2+1;
		//接通
		Dev.USBRELAY_SetRelay(Hdl, doorNo, 1);
        System.out.printf("Relay"+doorNo+" Connect\n");
		}

}
