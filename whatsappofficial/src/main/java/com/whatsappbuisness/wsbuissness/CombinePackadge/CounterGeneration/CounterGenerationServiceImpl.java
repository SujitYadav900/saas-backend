package com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration;
/*      
 Author=Supreet Singh
 Date= 11/02/21 2:26 PM
*/

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CounterGenerationServiceImpl implements CounterGenerationService {
    @Override
    public String generateUid() {
        return UUID.randomUUID().toString();
    }
}
