function checkForm() {
	// 校验用户名:
	// 获得用户名文本框的值:
	var username = document.getElementById("username").value;
	if (username == null || username == '') {
		alert("用户名不能为空!");
		return false;
	}

	// 校验密码:
	// 获得密码框的值:
	var password = document.getElementById("password").value;
	if (password == null || password == '') {
		alert("密码不能为空!");
		return false;
	}
	// 校验确认密码:
	var repassword = document.getElementById("repassword").value;
	if (repassword != password) {
		alert("两次密码输入不一致!");
		return false;
	}
}
function showDetail(oid) {

	var but = document.getElementById("but" + oid);
	var div = document.getElementById("div" + oid);
	if (but.value == "订单详情") {
		// 创建异步交互对象
		var xhr = createXMLHttpRequest();
		// 绑定回调函数
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {// 判断响应状态码为200
					var data = xhr.responseText;
					// 接收的是一个HTML代码片段，将片段直接通过 innerHTML 插入指定位置
					div.innerHTML = data;
				}
			}
		}
		
	
	// 打开链接
	xhr.open("GET",
			"${pageContext.request.contextPath}/adminOrder_findOrderItem.action?time="
					+ new Date().getTime() + "&oid=" + oid, true);
	// 发送
	xhr.send(null);
	but.value = "关闭";
	}
	else{
	   but.value = "订单详情";
	   div.innerHTML="";
	}
}
function checkUser() {
	var username = document.getElementById("username").value;
	// 创建异步交互对象
	var xhr = createXMLHttpRequest();
	// 绑定回调函数
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {// 判断响应状态码为200
				var data = xhr.responseText;
				// 接收的是一个HTML代码片段，将片段直接通过 innerHTML 插入指定位置
				document.getElementById("alertUsername").innerHTML = data;
			}
		}
	}
	// 打开连接
	xhr.open("GET",
			"${pageContext.request.contextPath}/user_findByName.action?time="
					+ new Date().getTime() + "&username=" + username, true);
	// 发送

	xhr.send(null);
}
function createXMLHttpRequest() {
	try {
		xmlHttp = new XMLHttpRequest();
	} catch (tryMS) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (otherMS) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				xmlHttp = null;
				// 这里可以报一个错误，无法获得 XMLHttpRequest对象
			}
		}
	}
	return xmlHttp;
}
function change() {
	var img = document.getElementById("checkImage");
	img.src = "${pageContext.request.contextPath}/checkImg.action?time="
			+ new Date().getTime();
}
function showtime() {
	var today, hour, second, minute, year, month, date;
	var strDate;
	today = new Date();
	var n_day = today.getDay();
	switch (n_day) {
		case 0 : {
			strDate = "星期日"
		}
			break;
		case 1 : {
			strDate = "星期一"
		}
			break;
		case 2 : {
			strDate = "星期二"
		}
			break;
		case 3 : {
			strDate = "星期三"
		}
			break;
		case 4 : {
			strDate = "星期四"
		}
			break;
		case 5 : {
			strDate = "星期五"
		}
			break;
		case 6 : {
			strDate = "星期六"
		}
			break;
		case 7 : {
			strDate = "星期日"
		}
			break;
	}
	year = today.getFullYear();
	month = today.getMonth() + 1;
	date = today.getDate();
	hour = today.getHours();
	minute = today.getMinutes();
	if (minute <= 9) {
		minute = "0" + minute;
	}
	second = today.getSeconds();
	if (second <= 9) {
		second = "0" + second;
	}
	document.getElementById('time').innerHTML = year + "年" + month + "月" + date
			+ "日" + strDate + " " + hour + ":" + minute + ":" + second; // 显示时间
	setTimeout("showtime();", 1000); // 设定函数自动执行时间为 1000 ms(1 s)
}