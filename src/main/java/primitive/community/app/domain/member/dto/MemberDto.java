package primitive.community.app.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class MemberDto {
    private String studentNumber; // 학번
    private String userName;      // 이름
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;      // 비밀번호

    @Override
    public String toString() {
        return "MemberDto{" +
                "studentNumber='" + studentNumber + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}