package com.gestioncomptes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class MaFenetre  implements ActionListener{
    JFrame page;
    JFrame frame;
    JLabel label;
    Font myFont1;
    JButton btn1;


    MaFenetre(){
            // Définissez le frame
        page = new JFrame("Welcome To Our Java Application");
        page.setLayout(null);

        label = new JLabel("Bienvenue chez LoyaltyCard", JLabel.CENTER);
        myFont1 = new Font("Viga", myFont1.BOLD, 50);
        label.setBounds(40,70,1000,500);
        label.setFont(myFont1);
        page.add(label);

        // Définir les boutons
        btn1=new JButton("Connexion");
        btn1.setBounds(450,500,120,30);
        btn1.setFont(new Font("Viga",Font.BOLD,15));
        btn1.setBackground(new Color(0,0,0));
        btn1.setForeground(new Color(255,255,255));

        page.add(btn1);
        btn1.addActionListener(this);

        page.getContentPane().setBackground(Color.WHITE);

        page.setVisible(true);
        page.setSize(1000,1000);
        page.setLocation(400,300);

    }

    public void actionPerformed(ActionEvent ae){
        page.setVisible(false);
        new Login().frame.setVisible(true);
    }
    public static void main(String[] arg){
        Login login = new Login();
    }
}


