<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	.table_title{
		width:10%;
		text-align: center;
	}
	.table_data{
		text-align: center;
	}
	.employee_table1, .employee_table2{
		width:99%;	
		text-align: center;			
		margin-left:auto;
		margin-right:auto;		
	}
	article{
		margin-top:80px;
	}
	.employee_table1 tr, .employee_table2 tr{
		height:40px;
	}
	.col_title{
		width:8%;
	}
	.input_title{
		width:15%;
	}
	.employee_table1 select{
		width:99%;
		height:40px;
 		text-align: center center;
	}
	.employee_table1 input{
		width:99%;
		height:40px;
		text-align: center center;
	}
	
	.bt_area{
		padding-left:13.74%;
	}
	
	.bt_area input{
		width:60px;
		height:30px;
		float:left;
		margin-right:10px;
	}	
	.table_data td:hover{
		cursor: pointer;
	}
	.pagination{
/* 		float:left; */
/* 		display:inline-block; */
/*  	width:100%;  */
/* 		margin-left:auto; */
/* 		margin-right:auto; */
	}
	
	.pagination ul{
	/* 	width:950px; */
		margin-left:auto;
		margin-right:auto;
		display:table;
		list-style-type: none; /* 메인 메뉴 안의 ul 내부의 목록 표시를 없애줌 */
	   margin: 0px; /* 메인 메뉴 안의 ul의 margin을 없앰 */
	   padding: 0px; /* 메인 메뉴 안의 ul의 padding을 없앰 */
	}
	
	.pagination ul li{
		color: black; /* 글씨 색을 흰색으로 설정 */
	   float: left; /* 왼쪽으로 나열되도록 설정 */
	   vertical-align: middle; /* 세로 정렬을 가운데로 설정 */
	   text-align: center; /* 텍스트를 가운데로 정렬 */
	   position: relative; /* 해당 li 태그 내부의 top/left 포지션 초기화 */
	    z-index:9;
	}
	
	.pagination ul li a{
		text-decoration:none;
	}
	
	.pageNum{
		width:35px;
		display:block;
	}
	
	.selectedPage{
		width:35px;
		background-color:#2A5798;
		color:white;
	}
	
	.selectedPage B{
		color:white;
	}
	
	.selectedPage span, .pageNum span{
	}
	
	.pagination ul li a:hover{
		background-color:#2A5798;
		color:white;
	}
</style>
<script>
	$(function(){
		var sabun = $('#sabun');
		var name = $('#name').val();
		var joinYn = $('#join_yn');
		var putYn = $('#put_yn');
		var posGbnCode = $('#pos_gbn_code');
		var joinDate = $('#join_date').val();
		var retireDate = $('#retire_date').val();
		var jobType = $('#job_type');
		$('#bt_search').click(function(){
// 			console.log("1sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn.val() + ", putYn : " + putYn.val() + ", posGbnCode : " + posGbnCode.val()
// 					+ ", joinDate : " + joinDate + ", retireDate : " +retireDate);
// 			alert("1sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn.val() + ", putYn : " + putYn.val() + ", posGbnCode : " + posGbnCode.val()
// 					+ ", joinDate : " + joinDate + ", retireDate : " +retireDate);
			if(sabun.val() == null || sabun.val() == 0 || sabun.val() == ''){
				sabun.val('');
			}
			if(joinYn.val() == '(선택)'){
				joinYn.val('');
			}
			if(putYn.val() == '(선택)'){
				putYn.val('');
			}
			if(posGbnCode.val() == '(선택)'){
				posGbnCode.val('');
			}
			if(jobType.val() == '(선택)'){
				jobType.val('');
			}
// 			console.log("2sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn.val() + ", putYn : " + putYn.val() + ", posGbnCode : " + posGbnCode.val()
// 					+ ", joinDate : " + joinDate + ", retireDate : " +retireDate);
// 			alert("2sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn.val() + ", putYn : " + putYn.val() + ", posGbnCode : " + posGbnCode.val()
// 			+ ", joinDate : " + joinDate + ", retireDate : " +retireDate);
			$('#searchForm').submit();
		});
		
		
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="../import/header.jsp"/>	
	</header>
	<article>
		<h1 style="margin-left:10px;">직원 리스트</h1>
		<div>
			<form action="/insa_form.do" method="get" id="searchForm" name="searchForm">
				<table class="employee_table1" border="1">
	<!-- 			<table class="employee_table1"> -->
					<tr>
						<td class="col_title"><span>사번</span></td>
						<td class="input_title"><input type="text" id="sabun" name="sabun"/></td>
						<td class="col_title"><span>성명</span></td>
						<td class="input_title"><input type="text" id="name" name="name"/></td>
						<td class="col_title"><span>입사구분</span></td>
						<td class="input_title">
							<select id="join_yn" name="join_yn">
								<option>(선택)</option>
								<c:forEach items="${joinList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title"><span>투입여부</span></td>
						<td class="input_title">
							<select id="put_yn" name="put_yn">
								<option>(선택)</option>
								<c:forEach items="${ptList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>			
					<tr>
						<td class="col_title">
							<span>직위</span>
						</td>
						<td class="input_title">
							<select id="pos_gbn_code" name="pos_gbn_code">
								<option>(선택)</option>
								<c:forEach items="${posList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">
							<span>입사일자</span>
						</td>
						<td class="input_title">
							<input type="date" size="20" id="join_date" name="join_date">
						</td>
						<td class="col_title">
							<span>퇴사일자</span>
						</td>
						<td class="input_title">
							<input type="date" size="20" id="retire_date" name="retire_date">
						</td>
						<td class="col_title">
							<span>직종분류</span>
						</td>
						<td class="input_title">
							<select id="job_type" name="job_type">
								<option>(선택)</option>
								<c:forEach items="${jobList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="6"></td>
						<td colspan="2" class="bt_area">
							<input type="button" value="검색" id="bt_search"/>
							<input type="reset" value="초기화"/>
							<input type="button" value="이전" id="" name=""/>
						</td>						
					</tr>
				</table>
			</form>
			<br><br>
			<div>
				<table class="employee_table2" border="1">
					<tr>
						<th class="table_title">사번</th>
						<th class="table_title">성명</th>
						<th class="table_title">주민번호</th>
						<th class="table_title">핸드폰번호</th>
						<th class="table_title">직위</th>
						<th class="table_title">입사일자</th>
						<th class="table_title">퇴사일자</th>
						<th class="table_title">투입여부</th>
						<th class="table_title">연봉</th>
					</tr>
					<tbody>
						<c:if test="${insaList eq null || insaList eq '[]'}">
							<tr class="table_data">
								<td colspan="9">검색된 데이터가 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${insaList ne null && insaList ne '[]'}">
							<c:forEach var="il" items="${insaList}">
								<tr class="table_data">
<%-- 								href="insa_edit_form.do?sabun='${il.sabun}'" --%>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">${il.sabun}</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">${il.name}</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">
										<c:if test="${il.regNo ne null}">
											<c:out value="${fn:substring(il.regNo, 0, fn:length(il.regNo) - 6)}" />******
										</c:if>
										<c:if test="${il.regNo eq null}">
											<c:out value="${il.regNo}"/>
										</c:if>
									</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">${il.hp}</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">${il.posGbnCode}</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">
										<fmt:parseDate value="${il.joinDate}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate pattern="yyyy-MM-dd" value="${dateFmt}"/>
									</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">
										<c:if test="${il.retireDate ne null && il.retireDate ne ''}">
											<fmt:parseDate value="${il.retireDate}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
											<fmt:formatDate pattern="yyyy-MM-dd" value="${dateFmt}"/>
										</c:if>
										<c:if test="${il.retireDate eq null || il.retireDate eq ''}">
											${il.retireDate}
										</c:if>
									</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'">${il.putYn}</td>
									<td onclick="location.href='insa_edit_form.do?sabun=${il.sabun}'"><fmt:formatNumber pattern="###,###">${il.salary eq 0 ? '' : il.salary}</fmt:formatNumber></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
			</div>
		</div>
	</article>
	<footer style="text-align: center;">
		<div id="pagination" class="pagination" >
			<ul>	
				<c:if test="${maxPn > 1}">
				<li>
					<a href="${pageContext.request.contextPath}/insa_form.do?pn=1">
						<span>첫페이지</span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/insa_form.do?pn=${(pn-1) <= 1 ? 1 : (pn-1)}">
							<span>&nbsp;이전페이지</span>
					</a>
				</li>			
				</c:if>	
				<c:if test="${maxPn < 10}">
					<c:forEach varStatus="vs" begin="1" end="${maxPn}" step="1">
						<c:if test="${pn eq vs.current}"> 
								<li class="selectedPage"><span><B>${vs.current}</B></span></li> 
							</c:if> 
							<c:if test="${pn ne vs.current}">
								<li> 
									<a class="pageNum" href="${pageContext.request.contextPath}/insa_form.do?pn=${vs.current}"> 
										<span>${vs.current}</span>									 
									</a>				
								</li>
							</c:if>
							<li>${vs.current eq vs.end ? '' : '|'}</li>
					</c:forEach>
				</c:if>
				<c:if test="${maxPn >= 10}">		
					<c:forEach varStatus="vs" begin="${pn-4 <= 1 ? 1 : (maxPn - pn < 5 ? maxPn - 8 : pn-4)}" 
												end="${pn+4 < maxPn ? (pn+4 < 10 ? 9 : pn+4) : maxPn}" step="1">
							<c:if test="${pn eq vs.current}">
								<li class="selectedPage"><span><B>${vs.current}</B></span></li>
							</c:if>
							<c:if test="${pn ne vs.current}">
								<li>
									<a class="pageNum" href="${pageContext.request.contextPath}/insa_form.do?pn=${vs.current}">
										<span>${vs.current}</span>
									</a>
								</li>				
							</c:if>
							<li>${vs.current eq vs.end ? '' : '|'}</li>
					</c:forEach>
				</c:if>
				<c:if test="${maxPn > 1}">
				<li>
					<a href="${pageContext.request.contextPath}/insa_form.do?pn=${(pn+1) >= maxPn ? maxPn : (pn+1)}">
						<span>다음페이지&nbsp;</span>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/insa_form.do?pn=${maxPn}">
						<span>마지막페이지</span>									
					</a>
				</li>
				</c:if>
			</ul>				
		</div>
	</footer>
</body>
</html>