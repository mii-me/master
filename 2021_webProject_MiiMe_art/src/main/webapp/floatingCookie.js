
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
				let nullMsg = $("<p></p>").html("최근 본 작품이<br>없습니다.");
				$("#images").append(nullMsg);
					
			}else{
				//링크도 설정해줘야 한다. /  210402
				let i = cookieArr.length;
				let artPic;
				let data;
				let img;
				let img_div;
				
				
				if(i == 1){
					
					setTimeout(function() {  
						
					artPic = cookieArr[0];
					data = {artPic : artPic};
					
						$.ajax({url:"/findArtElements.do", data:data, success:function(av){
						img = $("<a></a>")
						.attr("href","artDetail.html?memNo="+av.memNo+"&artNo="
																		+av.artNo+"&tag="+av.artTag1)
						.html($("<img class='img_hover'>")
						.attr("src","./art_pic/"+cookieArr[0]).css("width","90px").css("height","90px"));
						
						img_div = $('<div style="padding-bottom:10px;"></div>').append(img);
						
						//images div에 추가해준다.
						$("#images").append(img_div);
						}});				
					}, 200);					
					
					
				}else if(i == 2){
					
					for(let k=i-1; k>=i-2; k--){
						
						setTimeout(function() {  
							
							artPic =  cookieArr[k];
							data = {artPic : artPic};
							
							$.ajax({url:"/findArtElements.do", data:data, success:function(av){
							img = $("<a></a>")
							.attr("href","artDetail.html?memNo="+av.memNo+"&artNo="
																			+av.artNo+"&tag="+av.artTag1)
							.html($("<img class='img_hover'>")
							.attr("src","./art_pic/"+cookieArr[k]).css("width","90px").css("height","90px"));
							
							img_div = $('<div style="padding-bottom:10px;"></div>').append(img);
							
							//images div에 추가해준다.
							$("#images").append(img_div);
							}});				
						}, 200);
					}					
					
				}else{
					for(let j= i-1; j>= i-3 ; j--){
						
						setTimeout(function() {  
							
							artPic = cookieArr[j];
							data = {artPic : artPic};
							
							$.ajax({url:"/findArtElements.do", data:data, success:function(av){
							img = $("<a></a>")
							.attr("href","artDetail.html?memNo="+av.memNo+"&artNo="
																			+av.artNo+"&tag="+av.artTag1)
							.html($("<img class='img_hover'>")
							.attr("src","./art_pic/"+cookieArr[j]).css("width","90px").css("height","90px"));
							
//							console.log('av.memNo: ' + av.memNo);
//							console.log('av.artNo: ' + av.artNo);
//							console.log('av.artTag1: ' + av.artTag1);
							
							img_div = $('<div style="padding-bottom:10px;"></div>').append(img);
							
							//images div에 추가해준다.
							$("#images").append(img_div);
							}});				
						}, 200);
					}//end for
				}
			}//end else
		}//showCookie
////////////////////////////////////////////////////////////////////////////////////////////////		
		