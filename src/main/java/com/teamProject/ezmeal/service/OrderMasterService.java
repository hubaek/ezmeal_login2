package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderMasterDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderMasterService {
    private final OrderMasterDao orderMasterDao;

    public Long getOrderId(Long memberId){
        return orderMasterDao.selectOrderId(memberId);
    }
}
