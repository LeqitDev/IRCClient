package Client;

import IRC.Listener;
import JFrame.Window;
import org.kitteh.irc.client.library.Client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IRCClient {
    public static String username;
    public static String cur_channel;
    public static Window win;
    public static String msg;
    private static List<String> channels = new ArrayList<>();
    private static boolean newChannel = true;

    public static void main(String[] args){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        boolean setMembers = true;
        Client client = null;

        win = new JFrame.Window();

        int i = 0;
        while (username == null && channels.size() == 0){
            System.out.print(".");
            i++;
            if (i > 2) {
                System.out.print("\r");
                i = 0;
            }
        }

        if (username != null && cur_channel != null)
        {
            client = org.kitteh.irc.client.library.Client.builder().nick(username).server().host("irc.euirc.net").then().buildAndConnect();

            client.getEventManager().registerEventListener(new Listener());
            client.addChannel(cur_channel);
            newChannel = false;
        }

        while (client != null)
        {
            System.out.print("|");
            System.out.print("\b");
            if (newChannel){
                client.addChannel(cur_channel);
                newChannel = false;
            }

            if (client.getChannel(cur_channel).isPresent() && client.getChannel(cur_channel).get().hasCompleteUserData() && setMembers){
                win.setMembers(client.getChannel(cur_channel).get().getUsers());
                setMembers = false;
            }
            if (msg != null)
            {
                win.addMSG("[" + simpleDateFormat.format(new Date()) + "] <" + username + ">: " + msg);
                client.sendMessage(cur_channel, msg);
                msg = null;
            }
        }
    }

    public static void setCurChannel(String channel) {
        IRCClient.cur_channel = channel;
        IRCClient.channels.add(channel);
        IRCClient.newChannel = true;
    }

    public static void addChannel(String channel) {
        if (!IRCClient.channels.contains(channel))
            IRCClient.channels.add(channel);
    }

    public static void changeCurChannel(String channel) {
        if (IRCClient.channels.contains(channel)) {
            IRCClient.cur_channel = channel;
        } else {
            setCurChannel(channel);
        }
    }
}
