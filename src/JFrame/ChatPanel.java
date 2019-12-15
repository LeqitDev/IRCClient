package JFrame;

import javax.swing.*;

public class ChatPanel extends JPanel {

    public void InitComponents() {
        if (chat == null) chat = new JTextPane();
        if (chat_scroll == null) chat_scroll = new JScrollPane();
        if (members == null) members = new JTextPane();
        if (members_scroll == null) members_scroll = new JScrollPane();
        if (msg == null) msg = new JTextField();
        msg.addActionListener(new JFrameListener());

        {
            chat.setEditable(false);
            chat_scroll.setViewportView(chat);
        }

        {
            members.setEditable(false);
            members_scroll.setViewportView(members);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(chat_scroll, GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                                        .addComponent(msg, GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addComponent(members_scroll, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(chat_scroll, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(msg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(members_scroll, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );
    }

    public JTextPane chat;
    private JScrollPane chat_scroll;
    public JTextPane members;
    private JScrollPane members_scroll;
    public JTextField msg;
}
