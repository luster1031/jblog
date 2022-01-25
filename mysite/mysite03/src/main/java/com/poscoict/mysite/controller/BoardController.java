package com.poscoict.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String board(@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@RequestParam(value = "kwd2", required = true, defaultValue = "") String kwd2, Model model) {
		Map<String, Object> map = boardService.getContentsList(page, kwd, kwd2);
		model.addAttribute("m", map);
		return "board/list";
	}

	@RequestMapping("/writeform")
	public String writeform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		return "board/write";
	}

	@RequestMapping("/deletepost")
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "") Long no
			, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board";
	}

	@RequestMapping("/write")
	public String write(BoardVo vo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		vo.setUserNo(authUser.getNo());
		boardService.addContents(vo);
		return "redirect:/board";
	}

	@RequestMapping("/view")
	public String viewform(@RequestParam(value = "no", required = true, defaultValue = "") Long no
			, Model model
			, HttpSession session) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}

	@RequestMapping("/updateform")
	public String updateform(@RequestParam(value = "no", required = true, defaultValue = "") Long no,
			HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		if(boardService.getTotalCount() != 0) {
			BoardVo vo = boardService.getContents(no, authUser.getNo());
			model.addAttribute("vo", vo);
		}
		return "board/modify";
	}

	@RequestMapping("/update")
	public String update(@RequestParam(value = "no", required = true, defaultValue = "") Long no, BoardVo vo,
			HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		vo.setNo(no);
		boardService.updateContents(vo);
		return "redirect:/board/view?no=" + no;
	}

	@RequestMapping("/comment")
	public String commnet(BoardVo vo, HttpSession session,
			@RequestParam(value = "g_no", required = true, defaultValue = "") Integer g_no,
			@RequestParam(value = "o_no", required = true, defaultValue = "") Integer o_no,
			@RequestParam(value = "depth", required = true, defaultValue = "") Integer depth) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}

		vo.setUserNo(authUser.getNo());
		vo.setDepth(depth);
		vo.setGroupNo(g_no);
		vo.setOrderNo(o_no);
		boardService.addContents(vo);
		return "board/write";
	}

	@RequestMapping("/commentform")
	public String commentform(@RequestParam(value = "no", required = true, defaultValue = "") Long no, Model model,
			HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "user/login";
		}
		BoardVo vo = boardService.getComment(no);
		vo.setNo(no);
		vo.setUserNo(authUser.getNo());
		model.addAttribute("vo", vo);
		return "board/write";
	}

}
