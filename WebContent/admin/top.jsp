<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>
<link href="${pageContext.request.contextPath}/css/Style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validate.js"></script>
</HEAD>
<body>
	<table width="100%" height="70%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td><img width="100%"
				src="${pageContext.request.contextPath}/images/top_01.jpg"></td>

			<td width="100%"
				background="${pageContext.request.contextPath}/images/top_100.jpg">
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" valign="bottom"
				background="${pageContext.request.contextPath}/images/mis_01.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="time"></span> <script
								language="javascript">
								showtime();
							</script>
						</td>
						<td width="15%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="16"
										background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										<img
										src="${pageContext.request.contextPath}/images/mis_05a.jpg"
										width="6" height="18">
									</td>
									<td width="145" valign="bottom"
										background="${pageContext.request.contextPath}/images/mis_05b.jpg">
										用户名： <font color="blue"><s:property
												value="#session.existAdminUser.username" /></font> <s:if
											test="#session.existAdminUser.username!=null">
											<a   style="text-decoration:none;" target="_parent"
												href="${pageContext.request.contextPath}/adminUser_quit.action"><font color="black">[退出]</font></a>
										</s:if>
									</td>
								</tr>
							</table>
						</td>
						<td align="right" width="5%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>
