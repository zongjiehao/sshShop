<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript">
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
	</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单
							列 表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="7%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<td align="center" width="10%">总金额</td>
								<td align="center" width="10%">收货人</td>
								<td align="center" width="10%">订单状态</td>
								<td width="*" align="center">
								                        订单详情    
                                </td>
							</tr>
							<s:iterator var="order" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#order.oid" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="7%"><s:property value="#order.total" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="7%"><s:property value="#order.name" /></td>
									
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                        width="7%">
                                        <s:if test="#order.state==1">未付款</s:if>
                                        <s:elseif test="#order.state==2"><a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#order.oid"/>"><font color="blue">发货</font></a></s:elseif>
                                        <s:elseif test="#order.state==3">未确认收货</s:elseif>
                                        <s:elseif test="#order.state==4">交易完成</s:elseif>
                                        </td>
									<td align="center" width="*" style="HEIGHT: 22px">
											<input  id="but<s:property value="#order.oid"/>"type="button" value="订单详情" onclick="showDetail(<s:property value="#order.oid"/>);"/>
											<div id="div<s:property value="#order.oid"/>"></div>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#f5fafe">第<s:property
							value="pageBean.page" />/<s:property value="pageBean.pageCount" />页&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="pageBean.page!=1">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">|&nbsp;上一页</a>
						</s:if> <s:if test="pageBean.page!=pageBean.pageCount">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">|&nbsp;下一页</a>
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.pageCount"/>">|&nbsp;尾页</a>
						</s:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

