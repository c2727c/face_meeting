//package cn.drrs.face_meeting.controller;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.usbrelay.UrDllBridge;
//
//@Controller
//@RequestMapping("/test")
//public class test {
//    @RequestMapping("/openUSB.do")
//    @ResponseBody
//    public String openUSB() {
//        System.out.println(" /test/openUSB.do REQUEST");
//        UrDllBridge Dev = new UrDllBridge();
//        int Hdl = Dev.USBRELAY_Open(1);
//        System.out.printf("OpenPort result = %d, 0x%x\n", Hdl, Hdl);
//        if (Hdl <= 0) {
//            System.out.printf("OpenPort Error\n");
//            return "err";
//        }
//        Dev.USBRELAY_SetRelay(Hdl, 2, 1);
//        System.out.printf("Relay1 Connect\n");
//        
//        
//        int[] RelayStatus = new int[1];  //javaû��ָ�룬�������������
//        Dev.USBRELAY_GetRelay(Hdl, 2, RelayStatus);
//        System.out.printf("Relay1 Status = %d\n", RelayStatus[0]);
//        try {
//			TimeUnit.SECONDS.sleep(1000
//					);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.out.printf("wait for 10 sec\n");
//        
//        Dev.USBRELAY_SetRelay(Hdl, 2, 0);
//        System.out.printf("Relay1 Disconnect\n");
//        Dev.USBRELAY_GetRelay(Hdl, 2, RelayStatus);
//        System.out.printf("Relay1 Status = %d\n", RelayStatus[0]);
//
//       int res = Dev.USBRELAY_Close(Hdl);
//        System.out.println("ClosePort res = " + res);
//        return "openTest";
//    }
//}
