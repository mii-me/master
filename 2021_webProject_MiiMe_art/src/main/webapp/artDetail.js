		let receiveArr=[]; //쿼리스트링 매개변수를 담을 변수
		let uri = decodeURI(location.search); //모든 쿼리스트링을 저장할 변수
		let receive = uri.substr(uri.indexOf("?") + 1) //쿼리스트링에서 ?를 제외한 나머지를 담은 변수
		let receiveSplit = receive.split("&"); //&를 기준으로 자른 값들을 담은 변수 
		

		for(i=0;i<receiveSplit.length;i++){
			receiveArr.push(receiveSplit[i].split("=")[1]); //=를 기준으로 자른 값들 중 숫자만 담는다
			//console.log("receiveArr : " + receiveArr);
		}
		
		
		//$("#goList").attr("href","artList.html"); //목록으로 돌아가기
			let userNo = sessionStorage.getItem("memNo");
			let memNo = receiveArr[0];	//선택된 작품의 작가 멤버번호
			let artNo = receiveArr[1]; //선택된 작품의 작품번호
			let tag = receiveArr[2]; //선택된 작품의 artTag1태그 
			let wishList = false; // 찜하기 : true(찜한상태) , false(찜하지 않은 상태)
		
			let aucBid;
			let aucBuy;
		
			let selected; //선택된 작품 정보를 담는변수 (현재보고있는 작품)
			let tagName; //선택된 작품의 태그이름

			let nowBid; //즉시 구매하기로 넘어갈때 selected.aucBuy를 담을 변수.

	//작품 상세보기 함수
	function loadArtdetail(memNo,artNo) {
		
		$.ajax({url:"detailArt.do?memNo="+memNo+"&artNo="+artNo,success:function(data){
				
		selected = eval("("+data+")");
					
		//현재 경매가를 즉시 구매가로 설정
		nowBid = selected.aucBuy;
					
		//이미지 삽입
		$(".detail-content-pic-link").attr("src","art_pic/"+selected.artPic);
		
		//by 현익 / 쿠키에 artNo, artPic 담아주기
		let cookieName; //쿠키아이디 설정 by 현익
		let cookieArtNo;
		let cookieArtPic;	
		cookieArtNo = artNo;
		cookieArtPic = selected.artPic;
		//alert("cookieArtNo : "+cookieArtNo);
		//alert("cookieArtPic : "+cookieArtPic);
		cookieName = "'"+"artNo"+cookieArtNo+"'";
		//alert(" 4. 쿠키명:"+cookieName);
		setCookie(cookieName, cookieArtPic, 1); // 쿠키를 담아주는 메소드
		
		
		
		//동적으로 타이틀 변경하기
		$('html>head>title').text("미미 美;Me - " + selected.artistName + " | " + selected.artName);
		
		$(".name").html(selected.artName);
		$("#artistName").html(selected.artistName);
		$(".material").html(selected.artMaterial);
		$(".size").html(selected.artSize1+"x"+selected.artSize2+"cm ");
		$(".year").html(selected.artYear);
	
		//가격에 쉼표찍기
		let aucBidString = (selected.aucBid).toString(); //경매가격
		aucBid = aucBidString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		let aucBuyString = (selected.aucBuy).toString();
		aucBuy = aucBuyString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
		
		//판매 상황을 반영한다. (판매 시작 미등록 : null / 판매중 : sale / 판매 완료 : sold)
		let selectedSell = selected.artSell;
		//console.log("selectedSell : " + selectedSell);
		if(selectedSell == null) {
			aucBid = '-';
			aucBuy = '-';
			$(".sellStatusArea").addClass("badge badge-pill badge-secondary");
			$(".sellStatusArea").html("※ 판매예정인 작품입니다.");
		}
		if(selectedSell == 'sold') {
			aucBid = 'Sold Out';
			aucBuy = 'Sold Out';
			$(".sellStatusArea").addClass("badge badge-pill badge-danger");
			$(".sellStatusArea").html("Sold Out");
		}
		
		
		$("#bid").html(aucBid);
		$("#buy").html(aucBuy);
					
		$("#pic").html(selected.artPic);
		$("#infoArtistName").html(selected.artistName);
		$("#infoArtistIntro").html(selected.artistIntro);
		
		
		//career 컬럼에 들어있는 줄바꿈 적용하여 span에 출력하기 
		let temp = selected.artistCareer;
		temp = temp.replaceAll("\n\r","<br>");
		$("#infoArtistCareer").html(temp);
				
	
		$(".simulation-art").attr("src","art_pic/"+selected.artPic);
					
		//방 배경 출력하기
		let bgArr = ['bedRoom.jpg','diningRoom.jpg','diningRoom2.jpg','diningRoom3.jpg','studyRoom.jpg'];
		let bgName = ['침실','거실1','거실2','거실3','방1'];
		let divImgOther = $("<div class='simulation-bg-box'></div>");	//다른 배경사진들 모든 div를 감쌈
			
			$.each(bgArr,function(index){
				let bgItem = bgArr[index];
				let bgNameItem = bgName[index]; //bgArr과 배열의 크기가 같으므로 같은 인덱스를 받아온다.
				//오른쪽에 출력 될 다른 배경사진들
				let imgOther = $("<img class='simulation-bg-other'></img>").attr("src","img/"+bgItem);
				$(imgOther).attr("idx",index);
				//방 배경들의 아래에 붙을 이름
				let spanImgOtherName = $("<span id='nameTag'></span>").html(bgNameItem);
				let imgbox = $("<div class='simulation-bg-box-one'></div>").append(imgOther,spanImgOtherName);
				$(divImgOther).append(imgbox);
				$(".simulation-img-box-other").append(divImgOther);
			}); //end each
			
						
			//다른 배경을 클릭했을 때 
			$(".simulation-bg-other").click(function(){
			//	let bgsrc = $(".simulation-bg").attr("src");
			//console.log("기존배경src = " + bgsrc);
				
				//현재 클릭 된 방의 src
				let now = $(this).attr("src"); 
					//console.log("클릭된배경src = " + now);
					//console.log("=================");
				//현재 새로 누른 방 사진의 src를 왼쪽 걸어보기에 출력하도록 newsrc에 담는다. 		
				let newsrc = $(".simulation-bg").attr("src",now);
						
				$(".simulation-bg").empty();
				$(".simulation-bg").append(newsrc);
			});
			
					
				//작가정보에 href 기능 추가 (mem_no는 작가의 mem_no임)
				$(".artistInfo-box-btn-moreArtist").attr("href","artistDetail.html?memNo="+selected.memNo);
					
				//작품 설명
				let span1 = $("<span></span>").html("“&nbsp;&nbsp;");
				$(span1).css("font-size","70px");
				let span2 = $("<span></span>").html("&nbsp;&nbsp;”");
				$(span2).css("font-size","70px");
				let exp = $("<div class='artExp-box-expText'></div>").html(selected.artExp);
				let artistPic = $("<img class='artistInfo-box-name-pic-img'></img>").attr("src","artist_pic/"+selected.artistPic);
				//console.log(selected.artistPic)
				//artistPic.css("width","70","height","70");
				$(".artistInfo-box-name-pic").append(artistPic)	
				$(".artExp-box").append(span1,exp,span2);
				
				
					//즉시 구매하기 클릭 이벤트 함수 [by 현규]
		            $("#btnBuy").click(function(){
					
		               clickBtnBuy(artNo,nowBid);
		            });
				}}); //ajax
				

			//이미 찜 되어 있는지 아닌지를 판별하기 위해 sessionStorage의 value를 가져옴
			// 하트가 비어있는 것이 default이므로 null이나 false일 경우의 css는 설정하지 않는다.
			//얻어온 val이 true(찜목록에 insert 되어있는 상태)라면 하트를 빨간색으로 채운다. 				
			let wishListVal = sessionStorage.getItem("wishList"+artNo);
			
			if(wishListVal== 'true') {
				$(".wishHeartIcon").css("color","red"); 
			//console.log("현재 wishList1 : " + wishList);
			}
				
				
				
		}//loadArtdetail()
		
		
			//위시리스트 insert,delete
			$("#divBtnWishList").click(function(){
				
				let wishListVal = sessionStorage.getItem("wishList"+artNo);
				
				//찜하기를 클릭했을 때 userNo가 null이 아니라면 아래를 실행한다
				if(userNo != null) {
					//Sold Out, 판매예정이 아닐 때만 찜하기 버튼 실행 가능
					if(aucBid != 'Sold Out' && aucBid != '-'){
							
							//wishListVal = sessionStorage.getItem("wishList"+artNo); //value값
							//console.log(wishList + " : value의 값");
							
							//만약 한번도 클릭되지 않았으면 처음 클릭했을 때 상태를 true로 둔다.
							//찜하기 처음 클릭했을 때 : 찜하기
							if(wishListVal == null){
								$(".wishHeartIcon").css("color","red"); //아이콘에 빨간색을 채운다.
								wishlistInsert(userNo,artNo);
								wishList = true; //찜 한 상태
							}else{ //wishListVal이 sessionStorage에 이미 생성되어 있는 상태라면 아래를 실행한다.
								//얻어온 wishList의 value가 true라면 delete를 실행하고(true는 찜 되어있는 상태이므로) wishList를 false로 바꾼다. 
								if(wishListVal == 'true') {//이전에 true였고 지금은 false로 만들어야함 (찜하기에서 뺀다)
									$(".wishHeartIcon").css("color","white"); //찜 취소시 색을 비운다.
									wishlistDelete(userNo,artNo);
									wishList = false;	
								}else{ // 이전에 false였으면(찜목록에 없는 상태) insert를 실행하고, 현재 wishList는 true로 만들어야함 (찜하기에 담아야함)
									$(".wishHeartIcon").css("color","red"); //찜했을 경우 빨간색을 채운다.
									wishlistInsert(userNo,artNo);
									wishList = true;
								}
							}
							//세션스토리지에 작품관련 세션 생성 : 위 상태(insert&delete,하트의 색 등)를 setItem하기위해 맨 밑에 둠
							sessionStorage.setItem("wishList"+artNo, wishList);
							
					}else { // Sold Out, 판매예정인 작품일 경우 
						if(aucBid == 'Sold Out') {
							alert("판매 완료된 작품으로, 해당 기능을 사용할 수 없습니다.");
							return;
						}else if(aucBid == '-'){ 
							alert("판매 예정인 작품입니다.");
							return;
						}
					}
					
				}else{ //userNo가 null이라면 로그인 페이지로 이동한다.
					alert("로그인 후 이용할 수 있습니다.");
					location.href="login.html";
				}
				
			});//click
			
			
			
////////////////////////////////////////////////////////////////////////////////////////////////		
//by 현익 / 작품눌러서 상세로 이동할때 쿠키에 저장 / 210401
		//쿠키를 저장하는 함수
		function setCookie(cookieName, cookieArtPic, days) {
		  //alert("5. setCookie 작동");
 			 let exdate = new Date();
  			 exdate.setDate(exdate.getDate() + days);
  			// 설정 일수만큼 현재시간에 만료값으로 지정
  			let cookieValue = escape(cookieArtPic) + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
 		    document.cookie = cookieName + '=' + cookieValue;
		}//setCookie
////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
			//이미지 전체 div의 위치를 담을 변수
			let positionBg = 0;
			let positionHash = 0;
			
			//배경들의 배열
			let bgArr = ['bedRoom.jpg','diningRoom.jpg','diningRoom2.jpg','diningRoom3.jpg','studyRoom.jpg'];
			
			
			//배경이미지단 이미지 이동 버튼
			function clickBtnBg(){
				//위치가 0이라면 왼쪽버튼을 누를 수 없다
				if(positionBg>= 0) {
					$(".btnLeft-left").attr("disabled", true);
				}else if(positionBg< 0){//위치가 0보다 작다면(오른쪽 버튼을 누름으로 이동한 상태) 다시 왼쪽버튼을 누를 수 있다.
					$(".btnLeft-left").removeAttr("disabled");
				}
				
				//위치가 bgArr의 length에서 4를 뺀 수(한번에 4개를 보여주므로) * (-110)의 크기와 같다면 
				//오른쪽 버튼을 누를 수 없다. (더 보여줄 이미지가 없으므로)
				if(positionBg >= (-110*(bgArr.length-4))) {
					$(".btnRight-right").attr("disabled", true);
				}else if(positionBg > (-110*(bgArr.length-4))){ //위치가 그 수보다 크다면 다시 오른쪽 버튼을 누를 수 있다. 
					$(".btnRight-right").removeAttr("disabled");
				}
			}//clickBtnBg
			
			//태그단 이미지 이동 버튼
			function clickBtnTag(){
				//위치가 0이라면 왼쪽버튼을 누를 수 없다
				if(positionHash>=0) {
					$(".artHashTag-img-btnLeft-left").attr("disabled", true);
				}else if(positionHash < 0){ //위치가 0보다 작다면(오른쪽 버튼을 누름으로 이동한 상태) 다시 왼쪽버튼을 누를 수 있다.
					$(".artHashTag-img-btnLeft-left").removeAttr("disabled");
				}
				//위치가 arr(같은 태그를 가진 사진들의 배열)의 길이에서 3을 뺀 수(한번에 3개를 보여주므로) * (-310px)의 위치와 같다면 
				//오른쪽 버튼을 누를 수 없다. (더 보여줄 이미지가 없으므로)
				if(positionHash == (-315*(arr.length-3))) {
					$(".artHashTag-img-btnRight-right").attr("disabled", true);
				}else if(positionHash > (-315*(arr.length-3))){//위치가 그 수보다 크다면 오른쪽 버튼을 다시 누를 수 있다. 
					$(".artHashTag-img-btnRight-right").removeAttr("disabled");
				}
			}//canClick
			
		//버튼을 클릭했을때의 이벤트를 담은 함수
		function btnClick(){

			//위치가 0이라면 왼쪽 버튼을 누를 수 없다.(왼쪽으로 가도 보여줄 이미지가 없으므로)
			if(positionBg== 0) {
				$(".btnLeft-left").attr("disabled", true);
				$(".artHashTag-img-btnLeft-left").attr("disabled", true);
			}
			
			//걸어보기단에서 왼쪽 버튼을 눌렀을 때
			$(".btnLeft").click(function(){
				//누를때마다 x축의 방향으로 110px만큼 움직인다.(이미지크기)
				$(".simulation-bg-box").css("transform","translateX("+ (positionBg+=110) + "px)");
				
				clickBtnBg();
			});
			
			//걸어보기단에서 오른쪽 버튼을 눌렀을 때
			$(".btnRight").click(function(){
				//누를때마다 x축의 방향으로 110px만큼 움직인다.(이미지크기)
				$(".simulation-bg-box").css("transform","translateX("+ (positionBg-=110) + "px)");

				clickBtnBg();
			})
			
			//해시태그단 왼쪽 버튼을 클릭했을 때
			$(".artHashTag-img-btnLeft").click(function(){
				//누를때마다 x축의 방향으로 310px만큼 움직인다.(이미지크기)
				$(".artHashTag-img").css("transform","translateX("+ (positionHash+=315) + "px)");
				
					clickBtnTag();
			});
			
			//해시태그단 오른쪽 버튼을 클릭했을 때
			$(".artHashTag-img-btnRight").click(function(){
				//누를때마다 x축의 방향으로 -310px만큼 움직인다.(이미지크기)
				$(".artHashTag-img").css("transform","translateX("+ (positionHash-=315) + "px)");
					clickBtnTag();
			});
			
			
		}//spanClick;
		
		
		
		
		//해시태그단 함수
		function loadArtHash(tag){
			$.ajax({url:"detailArtHash.do?tag="+tag,success:function(data){
				arr = [];
				data = eval("("+data+")");
				//console.log("loadArtHash : " + data);
									
				$.each(data,function(index,item){
					//tag는 artTag1의 값을 담는다.
					let tag = item.artTag1;
					
					//만약 tag가 선택안함을 담았다면, tag는 공백을 다시 담는다.
					if(tag == '선택안함') {
						tag = '';
					}
					
					//배열에 같은 태그를 가진 사진들을 담는다.
					arr.push(item.artPic);
					//작품 상세보기로 이동할 수 있는 링크를 설정한다. (encodeURI는 tag가 한글이므로 이를 인코딩하기 위함)
					let link = $("<a></a>").attr("href",encodeURI("artDetail.html?memNo="+item.memNo+"&artNo="+item.artNo+"&tag="+item.artTag1));
					//같은 해시태그를 가진 사진들을 배열에서 꺼내서 출력한다.
					let hashImg = $("<img class='artHashTag-imgbox'></img>").attr("src","art_pic/"+arr[index]);
					$(link).append(hashImg);
					let div_link = $("<div class='artHashTag-img-div-link'></div>").append(link);
					$(div_link).append(link);
					
					$(".artHashTag-Name").html(tag);
					$(".artHashTag-img").append(div_link);
				});
				
			}})
		}
		
		
		
		//경매장가기 버튼을 클릭했을 때에 담을 함수
		function clickBtnBid(artNo) {
				//로그인했다면 현재 보고있는 작품의 작품번호를 갖고 페이지를 이동한다.
				if(userNo!=null) {
					
					//솔드아웃이면 경매페이지로 이동하지 않는다.
					if(aucBid == 'Sold Out') {
						alert("판매 완료된 작품입니다.");
						return;
					}else if(aucBuy == '-'){//판매예정인 작품이면 경매페이지로 이동하지 않는다.
						alert("판매 예정인 작품입니다.");
						return;
					}
					
					//alert("로그인한 회원으로, 경매에 참여할 수 있습니다.");
					location.href="auction.html?artNo="+artNo;	
				}else{ //로그인하지않았다면 로그인페이지로 이동한다.
					alert("로그인이 필요한 서비스입니다.");
					location.href="login.html";
				}//end else	
		}//clickBtnBid
		
		//즉시 구매하기 버튼을 클릭했을 때에 담을 함수
		function clickBtnBuy(artNo,nowBid) {
				//로그인했다면 현재 보고있는 작품의 작품번호를 갖고 페이지를 이동한다.
				if(userNo!=null) {
					
					//솔드아웃이면 구매페이지로 이동하지 않는다.
					if(aucBuy == 'Sold Out') {
						alert("판매 완료된 작품입니다.");
						return;
					}else if(aucBuy == '-'){ //판매예정인 작품이면 구매페이지로 이동하지 않는다.
						alert("판매 예정인 작품입니다.");
						return;
					}
					
					//alert("로그인한 회원으로, 경매에 참여할 수 있습니다.");
					
					//현재 입찰가를 즉시구매가로 업데이트 후 결제 진행 [by 현규]
	               $.ajax({
	               url:"/updateBid.do",
	               data:{artNo:artNo,aucBid:nowBid},
	               success:function(){
	                  location.href="payment.html?artNo="+artNo;
	               }});
							
				}else{//로그인하지않았다면 로그인페이지로 이동한다.
					alert("로그인이 필요한 서비스입니다.");
					location.href="login.html";
				}//end else	
		}//clickBtnBid
		
		//경매장 버튼 클릭 이벤트 함수
		$("#btnBid").click(function(){
				clickBtnBid(artNo);	
		})
		
		/*		
		//즉시 구매하기 클릭 이벤트 함수
		$("#btnBuy").click(function(){
			clickBtnBuy(artNo);
		})
		*/
		
	//찜하기 insert 함수
	function wishlistInsert(userNo,artNo) {
		
		//솔드아웃이면 찜목록에 insert하지 않는다.
		if(aucBid == 'Sold Out') {
				alert("판매 완료된 작품으로, 해당 기능을 사용할 수 없습니다.");
				return;
		}else if(aucBid == '-'){ //판매예정인 작품이면 찜목록에 insert하지 않는다.
				alert("판매 예정인 작품입니다.");
				return;
		}
		
		$.ajax({url:"insertWishList.do/?userNo="+userNo+"&artNo="+artNo,success:function(r){
			alert(r);
		}}); //ajax
	};//wishlist
	
	//찜하기 delete 함수
	function wishlistDelete(userNo,artNo) {
		
		//솔드아웃이면 delete하지 않는다.
		if(aucBid == 'Sold out') {
				alert("판매 완료된 작품으로, 해당 기능을 사용할 수 없습니다.");
				return;
		}else if(aucBid == '-'){ //판매예정인 작품이면 delete하지 않는다.
				alert("판매 예정인 작품입니다.");
				return;
		}
		
		$.ajax({url:"deleteWishList.do/?userNo="+userNo+"&artNo="+artNo,success:function(r){
			alert(r);
		}}); //ajax
	};//wishlist
		
