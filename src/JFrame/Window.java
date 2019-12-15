package JFrame;

import Client.IRCClient;
import org.kitteh.irc.client.library.element.User;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Window extends JFrame {

    JTextField nick_field;
    JTextField channel_field;
    JButton button;
    private JPanel login_panel;
    ChatPanel chat_panel;
    public MainPanel main_panel;
    public DefaultMutableTreeNode irc;
    public HashMap<String, ChatPanel> chats = new HashMap<String, ChatPanel>();
    JTree channels;

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
        JLabel label_nick = new JLabel("Enter username: ");
        nick_field = new JTextField(20);
        JLabel label_channel = new JLabel("Enter cur_channel: ");
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

        /*
         * Chat GUI
         */
        main_panel = new MainPanel();
        irc = new DefaultMutableTreeNode("EuIRC");
        channels = new JTree(irc);
        channels.addTreeSelectionListener(new TreeListener());
        main_panel.channels = channels;
        main_panel.InitComponents();

        /*chat_panel = new JPanel();
        chat_panel.setSize(screen_size);
        chat_panel.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(0, 0, 0, 0);


        JList<JTree> channel_list = new JList<>();
        DefaultMutableTreeNode euirc = new DefaultMutableTreeNode("EuIRC");
        JTree euirc_tree = new JTree(euirc);
        channel_list.add(euirc_tree);
        channel_list.setPreferredSize(new Dimension((int) (0.5 * (screen_size.getWidth()/4)), (int) (15 * (screen_size.getHeight()/20))));

        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 0.5;
        c.weightx = 0.5;
        chat_panel.add(channel_list, c);


        chat_pane = new JTextPane();
        chat_pane.setText("Conecting...");
        chat_pane.setEditable(false);
        caret = (DefaultCaret) chat_pane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane chat_scrollpane = new JScrollPane(chat_pane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        chat_scrollpane.setPreferredSize(new Dimension((int) (2 * (screen_size.getWidth()/4)), (int) (15 * (screen_size.getHeight()/20))));

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.SOUTH;
        c.weighty = 1;
        c.weightx = 1;
        chat_panel.add(chat_scrollpane, c);


        member_pane = new JTextPane();
        member_pane.setEditable(false);
        member_pane.setText("Members:\n---------------------");
        caret = (DefaultCaret) member_pane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane member_scrollpane = new JScrollPane(member_pane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        member_scrollpane.setPreferredSize(new Dimension((int) (0.5 * (screen_size.getWidth()/4)), (int) (15 * (screen_size.getHeight()/20))));

        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 0.5;
        c.weightx = 0.5;
        chat_panel.add(member_scrollpane, c);


        chat_msg = new JTextField();
        chat_msg.addActionListener(new JFrameListener());
        chat_msg.setPreferredSize(new Dimension((int) (2 * (screen_size.getWidth()/4)), 30));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.weighty = 0.5;
        c.weightx = 0.5;
        chat_panel.add(chat_msg, c);*/
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
                this.getContentPane().add(main_panel);
                break;
        }
    }

    public void addMSG(String msg, String channel)
    {
        if (!chats.get(channel).chat.getText().equalsIgnoreCase(""))
            chats.get(channel).chat.setText(chats.get(channel).chat.getText() + "\n" + msg);
        else
            chats.get(channel).chat.setText(msg);
    }

    public void setMembers(List<User> members, String channel)
    {
        chats.get(channel).members.setText("");
        for (User member : members)
        {
            if (!chats.get(channel).chat.getText().equalsIgnoreCase(""))
                chats.get(channel).members.setText(chats.get(channel).members.getText() + "\n" + member.getNick());
            else
                chats.get(channel).members.setText(member.getNick());
        }
    }

    public void addMember(User member, String channel)
    {
        chats.get(channel).members.setText(chats.get(channel).members.getText() + "\n" + member.getNick());
    }

    public void changeChannel(String channel) {

    }

    public void addChannel(String channel) {
        DefaultMutableTreeNode chat_channel = new DefaultMutableTreeNode(channel);
        irc.add(chat_channel);
        channels = new JTree(irc);
        channels.expandPath(new TreePath(irc.getPath()));
        channels.setSelectionPath(new TreePath(chat_channel.getPath()));
        channels.addTreeSelectionListener(new TreeListener());
        main_panel.channels = channels;
        chat_panel = new ChatPanel();
        chat_panel.InitComponents();
        chats.put(channel, chat_panel);
        main_panel.chat = chat_panel;
        main_panel.InitComponents();
    }

    public class TreeListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            if (e.getSource() == channels) {
                if (chats.containsKey(e.getPath().getLastPathComponent().toString())){
                    chat_panel = chats.get(e.getPath().getLastPathComponent().toString());
                    main_panel.chat = chat_panel;
                    main_panel.InitComponents();
                    IRCClient.changeCurChannel(e.getPath().getLastPathComponent().toString());
                }
            }
        }
    }
}
