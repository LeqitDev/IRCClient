package JFrame;

import net.engio.mbassy.bus.common.DeadMessage;
import org.kitteh.irc.client.library.element.User;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class Window extends JFrame {

    static JLabel label_nick;
    static JTextField nick_field;
    static JLabel label_channel;
    static JTextField channel_field;
    static JButton button;
    private JPanel login_panel;
    private JPanel chat_panel;
    private JTextPane chat_pane;
    static JTextField chat_msg;
    private JTextPane member_pane;
    private JScrollPane chat_scrollpane;
    private JScrollPane member_scrollpane;

    public Window()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,300);
        createPanels();

        ChangeWindow("login");

        this.setVisible(true);
    }

    public void createPanels()
    {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        DefaultCaret caret;

        label_nick = new JLabel("Enter username: ");
        nick_field = new JTextField(20);
        label_channel = new JLabel("Enter cur_channel: ");
        channel_field = new JTextField(20);
        channel_field.addActionListener(new JFrameListener());
        button = new JButton("Join");
        button.addActionListener(new JFrameListener());
        login_panel = new JPanel();

        login_panel.add(label_nick);
        login_panel.add(nick_field);
        login_panel.add(label_channel);
        login_panel.add(channel_field);
        login_panel.add(button); // Adds Button to content pane of frame

        chat_pane = new JTextPane();
        chat_pane.setEditable(false);
        caret = (DefaultCaret) chat_pane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        chat_scrollpane = new JScrollPane(chat_pane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        chat_scrollpane.setPreferredSize(new Dimension((int) (2 * (screen_size.getWidth()/4)), (int) (15 * (screen_size.getHeight()/20))));
        member_pane = new JTextPane();
        member_pane.setEditable(false);
        member_pane.setText("Members");
        caret = (DefaultCaret) member_pane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        member_scrollpane = new JScrollPane(member_pane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        member_scrollpane.setPreferredSize(new Dimension((int) (1 * (screen_size.getWidth()/4)), (int) (15 * (screen_size.getHeight()/20))));
        chat_msg = new JTextField();
        chat_msg.addActionListener(new JFrameListener());
        chat_msg.setPreferredSize(new Dimension((int) (3 * (screen_size.getWidth()/4)), 30));
        chat_panel = new JPanel();
        chat_panel.setSize(screen_size);

        chat_panel.add(chat_scrollpane, BorderLayout.LINE_START);
        chat_panel.add(member_scrollpane, BorderLayout.LINE_END);
        chat_panel.add(chat_msg, BorderLayout.PAGE_END);
    }

    public void ChangeWindow(String window)
    {
        switch (window)
        {
            case "login":
                this.getContentPane().removeAll();
                this.getContentPane().add(login_panel);
                break;
            case "chat":
                this.getContentPane().removeAll();
                this.setLocation(0,0);
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.getContentPane().add(chat_panel);
                break;
        }
    }

    public void addMSG(String msg)
    {
        chat_pane.setText(chat_pane.getText() + "\n" + msg);
    }

    public void setMembers(List<User> members)
    {
        member_pane.setText("Members");
        for (User member : members)
        {
            member_pane.setText(member_pane.getText() + "\n" + member.getNick());
        }
    }

    public void addMember(User member)
    {
        member_pane.setText(member_pane.getText() + "\n" + member.getNick());
    }
}
