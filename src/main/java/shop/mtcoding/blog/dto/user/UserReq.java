package shop.mtcoding.blog.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @Setter
    @Getter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String email;
    }

    // 따로 클래스를 만들어도 되지만 그렇게 하면 나중에 파일이 너무 많아짐
    @Getter
    @Setter
    public static class loginReqDto {
        private String username;
        private String password;
    }
}
