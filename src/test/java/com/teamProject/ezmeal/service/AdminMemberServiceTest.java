package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminMemberServiceTest {
    
    @Autowired
    AdminMemberService adminMemberService;
    
    @Test
    public void getLoginAdminInfo() {
        AdminMemberDto loginAdminInfo = adminMemberService.getLoginAdminInfo(18);
        System.out.println("loginAdminInfo = " + loginAdminInfo);
    }
}