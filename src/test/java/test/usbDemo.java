package test;
import java.util.concurrent.TimeUnit;

import com.usbrelay.*;
public class usbDemo{
    public static void main(String[] args) throws Exception {
    	/*
    	 *    	OpenRelay是接通指定端口的指定继电器。
			   	CloseRelay是断开指定端口的指定继电器。
			   	GetRelay是获取指定端口指定继电器的当前状态。
    	 */
        UrDllBridge Dev = new UrDllBridge();
        
        //获取连接
        int Hdl = Dev.USBRELAY_Open(1);
        System.out.printf("OpenPort result = %d, 0x%x\n", Hdl, Hdl);
        if (Hdl <= 0) {
            System.out.printf("OpenPort Error\n");
            return;
        }
        //接通1
        Dev.USBRELAY_SetRelay(Hdl, 1, 1);
        System.out.printf("Relay1 Connect\n");
        TimeUnit.SECONDS.sleep(1);
        //接通2
        Dev.USBRELAY_SetRelay(Hdl, 2, 1);
        System.out.printf("Relay2 Connect\n");
        TimeUnit.SECONDS.sleep(1);
        
        //检验状态
        int[] RelayStatus1 = new int[1];  //java没有指针，必须用数组代替
        int[] RelayStatus2 = new int[1];
        Dev.USBRELAY_GetRelay(Hdl, 1, RelayStatus1);
        System.out.printf("Relay1 Status = %d\n", RelayStatus1[0]);
        Dev.USBRELAY_GetRelay(Hdl, 2, RelayStatus2);
        System.out.printf("Relay2 Status = %d\n", RelayStatus2[0]);
        
        //断开1
        Dev.USBRELAY_SetRelay(Hdl, 1, 0);
        System.out.printf("Relay1 Disconnect\n");
        TimeUnit.SECONDS.sleep(1);
        
        //检验状态
        Dev.USBRELAY_GetRelay(Hdl, 1, RelayStatus1);
        System.out.printf("Relay1 Status = %d\n", RelayStatus1[0]);
        Dev.USBRELAY_GetRelay(Hdl, 2, RelayStatus2);
        System.out.printf("Relay2 Status = %d\n", RelayStatus2[0]);
        
        //断开2
        Dev.USBRELAY_SetRelay(Hdl, 2, 0);
        System.out.printf("Relay1 Disconnect\n");
        
        //关闭连接
        int res = Dev.USBRELAY_Close(Hdl);
        System.out.println("ClosePort res = " + res);
    }
}