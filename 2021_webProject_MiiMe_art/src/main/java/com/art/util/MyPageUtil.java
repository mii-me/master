package com.art.util;
import java.io.File;
//by 현익 / 중복된 파일명을 변환해주는 util 클래스 / 210324
public class MyPageUtil {
	public static String changeFileName(String fname, String path) {
		String newFname = null;
		File dir = new File(path);//디렉토리도 파일로 취급된다.
		//디렉토리에 있는 파일이름들을 모아온다.
		String[] file_list = dir.list();
		boolean flag = false;
		//directory에 있는 파일 이름을 모두 검사해서 
		for (String name : file_list) {
			//만약 그 중 하나가 매개변수 fname과 일치한다면
			if (name.equals(fname)) {
				flag = true;//true를 반환한다.
				break;
			}//end if
		}//end for
		newFname = fname;
		//만약 flag가 true 이면 다른 이름을 정해준다.
		if (flag==true) {
			//원래 파일명에서 확장자를 생각해 그 사이에 넣어줘야 한다.
			String name = newFname.substring(0, newFname.indexOf("."));
			String ext = newFname.substring(newFname.indexOf("."));
			newFname = name+System.currentTimeMillis()+ext;
		}//end if
		return newFname;
	}//getFileName
}//class
