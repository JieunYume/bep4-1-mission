package com.back.shared.member.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MemberApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/api/v1/member")
            .build();

    public String getRandomSecureTip() {
        return restClient.get()
                .uri("/members/randomSecureTip")
                .retrieve() // 요청을 원격 서버로 전송하고 응답을 받아온다.
                .body(String.class); // 데이터를 문자열 객체로 파싱한다.
    }
}
