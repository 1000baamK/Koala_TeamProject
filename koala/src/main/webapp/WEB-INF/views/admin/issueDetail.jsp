<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[koala] issueDetail</title>
</head>

<!-- 코드 미러 CDN -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/6.65.7/codemirror.min.js"
	integrity="sha512-8RnEqURPUc5aqFEN04aQEiPlSAdE0jlFS/9iGgUyNtwFnSKCXhmB6ZTNl7LnDtDWKabJIASzXrzD0K+LYexU9g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/6.65.7/codemirror.min.css"
	integrity="sha512-uf06llspW44/LZpHzHT6qBOIVODjWtv4MxCricRxkzvopAlSWnTf6hpZTFxuuZcuNE9CBQhqE0Seu1CoRk84nQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/6.65.7/theme/blackboard.min.css"
	integrity="sha512-KnHAkH0/78Cyjs1tjV9/+00HK8gu1uKRCCKcWFxX0+rehRh9SYJqiG/2fY4St7H8rPItOsBkgQjN0m4rL5Wobw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/koala/resources/codemirror-5.53.2/mode/xml/xml.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/6.65.7/theme/ayu-dark.min.css"
	integrity="sha512-mV3RUXi1gt22jDb4UyRBFhZVFgAIiOfRE6ul+2l1Hcj6glyg6x4xlnjPH+neGm/t6XrFmsMRu4++McQu0asjqg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
div {
	box-sizing: border-box;
}

.ebEnrollForm {
	width: 1200px;
	margin: auto;
	padding-top: 50px;
}

.ebEnrollForm>* {
	width: 85%;
	margin: auto;
	padding-bottom: 50px;
}

.option-area1 {
	padding-bottom: 20px;
	padding-left: 290px;
}

* /
     div[class*='_b'] {
	width: 100%;
	background-color: black;
}

.enrollForm-area span, .modifyBtn-area span, .modifyForm-top span {
	margin: 0px 10px;
	color: rgb(147, 208, 248);
	font-size: 18px;
	font-weight: 700;
}

#eb_title, #eb_content {
	background-color: black;
	border: none;
	outline: none;
	color: white;
	font-size: 15px;
}
/* ===================================================== 영역잡기 */
div {
	box-sizing: border-box;
}

.createSettingForm {
	width: 1200px;
	margin: auto;
	padding-top: 200px;
	font-size: 17px;
}

.createSettingForm>form>div {
	width: 100%;
	margin-bottom: 50px;
}

.libraryName-author-area {
	height: 70px;
	position: relative;
}

.sortDescroption-area {
	height: 70px;
}

.menual-code-area, .option-area {
	overflow: auto;
}

.button-area {
	height: 100px;
}

/* ============================= */
.libraryName-author-area>div {
	height: 100%;
	float: left;
	position: absolute;
}

.libraryName-area {
	width: 50%;
	left: 0;
}

.author-area {
	width: 30%;
	right: 0;
}

.libraryName-author-area input, .sortDescroption-area>input {
	width: 70%;
	height: 50%;
	background-color: rgb(30, 30, 30);
	color: rgb(255, 217, 217);
	border-bottom: 2px solid white;
	font-size: 18px;
}
/* ============================= */
.menual-code-area>div {
	float: left;
}

.menual-area {
	width: 48%;
	margin-right: 2%;
}

.code-area {
	width: 48%;
	margin-left: 2%;
}

.menual-area>div {
	width: 100%;
	padding: 20px;
	background-color: white;
	border: 1px solid gray;
}

.code-area>div {
	width: 100%;
	height: 650px;
	padding: 20px;
	background-color: black;
}

.menual-code-area textarea {
	width: 100%;
	height: 600px;
	resize: none;
	outline: none;
	border: none;
}

.code-box>textarea {
	background-color: black;
	color: white;
}
/* ============================= */
.option-box {
	width: 100%;
	padding: 20px;
	border: 3px solid rgb(131, 131, 131);
	overflow: auto;
}

.option-box>div {
	width: 50%;
	float: left;
	padding: 0 30px;
}

.option-box>div>div {
	width: 100%;
}

.ajax-input>div, .ajax-select>div {
	width: 100%;
	margin-bottom: 20px;
}

.ajax-input>div>input, .ajax-select>div>input, .select-child-area>div>div>input
	{
	width: 250px;
	height: 27px;
	border: 1px solid gray;
}

.select-child-area {
	width: 60%;
	margin-left: 150px;
	padding: 10px 0;
}

.select-child-area>div>div {
	margin-bottom: 10px;
}

.option-area p {
	color: rgb(255, 212, 92);
}

.option-area i:hover {
	cursor: pointer;
}
/* ===================================================== 버튼 */
.button-area>div {
	width: 40%;
	height: 60%;
	margin: auto;
	position: relative;
}

.button-area>div>button {
	width: 45%;
	height: 100%;
	margin-top: 50px;
	position: absolute;
}

#cancelBtn {
	left: 0;
}

#cancelBtn:hover {
	background-color: rgb(180, 180, 180);
	border: 1px solid gray;
}

#submitBtn {
	right: 0;
	background-color: rgb(224, 130, 130);
}

#submitBtn:hover {
	background-color: rgb(224, 100, 100);
	border: 1px solid gray;
}
/* ===================================================== 공통 */
input {
	border: none;
}

input:focus {
	outline: none;
}

span {
	font-size: 21px;
	font-weight: 600;
	color: rgb(238, 238, 238);
}

p {
	font-size: 18px;
	font-weight: 600;
	margin-bottom: 20px;
}

.CodeMirror {
	font-family: Arial, monospace;
	fonc
}
</style>


<body>
	<%@include file="../common/header.jsp"%>
	<script>
		$(function(){
			//input 옵션 생성
			$("#addBtn_input").on("click", function(){
				var str = "<div><input class=\"input_n\" type=\"text\" name=\"\" id=\"\" required><i class=\"fa-solid fa-circle-minus minus_input\" style=\"color: #a0a0a0; margin-left: 10px;\"></i></i></div>";
				$(".ajax-input").append(str);
			});
			//input 옵션 삭제
			$(".input-box").on("click", "i[class*='minus_input']", function(){
				$(this).parent().remove();	
			})
			//select 부모 옵션 생성
			$("#addBtn_select").on("click", function(){
				var str = "<div>";
				    str+= "<i class=\"fa-solid fa-paw\" style=\"color: #ffce47;\"></i>";
				    str+= "&nbsp;<input class=\"select_n\" type=\"text\" name=\"\" id=\"\" required>";
				    str+= "<i class=\"fa-solid fa-circle-minus minus_select\" style=\"color: #a0a0a0; margin-left: 10px;\"></i></i>";
				    str+= "<div class=\"select-child-area\">";
				    str+= "<span class=\"hide\" style=\"font-size: 15px; color: #8aa2be;\">[옵션 항목]&nbsp;<i class=\"fa-solid fa-caret-up fa-lg\"></i></span><br>";
				    str+= "<div class=\"ajax-select-child\">";
				    str+= "<div>";
				    str+= "<input class=\"option_n\" type=\"text\" name=\"\" id=\"\" required>";
				    str+= "<i class=\"fa-solid fa-circle-minus minus_select_child\" style=\"color: #a0a0a0; margin-left: 10px;\"></i></i></div></div>";
				    str+= "<div class=\"addBtn\">";
				    str+= "<i class=\"fa-solid fa-circle-plus addBtn_select_child\" style=\"color: #8aa2be; margin-left: 115px;\"></i></div></div></div>";
				$(".ajax-select").append(str);
			});
			//select 부모 옵션 삭제
			$(".select-box").on("click", "i[class$='minus_select']", function(){
				$(this).parent().remove();	
			})
			//select 자식 옵션 생성
			$(".select-box").on("click", "i[class*='addBtn_select_child']", function(){
				var str = "<div><input class=\"option_n\" type=\"text\" name=\"\" id=\"\" required><i class=\"fa-solid fa-circle-minus minus_select_child\" style=\"color: #a0a0a0; margin-left: 10px;\"></i></i></div>"
				$(this).parent().prev().append(str);
			});
			//select 자식 옵션 삭제
			$(".select-box").on("click", "i[class*='minus_select_child']", function(){
				
				if($(this).parent().siblings().length != 0){
					$(this).parent().remove();	
				}else{ //옵션이 하나도 없을 시
					alert("옵션항목은 최소 한개 이상 존재해야 합니다.");
				}
			});
			//select 자식 옵션 접기/풀기
			$(".select-box").on("click", "span[class*='hide']", function(){
				if(($(this).html()).includes('up')){
					$(this).siblings('div').hide();
					$(this).html("[옵션 항목]&nbsp;<i class=\"fa-solid fa-caret-down fa-lg\"></i>");
				}else{
					$(this).siblings('div').show();
					$(this).html("[옵션 항목]&nbsp;<i class=\"fa-solid fa-caret-up fa-lg\"></i>");
				}
			});
			
		});
	</script>

	<div class="createSettingForm">

		<div class="enrollForm-area">
			<div class="option-area1">
				<span>category</span> <span id="settingTitle">${issueDetail.setting.settingTitle }</span>
				<span>version</span> <span id="settingVersion">${issueDetail.setting.settingVersion }</span>
				<div
					style="text-align: right; display: inline; padding-left: 310px; padding-top: 50px">
					<button id="nextError" type="button" class="btn btn-info">NEXT</button>
				</div>
			</div>

			<div id="boardNo" style="display: none;">${issueDetail.board.boardNo }</div>
			<div id="boardWriter" style="display: none;">${issueDetail.board.boardWriter }</div>
			<div id="settingNo" style="display: none;">${issueDetail.setting.settingNo }</div>
			<%-- 				<div id="settingInfo" style="display:none;">${issueDetail.setting.settingInfo}</div> --%>
			<%-- 				<div id="settingCode" style="display:none;">${issueDetail.setting.settingCode }</div> --%>
			<div id="sortDescription" style="display: none;">${issueDetail.setting.sortDescription }</div>


			<div class="title_b" style="height: 30px;">
				<span>title</span> <input class="ii ic" type="text" name="title"
					id="eb_title" style="width: 1100px" placeholder="제목을 작성하세요"
					value="${issueDetail.board.title }" readonly>
			</div>
			<div style="height: 5px;"></div>
			<!-- 간격용 -->
			<div class="content_1_b">
				<span>content</span>
			</div>
			<div class="content_2_b" style="height: 150px;">
				<textarea class="ii ic" name="content" id="eb_content"
					placeholder="내용을 작성하세요" readonly
					style="width: 100%; height: 100%; padding: 5px 55px; resize: none">${issueDetail.board.content }</textarea>
			</div>
			<span>남은 이슈 개수 [ ${size } ]</span> <br>
			<br>
		</div>

		<!-------------------------------------------------------------------------------------------------------------------------------  -->

		<!--설명서/코드 영역-->
		<div class="menual-code-area">
			<div class="menual-area">
				<p>
					<span class="ii"
						style="background: linear-gradient(to top, rgba(253, 172, 172, 0.7) 50%, transparent 40%);">&nbsp;<i
						class="fa-solid fa-star-of-life fa-xs"></i> Menual&nbsp;
					</span>
				</p>
				<div class="menual-box">
					<textarea name="settingInfo" id="settingInfo" spellcheck="false"
						required>${prevSetting.settingInfo }</textarea>
				</div>
			</div>
			<div class="code-area">
				<p>
					<span class="ii"
						style="background: linear-gradient(to top, rgba(135, 139, 186, 0.7) 50%, transparent 40%);">&nbsp;<i
						class="fa-solid fa-star-of-life fa-xs"></i> Code&nbsp;
					</span>
				</p>
				<div class="code-box">
					<textarea id="settingCode" name="settingCode" spellcheck="false">${prevSetting.settingCode }</textarea>
				</div>
			</div>
		</div>

		<!--옵션선택 영역-->
		<input type="hidden" name="input" value="">
		<div class="option-area">
			<p>
				<span class="ii"
					style="background: linear-gradient(to top, rgba(234, 255, 118, 0.418) 50%, transparent 40%);">&nbsp;<i
					class="fa-solid fa-star-of-life fa-xs"></i> Select-box&nbsp;
				</span>
			</p>
			<div class="option-box">
				<div class="input-box">
					<p>[입력사항]</p>
					<div class="ajax-input">
						<!--반복될 영역-->
					</div>
					<div class="addBtn">
						<i id="addBtn_input" class="fa-solid fa-circle-plus fa-lg"
							style="color: #ffce47; margin-left: 120px;"></i>
					</div>
				</div>
				<div class="select-box">
					<p>[선택사항]</p>
					<div class="ajax-select">
						<!--반복될 영역-->
					</div>
					<div class="addBtn">
						<i id="addBtn_select" class="fa-solid fa-circle-plus fa-lg"
							style="color: #ffce47; margin-left: 140px;"></i>
					</div>
				</div>
			</div>
		</div>

		<!--버튼 영역-->
		<div class="button-area">
			<div>
				<button id="cancelBtn" type="button">cancel</button>
				<button id="submitBtn" type="button">submit</button>
			</div>
		</div>
		<br>
		<br>
		<br>
		<script>
        	$(function(){
        		var i = 1;
        		
        		
        		// input value 뿌리기
        		var fullInput = "${prevSetting.input}";
        		if(fullInput != ""){ // 여기 오류남
	//         		input
	        		var inputele = fullInput.split("|")[1].split("/");
	        		for(var i = 0; i < inputele.length; i++){
		        		$("#addBtn_input").trigger("click");
	        			$(".ajax-input").children().eq(i).children().eq(0).val(inputele[i]);
	        		}
	        		// select
	        		var k = 0;
	        		var selectTest = [];
	        		var selectele = fullInput.split("|")[0].split("/")
	        		for(var i = 0; i < selectele.length; i++){
	        			$("#addBtn_select").trigger("click");
	        			var selectele2 = selectele[i].split(/-|,|Z/);
	        			$(".select_n").eq(i).val(selectele2[0]);
						selectele2.shift();
						selectTest = selectTest.concat(selectele2);
						selectele2.unshift ("");
	        			for(var j = 0; j < selectele2.length - 2; j++){
		        			$(".ajax-select .addBtn").children().eq(i).trigger("click");
	        			}
	        			for(var k = 0; k < selectTest.length; k++){
	        				$(".ajax-select-child .option_n").eq(k).val(selectTest[k]);
	        			}
	        		}
        		}
        		
        		
        		
        		$("#submitBtn").on("click", function checkSubmit(){ 
        		
    	        	/*
    		        	| : 선택사항과 입력사항을 구분
    		        	/ : 큰 단위의 옵션 구분 
    		        	- : 선택사항 안 옵션사항 시작 기호
    		        	, : 선택사항 안 옵션사항 구분 
    	        	*/
    	        	var str = ""; // 최종본
    	  		  
    	  		  	//선택사항
    	  		  	$('.select_n').each(function(index, item){ //선택사항 반복
    	  			  
    	  				str += $(this).val()+'-';
    	   				  
    	  				var length = $(this).siblings().eq(2).find('.option_n').length;
    	   				$($(this).siblings().eq(2).find('.option_n')).each(function(index, item){ //옵션사항 반복
    	   				  
    	       				if(index < (length-1) ){
    	        				str += $(this).val()+',';
    	       				}else{
    		   					str += $(this).val();
    	       				}
    	       			})
    	          		  
    	  				if(index < ($(".select_n").length-1) ){
    	          			str += '/'
    	  				}
    	  		    })
    	  		  
    	  		    str += '|'; //구분 기호
    	  		  
    	  		    //입력사항
    	  		    $('.input_n').each(function(index, item){
    	  				if(index < ($(".input_n").length-1) ){
    	      				str += $(this).val()+'/';
    	  				}else{
    	  			 		str += $(this).val();
    	  				}
    	  		    })
    	  		    $("input[name='input']").val(str);
    				
    	  		    alert(str);
        			$.ajax({
        				url : "issuesSuccess",
        				data : {
        					boardNo : $("#boardNo").text(),
        					settingNo : $("#settingNo").text(),
        					settingTitle : $("#settingTitle").text(),
        					settingInfo : $("#settingInfo").val(),
        					settingCode : $("#settingCode").val(),
        					settingVersion : $("#settingVersion").text(),
        					boardWriter : $("#boardWriter").text(),
        					sortDescription : $("#sortDescription").text(),
        					input : str
        				},
        				type:"POST",
        				success : function(data){
        					if(data > 0){						
        						alert("setting update success");
        						location.reload()
        					}
        				},
        				error : function(){
        					console.log("error");
        				}
        			});
    	  	    });
        		
//         		$("#submitBtn").on("click", function(){
//         		});		

        		$("#nextError").on("click", function(){
        			$.ajax({
        				url : "issuesDetail",
        				data : {
        					settingTitle : "${issueDetail.setting.settingTitle }",
        					page : i
        				},
        				success : function(data){
        					if("${page}" >= "${size}"){
        						alert("마지막 페이지 입니다. 처음으로 돌아갑니다.");
        						location.href="/koala/admin/issuesDetail?settingTitle=${issueDetail.setting.settingTitle }&page=1";
        					}else{						
        						i = Number("${page}") + 1;
        						location.href="/koala/admin/issuesDetail?settingTitle=${issueDetail.setting.settingTitle }&page="+i;
        					}
        				},
        				error : function(){
        					console.log("ajax Error");					
        				}
        			});
        		});
        		
        		
        	});
	        
        </script>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>