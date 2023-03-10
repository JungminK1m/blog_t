package shop.mtcoding.blog.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardReq {

    @Setter
    @Getter
    public static class BoardSaveReqDto {
        private String title;
        private String content;

    }

    @Setter
    @Getter
    public static class BoardUpdateReqDto {
        private int id;
        private String title;
        // private int userId;
        private String content;

    }

}
