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

import com.art.dao.ClientComplainDao;
import com.art.util.MyPageUtil;
import com.art.vo.ClientComplainVo;
import com.google.gson.Gson;

//by현익 / 배송지관련 컨트롤러 / 210319
@RestController
public class ClientComplainController {

	
	  @Autowired private ClientComplainDao dao; 
	  public void setDao(ClientComplainDao dao) {
		  this.dao = dao; }

	
	
	@RequestMapping("/listComplain.do")
	public String listComplain(int memNo) {
		//System.out.println("_____________________________________________");
		//System.out.println("1. Controller-listComplain 실행");
		String r = "";
		List<ClientComplainVo> list = dao.findAll(memNo);
		Gson gson = new Gson();
		r = gson.toJson(list);
		return r;
	}
	
	@RequestMapping("/insertComplain.do")
	public String insertComplain(ClientComplainVo cv,
											HttpServletRequest request) {
		//System.out.println("_____________________________________________");
		//System.out.println("1. Controller-insertComplain 실행");
		String path = request.getRealPath("complain");
		//System.out.println("path : "+path);
		MultipartFile uploadFile = cv.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		if (fname != null && !fname.equals("")) {//파일도 업로드 했는지?
			//파일명 중복시 새로운 이름으로 변경해준다.
			fname = MyPageUtil.changeFileName(fname, path);
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				System.err.println("예외발생 : "+e.getMessage());
			}//end catch
		}//end if
		cv.setComImg(fname);
		String r = "등록성공";
		int re = dao.insert(cv);
		if (re !=1) {
			r="등록실패";
		}//end if
		return r;
	}
	
	@RequestMapping("/deleteComplain.do")
	public String deleteComplain(int comNo,
											HttpServletRequest request) {
		//System.out.println("_____________________________________________");
		//System.out.println("1. Controller-deleteComplain 실행");
		String r = "no";
		ClientComplainVo cv = dao.findOne(comNo);
		String oldFname = cv.getComImg();
		//System.out.println("oldFname"+oldFname);
		int re = dao.deleteComplain(comNo);
		if (re ==1) {
			String path = request.getRealPath("complain");
			//System.out.println("path"+path);
			File file = new File(path+"/"+oldFname);
			file.delete();
			r="ok";
		}//end if
		return r;
	}
	
	
}//Controller
