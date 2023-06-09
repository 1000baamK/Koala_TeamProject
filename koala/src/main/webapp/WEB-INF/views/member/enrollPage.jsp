<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>

	*{
		box-sizing: border-box;
	}
	#content{
		margin: auto;
		width: 80%;
		height: 1400px;
		padding-top: 200px
	}
	
	#insert-form{
		width: 100%;
		height: 100%;
	}
	
	#content_1{
		width: 100%;
		height: 100%;
		margin: 0 auto;
		border-radius: 5px;
		margin-top: 15px;
		margin-bottom: 15px;
	}
	
	#enroll-area{
		height: 100%;
		width: 100%;
	}
	
	
	.box{
		float: left;
		width: 60%;
		height: 40px;
		border: 1px solid #dadada;
		border-radius: 5px;
		padding: 10px;
	}
	
	textarea{
		width: 100%;
		border: 1px solid #dadada;
		border-radius: 5px;
	}
	
	#center{
		width: 80%;
		height: 1050px;
		padding: 5% 0;
		background-color: rgb(45, 45, 45);
	}
	
	#center>table{
		height: 80%;
	}
	
	#center>#check-area{
		height: 8%;
	}
	
	/* 테이블 */
	#insert-table{
		width: 80%;
		height: 100%;
	}
	
	
	
	
	button{
		height: 40px;
		width: 80px;
		padding: 10px;
		border-radius: 5px;
	}
	
	
	.hide{
		color: yellow;
		display: none;
	}
	
	#level {
    	appearance: none;
    	border-radius: 10px;
	}
	
	#level::-webkit-progress-bar {
	    background: #f0f0f0;
	    border-radius: 10px;
	}
	#level::-webkit-progress-value {
	    border-radius: 10px;
	    /* 의사 클래스에 js로 접근하기 위해서는 변수화 하여 접근하도록 해야함 */
	    background-color: var(--color, red)
	}
	#level[value]::-webkit-progress-value {
	    transition: width 0.5s;
	}
	
	
	
	@keyframes slideIn {
	  0% {
	    opacity: 0;
	    transform: translateX(-100%);
	  }
	  100% {
	    opacity: 1;
	    transform: translateX(0);
	  }
	}
	
	.on{
		animation: slideIn 0.5s ease-in-out forwards;
	}

</style>
</head>
<body>
	<script>
		var code = "";
		
		//유효성(정규표현식)
		var idExp = /^[a-zA-Z0-9]{4,12}$/; //4~12자리 영어숫자만
		var pwdExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/; //영문,숫자 6자리 이상
		var nickExp = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,12}$/; // 2자 이상, 12자 이하, 영어또는 숫자 한글로 구성
		var emailExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //이메일
		
		var idCheck = false;
		var pwdCheck = false;
		var nickCheck = false;
		var emailCheck = false;
		var agreeCheck = false;
		
		$(function(){
			//submit버튼 계속 상태값 바꿔주기
			$.submitState = function(idCheck, pwdCheck, nickCheck, agreeCheck){
				if(idCheck && pwdCheck && nickCheck && emailCheck && agreeCheck){
					$("#submitBtn").attr("disabled", false);
					console.log("-------통과-------");
					console.log("id : "+idCheck);
					console.log("pwd : "+pwdCheck);
					console.log("nick : "+nickCheck);
					console.log("email : "+emailCheck);
					console.log("agree : "+agreeCheck);
				}else{
					$("#submitBtn").attr("disabled", true);
					console.log("-------미통과-------");
					console.log("id : "+idCheck);
					console.log("pwd : "+pwdCheck);
					console.log("nick : "+nickCheck);
					console.log("email : "+emailCheck);
					console.log("agree : "+agreeCheck);
				}
			}
				
			//유저 아이디 입력
			$("#userId").on("focusout", function(){
				
				if(idExp.test($("#userId").val())){ //유효성을 통과하면
					//아이디 중복체크
					$.ajax({
						url:"/koala/member/idCheck",
						data:{
							inputId:$("#userId").val()
						},
						success:function(result){
							if(result>0){
								//중복(사용불가능)
								$("#formId").css("display", "none");
								$("#useId").css("display", "block");
								idCheck = false;
								console.log("중복이야ㅣ");
							}else{
								//사용가능
								$("#formId").css("display", "none");
								$("#useId").css("display", "none");
								idCheck = true;
								console.log("사용할수있어!");
							}
						},
						error:function(){
							alert("통신오류");
						},
						complete:function(){
							$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
							if(idCheck == true){
								$("#ci-icon").addClass("on fa-solid fa-check fa-xl");
							}else{
								$("#ci-icon").removeClass("on fa-solid fa-check fa-xl");
							}
						}
					});
				}else if($("#userId").val() == ""){
					//비어있다면 다 지우기
					$("#formId").css("display", "none");
					$("#useId").css("display", "none");
					
					idCheck = false;
					
					if(idCheck == true){
						$("#ci-icon").addClass("on fa-solid fa-check fa-xl");
					}else{
						$("#ci-icon").removeClass("on fa-solid fa-check fa-xl");
					}
				}else{
					//적합하지 않는 아이디 형식입니다. -출력해야함.
					$("#useId").css("display", "none");
					$("#formId").css("display", "block");
					idCheck = false;
					$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
					
					if(idCheck == true){
						$("#ci-icon").addClass("on fa-solid fa-check fa-xl");
					}else{
						$("#ci-icon").removeClass("on fa-solid fa-check fa-xl");
					}
				}
				
				
			});
			
			
			//입력한 비밀번호들에 대한 요소 초기화
			var userPwd1 ="";
			var userPwd2 ="";
			//비밀번호 입력
			$("#userPwd").on("focusout", function(){
				
				if(pwdExp.test($("#userPwd").val())){
					
					$("#formPwd").css("display", "none");
					userPwd1 = $("#userPwd").val();
					
					//pwdCheck true 얻어 놓고 비밀번호입력칸 재입력 방지
					if((userPwd2 != "") && (userPwd1 != userPwd2)){
						pwdCheck = false;
						$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
						$("#notEqualPwd").css("display", "block");
						$("#pwd-icon").removeClass("on fa-solid fa-check fa-xl");
						$("#pwd2-icon").removeClass("on fa-solid fa-check fa-xl");
					}
					
				}else if($("#userPwd").val() == ""){
					//비어있다면 다 지우기
					$("#formPwd").css("display", "none");
					
					pwdCheck = false;
				}else{
					//적합하지 않는 비밀번호 형식입니다. -출력해야함.
					$("#formPwd").css("display", "block");
					
					pwdCheck = false;
				}
				
				$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
				if(pwdCheck == true){
					$("#pwd-icon").addClass("on fa-solid fa-check fa-xl");
					$("#pwd2-icon").addClass("on fa-solid fa-check fa-xl");
				}else{
					$("#pwd-icon").removeClass("on fa-solid fa-check fa-xl");
					$("#pwd2-icon").removeClass("on fa-solid fa-check fa-xl");
				}
			});
			
			//비밀번호 재확인 입력
			$("#checkPwd").on("focusout", function(){
				
				userPwd2 = $("#checkPwd").val();
				
				if(pwdExp.test($("#userPwd").val())){
					if(userPwd1 == userPwd2){
						//같다면
						$("#notEqualPwd").css("display", "none");
						pwdCheck = true;
					}else{
						$("#notEqualPwd").css("display", "block");
						pwdCheck = false;
					}
					
				}else{
					//처음입력한 비밀번호 유효성이 통과되지않으면
					$("#notEqualPwd").css("display", "none");
					pwdCheck = false;
				}
				
				
				$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
				
				
				if(pwdCheck == true){
					$("#pwd-icon").addClass("on fa-solid fa-check fa-xl");
					$("#pwd2-icon").addClass("on fa-solid fa-check fa-xl");
				}else{
					$("#pwd-icon").removeClass("on fa-solid fa-check fa-xl");
					$("#pwd2-icon").removeClass("on fa-solid fa-check fa-xl");
				}
			});
			
			
			
			//닉네임 입력
			$("#nickName").on("focusout", function(){
				
				if(nickExp.test($("#nickName").val())){
					
					//닉네임 중복체크
					$.ajax({
						url:"/koala/member/nickCheck",
						data:{
							inputNick:$("#nickName").val()
						},
						success:function(result){
							if(result>0){
								//중복(사용불가능)
								$("#formNick").css("display","none");
								$("#useNick").css("display", "block");
								nickCheck = false;
								
							}else{
								//사용가능
								$("#formNick").css("display","none");
								$("#useNick").css("display", "none");
								nickCheck = true;
							}
						},
						error:function(){
							alert("통신오류");
						},
						complete:function(){
							$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
							if(nickCheck == true){
								$("#nick-icon").addClass("on fa-solid fa-check fa-xl");
							}else{
								$("#nick-icon").removeClass("on fa-solid fa-check fa-xl");
							}
						}
					});
					
				}else if($("#nickName").val() == ""){
					//비어있다면 다 지우기
					$("#formNick").css("display","none");
					$("#useNick").css("display", "none");
					
					nickCheck = false;
					
					if(nickCheck == true){
						$("#nick-icon").addClass("on fa-solid fa-check fa-xl");
					}else{
						$("#nick-icon").removeClass("on fa-solid fa-check fa-xl");
					}
				}else{
					//적합하지 않는 닉네임 형식입니다.
					$("#useNick").css("display", "none");
					$("#formNick").css("display","block");
					
					nickCheck = false;
					$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
					if(nickCheck == true){
						$("#nick-icon").addClass("on fa-solid fa-check fa-xl");
					}else{
						$("#nick-icon").removeClass("on fa-solid fa-check fa-xl");
					}
				}
				
				
				
			});
			
			//이메일에 인증코드 보내기
			$("#emailSend").on("click", function(){
				
				if(emailExp.test($("#email").val())){
					$.ajax({
						url:"/koala/member/emailCheck",
						method:"get",
						data:{
							inputEmail:$("#email").val()
						},
						success:function(data){
							console.log(data);
							
							code = data;
							alert("이메일에 인증번호를 보냈습니다.");
							
						},
						error:function(){
							alert("통신오류");
						}
					});
				}else{
					//적합하지않으므
					alert("올바른 이메일형식으로 다시 입력해주세요.");
					
					emailCheck = false;
					$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
				}
			});
				
			//이메일 인증번호 확인
			$("#certiCheck").on("click", function(){
				
				var certiCode = $("#certiCode").val();
				
				if(code === certiCode){
					//서버에서 보낸 코드와 사용자가 입력한 코드가 같음
					alert("굳");
					$("#email-success").css("display", "block");
					$("#checkCode-group").css("display", "none");
					$("#email").prop("readonly", true);
					$("#emailSend").prop("disabled", true);
					
					emailCheck = true;
				}else{
					//다름
					
					alert("입력한 인증번호가 일치하지 않습니다. 다시한번 확인해주세요.");
					emailCheck = false;;
				}
				
				if(emailCheck == true){
					$("#email-icon").addClass("on fa-solid fa-check fa-xl");
					$("#certi-icon").addClass("on fa-solid fa-check fa-xl");
				}else{
					$("#email-icon").removeClass("on fa-solid fa-check fa-xl");
					$("#certi-icon").removeClass("on fa-solid fa-check fa-xl");
				}
				
				$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
			});
			
			
			$("#agree").on("change", function(){
				
				if($("#agree").prop("checked")){
					agreeCheck = true;
				}else{
					agreeCheck = false;
				}
				
				$.submitState(idCheck, pwdCheck, nickCheck, agreeCheck);
			});
			
			
		});
		
	</script>
	
	<script>
       	$(function(){
       		//정규표현식
       		var exps = [
       	        /[a-z]+/,
       	        /[A-Z]+/,
       	        /[0-9]+/,
       	        /[$@#&!]+/,
       	    ];
       		
       		$("#userPwd").on("keyup", function(){
       			var strength = 0;
       			
       		 	$.each(exps, function(index, exp){
       				strength += $("#userPwd").val().match(exp) ? 1 : 0;
       			});
       		 	
       		 	
       		 	switch(strength){
					case 1:$("#level").css("--color", "red");
						break;
					case 2:$("#level").css("--color", "orange");
						break;
					case 3:$("#level").css("--color", "green");
						break;
					case 4:$("#level").css("--color", "blue");
						break;
       		 	}
       		 	
       		 	if($("#userPwd").val().length<6){
       		 		//사용자가 입력한 패스워드길이가 6보다 작으면 빨건색 strenth도 1
       		 		strength = 1;
       		 		$("#level").css("--color", "red");
       		 	}
       		 	
       			$("#level").val(strength);
    		 	console.log(strength);
	       		 	
       		});
       		
   		});
	</script>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div id="content">
		<div id="content_1" align="center">
	        <div id="enroll-area">
	            <form action="insert" method="post" id="insert-form">
	            	<div id="center">
	            		<table id="insert-table" >
			                <tr> 
			                    <td><label for="userId">ID</label>&nbsp;<i id="ci-icon" class="" style="color: #00ff62;"></i></td>
			                </tr>
			                <tr>
			                    <td width="70%"><input class="box" type="text" name="userId" id="userId" maxlength="12" required placeholder = "영문,숫자만  4~12자리"></td>
			                    <td width="30%">
			                    	<span class="hide" id="useId">이미 사용중인 아이디입니다.</span>
			                    	<span class="hide" id="formId">양식에 맞게 작성해주세요.</span>
			                    </td>
			                </tr>
			                
			                <tr>
			                    <td><label for="userPwd">Password</label>&nbsp;<i id="pwd-icon" class="" style="color: #00ff62;"></i></td>
			                </tr>
			                <tr>
			                    <td>
			                    	<input class="box" type="password" name="userPwd" id="userPwd" maxlength="20" required placeholder = "숫자,영어포함  6자 이상" >
			                    	<div style="text-align: center;">
				                    	<span>보안성</span>
				                    	<progress id="level" value="0" max="4"></progress>
			                    	</div>
			                    </td>
			                    <td>
			                    	<span class="hide" id="formPwd">비밀번호 양식을 확인해 주세요.</span>
			                    </td>
			                </tr>
			                <tr>
			                    <td><label for="chkPwd">Password Check</label>&nbsp;&nbsp;<i id="pwd2-icon" class="" style="color: #00ff62;"></i></td>
			                </tr>
			                <tr>
			                    <td><input class="box" type="password" id="checkPwd" name="checkPwd" maxlength="20" required placeholder = "비밀번호 재확인"></td>
			                    <td>
			                    	<span class="hide" id="notEqualPwd">비밀번호가 일치하지 않습니다.</span>
			                    </td>
			                </tr>
			                
			                
			                
			                <tr>
			                    <td><label for="nickName">Nickname</label>&nbsp;<i id="nick-icon" class="" style="color: #00ff62;"></i></td>
			                </tr>
			                <tr>
			                    <td><input class="box" type="text" name="nickName" id="nickName" required placeholder="숫자, 영문포함 2~12자리"></td>
			                    <td>
			                    	<span class="hide" id="useNick">이미 사용중인 닉네임입니다.</span>
			                    	<span class="hide" id="formNick">양식에 맞게 작성해주세요.</span>
			                    </td>
			                </tr>
			                
			                
			                <tr>
			                    <td><label for="email">Email</label>&nbsp;<i id="email-icon" class="" style="color: #00ff62;"></i></td>
			                </tr>
			                <tr>
			                	<td>
			                		<div class="input-group" id="email-group">
				                		<input class="box" type="text" name="email" id="email" required placeholder="이메일 형식으로 입력">
				                		<button type="button" class="btn btn-success" id="emailSend" style="z-index: auto;">Send</button>
			                		</div>
			                	</td>
			                	<td>
			                    	<span class="hide"><i></i></span>
			                    </td>
			                </tr>
							
							<tr>
								<td><label for="">Certification Code</label>&nbsp;<i id="certi-icon" class="" style="color: #00ff62;"></i></td>
							</tr>
			                <tr>
								<td>
									<div class="input-group" id="checkCode-group">
										<input class="box" id="certiCode" type ="text" required placeholder="인증코드 입력">
										<button type="button" class="btn btn-success" id="certiCheck" style="z-index: auto;">Check</button>
									</div>
									<input type="text" class="box hide" id="email-success" style="background-color: black;"readonly value="인증 완료">
								</td>
								
			                </tr>
			                
							<tr>
			                    <td>Terms of Use</td>
			                </tr>
			                <tr>
			                	<td colspan="2" style="vertical-align: top">
									<textarea cols="30" rows="10" style="resize:none; height: 150px">
[코알라] 회원가입 및 개인정보 수집 및 이용약관

제 1조 (목적)
이 약관은 [코알라]에서 제공하는 서비스를 이용하는 회원의 개인정보 보호를 위해 회원가입 시 개인정보 수집 및 이용에 관한 내용을 규정함을 목적으로 합니다.

제 2조 (수집하는 개인정보의 항목)
[코알라]은 회원가입을 위해 아래와 같은 개인정보를 수집합니다.

- 이메일 주소: 회원 식별 및 서비스 이용에 필요한 정보로 사용됩니다.
- 비밀번호: 회원 계정 보호 및 개인정보 보안을 위해 사용됩니다.
- 닉네임 또는 사용자명: 서비스 내에서 회원을 구분하고, 타인에게 공개될 경우 회원의 동의를 받습니다.

제 3조 (개인정보의 수집 및 이용목적)
[코알라]은 수집한 개인정보를 다음의 목적으로 사용합니다.
- 회원 식별 및 서비스 제공: 회원의 식별과 서비스 이용에 필요한 정보로 사용됩니다.
- 서비스 개선 및 맞춤화: 회원의 서비스 이용 패턴 및 선호도를 분석하여 서비스를 개선하고 맞춤화합니다.
- 고객 지원 및 문의 응답: 회원의 문의나 요청에 대한 응답 및 서비스 관련 공지사항 전달에 사용됩니다.
- 마케팅 및 광고 활동: 회원의 동의 하에 개인정보를 활용하여 서비스와 관련된 마케팅 및 광고 활동을 진행할 수 있습니다.

제 4조 (개인정보의 보유 및 이용기간)
[코알라]은 회원 탈퇴 시 또는 개인정보 이용목적 달성 시까지 개인정보를 보유 및 이용하며, 법령에 따라 보관할 필요가 있는 경우에는 해당 법령에서 정한 기간 동안 개인정보를 보관합니다.

제 5조 (개인정보의 제3자 제공)
[코알라]은 회원의 개인정보를 본래의 목적 이외에 제3자에게 제공하지 않습니다. 다만, 회원의 사전 동의를 받은 경우 또는 법령에 의거하여 제공되는 경우에는 예외로 합니다.

제 6조 (개인정보의 안전성 확보)
[코알라]은 개인정보보호법에서 정한 기술적, 관리적, 물리적 보호조치를 취하여 회원의 개인정보를 안전하게 관리합니다.

제 7조 (회원의 권리와 의무)
회원은 언제든지 자신의 개인정보를 열람하고 수정할 수 있습니다. 회원은 개인정보의 수집 및 이용에 대한 동의를 거부할 권리가 있습니다. 단, 이 경우 서비스 이용에 제한이 있을 수 있습니다. 회원은 자신의 개인정보를 최신의 상태로 유지하여 정확한 정보를 제공해야 합니다. 회원은 자신의 개인정보에 대한 보안을 유지해야 하며, 타인에게 개인정보가 유출되지 않도록 주의해야 합니다.

제 8조 (개인정보 관리 책임자)
[코알라]은 회원의 개인정보를 보호하고 개인정보와 관련된 불만을 처리하기 위해 개인정보 관리 책임자를 지정하여 운영하고 있습니다. 개인정보 관련 문의사항은 아래의 연락처로 문의하여 주시기 바랍니다.

이름: 
이메일: 
전화번호:
									</textarea>
								</td>
			                </tr>
			            </table>
			            
		                <div id="check-area">
			                <input type="checkbox" id="agree">
			                <label for="agree">이용약관 및 개인정보 수집에 동의합니다.</label>
		                </div>
		               	
		               	<div>
			                <button type="submit" id="submitBtn" class="btn btn-primary" disabled>submit</button>		               	
		               	</div>
	            	</div>
	            </form>
	        </div>
	    </div>
	</div>
	
	
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>