package action;

import entity.Friend;
import org.apache.log4j.Logger;
import util.HttpClientUtil;
import util.ReadFileFirstLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFriendTest {

    private String url_addfriend = "https://weixin.spdbccc.com.cn/wxrp-page-steal/sendCommendFriends";
    private String charset = "utf-8";
    private Map<String,String> httpHeadMap = new HashMap<String,String>();
    private HttpClientUtil httpClientUtil = null;
    private static Logger logger = Logger.getLogger(AddFriendTest.class);
    public AddFriendTest() {
        super();
        httpClientUtil = new HttpClientUtil();
        initHttpHead();
    }


    public void run(){
        String content = "";
        String result =  "";
        String logStr = "";

        long commandId=374200489;

        for(; commandId <=374200489 + 20;commandId ++ ){
            content = "parm=" + commandId;
            logStr = "";
            result = httpClientUtil.doPost(url_addfriend,httpHeadMap,charset,content);
            logStr = commandId + "|";
            logStr = logStr + result;
            logger.info(logStr);
        }
    }

    public void initHttpHead() {
        httpHeadMap.put("Host","weixin.spdbccc.com.cn");
        httpHeadMap.put("Connection","keep-alive");
        httpHeadMap.put("Accept","application/json, text/javascript, */*; q=0.01");
        httpHeadMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpHeadMap.put("Origin","https://weixin.spdbccc.com.cn");
        httpHeadMap.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");
        httpHeadMap.put("X-Requested-With","XMLHttpRequest");
        httpHeadMap.put("Referer","https://weixin.spdbccc.com.cn/wxrp-page-steal/queryCommendFriends?stateNo=0&showPage=1");
        httpHeadMap.put("Accept-Encoding","gzip, deflate");
        httpHeadMap.put("Accept-Language","zh-CN,zh;q=0.8,en-us;q=0.6,en;q=0.5;q=0.4");
        //cookie init
        httpHeadMap.put("Cookie", ReadFileFirstLine.readFirstLine("cookie.txt"));
    }
}
