var stompClient = null;

function connect() {
    var socket = new SockJS('/test-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/test/messages', function (messages) {
        	showMessages(JSON.parse(messages.body));
        });
    });
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'content': $("#content").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function showMessages(msgs){
	$("#messages").empty();
	for( i = 0; i < msgs.length; ++i) {
		showMessage(msgs[i].content);
    }
}

$(function () {
	connect();
	$("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendMessage(); });
});
