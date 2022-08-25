package toUTF;


import java.io.UnsupportedEncodingException;

public class myUTF {

    //封装成工具类
    public static String getNewString(String str) throws UnsupportedEncodingException
    {
        return new String(str.getBytes("ISO-8859-1"),"UTF-8");
    }
}
