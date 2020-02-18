/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event_mahdi.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.event_mahdi.utils.connexion;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author mahdi
 */
public class fidelite_services {
        Connection conn = connexion.getInstance().getCnx();
    public int afficher_nbr_email()
    {
                int tot = 0;
            try {
                
                
                
                PreparedStatement pt = conn.prepareStatement("select COUNT(*) AS nbr from invitation ");
                ResultSet rs = pt.executeQuery();
                while(rs.next()){
                tot = rs.getInt("nbr");}
            } catch (SQLException ex) {
                Logger.getLogger(fidelite_services.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tot;
    }
    
    
        public static void sendMail(String recep) throws Exception{
    Properties p= new Properties();
    
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
    
    String e_mail="mehdihrairi6@gmail.com"; 
    String pass = "lol06061997mhlol";

    Session session =Session.getInstance(p,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(e_mail, pass);
        }
    });
     
        Message message=prepareMessage(session,e_mail,recep);
        Transport.send(message);
       
    }
private static Message prepareMessage(Session session,String e_mail, String recipient) throws MessagingException{
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("invitation letter :D ");
message.setText("Hello, i recommand this app it will make your life way easier. such a good app i will share it with you cause it's huge deal :p");
return message;

}
}
