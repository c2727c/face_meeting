package test;
import com.usbrelay.UrDllBridge;
public class demo{
    public static void main(String[] args){
        UrDllBridge Dev = new UrDllBridge();
        int Hdl = Dev.USBRELAY_Open(1);
        System.out.printf("OpenPort result = %d, 0x%x\n", Hdl, Hdl);
        if (Hdl <= 0) {
            System.out.printf("OpenPort Error\n");
            return;
        }
        Dev.USBRELAY_SetRelay(Hdl, 1, 1);
        System.out.printf("Relay1 Connect\n");
        
        
        int[] RelayStatus = new int[1];  //javaû��ָ�룬�������������
        Dev.USBRELAY_GetRelay(Hdl, 1, RelayStatus);
        System.out.printf("Relay1 Status = %d\n", RelayStatus[0]);
        
        
        Dev.USBRELAY_SetRelay(Hdl, 1, 0);
        System.out.printf("Relay1 Disconnect\n");
        Dev.USBRELAY_GetRelay(Hdl, 1, RelayStatus);
        System.out.printf("Relay1 Status = %d\n", RelayStatus[0]);

       int res = Dev.USBRELAY_Close(Hdl);
        System.out.println("ClosePort res = " + res);
    }
}