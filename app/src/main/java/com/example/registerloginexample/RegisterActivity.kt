package com.example.registerloginexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.registerloginexample.RegisterActivity
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // Activity 시작 시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 아이디 값 찾아주기
        var etid = findViewById<EditText>(R.id.et_id)
        var etpassword = findViewById<EditText>(R.id.et_password)
        var etname = findViewById<EditText>(R.id.et_name)
        var etbirthday = findViewById<EditText>(R.id.et_birthday)
        var etnickname = findViewById<EditText>(R.id.et_nickname)

        // 회원가입 버튼 클릭 시 수행
        var btnregister = findViewById<Button>(R.id.btn_register)
        btnregister.setOnClickListener(View.OnClickListener { // EditText 에 현재 입력되어있는 값을 get(가져온다)해온다.
            val userID = etid.text.toString()
            val userPass = etpassword.text.toString()
            val userName = etname.text.toString()
            val userBirthday = etbirthday.text.toString().toInt()
            val userNickname = etnickname.text.toString()


            // Volley 구문 활용
            val responseListener = Response.Listener<String?> { response ->

                // Json Object 에 담아서 서버에 전송. 일종의 운반체계
                // onResponse 결과값을 받겠다.
                try {
                    val jsonObject = JSONObject(response) // JsonObject 는 try catch 로 감싸줘야 함.
                    val success = jsonObject.getBoolean("success") // key 값을 가지고 왔을 때 판단. php 문에 이미 success 로 response 정해져 있음.
                    if (success) { // 회원 등록에 성공한 경우
                        Toast.makeText(applicationContext, "회원 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java) // 출발 activity 이동할 activi   ty
                        startActivity(intent)
                    } else { // 회원등록에 실패한 경우
                        Toast.makeText(applicationContext, "회원 등록에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 서버로 Volley 를 이용해서 요청을 함.
            val registerRequest = RegisterRequest(userID, userPass, userName, userBirthday, userNickname, responseListener)
            val queue = Volley.newRequestQueue(this@RegisterActivity)
            queue.add(registerRequest)
        })
    }
}