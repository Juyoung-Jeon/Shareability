package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 (php 파일 연동)
    final static private String URL = "http://shareability.dothome.co.kr/Register.php"; // 고정값이므로 final static, 서버 호스팅 주소 입력
    private Map<String, String> map;


    // 생성값 할당을 위해 만들어 준 것이고 실제 쓰이는 건 RegisterActivity
    public RegisterRequest(String userID, String userPassword, String userName, int userBirthday, String userNickname, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null); // 앞에서 et로 받은 값을 불러오기 위해 선언. 리스너 활용. // 서버 전송에 포스트 방식 사용. get 방식도 있음.

        map = new HashMap<>(); // HashMap 사용. IntentPutExtra 와 유사. put 으로 알아볼 수 있게 key 값과 실제 입력될 값 입력.
        map.put("userID", userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userBirthday",userBirthday+""); // int 형을 넣었기 때문에 오류 발생. String 으로 눈속임.
        map.put("userNickname",userNickname);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
