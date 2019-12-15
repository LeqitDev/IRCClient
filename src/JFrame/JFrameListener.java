package JFrame;

import Client.IRCClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Window win = IRCClient.win;
        if (e.getSource() == win.button || e.getSource() == win.channel_field) {
            IRCClient.username = win.nick_field.getText();
            IRCClient.setCurChannel("#" + win.channel_field.getText());
            win.ChangeWindow("chat");
        } else if (e.getSource() == win.chat_panel.msg) {
            String msg = win.chat_panel.msg.getText();
            if (msg.startsWith("/")) {
                String command;
                if (msg.contains(" ")) command = msg.substring(1, msg.indexOf(" "));
                else command = msg.substring(1);
                switch (command)
                {
                    case "help":
                        IRCClient.win.addMSG("╔", IRCClient.cur_channel);
                        IRCClient.win.addMSG("║HELP", IRCClient.cur_channel);
                        IRCClient.win.addMSG("║-----------------------------------------------------------", IRCClient.cur_channel);
                        IRCClient.win.addMSG("║/join {channel}", IRCClient.cur_channel);
                        IRCClient.win.addMSG("╚", IRCClient.cur_channel);
                        break;
                    case "join":
                        IRCClient.changeCurChannel(msg.substring(msg.indexOf(" ") + 1));
                        break;
                }
                win.chat_panel.msg.setText("");
            }else {
                IRCClient.msg = msg;
            }
            win.chat_panel.msg.setText("");
        }
    }
}
