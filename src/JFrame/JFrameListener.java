package JFrame;

import Client.IRCClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Window.button || e.getSource() == Window.channel_field) {
            IRCClient.username = Window.nick_field.getText();
            IRCClient.setCurChannel("#" + Window.channel_field.getText());
            System.out.println(IRCClient.username + " connecting to: " + IRCClient.cur_channel);
            IRCClient.win.ChangeWindow("chat");
        } else if (e.getSource() == Window.chat_msg) {
            String msg = Window.chat_msg.getText();
            if (msg.startsWith("/")) {
                String command = msg.substring(1, msg.indexOf(" "));
                switch (command)
                {
                    case "help":
                        IRCClient.win.addMSG("╔");
                        IRCClient.win.addMSG("║HELP");
                        IRCClient.win.addMSG("║-----------------------------------------------------------");
                        IRCClient.win.addMSG("║/join {channel}");
                        IRCClient.win.addMSG("╚");
                        break;
                    case "join":
                        IRCClient.setCurChannel(msg.substring(msg.indexOf(" ")));
                        break;
                }
            }else {
                IRCClient.msg = msg;
            }
            Window.chat_msg.setText("");
        }
    }
}
