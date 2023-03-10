package shop.mtcoding.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog.dto.board.BoardResp;
import shop.mtcoding.blog.model.User;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @Test
    public void main_test() throws Exception {
        // give 없음
        // when
        ResultActions resultActions = mvc.perform(
                get("/"));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<BoardResp.BoardMainRespDto> dtos = (List<BoardResp.BoardMainRespDto>) map.get("dtos");
        String model = om.writeValueAsString(dtos);
        System.out.println("테스트 : " + model);
        // then
        resultActions.andExpect(status().isOk());
    }

    @BeforeEach // Test 메서드 실행 직전 마다에 호출됨
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void save_test() throws Exception {
        // given
        String title = "";
        for (int i = 0; i < 99; i++) {
            title += "가";
        }

        String requestBody = "title=" + title + "&content=내용1";
        // when
        ResultActions resultActions = mvc.perform(
                post("/board")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void detail_test() throws Exception {
        // given
        int id = 1;
        // when
        ResultActions resultActions = mvc.perform(
                post("/board/" + id).session(mockSession));

        // then 302가 뜨는지 확인
        resultActions.andExpect(status().is3xxRedirection());

        // 내가 쓴 것
        // given
        // int id = 1;
        // when
        // ResultActions resultActions = mvc.perform(
        // get("/board/id"));
        // Map map = resultActions.andReturn().getModelAndView().getModel();
        // BoardResp.BoardDetailRespDto dto = (BoardResp.BoardDetailRespDto)
        // map.get("dto");
        // String model = om.writeValueAsString(dto);
        // System.out.println("테스트 : " + model);
        // // then
        // resultActions.andExpect(status().isOk());
    }

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;
        // when
        ResultActions resultActions = mvc.perform(
                delete("/board/" + id).session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);
        // then 302가 뜨는지 확인
        resultActions.andExpect(status().isOk());
    }
}
