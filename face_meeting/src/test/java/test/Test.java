package test;
public class Test{
    public static void main(String[] args){
        try{
               Runtime.getRuntime().exec("cmd.exe /c start java Test2");
               Thread.sleep(5000);
          }
          catch(Exception e){
               e.printStackTrace();
              
          }
    }
 
}