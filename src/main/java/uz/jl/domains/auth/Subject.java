package uz.jl.domains.auth;/*
  @author "Doniyor Erkinov"
  @since 6/22/2022 5:28 PM (Wednesday)
  trello_VU/IntelliJ IDEA
*/

import jakarta.persistence.*;
import lombok.*;
import uz.jl.domains.Auditable;


@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject extends Auditable {
    @Column (unique = true, nullable = false)
    private String subject_name;










}
