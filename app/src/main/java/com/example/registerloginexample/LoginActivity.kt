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
import com.example.registerloginexample.LoginActivity
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var etid = findViewById<EditText>(R.id.et_id)
        var etpassword = findViewById<EditText>(R.id.et_password)
        var btnlogin = findViewById<Button>(R.id.btn_login)
        var btnregister = findViewById<Button>(R.id.btn_register)

        // 회원가입 버튼을 클릭 시 수행 - 화면 넘어가도록.
        btnregister.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        })
        btnlogin.setOnClickListener(View.OnClickListener { // EditText 에 현재 입력되어있는 값을 get(가져온다)해온다.
            val userID = etid.text.toString()
            val userPass = etpassword.text.toString()
            val responseListener = Response.Listener<String?> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 로그인에 성공한 경우
                        val userID = jsonObject.getString("userID")
                        val userPass = jsonObject.getString("userPassword") // key 값 php 지정된 대로
                        Toast.makeText(applicationContext, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("userID", userID)
                        intent.putExtra("userPass", userPass)
                        startActivity(intent)
                    } else { // 로그인에 실패한 경우
                        Toast.makeText(applicationContext, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val loginRequest = LoginRequest(userID, userPass, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)
        })
    }
}