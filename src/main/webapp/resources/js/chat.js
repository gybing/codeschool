/**
 * 
 */

$(".toggle-show").click(function(e) {
	e.preventDefault();
	
	var $body = $(".chat-body");
	console.log("点击" + $body);
	if ($body.hasClass("hidden")) {
		$body.removeClass("hidden");
	} else {
		$body.addClass("hidden");
	}
});

// 设置聊天对话框的聊天目标
$(".chat-link").click(function(e) {
	e.preventDefault();
	
	$(".chat-user").html($(".chat-link").attr("data-user"));
});

// 发送消息
$("#btn-send").click(function(e) {
	e.preventDefault();
	if ("" == $(".chat-user").html()) {
		return;
	}
	
	
	var json = {
		message: $("#input-msg").val()
	};
	
	$.ajax({
		url : "/codeschool/chat/send/" + $(".chat-user").html(),
		type : "POST",
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(json),
		success : function(data) {
			if (true == data.result) {
				$(".chat").append(createMessageElement("消息发送成功!", "我"));
			} else {
				$(".chat").append(createMessageElement("消息[" + $("#input-msg").val() + "]发送失败!原因：用户不在线", "我"));
			}
		}
	});
});

// 接收消息
function receiveMsg() {
	$.ajax({
		url : "/codeschool/chat/recv/admin",
		type : "GET",
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			if (true == data.result) {
				$(".chat").append(createMessageElement(data.data[0].content, data.data[0].from));
			}
		}
	});
	setTimeout(receiveMsg, 10000);
}

function createMessageElement(content, user) {
	return $('<li class="right clearfix"><div class="chat-body clearfix"><div class="header"><small class=" text-muted"><span class="glyphicon glyphicon-time"></span>15 mins ago</small><strong class="pull-right primary-font">' + user + '</strong></div><p>' + content + '</p></div></li>');
}