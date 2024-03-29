package uz.jl.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.enums.AuthRole;
import uz.jl.vo.GenericVO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor

@Builder
public class AuthUserVO extends GenericVO {
    private String username;
    private String email;
    private AuthRole role;
    private Timestamp createdAt;

    public AuthUserVO(String username, String email, AuthRole role, Timestamp createdAt) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }


    public AuthUserVO(String username, String email, AuthRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
