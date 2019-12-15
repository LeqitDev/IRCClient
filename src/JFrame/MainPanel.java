package JFrame;

import javax.swing.*;

public class MainPanel extends JPanel {

    public void InitComponents()
    {
        if (channels == null) channels = new JTree();
        if (menuBar == null) menuBar = new JMenuBar();
        if (menu == null) menu = new JMenu();
        if (chat == null) chat = new JPanel();
        if (scrollPane == null) scrollPane = new JScrollPane();
        if (splitPane == null) splitPane = new JSplitPane();
        if (toolBar == null) toolBar = new JToolBar();

        {
            {
                {
                    menu.setText("Test");
                }
                menuBar.add(menu);
            }
            toolBar.add(menuBar);
        }

        {
            {
                scrollPane.setViewportView(channels);
            }
            splitPane.setLeftComponent(scrollPane);

            splitPane.setRightComponent(chat);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(splitPane, GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );
    }

    public JTree channels;
    public JMenuBar menuBar;
    public JMenu menu;
    public JPanel chat;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;
    public JToolBar toolBar;
}
