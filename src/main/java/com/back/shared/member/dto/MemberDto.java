package com.back.shared.member.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MemberDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username; // NOTE: password 필드는 제외 - 민감 정보이고, post에서 필요 없기 때문에
    private final String nickname;
    private final int activityScore;
}