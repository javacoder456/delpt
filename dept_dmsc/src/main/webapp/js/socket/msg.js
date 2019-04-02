var webSocket;
jQuery(document).ready(function(){
    //心跳检测,每30s心跳一次
    var heartCheck = {
        timeout: 1000 * 30, //30S
        timeoutObj: null,
        serverTimeoutObj: null,
        reset: function(){
            clearTimeout(this.timeoutObj);
            clearTimeout(this.serverTimeoutObj);
            return this;
        },
        start: function(){
            var self = this;
            this.timeoutObj = setTimeout(function(){
                //这里发送一个心跳，后端收到后，返回一个心跳消息，
                var sendMessage ={
                    text:"heart"
                };
                webSocket.send(sendMessage);
                self.serverTimeoutObj = setTimeout(function(){
                    webSocket.close();
                }, self.timeout)
            }, this.timeout)
        }
    }
    var webSocketPath = getRootPath().replace("http","ws") + "/websocket.do";
    //webSocket
    if ('WebSocket' in window) {
        //webSocket
        webSocket = new WebSocket(webSocketPath);
    }

    webSocket.onOpen = function onOpen() {
        heartCheck.start();
    };
    webSocket.onMessage = function onMessage(evt) {
        //如果获取到消息，心跳检测重置
        heartCheck.reset();
        var dataMsg = jQuery.parseJSON(evt.data);
        if(dataMsg.type=="SYS_msg"){
            MsgBox.AlertSysMsg("系统提示：", dataMsg.text);
        }else {
            MsgBox.Alert("消息提示：", dataMsg.text);
        }
    };
    window.close = function () {
        webSocket.onclose;
    }
});

