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
                    var txtBox = document.getElementById("chatlog");
                    txtBox.textContent += message.data + "\n";
                    txtBox.scrollTop = txtBox.scrollHeight;
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

        <textarea id="chatlog" rows="12" cols="30" readonly></textarea><br/>
        <input id="msg" type="text" onkeydown="if (event.keyCode == 13) document.getElementById('sendButton').click()"/>
        <button type="submit" id="sendButton" onClick="postToServer()">Send!</button>
        <button type="submit" id="endButton" onClick="closeConnect()">End</button>

    </body>
</html>
