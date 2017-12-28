<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<html>
	<head>
		<t:head />
		<script language="javascript" src="html/js/manageGrade.js"></script>
		<link rel="stylesheet" href="html/css/grade.css" type="text/css"></link>
	</head>
	<body>
		<t:menu />

		<div class="main-top">
			<span class="title">学生成绩查询</span>
		</div>

		<div class="main-body">
			<form name="gradeForm" method="POST" action="gradeSearch.do">
				<table class="main-table" cellspacing="0" width="100%" border="1"
					align="center">
					<tr>
						<td colspan="11">
							<input type="hidden" name="queryoption">
							<input type="radio" size="2" name="queryradio" value="0"
								<c:if test="${queryoption eq '0'}">checked</c:if>>
							查询学号或姓名为
							<input type="text" name="querystring" value="${querystring}"
								onfocus="selectoption(gradeForm,0)">
							的学生成绩
							<br> 
							<input type="radio" size="2" name="queryradio" value="1"
								<c:if test="${queryoption eq '1'}">checked</c:if>>
							要查询成绩小于&nbsp;
							<input type="text" name="querystring1" value="${querystring1}"
								onfocus="selectoption(gradeForm,1)">
							分的学生成绩
							<br>
							<input type="radio" size="2" name="queryradio" value="2"
								<c:if test="${queryoption eq '2'}">checked</c:if>>
							查询总成绩前&nbsp;&nbsp;
							<input type="text" name="querystring2" value="${querystring2}"
								onfocus="selectoption(gradeForm,2)">
							名的学生成绩
							<br>
	<!-- 新增查询学科平均成绩============================================================= -->						
<!-- 新增 start------------------------------------------------->
						
							<input type="radio" size="2" name="queryradio" value="2"
								<c:if test="${queryoption eq '3'}">checked</c:if>>
							查询课程名为&nbsp;&nbsp;
							<input type="text" name="querystring3" value="${querystring3}"
								onfocus="selectoption(gradeForm,3)">
							的学生平均成绩
							
<!-- 新增 end-------------------------------------------------->
							&nbsp;&nbsp;
							<input type=button name="querybtn" value="查 询" onclick="javascript:query(gradeForm)">
						</td>
					</tr>
<!-- 初次跳转到本页面显示系统当前已有课程名称以方便查询用户输入指定学科的名称  -->
				<c:if test="${empty gradelist}">
					<tr class="title">
						<td colspan="11">系统现有课程名称列表如下 ：</td>
					</tr>
					<tr>
						<td>
							电子技术
						</td>
						<td>
							软件工程
						</td>
						<td>
							计算机网络与信息安全
						</td>
						<td>
							Java程序设计
						</td>
						<td>
							高级数据库
						</td>
						<td>
							图形图像处理技术
						</td>
						<td>
							分布计算与互联网技术
						</td>
						<td>
							软件测试与自演化技术
						</td>
					</tr>
				</c:if>
				<c:if test="${!empty gradelist}">
					<tr class="title">
						<td>
							学号
						</td>
						<td class="name">
							姓名
						</td>
						<td>
							电子技术
						</td>
						<td>
							软件工程
						</td>
						<td>
							计算机网络与信息安全
						</td>
						<td>
							Java程序设计
						</td>
						<td>
							高级数据库
						</td>
						<td>
							图形图像处理技术
						</td>
						<td>
							分布计算与互联网技术
						</td>
						<td>
							软件测试与自演化技术
						</td>
						<td>
							总 分
						</td>
					</tr>
				<c:forEach items="${gradelist}" var="grade">
					<tr>
						<td>
							${grade.student.stuNumber}
						</td>
						<td>
							<c:out value="${grade.student.username}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numElectron}" default="0"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numSoftware}" default="0"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numSecurity}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numJava}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numDB}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numImage}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numDistributed}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.numTest}"></c:out>
						</td>
						<td class="num">
							<c:out value="${grade.total}"></c:out>
						</td>
					</tr>
				</c:forEach> 

</c:if>			
				<!-- 查询学科平均分的学科名输入框没有输入值，返回页面也并没有记录数时显示 -->
				<c:if test="${empty querystring3}">
					<c:if test="${empty gradelist}">
						<tr>
							<td colspan="11">
								<span class="error"> 没有任何学生成绩，请重新查询!!</span>
							</td>
						</tr>
					</c:if> 
				</c:if>
					<!-- 查询学科的平均成绩 -->
					<c:if test="${!empty querystring3}">
						<c:if test="${avg eq '0'}">
							<tr>
								<td colspan="11">
									<span> 系统不存在学科名为: <span style="font-size:18px;color:red;font-weight:strong;"> ${querystring3} </span>的学科或者该学科<span style="font-size:18px;color:red;font-weight:strong;"> 尚未开放 </span>，请检查学科名输入是否正确!</span>
								</td>
							</tr>
						</c:if> 
					<c:if test="${avg ne '0'}">
						<tr>
							<td colspan="11">
								学科 <span style="font-size:18px;color:red;font-weight:strong;"> ${querystring3} </span>的平均学科成绩为 <span style="font-size:18px;color:red;font-weight:strong;">${avg}</span>
							</td>
						</tr>
					</c:if>
					</c:if>
				</table>
			</form>
		</div>

		<t:foot />
	</body>
</html>