package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.LoginService;
import com.teamProject.ezmeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    LoginService loginService;

    @GetMapping("/signup")
    public String signUp() {    // 회원가입 버튼 클릭시 signup.jsp 화면 보여준다
        return "signup";
    }


    @PostMapping("/checkIdDuplicate")
    @ResponseBody
    public Map<String, Boolean> checkIdDuplicate(@RequestBody Map<String, String> request) {    // id중복체크 post JSON
        String id = request.get("id");  // input에 입력된 id의 value값 받아옴
        try {
            boolean isDuplicate = memberService.checkIdDuplicate(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("isDuplicate", isDuplicate);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signup/success")
    public String signupSuccess(MemberDto memberDto, Model model, RedirectAttributes rattr, HttpServletRequest req) {
        // 1. 유효성 검사
//        if (!isValid(memberDto)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.","utf-8");
//
//            m.addAttribute("msg",msg);
//            return "redirect:/member/signup";
////            return "redirect:/member/signup?msg="+msg;
//        }
        // 2. DB에 신규회원 정보를 저장
        try {
            //
            int rowCnt = memberService.signup(memberDto);    // insert

            if (rowCnt!=1)  // insert가 되지않았을 때 예외발생을 해서 signup페이지로 가도록 함
                throw new Exception("Signup failed");

            rattr.addFlashAttribute("msg","Signup_OK");
            // DB에 회원정보가 저장이 되고 동시에 로그인까지 되게 하려면,
            // session에 가입한 로그인 정보를 넣어줘야함.
//            System.out.println("lgin_id = " + memberDto.getLgin_id());
//            System.out.println("lgin_pw = " + memberDto.getLgin_pw());
            String lgin_id = memberDto.getLgin_id();
            String lgin_pw = memberDto.getLgin_pw();
            Long memberId = loginService.getLogin(lgin_id,lgin_pw);
            System.out.println("memberId = " + memberId);
            HttpSession session = req.getSession();
            session.setAttribute("memberId",memberId);
            model.addAttribute("checkSignupSuccess", "signup success!!");

            return "signupSuccess"; // insert 성공시에 signupSuccess 페이지로 감
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(memberDto);
            model.addAttribute("msg","Signup_ERR");

            return "signup";    // 예외처리 발생시 signup(회원가입)페이지로 돌아감
        }
    }

//    private boolean isValid(MemberDto memberDto) {
//        return false;
//    }



}
