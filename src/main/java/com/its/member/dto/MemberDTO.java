package com.its.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private int memberAge;
    private String memberMobile;
}
