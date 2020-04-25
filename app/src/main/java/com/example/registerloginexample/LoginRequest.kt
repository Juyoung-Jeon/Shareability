package com.example.registerloginexample

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class LoginRequest(userID: String?, userPassword: String?, listener: Response.Listener<String?>?) : StringRequest(Method.POST, URL, listener, null) {
    private val map: MutableMap<String?, String?>?

    @Throws(AuthFailureError::class)
    override fun getParams(): MutableMap<String?, String?>? {
        return map
    }

    companion object {
        // 서버 URL 설정 (php 파일 연동)
        private val URL: String? = "http://shareability.dothome.co.kr/Login.php" // 여기선 login php 활용
    }

    init { // 아이디 패스워드만 필요
        map = HashMap()
        map["userID"] = userID
        map["userPassword"] = userPassword
    }
}