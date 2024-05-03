package com.study.codepour.infrastructure.watchtower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscordMessageTests {
	
	@Autowired
    private DiscordMessageService msgService;

    @Test
    public void 디스코드_메시지_테스트() throws Exception{
        // given
        boolean result = this.msgService.sendMsg("개발 중인 서버에서 에러 메시지를 이걸로 보냅니다.");
        
        try {
        	throw new Exception("예외 발생!");
        } catch(Exception e) {
        	this.msgService.sendMsg("[Server Error] 에러 발생 : " + e);
        }
        
        // then
        Assertions.assertEquals(result,true);
    }

}
