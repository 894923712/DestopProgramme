package Common;

import java.io.*;

/**
 * @author LuoMing luom@3vjia.com
 * 日志打印帮助类
 * @since 2018-12-05 15:34
 */
public class LogUtils {

    private static final String PATH="F:/MYProject/DestopProgramme/FiveChessLog/fiveChessLog.txt";
    private static BufferedWriter bw = null;

    public static void  init(){
        File file=new File(PATH);
        if(!file.exists()){
            try {
                file.createNewFile();
                bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(String msg){
        try {
            bw.write(msg+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            if(bw!=null){
                bw.close();
                bw=null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
