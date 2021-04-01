package com.art.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.art.dao.ChangeMemberInfoDao;
import com.art.util.MyPageUtil;
import com.art.vo.ChangeInfoVo;
import com.art.vo.DeliveryVo;
import com.google.gson.Gson;

//by현익 / 회원정보변경 컨트롤러 / 210321
@RestController
public class ChangeMemberInfoController {

	
	  @Autowired private ChangeMemberInfoDao dao; 
	  public void setDao(ChangeMemberInfoDao dao) {
	  this.dao = dao; }

	
	@RequestMapping("/detailMemberInfo.do")
	public String detailMemberInfo(int memNo) {
		System.out.println("_________________________________________");
		System.out.println("1. Controller-detailMemberInfo 실행");
		String r = "";
		List<ChangeInfoVo> list = dao.findAll(memNo);
		Gson gson = new Gson();
		r = gson.toJson(list);
		return r;
	}
	
	@RequestMapping("/updateMemberInfo.do")
	public String updateMemberInfo(ChangeInfoVo cv) {
		System.out.println("_________________________________________");
		System.out.println("1. Controller-updateMemberInfo 실행");
		String r = "ok";
		int re = dao.updateMemberInfo(cv);
		if (re !=1) {
			r="no";
		}//end if
		return r;
	}
	
	@RequestMapping("/updateArtistInfo.do")
	public String updateArtistInfo(ChangeInfoVo cv,
											HttpServletRequest request) {
		System.out.println("_________________________________________");
		System.out.println("1. Controller-updateArtistInfo 실행");
		String oldFname= cv.getArtistPic();
		String path = request.getRealPath("artistPic");
		MultipartFile uploadFile = cv.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		if (fname != null && !fname.equals("")) {
			//파일명 중복시 새로운 이름으로 변경해준다.
			fname = MyPageUtil.changeFileName(fname, path);
			try {
				//파일의 내용을 byte형의 배열에 담아준다.
				byte[] data = uploadFile.getBytes();
				//파일 이름을 알아낸다.
				//경로를 설정해준다.
				FileOutputStream fos = 
							new FileOutputStream(path+"/"+fname);
				//파일을 기록해준다.
				fos.write(data);
				fos.close();
				//Vo에 파일명을 정해준다.
				cv.setArtistPic(fname);
			} catch (IOException e) {
				System.err.println("예외발생 : "+ e.getMessage());
			}//end catch
		}//end if
		String artistPic = null;
		int re = dao.updateArtistInfo(cv);
		if (re ==1 && fname != null && !fname.equals("")) {
			File file = new File(path+"/"+oldFname);
			file.delete();
			artistPic = cv.getArtistPic();
		}//end if
		return artistPic;
	}
	
}//Controller
