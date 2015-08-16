<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8>
        <title>Tomcat WebSocket Chat</title>
        <script>

                var ws = new WebSocket("ws://"+window.location.host +"/LeafCutter/echo");
                ws.onopen = function(){
                };
                ws.onmessage = function(message){
                    document.getElementById("chatlog").textContent += message.data + "\n";
                };
                function postToServer(){
                    ws.send(document.getElementById("msg").value);
                    document.getElementById("msg").value = "";
                }
                function closeConnect(){
                    ws.close();
                }

        </script>
    </head>
    <body>
        <textarea id="chatlog" readonly></textarea><br/>
        <input id="msg" type="text" />
        <button id="sendButton" onClick="postToServer()">Send!</button>
        <button id="endButton" onClick="closeConnect()">End</button>
    </body>
</html>
