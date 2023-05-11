package com.gyojincompany.home.dao;

import java.util.List;

import com.gyojincompany.home.dto.BoardDto;
import com.gyojincompany.home.dto.MemberDto;

public interface IDao {
	
	//회원관리
	public int joinDao(String mid, String mpw, String mname, String memail);//회원 가입
	public int checkIdDao(String mid);//가입하려는 id의 존재여부 체크
	public int checkIdPwDao(String mid, String mpw);//아이디와 비밀번호의 일치여부 체크
	public MemberDto getMemberInfo(String mid);//아이디로 조회하여 회원 정보 모두 가져오기
	public int modifyMemberDao(String mid, String mpw, String mname, String memail);//회원정보 수정
	
	//게시판관리
	public void questionWriteDao(String bid, String bname, String bcontent, String bemail);//질문하기 insert
	public List<BoardDto> questionListDao();//모든 글 목록 가져오기
}
