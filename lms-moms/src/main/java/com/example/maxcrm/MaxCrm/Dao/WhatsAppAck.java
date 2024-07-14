package com.example.maxcrm.MaxCrm.Dao;

import java.util.Map;

public class WhatsAppAck{

        private Map ack;
        private String endpoint;

        public Map getAck() {
            return ack;
        }

        public void setAck(Map ack) {
            this.ack = ack;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        @Override
        public String toString() {
            return "WhatsAppAck{" +
                    "ack=" + ack +
                    ", endpoint='" + endpoint + '\'' +
                    '}';
        }
    }
