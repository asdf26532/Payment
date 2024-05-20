package com.itbank;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("공지사항 목록 조회 테스트")
    void testGetNoticeList() throws Exception {
        mockMvc.perform(get("/board/notice"))
               .andExpect(status().isOk())
               .andExpect(view().name("board/notice"))
               .andExpect(model().attributeExists("notices"));
    }

    @Test
    @DisplayName("공지사항 상세 조회 테스트")
    void testGetNoticeDetail() throws Exception {
        mockMvc.perform(get("/board/notice_view/{id}", 1))
               .andExpect(status().isOk())
               .andExpect(view().name("board/notice_view"))
               .andExpect(model().attributeExists("notice"))
               .andExpect(model().attributeExists("memberid"));
    }

    @Test
    @DisplayName("장터 목록 조회 테스트")
    void testGetMarketList() throws Exception {
        mockMvc.perform(get("/board/freemarket"))
               .andExpect(status().isOk())
               .andExpect(view().name("board/freemarket"))
               .andExpect(model().attributeExists("freemarkets"));
    }

    @Test
    @DisplayName("장터 상세 조회 테스트")
    void testGetMarketDetail() throws Exception {
        mockMvc.perform(get("/board/freemarket_view/{id}", 1))
               .andExpect(status().isOk())
               .andExpect(view().name("board/freemarket_view"))
               .andExpect(model().attributeExists("freemarket"))
               .andExpect(model().attributeExists("memberid"));
    }

    @Test
    @DisplayName("공지사항 작성 폼 접근 테스트")
    void testGetNoticeWriteForm() throws Exception {
        mockMvc.perform(get("/board/notice_write"))
               .andExpect(status().isOk())
               .andExpect(view().name("board/notice_write"));
    }

    @Test
    @DisplayName("장터 작성 폼 접근 테스트")
    void testGetMarketWriteForm() throws Exception {
        mockMvc.perform(get("/board/freemarket_write"))
               .andExpect(status().isOk())
               .andExpect(view().name("board/freemarket_write"));
    }

}
