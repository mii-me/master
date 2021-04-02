
		let cookieName;
////////////////////////////////////////////////////////////////////////////////////////////////		
		//쿠키를 불러오는 함수
		function getCookie(cookieName) {
		  let x;
		  let y;
		  let val = document.cookie.split(';');
		  //alert("get쿠키함수 작동");
		  //alert("val.length : "+val.length);
		  let cookieArr = [];
		  for (let i = 0; i < val.length; i++) {
		    x = val[i].substr(0, val[i].indexOf('='));
		    y = val[i].substr(val[i].indexOf('=') + 1);
		    //alert("x : "+x);
		    //alert("y : "+y);
		    cookieArr.push(y);
		    x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
		    				//if (x == cookieName) {
		     				 //return unescape(y); // unescape로 디코딩 후 값 리턴
		  					  //}
		  }//end for
		    return cookieArr;
		}//getCookie
////////////////////////////////////////////////////////////////////////////////////////////////		
		//쿠키에 담긴 value들의 배열을 가져온다.
		function showCookie(){
			let cookieArr = getCookie(cookieName);
			//alert("show쿠키함수 작동");
			
			//링크도 설정해줘야 한다. 210402
			let i = cookieArr.length
			$("#sidenav1").html($("<img>").
			attr("src","./art_pic/"+cookieArr[i-1]).css("width","100px","height","70px"));
			$("#sidenav2").html($("<img>").
			attr("src","./art_pic/"+cookieArr[i-2]).css("width","100px","height","70px"));
			$("#sidenav3").html($("<img>").
			attr("src","./art_pic/"+cookieArr[i-3]).css("width","100px","height","70px"));
			
		}//showCookie
////////////////////////////////////////////////////////////////////////////////////////////////		
		