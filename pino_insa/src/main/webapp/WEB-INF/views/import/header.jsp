<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style>
	.title{
		overflow:hidden;
		background-color:#999;
		position:fixed;
		top:0;
		width:100%;
	}
	
	.header_left{
		text-align: left;
		width:49%;
		float:left;
		padding:14px 16px;
		color:#333;
	}
	
	.header_right{
		text-align: right;
		width:49%;
		float:right;
		padding:14px 16px;
		color:#333;
	}
	
	.header_left a{
		padding:14px 16px;
		color:#333;
		text-decoration: none;
	}
	
	.header_right a{
		padding:14px 16px;
		color:#333;
		text-decoration:none;
	}
	.title a:hover{
		background:#333;
		color:#ddd;
	}
</style>

<div class="title">
	<div class="header_left">
		<a href="insa_main.do">IT & BIZ</a>
	</div>
	<div class="header_right">
		<a href="insa_detail_form.do">입력하기</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="insa_form.do">조회하기</a>
	</div>
</div>