package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import com.teamProject.ezmeal.service.PointTransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PointController {

    private  final PointTransactionHistoryService pointTransactionHistoryService;

    @GetMapping("/point")
    @ResponseBody
    public Map<String, Integer> getUsablePoint(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        int point = pointTransactionHistoryService.getUsablePoint(memberId);
        Map<String , Integer> pointMap = new HashMap<>();
        pointMap.put("point", point);
        return pointMap;
    }

    @GetMapping("/mypage/point")
    public String getPointList(Model model, HttpSession session) {
        Long memberId =  (Long) session.getAttribute("memberId");
        List<PointTransactionHistoryDto> pointList = pointTransactionHistoryService.getPointList(memberId);

        // DateTimeFormatter를 사용하여 LocalDateTime을 "yy.MM.dd"  형식의 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd");
        for (PointTransactionHistoryDto point : pointList) {
            String formatDate = point.getTrjs_dtm().format(formatter);
            point.setFormattedTrjsDtm(formatDate);
        }

        // 현재 남은 적립금을 보여주기 위한 로직
        int point = pointTransactionHistoryService.getUsablePoint(memberId);

        model.addAttribute("pointList", pointList);
        model.addAttribute("point",point);
        return "point";
    }



}
