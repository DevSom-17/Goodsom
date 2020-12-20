package com.example.goodsom.controller.user;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class SmsController {
	@GetMapping("/check/sendSMS")
    public @ResponseBody
    String sendSMS(String phoneNumber) {

		String api_key = "NCSTWKIAKZQ7GXEJ";
        String api_secret = "NBCEM4FA8Y0NGWORJJ3JLPZA4XDQZMKD";
        Message coolsms = new Message(api_key, api_secret);
        
//		인증 번호 생성
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
        	String ran = Integer.toString(rand.nextInt(10));
        	numStr += ran;
        }

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01072264789");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[Web발신]\n[Goodsom] 인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        return numStr;
    }
}
