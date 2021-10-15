package com.example.mystudapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"


    // 아래 있는 on으로 되어있는 것들이 메모리를 이용하기 때문에 생명주기가 존재한다.
    // 액티비티가 생성되었을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃을 설정
        // xml 뷰 파일을 연결 시켜준다. 즉 설정한다.
        setContentView(R.layout.activity_main)

        // Log.d는 msg 내용을 출력하는 것이다. c언어 printf로 실행될때를 확인하기 위해서 사용.
        Log.d(TAG, "MainActivity - onCreate() called()")

        //
//        //로그인 버튼 뷰에 클릭 리스너를 설정하였다.
//        login_btn.setOnClickListener(View.OnClickListener {
//            onLoginButtonClicked()
//        })
//    }
        // 람다식으로 표현 하는 방법
//        login_btn.setOnClickListener {
//            onLoginButtonClicked()
//        }
//    }
    }
//        fun onLoginButtonClicked() {
//            Log.d(TAG, "MainActivity - onLoginButtonClicked() called()")
//
//            // 다른 페이지로 화면 전환하기
//            val intent = Intent(this, SecondActivity::class.java)
//
//            startActivity(intent)
//        }

    }
