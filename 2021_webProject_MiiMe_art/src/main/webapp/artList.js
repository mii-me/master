let arr;
let totalPage; //전체 페이지수 : 페이징처리		
let responseTheme; //조건검색에서 받아온 테마
let sizeRange1; //조건검색에서 받아온 사이즈1 (art_size1)
let sizeRange2; //조건검색에서 받아온 사이즈2 (art_size2)
let responsePrice; //조건검색에서 받아온 가격


//작품보기 페이지 함수(페이징,조건검색테마,조건검색사이즈1,조건검색사이즈2,조건검색가격)
function loadArtList(pageNO,responseTheme,sizeRange1,sizeRange2,responsePrice) {
			
	let data = {pageNO:pageNO,responseTheme:responseTheme,sizeRange1:sizeRange1,sizeRange2:sizeRange2,responsePrice:responsePrice};

	$.ajax({url:"/listArt.do",data:data,success:function(data){
				
			arr = [];
	 		data = eval("("+data+")"); 
			arr = data.list; 
			totalPage = data.totalPage;
			$("#page").empty();
			$(".art_list").empty();
				
				
			 		//페이지번호 출력하기
					for(i=1;i<=totalPage;i++) {
						let pageAnchor = $("<span class='pageNO'></span>").html(i);
						$("#page").append(pageAnchor);
						
					};
						$(".pageNO").click(function(){
							
							let pageNO = $(this).text();
							
							loadArtList(pageNO,responseTheme,sizeRange1,sizeRange2,responsePrice);
						});//span.click
					
				
			 		$.each(arr,function(index,a){ //
			 			let div = $("<div class='art_list-artbox'></div>"); //전체div
			 			$(div).attr("idx",index);
				 	
			 			//#(샵)을 붙여서 조회한 태그에서 #을 제외한 값을 변수에 담는다.
			 			let artTag = a.artTag1;
			 			artTag = artTag.substring(1);
					
						//art_tag1이 '선택안함'이라면 쿼리스트링을 공백으로 넘길것이다.
						if(artTag=='선택안함') {
							artTag = '';
						}
						
						
						//앵커태그 걸기 : encodeURI - 한글값을 넘기기 위함(태그:한글)
						let link = $("<a class='artLink'></a>").attr("href",encodeURI("artDetail.html?memNo="+a.memNo+"&artNo="+a.artNo+"&tag="+artTag))
						let div_link = $("<div id='divDetail'></div>").append(link); //앵커 div
						let img_pic = $("<img class='artPic'>").attr("src","art_pic/"+a.artPic);
						let div_img = $("<div class='artPicHover'></div>").append(img_pic); //이미지div 
						
						let text_name = $("<div></div>").html("<h3 class='listArtName'>" + a.artName + "</h3>"); //작품제목
						
						
						
						//태그가 선택안함이라면 작품 리스트에서 보여줄 때 공백으로 보여준다.
						if(a.artTag1 == '#선택안함'){
							a.artTag1 = '';
						}
						if(a.artTag2 == '#선택안함'){
							a.artTag2 = '';
						}
						if(a.artTag3 == '#선택안함') {
							a.artTag3 = '';
						}
						
						let text_tag = $("<div class='listTag'></div>").html(a.artTag1+a.artTag2+a.artTag3); //해시태그
						
						//가격에 쉼표찍기
						let aucBidString = (a.aucBid).toString(); //경매가격
						let aucBid = aucBidString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
						let aucBuyString = (a.aucBuy).toString();
						let aucBuy = aucBuyString.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
						
						
						$(".art_list").append(div);
						
					
						let text_bid; 
						let text_buy; 
						let div_text;
						
						let isSoldOut = a.artSell; //판매 여부를 판단하기 위한 변수 
						//console.log("isSoldOut : " + isSoldOut);
					
						let isSoldOutIcon;
						if(isSoldOut == null) {
							
							isSoldOutIcon = $("<div class='isSoldout isSoldout-none'></div>").html("<i class='fa fa-hourglass-end' aria-hidden='true'><span class='fa-hourglass-end-text'>&nbsp;판매예정&nbsp;</span></i>");
							text_bid =  $("<div></div>").html("<b>"+"현재 입찰가 &nbsp;&nbsp;&nbsp;"+"</b>" + "-"); 
							text_buy = $("<div></div>").html("<b>"+"즉시 구매가 &nbsp;&nbsp;&nbsp;"+"</b>"+ "-"); 
							div_text = $("<div id='div_text'></div>").append(text_name,text_tag,text_bid,text_buy,isSoldOutIcon);
							$(link).append(div_img,div_text);
						
							$(div).append(div_link);
							$(".fa-hourglass-end-text").css("color","#333745");
						}
						if(isSoldOut == 'sale'){
							isSoldOutIcon = $("<div class='isSoldout isSoldout-ing'></div>").html("<i class='fa fa-hourglass-2' aria-hidden='true'><span class='fa-hourglass-2-text'>&nbsp;판매중&nbsp;</span></i>");
							text_bid = $("<div></div>").html("<b>"+"현재 입찰가 &nbsp;&nbsp;&nbsp;"+"</b>" + aucBid); 
							text_buy = $("<div></div>").html("<b>"+"즉시 구매가 &nbsp;&nbsp;&nbsp;"+"</b>"+ aucBuy);
							div_text = $("<div id='div_text'></div>").append(text_name,text_tag,text_bid,text_buy,isSoldOutIcon);
							$(link).append(div_img,div_text);
						
							$(div).append(div_link);
							$(".fa-hourglass-2-text").css("color","#4085df");
						}
						if(isSoldOut == 'sold'){
							isSoldOutIcon = $("<div class='isSoldout isSoldout-sold'></div>").html("<i class='fa fa-hourglass' aria-hidden='true'><span class='fa-hourglass-text'>&nbsp;Sold out&nbsp;</span></i>");
							text_bid = $("<div></div>").html("<b>"+"현재 입찰가 &nbsp;&nbsp;&nbsp;"+"</b>" + "Sold out"); 
							text_buy = $("<div></div>").html("<b>"+"즉시 구매가 &nbsp;&nbsp;&nbsp;"+"</b>"+ "Sold out");
							div_text = $("<div id='div_text'></div>").append(text_name,text_tag,text_bid,text_buy,isSoldOutIcon);
							$(link).append(div_img,div_text);
						
							$(div).append(div_link);
							$(".fa-hourglass-text").css("color","#EA2E49");
						}
					
						$(".listTag").css("font-size","13px");
							
					
						//가격 input range바 설정(최솟값 0, 최댓값 1000만,한번에 10만씩 증가)
						$("#searchPriceBar").attr("min","0");
						$("#searchPriceBar").attr("max","10000000");
						$("#searchPriceBar").attr("value","10000000");
						$("#searchPriceBar").attr("step","100000");
					
			 		}); //each

			 	}}); //ajax
		
		
		}//loadArtList
		
		
		//테마 리스트를 클릭했을 때 
		$(".searchThemeli").click(function(){
					
			//클릭했을시의 css	: 다른걸 클릭하면 이전에 있던 css를 다 지운다.
			$(".searchThemeli").css("background-color","");
			$(".searchThemeli").css("color","");
			$(this).css("background-color","#2d3648");
			$(this).css("color","#fff");
			$(this).css("transition","background 0.15s");
					
			//responseTheme 변수에 현재 누른 li의 text를 담는다.
			responseTheme = $(this).text();
			
			//만약 다른 조건이 아직 눌리지 않았으면
			if(sizeRange1 == null && sizeRange2 == null && responsePrice == null){
				//기본값으로설정한다.
				sizeRange1 = 0;
				sizeRange2 = 101;
				responsePrice = 1000000000;
			}else if(sizeRange1 == null && sizeRange2 == null){
				sizeRange1 = 0;
				sizeRange2 = 101;
			}else if(responsePrice == null){
				responsePrice = 1000000000;
			}
			//console.log("responseTheme : "+ responseTheme);
			// 나중에 누른것에 첫번째 누른것이 상태유지 되어야함
			loadArtList(1,responseTheme,sizeRange1,sizeRange2,responsePrice);
			//console.log("Themeli 클릭됨");
		});
			 		
		//사이즈 리스트를 클릭했을 때 				
		$(".searchSizeli").click(function(){
			
			//클릭했을시의 css : 다른걸 클릭하면 이전에 있던 css를 다 지운다. 
			$(".searchSizeli").css("background-color","");
			$(".searchSizeli").css("color","");
			$(this).css("background-color","#2d3648");
			$(this).css("color","#fff");
			$(this).css("transition","background 0.15s");
			
			//responseSize라는 변수에 현재 클릭된 사이즈 리스트의 text를 담는다.
			//예) 1~5호 / 6~10호 ...
			let responseSize = $(this).text();
			//console.log("responseSize : " + responseSize);
			
			//담은 responseSize가 다음 글자와 같으면 sizeRang1,2는 다음과 같다.
			if(responseSize == '1~5호') {
					sizeRange1 = 22;
					sizeRange2 = 35;
					//console.log("sizeRange1 : " + sizeRange1 + ", sizeRange2 : " + sizeRange2);		
			}else if(responseSize == '6~10호') {
					sizeRange1 = 36;
					sizeRange2 = 53;		
					//console.log("sizeRange1 : " + sizeRange1 + ", sizeRange2 : " + sizeRange2);		
			}else if(responseSize == '~20호') {
					sizeRange1 = 54;
					sizeRange2 = 73;		
					//console.log("sizeRange1 : " + sizeRange1 + ", sizeRange2 : " + sizeRange2);		
			}else if(responseSize == '~30호') {
					sizeRange1 = 74;
					sizeRange2 = 91;		
					//console.log("sizeRange1 : " + sizeRange1 + ", sizeRange2 : " + sizeRange2);		
			}else if(responseSize == '~40호') {
					sizeRange1 = 92;
					sizeRange2 = 100;		
					//console.log("sizeRange1 : " + sizeRange1 + ", sizeRange2 : " + sizeRange2);		
			}
			
			
			//만약 테마가 아직 눌리지 않았으면
			if(responseTheme == null && responsePrice == null){
				responseTheme = '';
				responsePrice = 1000000000;
			}else if(responseTheme == null){
				responseTheme = '';
			}else if(responsePrice == null){
				responsePrice = 1000000000;
			}
			
							
			loadArtList(1,responseTheme,sizeRange1,sizeRange2,responsePrice);
		});//.searchSizeli.click : function()
		
		//가격 바의 range를 변경했을 때 
		$("#searchPriceBar").change(function(){
			//responsePrice에 현재 range커서가 위치하는 곳의 value를 담는다.
			responsePrice = $(this).val();
			//그리고 오른쪽에 그 값을 표시한다.
			$("#searchPriceBar-value").html(responsePrice);
			
			//만약 조건이 눌리지 않았다면
			if(responseTheme == null && sizeRange1 == null && sizeRange2 == null){
				//기본값으로 설정한다.
				responseTheme = '';
				sizeRange1 = 0;
				sizeRange2 = 101;
			}else if(responseTheme == null){
				responseTheme = '';
			}else if(sizeRange1 == null && sizeRange2 == null){
				sizeRange1 = 0;
				sizeRange2 = 101;
			}
			
			
			
			
			loadArtList(1,responseTheme,sizeRange1,sizeRange2,responsePrice);
		})//#searchPriceBar.change : function()
		
			
					
		//초기화 버튼을 누르면 선택한 조건들이 모두 초기화된다.
		$(".btnReset").click(function(){
			//입혀져있던 css도 초기화한다
			$(".searchThemeli").css("background-color","");
			$(".searchThemeli").css("color","");
			$(".searchSizeli").css("background-color","");
			$(".searchSizeli").css("color","");
			$("#searchPriceBar-value").html("");
			
			responseTheme = '';
			sizeRange1 = 0;
			sizeRange2 = 101;
						
			loadArtList(1,"",0,101,1000000000);
		})//.btnReset.click : function()
		
		
		
