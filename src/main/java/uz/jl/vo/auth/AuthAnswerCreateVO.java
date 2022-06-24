package uz.jl.vo.auth;

import lombok.*;
import uz.jl.domains.auth.AuthAnswers;
import uz.jl.vo.BaseVO;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class AuthAnswerCreateVO extends AuthAnswers implements BaseVO {
    private String variant_A;
    private String variant_B;
    private String variant_D;
    private String answer;
    private AuthTestCreateVO test;



    @Builder(builderMethodName = "childbuilder")
    public AuthAnswerCreateVO(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String variant_A, String variant_B, String variant_D, String answer, String variant_A1, String variant_B1, String variant_D1, String answer1, AuthTestCreateVO test) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted, variant_A, variant_B, variant_D, answer);
        this.variant_A = variant_A1;
        this.variant_B = variant_B1;
        this.variant_D = variant_D1;
        this.answer = answer1;
        this.test = test;
    }

    public AuthTestCreateVO getTest() {
        return test;
    }
}


