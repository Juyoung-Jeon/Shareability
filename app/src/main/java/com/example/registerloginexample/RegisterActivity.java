package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_password, et_name, et_birthday, et_nickname; // 받아올 et 선언. xml 과 일치하게
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Activity 시작 시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        et_name = findViewById(R.id.et_name);
        et_birthday = findViewById(R.id.et_birthday);
        et_nickname = findViewById(R.id.et_nickname);

        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EditText 에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_password.getText().toString();
                String userName = et_name.getText().toString();
                int userBirthday = Integer.parseInt(et_birthday.getText().toString());
                String userNickname = et_nickname.getText().toString();

                // Volley 구문 활용
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override // Json Object 에 담아서 서버에 전송. 일종의 운반체계
                    public void onResponse(String response) { // onResponse 결과값을 받겠다.
                        try {
                            JSONObject jsonObject = new JSONObject(response); // JsonObject 는 try catch 로 감싸줘야 함.
                            boolean success = jsonObject.getBoolean("success"); // key 값을 가지고 왔을 때 판단. php 문에 이미 success 로 response 정해져 있음.
                            if (success) { // 회원 등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); // 출발 activity 이동할 activi   ty
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley 를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userBirthday, userNickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
         });

    }
}
