package com.gyojincompany.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.home.dao.IDao;

@Controller
public class WebController {	
	
	@Autowired
	private SqlSession sqlSession;	
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/")
	public String index2() {
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/profile")
	public String profile() {
		return "profile";
	}
	
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping(value = "/question")
	public String question() {
		return "question";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		IDao dao = sqlSession.getMapper(IDao.class);
	
		int joinCheck = 0;
		
		int checkId = dao.checkIdDao(mid);//가입하려는 id 존재여부 체크 1이면 이미 존재
		
		if(checkId == 0) {
			joinCheck = dao.joinDao(mid, mpw, mname, memail);
			model.addAttribute("checkId", checkId);
		//joinCheck 값이 1이면 회원가입 성공, 아니면 가입실패
		} else {
			model.addAttribute("checkId", checkId);
		}
		
		if(joinCheck == 1) {
			model.addAttribute("joinFlag", joinCheck);
			model.addAttribute("memberName", mname);
			model.addAttribute("memberId", mid);
		} else {//회원가입실패
			model.addAttribute("joinFlag", joinCheck);
		}
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		int checkIdPwFlag = dao.checkIdPwDao(mid, mpw);
		//1이면 로그인 성공, 0이면 로그인 실패
		
		model.addAttribute("checkIdPwFlag", checkIdPwFlag);
		
		if(checkIdPwFlag == 1) {//로그인 성공 실행
			session.setAttribute("sessionId", mid);			
			
			model.addAttribute("memberDto", dao.getMemberInfo(mid));
		}
		
		return "loginOk";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();//세션 삭제->로그아웃
		
		return "redirect:login";
	}
	
	
	
	
	
}
