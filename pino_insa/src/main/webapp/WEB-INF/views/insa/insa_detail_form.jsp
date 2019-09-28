<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인사관리시스템</title>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	article{
		margin-top:80px;
	}
	.table_title{
		width:10%;
	}
	.table_data{
		text-align: center;
	}
	.employee_table1, .employee_table2{
		width:99%;	
		text-align: center;
		margin:auto;	
	}
	.employee_table1 tr{
		height:50px;
	}
	.bt_area{
 		float:right; 
		margin-right:10px;
	}
	.employee_table1 .bt_area{
		width:200%;
		border:1px red dotted;
	}
	.employee_table1 .bt_area2 input{
   		width:50%;
 		height:35px; 
 		float:left; 		
		text-align: center center;
		background-color:white;
		color:black;
		border: 1px black solid;
	}
	.coltitle{
		width:6%;
	}
	.input_title input, .input_title select, .input_title textarea{
 		width:99%; 
		height:35px;
	}
	.modal-title{
		text-align: center;
	}
	#view_area{
		background-image: url("http://blogfiles.naver.net/MjAxNzAxMTNfMjQx/MDAxNDg0MzAzNzI1NTMz.SazKEYPz8cot5BRbEB79gweckCOsZP7xVHZFjCm7dj0g.yqy8nJ9DVRU8dAwo3nGL2w41jT-m-8LtE1hNhsJcTbAg.JPEG.koomarin/2017-01-13_19%3B23%3B45.jpg");
		height: 270px;
	    width: 300px;
	    margin: auto;
	    background-repeat: no-repeat;
/* 	    border: 1px red dotted; */
	}
</style>
<script>

	function previewImage(targetObj, View_area) {
		var preview = document.getElementById(View_area); //div id
		var ua = window.navigator.userAgent;
	
	  //ie일때(IE8 이하에서만 작동)
		if (ua.indexOf("MSIE") > -1) {
			targetObj.select();
			try {
				var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
				var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);
	
	
				if (ie_preview_error) {
					preview.removeChild(ie_preview_error); //error가 있으면 delete
				}
	
				var img = document.getElementById(View_area); //이미지가 뿌려질 곳
	
				//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
			} catch (e) {
				if (!document.getElementById("ie_preview_error_" + View_area)) {
					var info = document.createElement("<p>");
					info.id = "ie_preview_error_" + View_area;
					info.innerHTML = e.name;
					preview.insertBefore(info, null);
				}
			}
	  //ie가 아닐때(크롬, 사파리, FF)
		} else {
			var files = targetObj.files;
			for ( var i = 0; i < files.length; i++) {
				var file = files[i];
				var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
				if (!file.type.match(imageType))
					continue;
				var prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
				var nowImg = document.getElementById("profile_img");
				if(nowImg){
					preview.removeChild(nowImg);
				}
				if (prevImg) {
					preview.removeChild(prevImg);
				}
				var img = document.createElement("img"); 
				img.id = "prev_" + View_area;
				img.classList.add("obj");
				img.file = file;
				img.style.width = '270px'; 
				img.style.height = '300px';
				preview.appendChild(img);
				if (window.FileReader) { // FireFox, Chrome, Opera 확인.
					var reader = new FileReader();
					reader.onloadend = (function(aImg) {
						return function(e) {
							aImg.src = e.target.result;
						};
					})(img);
					reader.readAsDataURL(file);
				} else { // safari is not supported FileReader
					//alert('not supported FileReader');
					if (!document.getElementById("sfr_preview_error_"
							+ View_area)) {
						var info = document.createElement("p");
						info.id = "sfr_preview_error_" + View_area;
						info.innerHTML = "not supported FileReader";
						preview.insertBefore(info, null);
					}
				}
			}
		}
	}

	/** * 이메일 형식 체크 * * @param 데이터 */ 
	function emailCheck(email) { 
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; 
		if(exptext.test(email) == false) { 
			// 이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우 
			alert("이메일형식이 올바르지 않습니다."); 
			return false; 
		} 
		return true; 
	} 
	/** * 특수문자 여부 체크 * * @param 데이터 */ 
	function checkSpecial(str) { 
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi; if (special_pattern.test(str) == true) { 
			return 0; 
		} else { 
			return -1; 
		} 
	} 
	/** * 전화번호 포맷으로 변환 * * @param 데이터 */ 
	function formatPhone(phoneNum) { 
		if(isPhone(phoneNum)) { 
			var rtnNum; 
			var regExp =/(02)([0-9]{3,4})([0-9]{4})$/; 
			var myArray; 
			if(regExp.test(phoneNum)){ 
				myArray = regExp.exec(phoneNum); 
				rtnNum = myArray[1]+'-' + myArray[2]+'-'+myArray[3]; 
				return rtnNum; 
			} else { 
				regExp =/(0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/; 
				if(regExp.test(phoneNum)){ 
					myArray = regExp.exec(phoneNum); 
					rtnNum = myArray[1]+'-'+myArray[2]+'-'+myArray[3]; 
					return rtnNum; 
				} else { 
					return phoneNum; 
				} 
			} 
		} else { 
			return phoneNum; 
		} 
	} 
	/** * 핸드폰번호 포맷으로 변환 * * @param 데이터 */ 
	function formatMobile(phoneNum) { 
		if(isMobile(phoneNum)) {
			var rtnNum; 
			var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/; 
			var myArray; 
			if(regExp.test(phoneNum)){ 
				myArray = regExp.exec(phoneNum); 
				rtnNum = myArray[1]+'-'+myArray[2]+'-'+myArray[3]; 
				return rtnNum; 
			} else { 
				return phoneNum; 
			} 
		} else if(isMobileHyphen(phoneNum)){
			var regExp =/(01[016789])-([1-9]{1}[0-9]{2,3})-([0-9]{4})$/;
			var myArray;
			if(regExp.test(phoneNum)){
				myArray = regExp.exec(phoneNum);
				return myArray;
			}else{
				return phoneNum;
			}
		} else {
			return phoneNum; 
		} 
	} 
	/** * 전화번호 형식 체크 * * @param 데이터 */ 
	function isPhone(phoneNum) { 
		//var regExp =/(02|0[3-9]{1}[0-9]{1})[1-9]{1}[0-9]{2,3}[0-9]{4}$/; 
		var regExp =/(02)([0-9]{3,4})([0-9]{4})$/; 
		var myArray; 
		if(regExp.test(phoneNum)){ 
			myArray = regExp.exec(phoneNum); 
			// console.log(myArray[1]); 
			// console.log(myArray[2]); 
			// console.log(myArray[3]); 
			return true; 
		} else { 
			regExp =/(0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/; 
			if(regExp.test(phoneNum)){ 
				myArray = regExp.exec(phoneNum); 
				// console.log(myArray[1]); 
				// console.log(myArray[2]); 
				// console.log(myArray[3]); 
				return true; 
			} else { 
// 				alert('전화번호 형식이 올바르지 않습니다.');
				return false; 
			} 
		} 
	} 
	
	function isPhoneHyphen(phoneNum) { 
		//var regExp =/(02|0[3-9]{1}[0-9]{1})[1-9]{1}[0-9]{2,3}[0-9]{4}$/; 
		var regExp =/(02)-([0-9]{3,4})-([0-9]{4})$/; 
		var myArray; 
		if(regExp.test(phoneNum)){ 
			myArray = regExp.exec(phoneNum); 
			// console.log(myArray[1]); 
			// console.log(myArray[2]); 
			// console.log(myArray[3]); 
			return true; 
		} else { 
			regExp =/(0[3-9]{1}[0-9]{1})-([0-9]{3,4})-([0-9]{4})$/; 
			if(regExp.test(phoneNum)){ 
				myArray = regExp.exec(phoneNum); 
				// console.log(myArray[1]); 
				// console.log(myArray[2]); 
				// console.log(myArray[3]); 
				return true; 
			} else { 
// 				alert('전화번호 형식이 올바르지 않습니다.');
				return false; 
			} 
		} 
	} 
	
	/** * 핸드폰번호 형식 체크 * * @param 데이터 */ 
	function isMobile(phoneNum) { 
		var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/; 
		var myArray; 
		if(regExp.test(phoneNum)){ 
			myArray = regExp.exec(phoneNum); 
			// console.log(myArray[1]); 
			// console.log(myArray[2]); 
			// console.log(myArray[3]); 
			return true; 
		} else { 
// 			alert('휴대전화번호 형식이 올바르지 않습니다.');
			return false; 
		} 
	}
	
	function isMobileHyphen(phoneNum) { 
		var regExp =/(01[016789])-([1-9]{1}[0-9]{2,3})-([0-9]{4})$/; 
		var myArray; 
		if(regExp.test(phoneNum)){ 
			myArray = regExp.exec(phoneNum); 
			// console.log(myArray[1]); 
			// console.log(myArray[2]); 
			// console.log(myArray[3]); 
			return true; 
		} else { 
// 			alert('휴대전화번호 형식이 올바르지 않습니다.');
			return false; 
		} 
	}
	
	/** * 주민번호 포맷으로 변환 * * @param 데이터 */
	function formatRegNo(regNo){
		if(isRegNo(regNo)){
			var regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))[1-4][0-9]{6}$/;
			var regExp2 = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
			var myArray;
			var rtnNum = "";
			if(regExp.test(regNo)){
				myArray = regExp.exec(regNo);
				for(var i = 0; i < myArray[0].length; i++){
					rtnNum += myArray[0][i];
// 					console.log('i : ' + i + ', rtnNum : ' + rtnNum);
					if(i == 5){
						rtnNum += '-';
					}
				}
// 				alert('rtnNum : ' + rtnNum);
				return rtnNum;
			}else if(isRegNoHyphen(regNo)){
				myArray = regExp.exec(regNo);
				return myArray;
			}else{
				return regNo;
			}
		}else{
			return regNo;
		}
	}
	
	/** * 주민번호 형식 체크 * * @param 데이터 */ 
	function isRegNo(regNo) { 
		var regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))[1-4][0-9]{6}$/; 
		var myArray; 
		if(regExp.test(regNo)){ 
			myArray = regExp.exec(regNo); 
// 			alert("[0] : " + myArray[0]);
			return true; 
		} else { 
// 			alert('주민번호 형식이 올바르지 않습니다.');
			return false; 
		} 
	}
	
	/** * 주민번호 형식 체크 * * @param 데이터 */ 
	function isRegNoHyphen(regNo) { 
		var regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/; 
		var myArray; 
		if(regExp.test(regNo)){ 
			myArray = regExp.exec(regNo); 
// 			alert("[0] : " + myArray[0]);
			return true; 
		} else { 
// 			alert('주민번호 형식이 올바르지 않습니다.');
			return false; 
		} 
	}
	
	/** * 사업자번호 포맷으로 변환 * * @param 데이터 */
	function formatCmpNo(cmpNo){
		if(isCmpNo(cmpNo)){
			var regExp = /(\d{3})(\d{2})(\d{5})/;
			var myArray;
			var rtnNum = "";
			if(regExp.test(cmpNo)){
				myArray = regExp.exec(cmpNo);
				rtnNum = myArray[1] + '-' + myArray[2] + '-' + myArray[3];
// 				alert('rtnNum : ' + rtnNum);
				return rtnNum;
			}else if(regExp.test(cmpNo)){
				myArray = regExp.exec(cmpNo);
				rtnNum = myArray;
// 				alert('rtnNum : ' + rtnNum);
				return rtnNum;
			}else{
				return cmpNo;
			}
		}else{
			return cmpNo;
		}
	}
	
	/* 사업자번호 형식 체크 */
	function isCmpNo(cmpNo){
		var regExp = /(\d{3})(\d{2})(\d{5})/;
		var myArray;
		if(regExp.test(cmpNo)){
			myArray = regExp.exec(cmpNo);
// 			alert(myArray);
			return true;
		}else{
			return false;
		}
	}	
	function isCmpNoHyphen(cmpNo){
		var regExp = /(\d{3})-(\d{2})-(\d{5})/;
		var myArray;
		if(regExp.test(cmpNo)){
			myArray = regExp.exec(cmpNo);
// 			alert(myArray);
			return true;
		}else{
			return false;
		}
	}
	
	function isKorName(){
		var korNameChk = $('#kor_name');
		var regExp = /^[가-힣]{2,5}$/;
		var myArray;
		if(korNameChk.val() != ""){
			if(regExp.test(korNameChk.val())){
				myArray = regExp.exec(korNameChk.val());
	// 			alert(myArray);
				return true;
			}else{
				alert('이름 형식이 올바르지 않습니다.');
				korNameChk.val("");
				korNameChk.focus();
				return false;
			}
		}
	}
	
	function isEngName(){
		var korNameChk = $('#eng_name');
		var regExp = /^[a-zA-Z]*$/;
		var myArray;
		if(korNameChk.val() != ""){
			if(regExp.test(korNameChk.val())){
				myArray = regExp.exec(korNameChk.val());
	// 			alert(myArray);
				return true;
			}else{
				alert('이름 형식이 올바르지 않습니다.');
				korNameChk.val("");
				korNameChk.focus();
				return false;
			}
		}
	}
	
	function isSalary(salary){
		var regExp = /^[0-9]$/;
		var myArray;
		if(regExp.test(name)){
			myArray = regExp.exec(salary);
			return true;
		}else{
			alert('숫자만 입력 가능합니다');
			return false;
		}
	}
	
	function inputNumberFormat(obj) {
	    obj.value = comma(uncomma(obj.value));
	}

	function comma(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}

	function uncomma(str) {
	    str = String(str);
	    return str.replace(/[^\d]+/g, '');
	}
	
	
	
	function telBlur(){
		if($('#tel').val() != ""){
			if(isPhone($('#tel').val()) == true){
				$('#tel').val(formatPhone($('#tel').val()));
				return;
			}else if(isPhoneHyphen($('#tel').val()) == true){
				return;
			}else{
				alert("전화번호 형식이 올바르지 않습니다.");
				$('#tel').val("");
				$('#tel').focus();
				return;
			}
		}
	};
	
	function hpBlur(){
		if($('#hp').val() != ""){
			if(isMobile($('#hp').val()) == true){
				$('#hp').val(formatMobile($('#hp').val()));
				return;
			}else if(isMobileHyphen($('#hp').val()) == true){
				return;
			}else{
				alert("휴대전화번호 형식이 올바르지 않습니다.");
				$('#hp').val("");
				$('#hp').focus();
				return;
			}
		}
	};
	
	var reg_no_arr = "";
	var pwd_arr = "";
	var pwdchk_arr = "";
	
	function regNoBlur(){
		if($('#reg_no_hidden').val() != ""){
			if(isRegNo($('#reg_no_hidden').val()) == true){
				var xsex = $('#reg_no_hidden').val().substring(6, 7);
				$('#reg_no_hidden').val(formatRegNo($('#reg_no_hidden').val()));
				var birthYear = $('#reg_no_hidden').val().substring(0, 2);
				if(xsex >= 3){
					age = new Date().getYear() - birthYear + 1 - 100;		
				}else{
					age = new Date().getYear() - birthYear + 1;
				}
				$('#age').val(age);
				return;
			}else if(isRegNoHyphen($('#reg_no_hidden').val()) == true){
// 				alert("hyphen : " + isRegNoHyphen($('#reg_no')));
				var xsex = $('#reg_no_hidden').val().substring(7, 8);
				var birthYear = $('#reg_no_hidden').val().substring(0, 2);
				if(xsex >= 3){
					age = new Date().getYear() - birthYear + 1 - 100;		
				}else{
					age = new Date().getYear() - birthYear + 1;
				}
				$('#age').val(age);
				return;
			}else{
				alert("주민번호 형식이 올바르지 않습니다.");
				$('#reg_no').val("");
				$('#reg_no_hidden').val("");
				reg_no_arr = new Array();
// 				$('#reg_no').focus();
				return;
			}
		}
	};
	
	function cmpNoBlur(){
		if($('#cmp_no').val() != ""){
			if(isCmpNo($('#cmp_no').val()) == true){
				var first = $('#cmp_no').val().substring(0, 3);
				var second = $('#cmp_no').val().substring(3, 5);
				var third = $('#cmp_no').val().substring(5, 9);
				
				$('#cmp_no').val(formatCmpNo($('#cmp_no').val()));
// 				alert($('#cmp_no').val());				
				return;
			}else if(isCmpNoHyphen($('#cmp_no').val()) == true){
// 				alert("hyphen : " + isCmpNoHyphen($('#cmp_no')));
				
				return;
			}else{
				alert("사업자번호 형식이 올바르지 않습니다.");
				$('#cmp_no').val("");
				$('#cmp_no').focus();
				return;
			}
		}
	};
	
	function dupChk(){
		var dupId = $('#userid').val();
		if(dupId != ""){
			if(isId(dupId) == true){
				return dupId;
			}else{
				alert("이미 가입된 아이디입니다.");
				$('#userid').val("");
				$('#userid').focus();
				return;
			}
		}
	}
	
	function isId() {
		var chkId = $('#userid').val();
		var exptext = /^[A-Za-z0-9]{4,16}$/;
// 		console.log("id : " + chkId);
		if(chkId != ""){
			if(exptext.test(chkId) == false) { 
				// 이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우 
				alert("아이디는 영문, 숫자만 가능합니다."); 
				$('#userid').val("");
				$('#userid').focus();
				return false; 
			}
		}
		return true; 
	}
	
	
	
	function isZip(){
		var chkZip = $('#zip').val();
		var regExp = /^[0-9]{5,6}$/;
		var myArray;
		if(chkZip != ""){
			if(regExp.test(chkZip)){
				myArray = regExp.exec(chkZip);
				return true;
			}else{
				alert('우편번호는 숫자만 입력 가능합니다');
				$('#zip').val("");
				$('#zip').focus();
				return false;
			}
		}
	}
	
	$(function(){	
		
		reg_no_arr = new Array();
		pwd_arr = new Array();
		pwdchk_arr = new Array();
		
		$('#email2').change(function(){
			$("#email2 option:selected").each(function(){
				if($(this).val() == '1'){	//직접입력
					$('#str_email').val('@');
					$('#str_email').attr("disabled", false);	//활성화
					$('#str_email').css('display', 'block');
					$('#email2').attr('disabled', true);	//비활성화
					$('#email2').css('display', 'none');
				}else{	//리스트 선택
					$('#str_email').attr('disabled', true); //비활성화
					$('#email2').attr('disabled', false);	// 활성화
					$('#str_email').css('display', 'none');
					$('#email2').css('display', 'block');
				}
			});
		});
		
	     $("#reg_no").on("keyup", function(e) {
	    	 var reg_no = $("#reg_no").val();
	    	 var key = reg_no.replace(/\-/g,'');
// 	    	 var spanId = "#reg_no_hidden";
	    	 var num = "";
	    	 var ch_num = "";
	    	 var star_num = "";
	   		 var key_num = e.key;
	   		 var hidden_num = "";
	   		if (key.length < 7) {
	   		 $(this).val(key);
	   		 ch_num = key;
		   	 } else if (key.length >= 7 && key.length < 15) {
		  			 reg_no_arr.push(key_num);
		   		 post_reg_no = key.substr(6, 7);
		   		 for(i = 0; i < post_reg_no.length-1; i++) {
		   			 star_num += "*";
		   		 }
		   		 
		   		 num += key.substr(0, 6);
		   		 num += "-";
		   		 num += key.substr(6, 1);
		   		 num += star_num;
		   		 num += key.substr(12, 2);
		   		 $(this).val(num);
		   		 
		   		 ch_num += key.substr(0, 7);
		   		 ch_num += reg_no_arr.join("");
		   	 }
		   	 hidden_num += key.substr(0, 6);
		   	 hidden_num += "-";
		   	 hidden_num += reg_no_arr.join("");
		   	 $("#reg_no_hidden").val(hidden_num);
	     });
	
	     
	     $("#pwd").on("keyup", function(e) {
	    	 var pwd = $("#pwd_hidden");
	    	 var key = pwd.val().replace(/^[a-zA-Z-0-9]$/g,'');
	    	 var num = "";
	    	 var ch_num = "";
	    	 var star_num = "";
	   		 var key_num = e.key;
	   		 var hidden_num = "";
	   		 
			var code = e.keyCode;
			
			$(this).val(key);
			
			chk_num = key;
			pwd_arr.push(key_num);	
			
			var replace = "";
			post_pwd = pwd_arr;
			for(i = 0; i < post_pwd.length-1; i++){
				star_num += "*";
				replace = pwd.val().replace(pwd.val().substr(0, post_pwd.length), star_num);
			}
			if((code >= 48 && code <= 57) || (code >= 97 && code <= 122) || (code >= 65 && code <= 90)){
				//영문자 or 숫자만 받음
				$(this).val(replace + key_num);
				hidden_num += pwd_arr.join("");
			}else{
				$(this).val(replace);
				pwd_arr.pop();
				hidden_num += pwd_arr.join("");
				star_num = "";
				for(i = 0; i < pwd_arr.length-1; i++){
					star_num += "*";
				}
				$(this).val(star_num);
				if(code == 8){
					//backspace 입력시					
					pwd_arr.pop();
					$(this).val().replace($(this).val().substr(pwd_arr.length-1), '');
					hidden_num = pwd_arr.join("");
				}else{//기타 다른 키
					$(this).val(star_num + "*");
				}
			}
			
		   	 
		   	 $("#pwd_hidden").val(hidden_num);
	     });
	     
	     $("#pwdchk").on("keyup", function(e) {
	    	 var pwd = $("#pwdchk_hidden");
	    	 var key = pwd.val().replace(/^[a-zA-Z-0-9]$/g,'');
	    	 var num = "";
	    	 var ch_num = "";
	    	 var star_num = "";
	   		 var key_num = e.key;
	   		 var hidden_num = "";
	   		 
			var code = e.keyCode;
			
			$(this).val(key);
			
			chk_num = key;
			pwdchk_arr.push(key_num);	
			
			var replace = "";
			post_pwd = pwdchk_arr;
			for(i = 0; i < post_pwd.length-1; i++){
				star_num += "*";
				replace = pwd.val().replace(pwd.val().substr(0, post_pwd.length), star_num);
			}
			if((code >= 48 && code <= 57) || (code >= 97 && code <= 122) || (code >= 65 && code <= 90)){
				//영문자 or 숫자만 받음
				$(this).val(replace + key_num);
				hidden_num += pwdchk_arr.join("");
			}else{
				$(this).val(replace);
				pwdchk_arr.pop();
				hidden_num += pwdchk_arr.join("");
				star_num = "";
				for(i = 0; i < pwdchk_arr.length-1; i++){
					star_num += "*";
				}
				$(this).val(star_num);
				if(code == 8){
					//backspace 입력시					
					pwdchk_arr.pop();
					$(this).val().replace($(this).val().substr(pwdchk_arr.length-1), '');
					hidden_num = pwdchk_arr.join("");
				}else{//기타 다른 키
					$(this).val(star_num + "*");
				}
			}
		   	 
		   	 $("#pwdchk_hidden").val(hidden_num);
	     });
	     
		var btCmpReg = $('#bt_cmp_reg');
		var cmpReg = $('#cmp_reg');
		
		btCmpReg.click(function(){
			cmpReg.click();
		});
		
		var btCarrierReg = $('#bt_carrier_reg');
		var carrierReg = $('#carrier_reg');
		
		btCarrierReg.click(function(){
			carrierReg.click();
		});
	    
		var btImgUpload = $('#bt_img_upload');
		var imgUpload = $('#img_upload');
		
		btImgUpload.click(function(){
			imgUpload.click();
		});
		
		
		
		$('#bt_reg').click(function(){
			var imgUpload = $('img_upload').val();
			var sabun = $('#sabun').val();
			var korName = $('#kor_name').val();
			var engName = $('#eng_name').val();
			var userId = $('#userid').val();
			var pwd = $('#pwd_hidden').val();
			var pwdchk = $('#pwdchk_hidden').val();
			var tel = $('#tel').val();
			var hp = $('#hp').val();
			var regNo = $('#reg_no').val();
			var regNoHidden = $('#reg_no_hidden').val();
			var age = $('#age').val();
			var email1 = $('#email1').val();
			var email2 = $('#email2').val();
			var email = $('#email');
			var strEmail = $('#str_email').val();
			var jobType = $('#job_type').val();
			var sex = $('#sex').val();
			var zip = $('#zip').val();
			var addr1 = $('#addr1').val();
			var addr2 = $('#addr2').val();
			var posGbn = $('#pos_gbn').val();
			var dept = $('#dept').val();
			var salary = $('#salary').val();
			var joinGbn = $('#join_gbn').val();
			var gartLevel = $('#gart_level').val();
			var putYn = $('#put_yn').val();
			var milYn = $('#mil_yn').val();
			var milType = $('#mil_type').val();
			var milLevel = $('#mil_level').val();
			var milStartDate = $('#mil_start_date').val();
			var milEndDate = $('#mil_end_date').val();
			var kosaReg = $('#kosa_reg').val();
			var kosaClass = $('#kosa_class').val();
			var joinDate = $('#join_date').val();
			var endDate = $('#end_date').val();
			var cmpNo = $('#cmp_no').val();
			var cmpName = $('#cmp_name').val();
			var cmpImg = $('#cmp_img').val();
			var cmpReg = $('#cmp_reg').val();
			var selfIntro = $('#self_intro').val();
			var carrier = $('#carrier').val();
			var carrierReg = $('#carrier_reg').val();
			
			if(korName == null || userId == null || pwd == null || pwdchk == null || hp == null || email1 == null || joinDate == null
		 	  || korName == "" || userId == "" || pwd == "" || pwdchk == "" || hp == "" || email1 == "" || joinDate == ""){
				alert('필수 입력사항을 확인해주세요.');
				return;
			}else if(pwd != pwdchk){
				alert('암호가 일치하지 않습니다.');
				return;
			}else{
// 				console.log("email2 : " + email2 + ", strEmail : " + strEmail);
				if(emailCheck(email1 + (email2 == '1' ? strEmail : email2)) == false){
					return;
// 				}else if(isPhone(tel) == false){
// 					return;
// 				}else if(isMobile(hp) == false){
// 					return;
// 				}else if(isRegNo(regNo) == false){
// 					return;
// 				}else if(isCmpNo(cmpNo) == false){
// 					return;
				}else{
// 					console.log('hp : ' + formatMobile(hp));
// 					console.log('주민 : ' + formatRegNo(regNo));
					tel = formatPhone(tel);
					hp = formatMobile(hp);
					regNo = formatRegNo(regNo);
					cmpNo = formatCmpNo(cmpNo);
					email.val(email1 + (email2 == '1' ? strEmail : email2));
					$.post("/proj/dup_chk.do", {
						"userId":userId
					}, function(response){
						if(response == 0){
							$('#insa_submit').submit();
							alert('입력 완료 되었습니다.');							
						}else{
							alert('이미 사용중인 아이디입니다.');
							return;
						}
					});
				}
			}
			
		});
		
		$('#mil_yn').change(function(){
			var milYn = $('#mil_yn').val();
			if(milYn == 'N'){
				$('.mil_stat').attr("hidden", true);
			}else{
				$('.mil_stat').attr("hidden", false);
			}
		})
		
		$('#bt_img_upload').click(function(){
			
		});
	});
	
	$('#myModal').on('shown.bs.modal', function () {
		  $('#myInput').focus();
	});	
	
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zip').value = data.zonecode;
                document.getElementById("addr1").value = roadAddr;
//                 document.getElementById("addr1").value = data.jibunAddress;

                var guideTextBox = document.getElementById("guide");
        		autoClose: false;
            }       
        }).open();
    }
</script>
</head>
<body>
	<header>
		<jsp:include page="../import/header.jsp"/>	
	</header>
	<article>
		<h1 style="">직원 상세 정보</h1>
		<div class="bt_area">
			<input id="bt_reg" type="button" value="등록"/>
			<input type="button" value="전화면" onclick="window.location.href='insa_form.do'"/>
		</div>
		<br><br>
		<div>
<!-- 			<table class="employee_table1" border="1"> -->
			<form id="insa_submit" name="insa_submit" action="/proj/insa_insert.do" method="post" enctype="multipart/form-data">
				<table class="employee_table1">
					<tr>
						<td colspan="2" rowspan="6">
<%-- 							<c:if test="${insa.profile}"> --%>
								<div id="view_area" style="width:270px; height:300px; ">
									<img id="profile_img" style="width:100%;height:100%;" alt="${pageContext.request.contextPath}/images/defaultPrf.png" 
									src="${pageContext.request.contextPath}/images/defaultPrf.png"/>
								</div><br>
<%-- 							</c:if> --%>
							<input type="button" value="사진올리기" id="bt_img_upload" name="bt_img_upload"/>
							<input type="file" onchange="previewImage(this, 'view_area');" style="display:none;" id="img_upload" name="img_upload"/>
						</td>
						<td class="col_title"><span>*사번</span></td>
						<td class="input_title"><input type="text" id="sabun" name="sabun" disabled="disabled"/></td>
						<td class="col_title"><span>*한글성명</span></td>
						<td class="input_title"><input type="text" id="kor_name" name="kor_name" onblur="isKorName();" /></td>
						<td class="col_title"><span>영문성명</span></td>
						<td class="input_title" colspan="3"><input type="text" id="eng_name" name="eng_name" onblur="isEngName();" /></td>
					</tr>
					<tr>
						<td class="col_title"><span>*아이디</span></td>
						<td class="input_title"><input type="text" id="userid" name="userid" onblur="isId();" /></td>
						<td class="col_title"><span>*패스워드</span></td>
						<td class="input_title">
							<input type="text" id="pwd" name="pwd" />
							<input type="hidden" id="pwd_hidden" name="pwd_hidden" style="display:none;"/>
						</td>
						<td class="col_title"><span>*패스워드</span></td>
						<td class="input_title" colspan="3">
							<input type="text" id="pwdchk" name="pwdchk"/>
							<input type="hidden" id="pwdchk_hidden" name="pwdchk_hidden" style="display:none;"/>
						</td>
					</tr>
					<tr>
						<td class="col_title"><span>전화번호</span></td>
						<td class="input_title"><input type="text" id="tel" name="tel" onblur="telBlur();"/></td>
						<td class="col_title"><span>*핸드폰번호</span></td>
						<td class="input_title"><input type="text" id="hp" name="hp" onblur="hpBlur();"/></td>
						<td class="col_title"><span>주민번호</span></td>
						<td class="input_title" colspan="3">
							<input type="text" id="reg_no" name="reg_no" onblur="regNoBlur();"/>
							<input type="hidden" id="reg_no_hidden" name="reg_no_hidden" style="display:none;"/>
						</td>
					</tr>
					<tr>
						<td class="col_title"><span>연령</span></td>
						<td class="input_title"><input type="text" id="age" name="age"/></td>
						<td class="col_title"><span>*이메일</span></td>
						<td class="input_title">
							<input style="width:43%; float:left;" type="text" id="email1" name="email1"/>
							<input style="width:55%; float:right; display:none;" id="str_email" name="str_email"/>
							<select style="width:53%; float:right;" id="email2" name="email2">
								<option >(선택)</option>
								<c:forEach items="${emList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
								<option value="1">직접입력</option>
							</select>
							<input type="hidden" id="email" name="email"/>
<!-- 							<input type="text" id="email" name="email"/> -->
						</td>
						<td class="col_title"><span>직종체크</span></td>
						<td class="input_title">
							<select id="job_type" name="job_type">
								<option >(선택)</option>
								<c:forEach items="${jList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">
							<span>성별</span>
						</td>
						<td class="input_title">
							<select id="sex" name="sex">
								<option >(선택)</option>
								<c:forEach items="${sList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="col_title"><span>주소</span></td>
						<td class="input_title"><input type="text" placeholder="우편번호" id="zip" name="zip" onblur="isZip();"/></td>
						<td class="input_title"><input type="button" value="주소검색" id="bt_addr" name="bt_addr" onclick="sample4_execDaumPostcode()"/></td>
						<td class="input_title"><input type="text" placeholder="주소" id="addr1" name="addr1"/></td>
						<td class="input_title" colspan="4"><input type="text" placeholder="세부주소" id="addr2" name="addr2"/></td>
					</tr>
					<tr>
						<td class="col_title"><span>직위</span></td>
						<td class="input_title">
							<select id="pos_gbn" name="pos_gbn">
								<option >(선택)</option>
								<c:forEach items="${psList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title"><span>부서</span></td>
						<td class="input_title">
							<select id="dept" name="dept">
								<option >(선택)</option>
								<c:forEach items="${dtList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title"><span>연봉(만원)</span></td>
						<td class="input_title" colspan="3">
							<input type="text" style="text-align:right;" placeholder="(만원) " id="salary" name="salary" onkeyup="inputNumberFormat(this)"/>
						</td>
					</tr>
					<tr>
						<td class="col_title">입사구분</td>
						<td class="input_title">
							<select id="join_gbn" name="join_gbn">
								<option >(선택)</option>
								<c:forEach items="${jnList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">등급</td>
						<td class="input_title">
							<select id="gart_level" name="gart_level">
								<option >(선택)</option>
								<c:forEach items="${gtList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">투입여부</td>
						<td class="input_title">
							<select id="put_yn" name="put_yn">
								<option >(선택)</option>
								<c:forEach items="${ptList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">군필여부</td>
						<td class="input_title" colspan="3">
							<select id="mil_yn" name="mil_yn">
								<option >(선택)</option>
								<c:forEach items="${mList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr class="mil_stat">
						<td class="col_title">군별</td>
						<td class="input_title">
							<select id="mil_type" name="mil_type">
								<option >(선택)</option>
								<c:forEach items="${mtList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">계급</td>
						<td class="input_title">
							<select id="mil_level" name="mil_level">
								<option >(선택)</option>
								<c:forEach items="${mlList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">입영일자</td>
						<td class="input_title" colspan="1">
							<input type="date" id="mil_start_date" name="mil_start_date"/>
						</td>
						<td class="col_title">전역일자</td>
						<td class="input_title" colspan="3">
							<input type="date" id="mil_end_date" name="mil_end_date"/>
						</td>
					</tr>
					<tr>
						<td class="col_title">KOSA등록</td>
						<td class="input_title">
							<select id="kosa_reg" name="kosa_reg">
								<option >(선택)</option>
								<c:forEach items="${krList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">KOSA등급</td>
						<td class="input_title">
							<select id="kosa_class" name="kosa_class">
								<option >(선택)</option>
								<c:forEach items="${kcList}" var="em">
									<option value="${em.name}">${em.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="col_title">*입사일자</td>
						<td class="input_title">
							<input type="date" id="join_date" name="join_date" />
						</td>
						<td class="col_title">퇴사일자</td>
						<td class="input_title" colspan="3">
							<input type="date" id="end_date" name="end_date"/>
						</td>
					</tr>
					<tr>
						<td class="col_title">사업자번호</td>
						<td class="input_title">
							<input type="text" id="cmp_no" name="cmp_no" onblur="cmpNoBlur();"/>
						</td>
						<td class="col_title">업체명</td>
						<td class="input_title">
							<input type="text" id="cmp_name" name="cmp_name"/>
						</td>
						<td class="col_title">사업자등록증</td>
						<td class="input_title">
							<input type="text"  style="background-color:lightgray;" readonly="readonly"  id="cmp_img" name="cmp_img"/>
						</td>
						<td colspan="4" class="bt_area2">
							<input type="button" value="미리보기" id="bt_cmp_preview" name="bt_cmp_preview" 
							class="btn default btn-lg" data-toggle="modal" data-target="#myModal"/>
							<input type="button" value="등록" id="bt_cmp_reg" name="bt_cmp_reg" class="btn default btn-lg"/>
							<input type="file" style="display:none;" onchange="$('#cmp_img').val(this.value);" id="cmp_reg" name="cmp_reg"/>
						</td>
					</tr>
					<tr>
						<td class="col_title">자기소개</td>					
						<td class="input_title" colspan="3">
							<textarea rows="2" cols="80" placeholder="100자 이내로 적으시오" style="resize: none;" maxlength="100"
							 id="self_intro" name="self_intro"></textarea>
						</td>
						<td class="col_title"><span>이력서</span></td>
						<td class="input_title">
							<input type="text"  style="background-color:lightgray;" readonly="readonly"  id="carrier" name="carrier"/>
						</td>
						<td colspan="4" class="bt_area2">
							<input type="button" value="미리보기" id="bt_carrier_preview" name="bt_carrier_preview"
							class="btn default btn-lg" data-toggle="modal" data-target="#myModal"/>
							<input type="button" value="파일 업로드" id="bt_carrier_reg" name="bt_carrier_reg" class="btn default btn-lg"/>
							<input type="file" style="display: none;" onchange="$('#carrier').val(this.value)" id="carrier_reg" name="carrier_reg"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">미리보기</h4>
		      </div>
		      <div class="modal-body">
		        <p><img alt="이미지가 없습니다." ></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!-- 		        <button type="button" class="btn btn-primary">Save changes</button> -->
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</article>
	<footer>
	
	</footer>
</body>
</html>