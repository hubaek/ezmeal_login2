package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.PointTransactionHistoryDao;
import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointTransactionHistoryService {

    private final PointTransactionHistoryDao pointTransactionHistoryDao;

    // dao에서 포인트 조회 메서드 호출해서 사용할것, 회원번호로 조회, 포인트도 Long
    public int getUsablePoint(Long memberId) {
        return pointTransactionHistoryDao.selectPoint(memberId);
    }

    // 포인트내역을 보여주기 위한, 포인트 내역 테이블 조회
    public List<PointTransactionHistoryDto> getPointList(Long memberId) {
        return pointTransactionHistoryDao.selectPointList(memberId);
    }

    // 회원가입시 포인트 지급을 위한 로직
    public int addPoint(Long memberId) {
        return pointTransactionHistoryDao.insertPoint(memberId);
    }
}
