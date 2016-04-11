/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@MessageDriven(mappedName = "jms/KwetterGo/queue")
public class KwetterGo implements MessageListener {
    
    @Inject
    private KwetterService service;

    @Override
    public void onMessage(Message message)
    {
        try
        {
            String username = message.getStringProperty("user");
            String content = message.getStringProperty("content");
            String location = message.getStringProperty("location");

            User user = service.findUserByName(username);
            if (user != null)
            {
                Kweet kweet = new Kweet(content, location, user); 
                service.createKweet(kweet);
            }
        } catch(JMSException jmsException){
            Logger.getLogger(KwetterGo.class.getName()).log(Level.SEVERE, null, jmsException);
        }
    }
}
