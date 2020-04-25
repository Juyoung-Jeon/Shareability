package com.example.registerloginexample

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class RegisterRequest(userID: String?, userPassword: String?, userName: String?, userBirthday: Int, userNickname: String?, listener: Response.Listener<String?>?) : StringRequest(Method.POST, URL, listener, null) {
    private val map: MutableMap<String?, String?>?

    @Throws(AuthFailureError::class)
    override fun getParams(): MutableMap<String?, String?>? {
        return map
    }

    companion object {
        // 서버 URL 설정 (php 파일 연동)
        private val URL: String? = "http://shareability.dothome.co.kr/Register.php" // 고정값이므로 final static, 서버 호스팅 주소 입력
    }

    // 생성값 할당을 위해 만들어 준 것이고 실제 쓰이는 건 RegisterActivity
    init {
        map = HashMap() // HashMap 사용. IntentPutExtra 와 유사. put 으로 알아볼 수 있게 key 값과 실제 입력될 값 입력.
        map["userID"] = userID
        map["userPassword"] = userPassword
        map["userName"] = userName
        map["userBirthday"] = userBirthday.toString() + "" // int 형을 넣었기 때문에 오류 발생. String 으로 눈속임.
        map["userNickname"] = userNickname
    }
}