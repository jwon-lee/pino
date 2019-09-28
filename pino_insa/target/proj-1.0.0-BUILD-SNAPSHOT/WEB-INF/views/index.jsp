<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인사시스템</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style>

	body{
		background-color:#333;
		color:#f2f2f2;
	}

	.title{
		overflow:hidden;
		background-color:#333;
		position:fixed;
		top:0;
		width:100%;
	}
	
	.index_main .bt{
		color:black;
	}
	
	.main_logo, .main_top_center, .main_top_right{
		width:30%;
 		float:left;
    	border:1px #333 dotted;
	}
	
	.main_logo a{
		float:right;
		display:block;
		color:#f2f2f2;
		text-align:right;
		padding:14px 16px;
		text-decoration: none;
	}
	
	.main_top_right a{
		float:left;
		display:block;
		color:#f2f2f2;
		text-align:left;
		padding:14px 16px;
		text-decoration:none;
	}
	
	.main_top_right a:hover{
		background:#ddd;
		color:balck;
	}
	
	.index_main{
		margin-top:60px;
		width:100%;
		text-align:center;
		margin-top:20%;
		margin-bottom: 20%;
	}
	
	.main_footer{
		width:32%;
/* 		border:1px red dotted; */
		text-align:center;
		margin-left:auto;
 		margin-right:auto;
	}
	
	.main_footer_container{
		margin-left:auto;
		margin-right:auto;
/* 		border:1px red dotted; */
	}

</style>

</head>
<body>
	<header>
		<div class="title">
			<div class="main_logo">
				<a href="insa_main.do">IT & BIZ</a>
			</div>
			<div class="main_top_center"></div>
			<div class="main_top_right">
				<nav class="header_nav">
					<a href="#Home" class="main_menu_link">Home</a>
					<a href="#Input" class="main_menu_link">Input</a>
					<a href="#Search" class="main_menu_link">Search</a>
				</nav>
			</div>
		</div>
	</header>
	<article class="index_main">
		<h2>인사관리 시스템</h2>
		<h4>인사정보를 입력하겠습니다.<a href="insa_detail_form.do"><input type="button" value="입력" class="bt"/></a></h4>
		<h4>인사정보를 조회하겠습니다.<a href="insa_form.do"><input type="button" value="조회" class="bt"/></a></h4>
	</article>
	<footer>
		<div class="main_footer_container">
			<div class="main_footer">
				서울시 금천구 서부샛길 606 A-1306(가산동 대성디폴리스 지식산업센터)/Tel 02-6049-1114 / Fax 02-861-9883 Copyright&copy;2015 IT&BIZ co.LTD.All rights reserved by itnbiz@naver.com
			</div>
		</div>
	</footer>
</body>
</html>