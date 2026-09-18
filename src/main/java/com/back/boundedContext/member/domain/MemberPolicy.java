package com.back.boundedContext.member.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class MemberPolicy { // 정책별로 모아둔다.
    private static final int PASSWORD_CHANGE_DAYS = 90;

    public Duration getNeedToChangePasswordPeriod() {
        return Duration.ofDays(PASSWORD_CHANGE_DAYS);
    }

    public int getNeedToChangePasswordDays() {
        return PASSWORD_CHANGE_DAYS;
    }

    public boolean isNeedToChangePassword(LocalDateTime lastChangeDate){
        if (lastChangeDate == null) return true;

        return lastChangeDate.plusDays(PASSWORD_CHANGE_DAYS)
                .isBefore(LocalDateTime.now()); // 비밀번호는 90일 이후에 변경할 수 있다.
    }
}
