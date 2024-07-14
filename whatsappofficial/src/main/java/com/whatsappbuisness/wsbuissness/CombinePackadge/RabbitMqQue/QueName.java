package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue;
/*      
 Author=Supreet Singh
 Date= 08/02/21 2:46 PM
*/

public enum QueName {
    Contact_Que("Contact_Que","Contact_Que","Contact_Que"),
    Contact_Que2("Contact_Que2","Contact_Que2","Contact_Que2"),
    Contact_Que3("Contact_Que3","Contact_Que3","Contact_Que3"),
    CreditManagment_Que("CreditManagment_Que","CreditManagment_Que","CreditManagment_Que"),  // all credit related que
    Main_Que("Main_Que","Main_Que","Main_Que"), //any new message will be send to this que
    Main_Que_Api("Main_Que_Api","Main_Que_Api","Main_Que_Api"), //any new message will be send to this que
    Main_Que2("Main_Que2","Main_Que2","Main_Que2"), //any new message will be send to this que
    Main_Que3("Main_Que3","Main_Que3","Main_Que3"), //any new message will be send to this que


    Mongo_Que("Mongo_Que","Mongo_Que","Mongo_Que"),
    NodejsEvent_Que("NodejsEvent_Que","NodejsEvent_Que","NodejsEvent_Que"),
    Webhook_Que("Webhook_Que","Webhook_Que","Webhook_Que"),
    DLR_Que("DLR_Que","DLR_Que","DLR_Que"),
    SendWs_QUE("SendWs_QUE","SendWs_QUE","SendWs_QUE"),
    SendWs_QUE2("SendWs_QUE2","SendWs_QUE2","SendWs_QUE2"),
    SendWs_QUE3("SendWs_QUE3","SendWs_QUE3","SendWs_QUE3"),
    Api_Que("Api_Que","Api_Que","Api_Que"), //any new api message will be send to this que
    SendWs_QUE_API("SendWs_QUE_API","SendWs_QUE_API","SendWs_QUE_API"),
    Contact_Que_Api("Contact_Que_Api","Contact_Que_Api","Contact_Que_Api"); //any new api message will be send to this que
    public  final String queName;
    public final String routing;
    public final String topic;
    QueName(String queName, String routing, String topic) {
        this.queName = queName ;
        this.routing = routing ;
        this.topic=topic;


    }



}
