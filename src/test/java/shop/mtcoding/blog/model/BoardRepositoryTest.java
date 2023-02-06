package shop.mtcoding.blog.model;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAllWithUser_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();

        // when
        List<BoardMainRespDto> boardMainRespDtos = boardRepository.findAllWithUser();
        System.out.println("테스트 : size : " + boardMainRespDtos.size());
        String reponseBody = om.writeValueAsString(boardMainRespDtos);
        System.out.println("테스트 : " + reponseBody);

        // then
    }
}
