package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Crud;
/*
 Author=Supreet Singh
 Date= 09/03/21 2:35 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<MessageDao,String> {
}
