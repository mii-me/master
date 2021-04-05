package com.art.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.art.vo.ArtInfoVo;
import com.art.vo.ArtistCommentVo;
import com.art.vo.ArtistInfoVo;
import com.art.vo.AuctionVo;
import com.art.vo.ChangeInfoVo;
import com.art.vo.ClientComplainVo;
import com.art.vo.DeliveryVo;
import com.art.vo.MemberVo;
import com.art.vo.PaymentVo;
import com.art.vo.PurchaseListVo;
import com.art.vo.ReviewVo;
import com.art.vo.WishListVo;

public class DBManager {
private static SqlSessionFactory factory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/art/db/dbConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		}catch(Exception e) {
			System.out.println("예외발생: "+e.getMessage());
		}
	}//static


// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 정소윤] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	/**
	 * 최상단 메인페이지 진입 시 최신 작품 3개
	 * @author 정소윤
	 * @return 해당 작품 정보
	 */
	public static List<ArtistInfoVo> mainArt() {
		SqlSession session = factory.openSession();
		List<ArtistInfoVo> list = session.selectList("artistInfo.mainArt");
		session.close();
		return list;
	}
	// ========================= 작가소개 페이징처리를 위한 총 개수 반환 메소드 =========================
	
	
	// 작가목록 개수
	public static int getTotalArtist(String keyword, String consonant, String sort) {
		System.out.println("3 .작가목록 개수 Manager 작동: keyword "+keyword+"/consonant "+consonant+"/sort "+sort);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("consonant", consonant);
		map.put("sort", sort);
		SqlSession session = factory.openSession();
		int count = session.selectOne("artistInfo.totalArtist", map);
		session.close();
		return count;
	}
	
	// 해당 작가의 작품목록 개수
	public static int getTotalArtistArt(int memNo) {
//		System.out.println("3 .작품목록 개수 Manager 작동: memNo "+memNo);
		
		SqlSession session = factory.openSession();
		int count = session.selectOne("artistInfo.totalArtistArt", memNo);
		session.close();
		return count;
	}
	
	// 해당 작가의 한줄기대평 개수
	public static int getTotalArtistComment(int memNo) {
//		System.out.println("3 .작가기대평 개수 Manager 작동: memNo "+memNo);
		
		SqlSession session = factory.openSession();
		int count = session.selectOne("artistInfo.totalArtistComment", memNo);
		session.close();
		return count;
	}
	
	// ========================= 작가소개 페이징처리를 위한 총 개수 반환 메소드 END =========================
	
	
	
	// ========================= 작가소개 메인페이지 진입 =========================
	

	/**
	 * 작가소개 메인 진입 시 상단에 보이는 인기작가 Top3 슬라이드
	 * @return 인기작가 Top3 작가 정보
	 */
	public static List<ArtistInfoVo> topArtistInfo() {
		SqlSession session = factory.openSession();
		List<ArtistInfoVo> list = session.selectList("artistInfo.topArtistInfo");
		session.close();
		return list;
	}//topArtistInfo
	
	
	/**
	 * 작가소개 메인 진입 시 보이는 작가목록,
	 * 최신순 정렬 클릭 시 보이는 작가목록
	 * @author 정소윤
	 * @param 시작페이지
	 * @param 끝페이지
	 * @param 검색어
	 * @return 작가목록(최신순 정렬)
	 */
	public static List<ArtistInfoVo> listArtistInfo(int start, int end, String keyword, String consonant, String sort){
		System.out.println("5. 작가목록 조회 Manager 작동: start "+start+"/end "+end+"/keyword "+keyword+"/consonant "+consonant+"/sort "+sort);
		System.out.println("==============================");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);
		map.put("consonant", consonant);
		map.put("sort", sort);
		
		SqlSession session = factory.openSession();
		List<ArtistInfoVo> list = session.selectList("artistInfo.listArtistInfo", map);
		session.close();
//		System.out.println("작가목록 Manager 작동");
		return list;
	}//listArtistInfo
	
	// ========================= 작가소개 메인페이지 END =========================
	
	
	
	// ========================= 특정 작가 클릭 시 해당 작가의 상세프로필 페이지 진입 =========================
	
	/**
	 * 해당 작가의 상세프로필 상단에 보이는 작가 정보
	 * @author 정소윤
	 * @param memNo 선택한 작가의 회원번호
	 * @return 해당 작가의 정보
	 */
	public static ArtistInfoVo selectArtistInfo(int memNo) {
		System.out.println("3. 작가프로필 조회 Manager 작동: memNo "+memNo);
		
		SqlSession session = factory.openSession();
		ArtistInfoVo a = session.selectOne("artistInfo.selectArtistInfo", memNo);
		session.close();
		return a;
	}//selectArtistInfo
	
	
	/**
	 * 해당 작가의 상세프로필 중단에 보이는 작품 목록
	 * @author 정소윤
	 * @param memNo 선택한 작가의 회원번호
	 * @param end 
	 * @param start 
	 * @return 해당 작가의 작품 정보
	 */
	public static List<ArtistInfoVo> listArtistInfoArt(int memNo, int start, int end) {
//		System.out.println("5. 작품목록 조회 Manager 작동: memNo "+memNo+"/start "+start+"/end "+end);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memNo", memNo);
		map.put("start", start);
		map.put("end", end);
		
		SqlSession session = factory.openSession();
		List<ArtistInfoVo> list = session.selectList("artistInfo.listArtistInfoArt", map);
		session.close();
		return list;
	}//listArtistInfoArt
	
	
	/**
	 * 해당 작가의 상세프로필 하단에 보이는 작가 기대평
	 * @author 정소윤
	 * @param memNo 선택한 작가의 회원번호
	 * @param end 
	 * @param start 
	 * @return 해당 작가에 대한 한줄기대평 목록
	 */
	public static List<ArtistCommentVo> listArtistComment(int memNo, int start, int end) {
//		System.out.println("5. 작가기대평 조회 Manager 작동: memNo "+memNo+"/start "+start+"/end "+end);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memNo", memNo);
		map.put("start", start);
		map.put("end", end);
		
		SqlSession session = factory.openSession();
		List<ArtistCommentVo> list = session.selectList("artistInfo.listArtistComment", map);
		session.close();
		return list;
	}//listArtistInfoArt
	
	
	/**
	 * 작가 기대평 등록 시 해당 테이블에 insert
	 * @author 정소윤
	 * @param ac ArtistCommentVo, 작가 기대평 테이블에 들어갈 값
	 * @return 작가 기대평 등록
	 */
	public static int insertArtistComment(ArtistCommentVo ac) {
//		System.out.println("3. 작가기대평 등록 Manager 작동: "+ac);
		
		SqlSession session = factory.openSession();
		int re = session.insert("artistInfo.insertArtistComment", ac);
		session.commit();
		session.close();
		return re;
	}//insertArtistComment
	
	
	/**
	 * 작가 기대평 삭제 시 해당 테이블에서 delete
	 * @author 정소윤
	 * @param artistCmtNo 삭제할 코멘트 번호
	 * @return 삭제할 번호 쿼리문에 전달
	 */
	public static int deleteArtistComment(int artistCmtNo) {
		System.out.println("3. 작가기대평 삭제 Manager 작동: "+artistCmtNo);
		
		SqlSession session = factory.openSession();
		int re = session.delete("artistInfo.deleteArtistComment", artistCmtNo);
		session.commit();
		session.close();
		return re;
	}//deleteArtistComment
	
	// ========================= 특정 작가 클릭 시 해당 작가의 상세프로필 페이지 END =========================

	
	

	
	// ============================== 회원가입 ==============================
	
	/**
	 * 회원가입: 일반회원(B)일 경우 member_tb에 insert
	 * @author 정소윤
	 * @param 회원정보VO
	 * @return insert into member_tb 쿼리문 실행
	 */
	public static int insertMember(MemberVo m) {
//		System.out.println("3. 일반회원가입 Manager 작동: "+m);
		
		SqlSession session = factory.openSession();
		int re = session.insert("member.insertMember", m);
		session.commit();
		session.close();
		return re;
	}//insertMember
	
	
	/**
	 * 회원가입: 작가회원(S)일 경우 member_tb과 artist_info_tb에 동시에 insert
	 * @author 정소윤
	 * @param 회원정보VO + 작가정보VO
	 * @return insert into member_tb and artist_info_tb
	 */
	public static int insertArtistInfo(ArtistInfoVo a) {
//		System.out.println("3. 작가회원가입 Manager 작동: "+a);
		
		SqlSession session = factory.openSession();
		int re = session.insert("artistInfo.insertArtistInfo", a);
		session.commit();
		session.close();
		return re;
	}//insertArtistInfo
	
	// ============================== 회원가입 END ==============================

	
	
	// ============================== 로그인 ==============================
	
	/**
	 * 로그인 후 세션유지용
	 * @author 정소윤
	 * @param memId 로그인한 회원의 id
	 * @return 로그인한 회원의 정보
	 */
	public static MemberVo selectMember(String memId) {
//		System.out.println("3. 로그인 Manager 작동: memId "+memId);
//		System.out.println("==============================");
		
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.selectMember", memId);
		session.close();
		return m;
	}

	// ============================== 로그인 END ==============================
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 정소윤] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	


	
	
	
	
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 김현규] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//핫딜 조건별 조회1, 찜 횟수가 많은 작품	// by.현규
	public static List<AuctionVo> listHot_1(){
		List<AuctionVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("auction.listHot_1"); 
		session.close();
		return list;
	}//hotArtsInfo
	
	//조건1 순위 1씩 증가
	public static int updateRank1(AuctionVo a) {
		SqlSession session = factory.openSession();
		int re = session.update("auction.updateRank1",a);
		session.commit();
		session.close();
		return re;
	}//updateRank1
	
	//핫딜 조건별 조회2, 입찰 횟수가 가장 높은 작품	// by.현규
	public static List<AuctionVo> listHot_2() {
		List<AuctionVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("auction.listHot_2"); 
		session.close();
		return list;
	}
	
	//조건2 순위 1씩 증가
	public static int updateRank2(AuctionVo a) {
		SqlSession session = factory.openSession();
		int re = session.update("auction.updateRank2",a);
		session.commit();
		session.close();
		return re;
	}//updateRank2
	
	//핫딜 조건별 조회3, 마감 임박 작품	// by.현규
	public static List<AuctionVo> listHot_3() {
		List<AuctionVo> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("auction.listHot_3"); 
		session.close();
		return list;
	}
	
	//조건2 순위 1씩 증가
	public static int updateRank3(AuctionVo a) {
		SqlSession session = factory.openSession();
		int re = session.update("auction.updateRank3",a);
		session.commit();
		session.close();
		return re;
	}//updateRank3
	
	//결제창 진입시 가져와야하는 정보들	//작품번호, 회원번호를 참조해야한다.
	//회원명, 휴대전화, 주소, 작품이미지, 작품명, 즉시구매가,낙찰가(입찰가),총액	// by.현규
	public static List<PaymentVo> findInfo(int artNo) {
		List<PaymentVo> list =null;
		SqlSession session = factory.openSession();
		list = session.selectList("payment.findInfo",artNo);
		session.close();
		return list;
	}//findAll
	
	
	//경매장 진입시 art_info_tb에서 작품정보를 가져온다.	// by.현규
	public static List<AuctionVo> detailAuction(int artNo) {
		List<AuctionVo> list =null;
		SqlSession session = factory.openSession();
		list = session.selectList("auction.detailAuction",artNo);
		session.close();
		return list;
	}//findAll
	
	
	//경매장에서 입찰가 입력하면 art_info_tb에서 aucBid가 update된다. 	// by.현규
	public static int updateBid(AuctionVo a) {
		SqlSession session = factory.openSession();
		int re = session.update("auction.updateBid",a);
		session.commit();
		session.close();
		return re;
	}//updatePrice
	
	
	//입찰 성공할 때 입찰 횟수 증가	// by.현규
	public static int countBid(int artNo,int memNo) {
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("artNo", artNo);
		map.put("memNo", memNo);
		SqlSession session = factory.openSession();
		int re = session.update("auction.countBid",map);
		session.commit();
		session.close();
		return re;
	}//countBid
	
	
	//결제정보 입력: 결제페이지에서 결제하기 누를 때 payment_tb에 insert   	// by.현규
	public static int insertPayment(PaymentVo p) {
		SqlSession session = factory.openSession();
		int re= session.insert("payment.insertPayment",p);
		session.commit();
		session.close();
		return re;
	}//insertPayment

	
	//작품 판매 상태를 'sold'로 바꿔준다.   // by.현규
	public static int updateSold(PaymentVo p) {
		SqlSession session =factory.openSession();
		int re= session.update("payment.updateSold",p);
		session.commit();
		session.close();
		return re;
	}

	//작품 판매 상태를 'sale'로 바꿔준다.   // by.현규
	public static int updateSale(PaymentVo p) {
		SqlSession session =factory.openSession();
		int re= session.update("payment.updateSale",p);
		session.commit();
		session.close();
		return re;
	}
	
	//최고입찰자 초기화  // by.현규
	public static int resetTop(PaymentVo p) {
		SqlSession session =factory.openSession();
		int re= session.update("payment.resetTop",p);
		session.commit();
		session.close();
		return re;
	}
	
	//경매장에서 작품 남은시간 보여주기     // by.현규
	public static AuctionVo remainTime(int artNo) {
		SqlSession session = factory.openSession();
		AuctionVo av = session.selectOne("auction.remainTime",artNo);
		session.close();
		return av;
	}

	//art_point_tb에 포인트 업데이트		// by.현규
	public static int updatePoint(PaymentVo p) {
		SqlSession session = factory.openSession();
		int re = session.update("payment.updatePoint",p);
		session.commit();
		session.close();
		return re;
	}//updatePoint


	//art_sell_tb에서 남은시간을 5초로 바꿔준다.		// by.현규
	public static int remainFive(AuctionVo a) {
		SqlSession session =factory.openSession();
		int re= session.update("auction.remainFive",a);
		session.commit();
		session.close();
		return re;
	}
	
	//남은시간 +5시간 추가(결제취소용)
	public static int upTimeFive(AuctionVo a) {
		SqlSession session =factory.openSession();
		int re= session.update("payment.upTimeFive",a);
		session.commit();
		session.close();
		return re;
	}
	
	//낙찰완료된 작품에 대한 결제대기 목록
	public static List<AuctionVo> payWait(int memNo) {
		List<AuctionVo> list =null;
		SqlSession session = factory.openSession();
		list = session.selectList("payment.payWait",memNo);
		session.close();
		return list;
	}
	
	//로그인한 회원정보 가져오기 // by.현규
	public static PaymentVo getMember(int memNo) {
		SqlSession session = factory.openSession();
		PaymentVo pv = session.selectOne("payment.getMember",memNo);
		session.close();
		return pv;
	}
	

// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 김현규] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	
	
	
	
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 최은혜] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	//찜목록에 insert (userNo:로그인한 회원의 memNo / artNo:찜하고싶은 작품의 작품번호)
	public static int insertWishList(int userNo, int artNo) {
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("userNo", userNo);
		map.put("artNo", artNo);
		
		SqlSession session = factory.openSession();
		int re = session.insert("artinfo.insertWishList",map);
		session.commit();
		session.close();
		
		return re;
	}
	
	//찜목록에서 delete (userNo:로그인한 회원의 memNo / artNo:찜하고싶은 작품의 작품번호)
	public static int deleteWishList(int userNo, int artNo) {
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("userNo", userNo);
		map.put("artNo", artNo);
		
		SqlSession session = factory.openSession(true); //자동 커밋하기
		int re = session.insert("artinfo.deleteWishList",map);
		session.close();
		
		return re;
	}
	 
	
	//작품 목록 전체 읽어오기 (페이징, 조건검색을 위한 테마,사이즈,가격 읽어오기)
	public static List<ArtInfoVo> listArt(int start, int end, String responseTheme, int sizeRange1,int sizeRange2 ,int responsePrice) { 
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("responseTheme", responseTheme);
		map.put("sizeRange1", sizeRange1);
		map.put("sizeRange2", sizeRange2);
		map.put("responsePrice", responsePrice);
		
		
		SqlSession session = factory.openSession();
		List<ArtInfoVo> list = null;
		list = session.selectList("artinfo.selectAll",map); //mapper.xml
		session.close();
		/* System.out.println("Manager : "+list); */
		System.out.println("Manager의 listArt작동함");
		
		return list;
	}
	
	//페이징처리를 위해 총 작품 수 읽어오기 ( 조건검색에서도 페이징 처리를 위한 테마,사이즈,가격)
	public static int totArt(String responseTheme, int sizeRange1,int sizeRange2,int responsePrice) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("responseTheme", responseTheme);
		map.put("sizeRange1", sizeRange1);
		map.put("sizeRange2", sizeRange2);
		map.put("responsePrice", responsePrice);
		
		SqlSession session = factory.openSession();
		int total = session.selectOne("artinfo.totArt",map);
		//System.out.println("Manager의 map : " + map);
		session.close();
		
		return total;
	}
	
	
	//작품 상세보기 (memNo:해당 작품의 작가의 memNo / artNo:상세보기 할 작품의 artNo)
	public static ArtInfoVo detailArt(int memNo, int artNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("memNo", memNo);
		map.put("artNo", artNo);
		
		SqlSession session = factory.openSession();
		ArtInfoVo a = session.selectOne("artinfo.detail",map);
		session.close();
		return  a;
	}
	
	//작품 상세보기 - 하단에 해시태그가 같은 다른 작품 보기
	public static List<ArtInfoVo> detailArtHash(String tag) {
		SqlSession session = factory.openSession();
		
		List<ArtInfoVo> list = session.selectList("artinfo.detailArtHash",tag);
		session.close();
		
		return list;
	}
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 최은혜] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	
	
	
	
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 서현익] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	//by현익 / 기본 배송정보 출력  / 210319
	public static DeliveryVo defaultDelivery(int memNo) {
		SqlSession session = factory.openSession(); //세션얻어오기
		System.out.println(" DBManager-defaultDelivery 작동");
		System.out.println(" DeliveryMapper -> SQL문 실행");
		DeliveryVo dv = session.selectOne("delivery.default", memNo);
		session.close();
		return dv;
	}
	//by현익 / 전체 배송정보 목록 출력 / 210319
	public static List<DeliveryVo> listDelivery(int memNo){
		SqlSession session = factory.openSession(); //세션얻어오기
		List<DeliveryVo> list = session.selectList("delivery.findAll", memNo);
		System.out.println(" DBManager-listDelivery 작동");
		System.out.println(" DeliveryMapper -> SQL문 실행");
		session.close();
		return list;
	}
	//by현익 / 신규 배송정보 등록 / 210319
	public static int insertDelivery(DeliveryVo dv) {
		SqlSession session = factory.openSession(); //세션얻어오기
		int re = session.insert("delivery.insert", dv);
		System.out.println("4. DBManager-insert 작동");
		System.out.println("5. DeliveryMapper -> SQL문 실행");
		session.commit();
		return re;
	}
	//by현익 / 목록에서 배송정보 삭제 / 210319
	public static int deleteDelivery(int no) {
		SqlSession session = factory.openSession(); //세션얻어오기
		int re = session.delete("delivery.delete", no);
		System.out.println("4. DBManager-delete 작동");
		System.out.println("5. DeliveryMapper -> SQL문 실행");
		session.commit();
		return re;
	}//delete
	//by현익 / 기본 배송정보로 변경 / 210319
	public static int changeDefaultDelivery(int memNo, int delNo) {
		SqlSession session = factory.openSession(); //세션얻어오기
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("memNo", memNo);
		map.put("delNo", delNo);
		int re = session.update("delivery.update", map);
		System.out.println("4. DBManager-update 작동");
		System.out.println("5. DeliveryMapper -> SQL문 실행");
		session.commit();
		return re;
	}//delete
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		//by현익 / 문의 내역 목록 출력 / 210319
		public static List<ClientComplainVo> listComplain(int memNo){
			SqlSession session = factory.openSession(); //세션얻어오기
			List<ClientComplainVo> list = session.selectList("complain.findAll", memNo);
			System.out.println("4. DBManager-listComplain 작동");
			System.out.println("5. ComplainMapper -> SQL문 실행");
			session.close();
			return list;
		}
		//by 현익 / 문의 등록 / 210321
		public static int insertComplain(ClientComplainVo cv) {
			SqlSession session = factory.openSession(); //세션얻어오기
			int re = session.insert("complain.insert", cv);
			System.out.println("4. DBManager-insert 작동");
			System.out.println("5. ComplainMapper -> SQL문 실행");
			session.commit();
			return re;
		}
		//by 현익 / 문의내용 삭제 / 210321
		public static int deleteComplain(int comNo) {
			SqlSession session = factory.openSession(); //세션얻어오기
			int re = session.delete("complain.delete", comNo);
			System.out.println("6. DBManager-delete 작동");
			System.out.println("7. DeliveryMapper -> SQL문 실행");
			session.commit();
			return re;
		}
		
		public static ClientComplainVo findOneComplain(int comNo) {
			SqlSession session = factory.openSession();
			ClientComplainVo vo = session.selectOne("complain.findOne", comNo);
			System.out.println("3. manager의 findOneComplain 동작함");
			session.close();
			return vo;
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		//by 현익 / 회원정보 출력 / 210322
		public static List<ChangeInfoVo> detailMemberInfo(int memNo) {
			SqlSession session = factory.openSession(); //세션얻어오기
			List<ChangeInfoVo> list = session.selectList("changeInfo.findAll", memNo);
			System.out.println("4. DBManager-detailMemberInfo 작동");
			System.out.println("5. ChangeInfoMapper -> SQL문 실행");
			session.close();
			return list;
		}
		//by 현익 / 기본 회원정보 변경 / 210322
		public static int updateMemberInfo(ChangeInfoVo cv) {
			SqlSession session = factory.openSession(); //세션얻어오기
			int re = session.update("changeInfo.updateMember", cv);
			System.out.println("4. DBManager-updateMember 작동");
			System.out.println("5. ChangeInfoMapper -> SQL문 실행");
			session.commit();
			return re;
		}
		//by 현익 / 작가 회원정보 변경 / 210322
		public static int updateArtistInfo(ChangeInfoVo cv) {
			SqlSession session = factory.openSession(); //세션얻어오기
			int re = session.update("changeInfo.updateArtist", cv);
			System.out.println("4. DBManager-updateArtist 작동");
			System.out.println("5. ChangeInfoMapper -> SQL문 실행");
			session.commit();
			return re;
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		//by 현익 / 구매목록 출력 / 210323
		public static List<PurchaseListVo> listPurchase(int memNo) {
			SqlSession session = factory.openSession(); //세션얻어오기
			List<PurchaseListVo> list = session.selectList("purchase.findAll", memNo);
			System.out.println("4. DBManager-listPurchase 작동");
			System.out.println("5. PurchaseListMapper -> SQL문 실행");
			session.close();
			return list;
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		//by 현익 / 찜목록 출력 / 210323
		public static List<WishListVo> listWish(int memNo) {
			SqlSession session = factory.openSession(); //세션얻어오기
			List<WishListVo> list = session.selectList("wish.findAll", memNo);
			System.out.println("4. DBManager-listWish 작동");
			System.out.println("5. WishListMapper -> SQL문 실행");
			session.close();
			return list;
		}

		//by 현익 / floatingbar url 설정에 필요한 artElement 찾기 / 210402
		public static ArtInfoVo findArtElements(String artPic) {
			SqlSession session = factory.openSession(); //세션얻어오기
			ArtInfoVo vo = session.selectOne("wish.findOne", artPic);
			System.out.println("4. DBManager-findArtElements 작동");
			System.out.println("5. WishListMapper -> SQL문 실행");
			session.close();
			return vo;
		}
  
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 서현익] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	
	
	
	
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 남혜진] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		/**
		 * 남혜진_작품정보 art_info_tb 에 insert 하는 일
		 * @param vo 테이블에 담을 작품정보
		 * @return insert 성공여부
		 */
		public static int insertArtInfo(ArtInfoVo vo) {
			SqlSession session = factory.openSession();
			int re = session.insert("artSell.insertArtInfo", vo);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_특정 회원의 작품정보 art_info_tb 에서 
		 * 그 회원의 art_sell_check_tb 에 레코드가 존재하는
		 * 값만 조회하는 일 
		 * @param end 
		 * @param start 
		 * 
		 * @param memNo 회원번호
		 * @return 회원번호에 맞는 회원의 판매대기중인 작품의 정보
		 */
		public static List<ArtInfoVo> findSellCheckList(int memNo, int start, int end) {
			System.out.println("3. ");
			SqlSession session = factory.openSession();
			List<ArtInfoVo> list = null;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("memNo", memNo);
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("artSell.findSellCheckList", map);
			session.close();
			return list;
		}
		
		/**
		 * 남혜진_작품정보 수정
		 * @param a 수정할 작품 정보가 담긴 vo
		 * @return
		 */
		public static int updateArtInfo(ArtInfoVo a) {
			SqlSession session = factory.openSession();
			int re = session.update("artSell.updateArtInfo", a);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_작품판매하기 : ART_SELL_TB 에 레코드 추가
		 * @param artNo 작품번호
		 * @param memNo 세션유지하여 받아온 회원번호
		 * @return 성공여부
		 */
		public static int insertArtSell(int artNo, int memNo) {
			SqlSession session = factory.openSession();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("artNo", artNo);
			map.put("memNo", memNo);
			int re = session.insert("artSell.insertArtSell", map);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_작품판매하기 : ART_SELL_CHECK_TB 에서 레코드 삭제 
		 * @param artNo 작품번호
		 * @return 성공여부
		 */
		public static int deleteArtSellCheck(int artNo) {
			SqlSession session = factory.openSession();
			int re = session.delete("artSell.deleteArtSellCheck", artNo);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_작품정보 삭제 : ART_INFO_TB 에서 레코드 삭제
		 * @param artNo 작품번호
		 * @return 성공여부
		 */
		public static int deleteArtInfo(int artNo) {
			SqlSession session = factory.openSession();
			int re = session.delete("artSell.deleteArtInfo", artNo);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_판매중인 작품 목록 보기 : ART_SELL_TB 에서 해당 회원의 레코드 중,
		 * ART_SELL 컬럼의 값이 'sale'인 작품
		 * @param memNo 세션유지된 회원번호
		 * @return 작품정보가 담긴 List
		 */
		public static List<ArtInfoVo> findArtSell(int memNo) {
			SqlSession session = factory.openSession();
			List<ArtInfoVo> list = session.selectList("artSell.findArtSellList", memNo);
			session.close();
			return list;
		}

		//바로 아래의 method와 합쳐져서 실행된다
		/**
		 * 남혜진_판매중인 작품 판매 취소하기 : ART_SELL_TB 에 담긴 레코드 삭제
		 * @param artNo 작품번호
		 * @return 성공여부
		 */
		public static int cancleArtSell(int artNo) {
			SqlSession session = factory.openSession();
			int re = session.delete("artSell.deleteArtSell", artNo);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_판매중인 작품 판매 취소하기 : ART_SELL_CHECK_TB 에 다시 레코드가 삽입된다
		 * @param artNo 작품번호
		 * @param memNo 세션유지된 회원번호
		 * @return 성공여부
		 */
		public static int insertArtSellCheck(int artNo, int memNo) {
			SqlSession session = factory.openSession();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("artNo", artNo);
			map.put("memNo", memNo);
			System.out.println("DBManager insertArtSellCheck의 memNo: " + memNo);
			int re = session.insert("artSell.insertArtSellCheck", map);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 남혜진_작가 회원의 포인트 현황 조회
		 * @param memNo 세션유지된 회원번호
		 * @return 해당 회원의 포인트 잔액을 정수로 반환
		 */
		public static int findArtistPoint(int memNo) {
			SqlSession session = factory.openSession();
			int point = session.selectOne("artSell.findArtistPoint", memNo);
			//int point = p.getPayPoint();
			//System.out.println("5 dbM point: " + point);
			session.close();
			return point;
		}
		
		/**
		 * 남혜진_작가이름 조회
		 * @param memNo 세션유지된 회원번호
		 * @return 회원번호를 통해 MEMBER_TB에 접근, 작가이름정보를 문자열로 반환한다
		 */
		public static String findArtistName(int memNo) {
			SqlSession session = factory.openSession();
			String artistName = session.selectOne("artSell.findArtistName", memNo);
			session.close();
			return artistName;
		}
		
		/**
		 * 남혜진_포인트 환전하기
		 * @param resultPoint 환전할 포인트(계산된)
		 * @param memNo 회원번호
		 * @return
		 */
		public static int updateArtistPoint(int resultPoint, int memNo) {
			System.out.println("3 dbmanager");
			SqlSession session = factory.openSession();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("resultPoint", resultPoint);
			map.put("memNo", memNo);
			int re = session.update("artSell.updateArtistPoint", map);
			session.commit();
			session.close();
			return re;
		}
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 남혜진] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
	
	
	
	
// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< [by 신지영] Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
		/**
		 * 전체 레코드의 수를 반환하는 메소드
		 * @param keyword: 검색 키워드
		 * @param searchField: 검색 키워드의 해당 컬럼 
		 */
		public static int getTotalRecord(String keyword, String searchField) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("keyword", keyword);
			map.put("searchField", searchField);
			SqlSession session = factory.openSession();
			int count = session.selectOne("review.totalRecord", map);
			session.close();
			return count;
		}

		/**
		 * 모든 레코드를 List로 반환하는 메소드
		 * @param keyword: 검색 키워드
		 * @param searchField: 검색 키워드의 해당 컬럼 
		 * @param orderField: 정렬 기준 
		 * @return 검색된 모든 자료의 list 
		 */
		public static List<ReviewVo> findAll(String keyword, String searchField, String orderField) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword);
			map.put("searchField", searchField);
			map.put("orderField", orderField);

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findAll", map);
			session.close();
			return list;
		}

		/**
		 * 이용후기>후기검색 메뉴에서 검색된 후기들을 list로 반환하는 메소드 
		 * @param keyword: 검색 키워드 
		 * @param searchField: 검색 키워드의 해당 컬럼
		 * @param orderField: 정렬 기준 
		 * @return: 검색 키워드를 기준으로 검색된 모든 자료의 list 
		 */
		public static List<ReviewVo> findSearchAll( String keyword, String searchField,String orderField) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword);
			map.put("searchField", searchField);
			map.put("orderField", orderField);

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findSearchAll", map);
			session.close();
			return list;
		}

		/**
		 * 이용후기>테마별후기 메뉴에서 테마에 따라 구분되는 후기들을 List로 반환하는 메소드
		 * @param keyword: 검색 키워드 (테마의 키워드에 해당)
		 * @param orderField: 정렬 기준(테마)
		 * @return: 검색 키워드를 기준으로 검색된 모든 자료의 list 
		 */
		public static List<ReviewVo> findThemeAll( String keyword, String orderField) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword);
			map.put("orderField", orderField);

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findThemeAll", map);
			session.close();
			return list;
		}

		/**
		 * 이용후기>태그별후기 메뉴에서 태그에 따라 구분되는 후기들을 List로 반환하는 메소드 
		 * @param keyword: 검색 키워드(태그의 키워드에 해당)
		 * @param orderField: 정렬 기준(태그)
		 * @return: 검색 키워드를 기준으로 검색된 모든 자료의 list 
		 */
		public static List<ReviewVo> findTagAll(String keyword, String orderField) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword);
			map.put("orderField", orderField);

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findTagAll", map);
			session.close();
			return list;
		}

		/**
		 * 이용후기>실시간후기 메뉴에 띄울 후기들을 list로 반환하는 메소드 
		 * @return: 실행 되기 전까지 작성되었던 모든 후기 list 
		 */
		public static List<ReviewVo> findRealTimeAll() {

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findRealTimeAll");
			session.close();

			return list;
		}

		/**
		 * 이용후기>실시간후기 메뉴 중 좋아요 상위 3개의 후기를 list로 반환하는 메소드
		 * @return: 실행 되기 전까지 작성되었던 모든 후기들 중 좋아요 상위 3개 list 
		 */
		public static List<ReviewVo> findRealTimeMost3LikeAll() {

			SqlSession session = factory.openSession();
			List<ReviewVo> list = session.selectList("review.findRealTimeMost3LikeAll");
			session.close();
			return list;
		}

		/**
		 * 이용후기작성을 위한 메소드 
		 * @param r: 작성자가 작성한 정보를 ReviewVo 형태로 담는다 
		 */
		public static int insert(ReviewVo r) {
			SqlSession session = factory.openSession();
			int re = session.insert("review.insert", r);
			session.commit();
			session.close();
			return re;
		}

		/**
		 * 사용자가 후기에 좋아요를 눌렀을 시 DB에 좋아요+1를 해주는 메소드 
		 * @param reviewNo: 리뷰넘버(pk)를 패러미터로 받아 디비에 접근하여 해당 튜플의 좋아요수를 올려준다 
		 */
		public static int insertLikeReview(int reviewNo) {
			SqlSession session = factory.openSession();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("reviewNo", reviewNo);
			int re = session.update("review.insertLike", map);
			session.commit();
			session.close();
			return re;
		}
		
		/**
		 * 사용자가 후기에 좋아요를 또 한 번 눌렀을 시 DB에 좋아요-1를 해주는 메소드 
		 * @param reviewNo: 리뷰넘버(pk)를 패러미터로 받아 디비에 접근하여 해당 튜플의 좋아요수를 내려준다 
		 */
		public static int deleteLikeReview(int reviewNo) {
			SqlSession session = factory.openSession();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("reviewNo", reviewNo);
			int re = session.update("review.deleteLike", map);
			session.commit();
			session.close();
			return re;
		}

		public static int getTotalArtSellCheck(int memNo) {
			System.out.println("작품목록 DBManager 작동");
			SqlSession session = factory.openSession();
			int count = session.selectOne("artSell.totalRecord", memNo);
			session.close();
			return count;
		}	
		
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> [by 신지영] End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
}//class
