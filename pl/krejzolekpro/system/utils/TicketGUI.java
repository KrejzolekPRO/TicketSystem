package pl.krejzolekpro.system.utils;

import javax.swing.*;

public class TicketGUI {

    private static TicketGUI instance;

    public TicketGUI(){
        instance = this;
    }

    public static TicketGUI getInstance(){
        if(instance == null){
            return new TicketGUI();
        }
        return instance;
    }

    public void showMainGUI(){
        JFrame frame = new JFrame();
        JButton button = new JButton("Stworz nowy bilet.");
        button.setBounds(20, 20, 200, 100);
        frame.add(button);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
