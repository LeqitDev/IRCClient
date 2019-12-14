package IRC;

import Client.IRCClient;
import net.engio.mbassy.listener.Filter;
import net.engio.mbassy.listener.Handler;
import org.kitteh.irc.client.library.event.abstractbase.ClientReceiveServerMessageEventBase;
import org.kitteh.irc.client.library.event.channel.ChannelJoinEvent;
import org.kitteh.irc.client.library.event.channel.ChannelNamesUpdatedEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Listener {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    @Handler
    public void onMessageReceive(ClientReceiveServerMessageEventBase event)
    {
        List params = event.getParameters();
        String msg = "";
        if (params.size() > 1) {
            msg = (String) params.get(1);
        }
        String author = "System";
        if (event.getRawMessage().contains(":") && event.getRawMessage().contains("!")){
            try{
                author = "<" + event.getRawMessage().substring(event.getRawMessage().indexOf(":") + 1, event.getRawMessage().indexOf("!")) + ">";
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("aioobe");
            }
        }
        IRCClient.win.addMSG("[" + simpleDateFormat.format(new Date()) + "] " + author + ": " + msg);
    }

    @Handler
    public void onChannelJoin(ChannelJoinEvent event)
    {
        if (event.getClient().isUser(event.getUser())) {
            System.out.println("Joined: " + event.getChannel().getName());
        } else {
            System.out.println(event.getUser().getNick() + " joined " + event.getChannel().getName());
            IRCClient.win.addMSG(event.getUser().getNick() + " joined " + event.getChannel().getName());
            IRCClient.win.addMember(event.getUser());
        }
    }
}
