package com.gestioncomptes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;


class Login  implements ActionListener{

    JFrame frame;
    JLabel label1;
    JLabel label2;
    JTextField text1;
    JPasswordField text2;
    JButton button1;
    JButton button2;
    Font Font;

    Login(){

        frame=new JFrame("Connexion");

        frame.setBackground(Color.white);
        frame.setLayout(null);

        label1 = new JLabel("Email");
        label1.setBounds(40,20,100,30);
        Font = new Font("Viga", Font.PLAIN, 20);
        label1.setFont(Font);
        frame.add(label1);

        label2 = new JLabel("Password");
        label2.setBounds(40,70,100,30);
        Font = new Font("Viga", Font.PLAIN, 20);
        label2.setFont(Font);
        frame.add(label2);

        text1=new JTextField();
        text1.setBounds(150,20,150,30);
        frame.add(text1);

        text2=new JPasswordField();
        text2.setBounds(150,70,150,30);
        frame.add(text2);

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("images/logo_LoyaltyCard.png"));
        Image image2 = image.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        ImageIcon image3 =  new ImageIcon(image2);
        JLabel label3 = new JLabel(image3);
        label3.setBounds(400,20,150,150);
        frame.add(label3);


        button1 = new JButton("Valider");
        button1.setBounds(40,140,120,30);
        button1.setFont(new Font("Viga",Font.BOLD,15));
        button1.setBackground(new Color(0,0,0));
        button1.setForeground(new Color(255,255,255));
        frame.add(button1);
        button1.addActionListener(this);

        button2=new JButton("Annuler");
        button2.setBounds(180,140,120,30);
        button2.setFont(new Font("Viga",Font.BOLD,15));
        button2.setBackground(new Color(0,0,0));
        button2.setForeground(new Color(255,255,255));

        frame.add(button2);
        button2.addActionListener(this);

        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
        frame.setSize(1000,1000);
        frame.setLocation(400,300);

    }

    public void actionPerformed(ActionEvent ae){

        try{
            Connection connection = new Connection();
            String myText1 = text1.getText();
            String myText2 = String.valueOf(text2.getPassword());

            //La méthode getInstance() est appelée avec l'algorithme SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Pour calculer le résumé de la chaine entrée par l'utilsateur => sous la forme d'un tableau d'octets
            byte[] messageDigest = md.digest(myText2.getBytes());

            //Convertion du tableau en représentaion signum
            BigInteger no = new BigInteger(1, messageDigest);
            //Convertion du message en valeur hexadécimale
            String hashtext = no.toString(16);

            //Ajout des 0 précédents pour le rendre en 32 bits
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            String q = "select * from user where email='"+myText1+"' and password='"+hashtext+"'";

            ResultSet resultSet = connection.s.executeQuery(q);
            if(resultSet.next()){
                MenuClient recupEmail = new MenuClient();
                recupEmail.MenuClient(myText1);

                frame.setVisible(false);
                new MenuClient().frame.setVisible(true);

            }else{

                JOptionPane.showMessageDialog(null, "Identifiant et mot de passe non valides");
                frame.setVisible(false);
                new Login().frame.setVisible(true);

            }
        }catch(Exception e){

            e.printStackTrace();

        }
        frame.removeAll();
    }
    public static void main(String[] arg){
        Login login =new Login();
    }
}


