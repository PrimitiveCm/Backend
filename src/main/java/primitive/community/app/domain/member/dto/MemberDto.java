package primitive.community.app.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
    private String studentNumber; // 학번
    private String userName;      // 이름
    private String password;      // 비밀번호
}