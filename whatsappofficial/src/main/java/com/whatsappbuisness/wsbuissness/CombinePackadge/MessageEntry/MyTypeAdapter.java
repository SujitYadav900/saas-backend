package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import java.io.IOException;

public class MyTypeAdapter extends TypeAdapter<MessageDao> {


    @Override
    public void write(JsonWriter out, MessageDao value) throws IOException {

    }

    @Override
    public MessageDao read(JsonReader jsonReader) throws IOException {
        return null;
    }
}