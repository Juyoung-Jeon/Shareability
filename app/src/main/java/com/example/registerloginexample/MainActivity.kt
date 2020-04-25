package com.example.registerloginexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tvid = findViewById<TextView>(R.id.tv_id)
        var tvpass = findViewById<TextView>(R.id.tv_pass)


        // 받아오기 위한 intent
        val intent = intent
        val userID = intent.getStringExtra("userID")
        val userPass = intent.getStringExtra("userPass")
        tvid.text = userID
        tvpass.text = userPass
    }
}