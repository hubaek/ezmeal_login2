package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final KakaoUserInfo kakaoUserInfo;

    private final MemberService memberService;

//    @GetMapping("/")경
//    @ResponseBody
//    public String KakaoOauth(@RequestParam("code") String code) {
//        log.info();
//    }

}


