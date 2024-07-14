<h1>Incoming DLR Webhook Json </h1>


<h2>Read Status</h2>
`{
    "statuses": [
        {
            "id": "gBEGkZmZQgWUAgkUbIEEj7onQwE",
            "recipient_id": "919999420594",
            "status": "read",
            "timestamp": "1612788433"
        }
    ]
}

<h2>Sent Status</h2>

{
    "statuses": [
        {
            "id": "gBEGkZZDQEASAglyJjc6UYwTK-o",
            "recipient_id": "919643404012",
            "status": "sent",
            "timestamp": "1612863475"
        }
    ]
}
<h2>DLR Status</h2>
{
    "statuses": [
        {
            "id": "gBEGkZZDQEASAglyJjc6UYwTK-o",
            "recipient_id": "919643404012",
            "status": "delivered",
            "timestamp": "1612863475"
        }
    ]
}`


<br>
<br>
<br>


<h1>Incoming Message Webhook Json </h1>
<h2>Text Message</h2>
{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhAr0dw0cscn1_S8xHYvWJYm",
            "text": {
                "body": "Hggg"
            },
            "timestamp": "1612788459",
            "type": "text"
        }
    ]
}

<h2>Image Message</h2>

{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhABTvRHKx-t7Rhev2WMyrAN",
            "image": {
                "id": "58e24b15-78c5-4707-8f33-745abe033188",
                "mime_type": "image/jpeg",
                "sha256": "6ff2320654d657f0a839a810e4c8313bb6305725b834b1f1a2ffe4734a78b529"
            },
            "timestamp": "1612863892",
            "type": "image"
        }
    ]
}


<h2>Video Message</h2>

{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhDcPrn7hT2HMr0mTPkjQqFF",
            "timestamp": "1612863961",
            "type": "video",
            "video": {
                "id": "c051b432-af0e-450a-9341-28bada3deb8c",
                "mime_type": "video/mp4",
                "sha256": "c0af3b5b784f3325989989feafe0cbaee0b8c3bbfdcf65e422947b9572202c3c",
  
            }
        }
    ]
}


<h2>Location Message</h2>

{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhDiyMH5UqymrDLBHvTqNYB-",
            "location": {
                "latitude": 28.6787297,
                "longitude": 77.0975355
            },
            "timestamp": "1612864510",
            "type": "location"
        }
    ]
}

<h2>Audio Message</h2>

{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "audio": {
                "id": "1e6b8b4c-e828-48b9-8665-d3a5952c45b3",
                "mime_type": "audio/mpeg",
                "sha256": "8bb83b09ad90d6a3f9a2e2b5827e3640b5db104268962b69bb9b2357c57253e1"
            },
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhCDJv4eUm17v6W_cAfX_VUT",
            "timestamp": "1612864556",
            "type": "audio"
        }
    ]
}


<h2>Voice Message</h2>
{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhB2EBGlTsTKGmQ1WCOz9Ljw",
            "timestamp": "1612864605",
            "type": "voice",
            "voice": {
                "id": "92791524-ee52-47cf-a371-188a68798e89",
                "mime_type": "audio/ogg; codecs=opus",
                "sha256": "61c6820f1fe108dee6fb4a8e9da41876f553372834e76fabebb45d41d6b7423b"
            }
        }
    ]
}

<h2>Document</h2>

{
    "contacts": [
        {
            "profile": {
                "name": "Supreet"
            },
            "wa_id": "919999420594"
        }
    ],
    "messages": [
        {
            "document": {
                "caption": "file-sample_100kB.doc",
                "filename": "file-sample_100kB.doc",
                "id": "63cb98e9-1da1-4f6e-8634-976d7bcb9de3",
                "mime_type": "application/msword",
                "sha256": "45e07cf623e634b96b3e82aeaf7ecc74efd03a05808bf1eb69ceef8a9df7d135"
            },
            "from": "919999420594",
            "id": "ABEGkZmZQgWUAhAZB4V0hd-OIpnIgxJiF66I",
            "timestamp": "1612864860",
            "type": "document"
        }
    ]
}

