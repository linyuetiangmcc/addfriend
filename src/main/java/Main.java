import action.AddFriend;
import action.AddFriendTest;
import action.MaybeFriend;
import entity.Friend;
import util.ReadFileFirstLine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public void run(int userIndex){
        String cookie="";
        String fileName="";

        if(userIndex == 1) {
            cookie = ReadFileFirstLine.readFirstLine("cookie.txt");
            fileName = "friend_history.txt";
        }
        else {
            cookie = ReadFileFirstLine.readFirstLine("cookie_hjl.txt");
            fileName = "friend_history_hjl.txt";
        }

        MaybeFriend maybeFriend = new MaybeFriend(cookie);
        maybeFriend.run();

        ArrayList<Friend> friends = maybeFriend.getFriends();

        File file = new File(fileName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowStr = format.format(now) + " total size is " + friends.size() + "\n";
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            writer.write(nowStr);
            for (Friend friend : friends) {
                String outPutStr = "";
                outPutStr = friend.getCommendId() + "|";
                outPutStr = outPutStr + friend.getCommendedName()
                        + "\n";
                writer.write(outPutStr);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        AddFriend addFriend = new AddFriend(friends,cookie);
        addFriend.run();
    }


    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        int userIndex = 1;
        if(args.length > 0){
            userIndex = Integer.parseInt(args[0]);
        }
        main.run(userIndex);
        //main.runtest();
    }

    public void runtest(){
        AddFriendTest addFriendTest = new AddFriendTest();
        addFriendTest.run();
    }
}
