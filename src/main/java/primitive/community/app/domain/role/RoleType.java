package primitive.community.app.domain.role;
public enum RoleType {
    USER,      // 일반 사용자
    ADMIN,     // 관리자
    GUEST;     // 게스트

    @Override
    public String toString() {
        return name();
    }
}
