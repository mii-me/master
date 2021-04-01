		let receiveArr=[]; //쿼리스트링 매개변수를 담을 변수
		let uri = decodeURI(location.search); //모든 쿼리스트링을 저장할 변수
		let receive = uri.substr(uri.indexOf("?") + 1) //쿼리스트링에서 ?를 제외한 나머지를 담은 변수
		let receiveSplit = receive.split("&"); //&를 기준으로 자른 값들을 담은 변수 
		
		for(i=0;i<receiveSplit.length;i++){
			receiveArr.push(receiveSplit[i].split("=")[1]); //=를 기준으로 자른 값들 중 숫자만 담는다
			//console.log("receiveArr : " + receiveArr);
		}
		
		
		
		$("#goList").attr("href","artList.html"); //목록으로 돌아가기
			let userNo = sessionStorage.getItem("memNo");
			let memNo = receiveArr[0];	//선택된 작품의 작가 멤버번호
			let artNo = receiveArr[1]; //선택된 작품의 작품번호
			let tag = receiveArr[2]; //선택된 작품의 artTag1태그 
			let wishList = true; // 찜하기 : true(찜한상태) , false(찜하지 않은 상태)
		
			
			let selected; //선택된 작품 정보를 담는변수 (현재보고있는 작품)
			let tagName; //선택된 작품의 태그이름


	//작품 상세보기 함수
	function loadArtdetail(memNo,artNo) {
		$.ajax({url:"detailArt.do?memNo="+memNo+"&artNo="+artNo,success:function(data){
				
		selected = eval("("+data+")");
					
		//console.log("selected : " + selected.artTag1);
					
		//이미지 삽입
		$(".detail-content-pic-link").attr("src","art_pic/"+selected.artPic);
					
		$(".name").html(selected.artName);
		$("#artistName").html(selected.artistName);
		$(".material").html(selected.artMaterial);
		$(".size").html(selected.artSize1+"x"+selected.artSize2+"cm ");
		$(".year").html(selected.artYear);
	
		//가격에 쉼표찍기
		let aucBidString = (selected.aucBid).toString(); //경매가격
		let aucBid = aucBidString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		let aucBuyString = (selected.aucBuy).toString();
		let aucBuy = aucBuyString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
		$("#bid").html(aucBid);
		$("#buy").html(aucBuy);
					
		$("#pic").html(selected.artPic);
		$("#infoArtistName").html(selected.artistName);
		$("#infoArtistIntro").html(selected.artistIntro);
		$("#infoArtistCareer").html(selected.artistCareer);
				
	
		$(".simulation-art").attr("src","art_pic/"+selected.artPic);
					
		//방 배경 출력하기
		let bgArr = ['bedRoom.jpg','diningRoom.jpg','diningRoom2.jpg','diningRoom3.jpg','studyRoom.jpg'];
		let bgName = ['침실','거실1','거실2','거실3','방1'];
		let divImgOther = $("<div class='simulation-bg-box'></div>");	//다른 배경사진들 모든 div를 감쌈
			
			$.each(bgArr,function(index){
				let bgItem = bgArr[index];
				let bgNameItem = bgName[index]; //bgArr과 배열의 크기가 같으므로 같은 인덱스를 받아온다.
				//오른쪽에 출력 될 다른 배경사진들
				let imgOther = $("<img class='simulation-bg-other'></img>").attr("src","serverPic/"+bgItem);
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
				let span1 = $("<span></span>").html("“");
				$(span1).css("text-size","30px");
				let span2 = $("<span></span>").html("”");
				let exp = $("<div></div>").html(selected.artExp);
				let artistPic = $("<img></img>").attr("src","artist_pic/"+selected.artistPic);
				console.log(selected.artistPic)
				artistPic.css("width","70","height","70");
				$(".artistInfo-box-name-pic").append(artistPic)	
				$(".artExp-box").append(span1,exp,span2);
				}}); //ajax
		}//loadArtdetail()
		
			//이미지 전체 div의 위치를 담을 변수
			let positionBg = 0;
			let positionHash = 0;
			
			//배경들의 배열
			let bgArr = ['bedRoom.jpg','diningRoom.jpg','diningRoom2.jpg','diningRoom3.jpg','studyRoom.jpg'];
			
			
			//배경이미지단 이미지 이동 버튼
			function clickBtnBg(){
				//위치가 0이라면 왼쪽버튼을 누를 수 없다
				if(positionBg== 0) {
					$(".btnLeft-left").attr("disabled", true);
				}else if(positionBg< 0){//위치가 0보다 작다면(오른쪽 버튼을 누름으로 이동한 상태) 다시 왼쪽버튼을 누를 수 있다.
					$(".btnLeft-left").removeAttr("disabled");
				}
				
				//위치가 bgArr의 length에서 4를 뺀 수(한번에 4개를 보여주므로) * (-110)의 크기와 같다면 
				//오른쪽 버튼을 누를 수 없다. (더 보여줄 이미지가 없으므로)
				if(positionBg== (-110*(bgArr.length-4))) {
					$(".btnRight-right").attr("disabled", true);
				}else if(positionBg > (-110*(bgArr.length-4))){ //위치가 그 수보다 크다면 다시 오른쪽 버튼을 누를 수 있다. 
					$(".btnRight-right").removeAttr("disabled");
				}
			}//clickBtnBg
			
			//태그단 이미지 이동 버튼
			function clickBtnTag(){
				//위치가 0이라면 왼쪽버튼을 누를 수 없다
				if(positionHash == 0) {
					$(".artHashTag-img-btnLeft-left").attr("disabled", true);
				}else if(positionHash < 0){ //위치가 0보다 작다면(오른쪽 버튼을 누름으로 이동한 상태) 다시 왼쪽버튼을 누를 수 있다.
					$(".artHashTag-img-btnLeft-left").removeAttr("disabled");
				}
				//위치가 arr(같은 태그를 가진 사진들의 배열)의 길이에서 3을 뺀 수(한번에 3개를 보여주므로) * (-310px)의 위치와 같다면 
				//오른쪽 버튼을 누를 수 없다. (더 보여줄 이미지가 없으므로)
				if(positionHash == (-310*(arr.length-3))) {
					$(".artHashTag-img-btnRight-right").attr("disabled", true);
				}else if(positionHash > (-310*(arr.length-3))){//위치가 그 수보다 크다면 오른쪽 버튼을 다시 누를 수 있다. 
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
				$(".artHashTag-img").css("transform","translateX("+ (positionHash+=310) + "px)");
				
					clickBtnTag();
			});
			
			//해시태그단 오른쪽 버튼을 클릭했을 때
			$(".artHashTag-img-btnRight").click(function(){
				//누를때마다 x축의 방향으로 -310px만큼 움직인다.(이미지크기)
				$(".artHashTag-img").css("transform","translateX("+ (positionHash-=310) + "px)");
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
					alert("로그인한 회원으로, 경매에 참여할 수 있습니다.");
					location.href="auction.html?artNo="+artNo;				
				}else{ //로그인하지않았다면 로그인페이지로 이동한다.
					alert("로그인이 필요한 서비스입니다.");
					location.href="login.html";
				}//end else	
		}//clickBtnBid
		
		//즉시 구매하기 버튼을 클릭했을 때에 담을 함수
		function clickBtnBuy(artNo) {
				//로그인했다면 현재 보고있는 작품의 작품번호를 갖고 페이지를 이동한다.
				if(userNo!=null) {
					alert("로그인한 회원으로, 경매에 참여할 수 있습니다.");
					location.href="payment.html?artNo="+artNo;				
				}else{//로그인하지않았다면 로그인페이지로 이동한다.
					alert("로그인이 필요한 서비스입니다.");
					location.href="login.html";
				}//end else	
		}//clickBtnBid
		
		//경매장 버튼 클릭 이벤트 함수
		$("#btnBid").click(function(){
				clickBtnBid(artNo);	
		})
			
		//즉시 구매하기 클릭 이벤트 함수
		$("#btnBuy").click(function(){
			clickBtnBuy(artNo);
		})
		
	//찜하기 insert 함수
	function wishlistInsert(userNo,artNo) {
		$.ajax({url:"insertWishList.do/?userNo="+userNo+"&artNo="+artNo,success:function(r){
			alert(r);
		}}); //ajax
	};//wishlist
	
	//찜하기 delete 함수
	function wishlistDelete(userNo,artNo) {
		$.ajax({url:"deleteWishList.do/?userNo="+userNo+"&artNo="+artNo,success:function(r){
			alert(r);
		}}); //ajax
	};//wishlist
		