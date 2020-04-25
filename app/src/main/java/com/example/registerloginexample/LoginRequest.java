package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    // 서버 URL 설정 (php 파일 연동)
    final static private String URL = "http://shareability.dothome.co.kr/Login.php"; // 여기선 login php 활용
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){ // 아이디 패스워드만 필요
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword",userPassword);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
