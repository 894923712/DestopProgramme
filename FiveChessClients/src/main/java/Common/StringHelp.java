package Common;

public class StringHelp {

    public static Boolean IsEmptyOrNull(String str){
        Boolean flag=false;
        if(str==null){
            flag=true;
        }else if(str.equals("")){
            flag=true;
        }
        return  flag;
    }
}
