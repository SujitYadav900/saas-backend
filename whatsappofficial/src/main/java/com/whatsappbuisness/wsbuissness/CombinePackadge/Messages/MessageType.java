package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages;
/*
 Author=Supreet Singh
 Date= 04/02/21 4:48 PM
*/

public enum MessageType {

    text {
        public String toString() {
            return "text";
        }
    },

    image {
        public String toString() {
            return "image";
        }
    }
    ,

    audio {
        public String toString() {
            return "audio";
        }
    }
    ,

    video {
        public String toString() {
            return "video";
        }
    }

    ,

    document {
        public String toString() {
            return "document";
        }
    }

    ,

    template {
        public String toString() {
            return "template";
        }
    }
    ,contacts {
        public String toString() {
            return "contacts";
        }
    }
    ,location {
        public String toString() {
            return "location";
        }
    }
    ,voice {
        public String toString() {
            return "voice";
        }
    }
    ,system {
        public String toString() {
            return "system";
        }
    }

    ,sticker {
        public String toString() {
            return "sticker";
        }
    }
    ,button {
        public String toString() {
            return "button";
        }
    },
    interactive {
        public String toString() {
            return "interactive";
        }
    },
    list {
        public String toString() {
            return "list";
        }
    },
    ephemeral {
        public String toString() {
            return "ephemeral";
        }
    },
    button_reply {
        public String toString() {
            return "button_reply";
        }
    },list_reply {
        public String toString() {
            return "list_reply";
        }
    },
    CATALOGUE{
        public String toString() {
            return "CATALOGUE";
        }
    },

    ORDER{
        public String toString() {
            return "order";
        }
    }

    ,unknown {
        public String toString() {
            return "unknown";
        }
    }



}
