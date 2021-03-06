package action;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entity.Friend;
import entity.FriendGroup;
import util.HttpClientUtil;
import util.ReadFileFirstLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaybeFriend {
    private String url_getfriend = "https://weixin.spdbccc.com.cn/wxrp-page-steal/ajaxQueryCommendFriends";
    private String charset = "utf-8";
    private Map<String,String> httpHeadMap = new HashMap<String,String>();
    private HttpClientUtil httpClientUtil = null;
    private String cookie;

    private ArrayList<Friend> friends = new ArrayList<Friend>();
    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public MaybeFriend(String cookie) {
        super();
        httpClientUtil = new HttpClientUtil();
        this.cookie =  cookie;
        initHttpHead();
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
        httpHeadMap.put("Cookie", cookie);
    }

    public void run(){
        String content = null;
        String result = null;
        friends.clear();
        //{"lean":"0","success":true,"pageNo":"0","pageSum":34,"openId":"oggN1jiZF8AthajXkotabFE6lbmk","totals":200,"list":""}
        String total_result = httpClientUtil.doPost(url_getfriend, httpHeadMap, charset, "stateNo=0&showPage=1");
        //System.out.print(total_result);
        FriendGroup friendGroup = new Gson().fromJson(total_result,FriendGroup.class);

        System.out.println("total friends is: " + friendGroup.getTotal());

        int totalPage;

        if(friendGroup.getTotal() % 9 == 0)
            totalPage = friendGroup.getTotal() / 9;
        else
            totalPage = friendGroup.getTotal() / 9 + 1;


        for (int i = 1; i <= totalPage; i++) {
            content = "stateNo=0&showPage=" + i ;
            result = httpClientUtil.doPost(url_getfriend, httpHeadMap, charset, content);
            //System.out.println(result);
            friendGroup = new Gson().fromJson(result,FriendGroup.class);

            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(friendGroup.getList()).getAsJsonArray();

            Gson gson = new Gson();

            //加强for循环遍历JsonArray
            for (JsonElement friendeml : jsonArray) {
                //使用GSON，直接转成Bean对象
                Friend friend = gson.fromJson(friendeml, Friend.class);
                //System.out.println(friend.getEncryptCertNo());
                friends.add(friend);
            }
        }
    }
}
