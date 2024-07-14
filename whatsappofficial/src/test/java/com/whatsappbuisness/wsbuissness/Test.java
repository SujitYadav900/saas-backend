package com.whatsappbuisness.wsbuissness;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveActionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveHeaderDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        MessageDao messageDao= new MessageDao();

        InteractiveMessageDao interactiveMessageDao=new InteractiveMessageDao();
        interactiveMessageDao.setType(MessageType.list);
        InteractiveHeaderDao interactiveHeaderDao=new InteractiveHeaderDao();
        ImageMessageDao imageMessageDao=new ImageMessageDao();
        imageMessageDao.setLink("https://fcw.com/media/ps360/GIG/FCWNow/Topics/Concepts/smiley.png");
        interactiveHeaderDao.setImage(imageMessageDao);
        interactiveHeaderDao.setType(MessageType.image);
//        interactiveMessageDao.setHeader(interactiveHeaderDao);
        interactiveMessageDao.setBody(new HashMap<>());
        interactiveMessageDao.getBody().put("text","hello");
        InteractiveActionDao action = new InteractiveActionDao();
//        List<Map> buttons = new ArrayList<>();
//        Map button1 = new HashMap();
//        Map reply1 = new HashMap();
//        button1.put("type","reply");
//        reply1.put("id",1);
//        reply1.put("title","One");
//        button1.put("reply",reply1);
//
//        Map button2 = new HashMap();
//        Map reply2 = new HashMap();
//        button2.put("type","reply");
//        reply2.put("id",2);
//        reply2.put("title","Two");
//        button2.put("reply",reply2);

//        buttons.add(button1);
//        buttons.add(button2);

        List<Map> sections = new ArrayList<>();
        List<Map> rows = new ArrayList<>();
        Map sections1 = new HashMap();
        Map rows1 = new HashMap();
        sections1.put("title","LMS");
        rows1.put("id",1);
        rows1.put("title","One");
        rows1.put("description","test");
        rows.add(rows1);
        sections1.put("rows",rows);


       action.setButton("menu");
       sections.add(sections1);
       action.setSections(sections);
       interactiveMessageDao.setAction(action);

        Gson gson = new Gson();
        String json = gson.toJson(interactiveMessageDao);
        //logger.info(json);
        //logger.info(String.valueOf(interactiveMessageDao));

        //System.out.println("MessageDao"+messageDao);
    }
}
