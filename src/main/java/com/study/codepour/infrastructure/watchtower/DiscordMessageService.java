package com.study.codepour.infrastructure.watchtower;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Discord WatchTower 연동
 */
@Service
@Slf4j
public class DiscordMessageService {
	
	@Value("${infra.discord}")
	private String webHookUrl;
	
	public boolean sendMsg(String msg){

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json; utf-8");
            HttpEntity<Message> messageEntity = new HttpEntity<>(new Message(msg), httpHeaders);

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> response = template.exchange(
            		webHookUrl,
                    HttpMethod.POST,
                    messageEntity,
                    String.class
            );

            if(response.getStatusCode().value() != HttpStatus.NO_CONTENT.value()){
                log.error("메시지 전송 이후 에러 발생");
                return false;
            }

        } catch (Exception e) {
            log.error("에러 발생 :: " + e);
            return false;
        }

        return true;
    }
	
	/**
	 * 데이터 클래스 명시(Message)
	 */
	public static record Message(String content) {
		
	}
}


