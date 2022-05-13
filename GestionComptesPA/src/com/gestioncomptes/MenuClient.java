package com.gestioncomptes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MenuClient implements ActionListener{
    JFrame frame;
    int y, montant, points;
    String type, description, partenaire;
    JLabel label1, label2, labelWelcome, labelReduction, labelPoints;
    JButton button1;
    Font Font;

    public void MenuClient(String myText1) {

        frame = new JFrame("Vos informations");

        labelWelcome = new JLabel("Welcome :)");
        labelWelcome.setBounds(40, 10, 200, 30);
        Font = new Font("Viga", Font.PLAIN, 20);
        labelWelcome.setFont(Font);
        frame.add(labelWelcome);


        frame.setBackground(Color.white);
        frame.setLayout(null);

        label1 = new JLabel("Vos reductions :");
        label1.setBounds(40, 50, 200, 30);
        Font = new Font("Viga", Font.PLAIN, 20);
        label1.setFont(Font);
        frame.add(label1);

        //get All Reductions
        try {
            Connection c1 = new Connection();
            String q = "select * from reduction";
            ResultSet resultSet = c1.s.executeQuery(q);

            y = 80;
            while (resultSet.next()) {
                montant = resultSet.getInt("montant");
                type = resultSet.getString("type");
                description = resultSet.getString("description");
                partenaire = resultSet.getString("partenaire");

                labelReduction = new JLabel(description + " " + partenaire + " : " + montant + type + "\n");
                labelReduction.setBounds(40, y, 500, 30);
                Font = new Font("Viga", Font.PLAIN, 13);
                labelReduction.setFont(Font);
                y += 50;
                frame.add(labelReduction);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print le nombre de points du user
        label2 = new JLabel("Votre nombre de points :");
        label2.setBounds(500, 50, 300, 30);
        Font = new Font("Viga", Font.PLAIN, 20);
        label2.setFont(Font);
        frame.add(label2);

        //get All Points
        try {
            Connection c1 = new Connection();
            String q = "select points from loyalty where user='" + myText1 + "'";
            ResultSet resultSet = c1.s.executeQuery(q);

            while (resultSet.next()) {
               points = resultSet.getInt("points");

               labelPoints = new JLabel("Vous avez actuellement " + points + " points sur votre compte\n");
               labelPoints.setBounds(500, 80, 500, 30);
               Font = new Font("Viga", Font.PLAIN, 13);
               labelPoints.setFont(Font);
               frame.add(labelPoints);
               }
            } catch(SQLException e){
                e.printStackTrace();
            }


        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("images/logo_LoyaltyCard.png"));
        Image image2 = image.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon image3 =  new ImageIcon(image2);
        JLabel label3 = new JLabel(image3);
        label3.setBounds(800,20,150,150);
        frame.add(label3);

        button1 = new JButton("Deconnexion");
        button1.setBounds(40,350,200,30);
        button1.setFont(new Font("Viga",Font.BOLD,15));
        button1.setBackground(new Color(0,0,0));
        button1.setForeground(new Color(255,255,255));

        frame.add(button1);
        button1.addActionListener(this);


        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
        frame.setSize(1000,1000);
        frame.setLocation(400,300);
    }

    public void actionPerformed(ActionEvent ae){
        try {
            frame.setVisible(false);
            new MaFenetre().frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] arg){
        Login login = new Login();
    }
}
