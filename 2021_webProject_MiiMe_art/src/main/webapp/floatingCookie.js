
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
			//alert("show쿠키함수 작동")
			//alert( cookieArr.length);
			//alert( cookieArr[0]);
			//쿠키가 비어있다면 "최근 본 작품 없음" 출력 / 210403
			if(cookieArr[0]=="" || cookieArr[0]==null){
				//alert("보여줄 그림 없음");
				let nullMsg = $("<p></p>").html("최근 본 작품 없음");
				$("#images").append(nullMsg);
			}else{
				//링크도 설정해줘야 한다. /  210402
				let i = cookieArr.length
				for(let j= i-1; j>= i-3 ; j--){
					let artPic = cookieArr[j];
					let data = {artPic : artPic}
					setTimeout(function() {  
						$.ajax({url:"/findArtElements.do", data:data, success:function(av){
						let img = $("<a></a>")
						.attr("href","artDetail.html?memNo="+av.memNo+"&artNo="
																		+av.artNo+"&tag="+av.artTag1)
						.html($("<img>")
						.attr("src","./art_pic/"+cookieArr[j]).css("width","100px","height","70px"));
						//images div에 추가해준다.
						$("#images").append(img);
						let hr = $("<hr>");
						$("#images").append(hr);
						}});				
					}, 200);
				}//end for
			}//end else
		}//showCookie
////////////////////////////////////////////////////////////////////////////////////////////////		
		