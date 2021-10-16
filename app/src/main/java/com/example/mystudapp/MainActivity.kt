package com.example.mystudapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt
import render.animations.*

class MainActivity : AppCompatActivity(),SensorEventListener {

//    val TAG: String = "로그"
    val TAG : String ="로그"

    // 변수를 선언하는데 값을 나중에 설정할때 사용
    // 센서 매니저
    private lateinit var sensorManager : SensorManager

    private var accel: Float = 0.0f
    private var accelCurrent : Float = 0.0f
    private var accelLast : Float = 0.0f

    // 아래 있는 on으로 되어있는 것들이 메모리를 이용하기 때문에 생명주기가 존재한다.
    // 액티비티가 생성되었을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃을 설정
        // xml 뷰 파일을 연결 시켜준다. 즉 설정한다.
        // R = res파일
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called()")
        this.sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        accel =10f
        accelCurrent = SensorManager.GRAVITY_EARTH
        accelLast = SensorManager.GRAVITY_EARTH
    }

    override fun onSensorChanged(event: SensorEvent?) {
       // Log.d(TAG, "MainActivity - onSensorChanged() called()")

        val x: Float = event?.values?.get(0) as Float
        val y: Float = event?.values?.get(1) as Float
        val z: Float = event?.values?.get(2) as Float

        //
        x_text.text = "X: " + x.toInt().toString()
        y_text.text = "y: " + x.toInt().toString()
        z_text.text = "z: " + x.toInt().toString()

        accelLast = accelCurrent
        accelCurrent = sqrt((x*x+y*y+z*z).toDouble()).toFloat()

        val delta: Float = accelCurrent - accelLast

        accel = accel * 0.9f +delta


        if (accel > 10) {
            Log.d(TAG, "MainActivity - 흔들었음.")
            Log.d(TAG, "MainActivity - accel : ${accel}")

            face_img.setImageResource(R.drawable.smile)

            // Create Render class
            val render = Render(this)

            // Set Animation
            render.setAnimation(Bounce().InDown(textView))
            render.start()

            Handler().postDelayed({
                face_img.setImageResource(R.drawable.normal)

            },1000L)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
       Log.d(TAG, "MainActivity - onAccuracyChanged() called()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume() called()")
        // registerListener를 설정해주지 않으면 핸드폰을 흔드는 것을 감지하지 못한다.
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        Log.d(TAG, "MainActivity - onPause() called()")
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}
    // Log.d는 msg 내용을 출력하는 것이다. c언어 printf로 실행될때를 확인하기 위해서 사용.
//        Log.d(TAG, "MainActivity - onCreate() called()")

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
//        fun onLoginButtonClicked() {
//            Log.d(TAG, "MainActivity - onLoginButtonClicked() called()")
//
//            // 다른 페이지로 화면 전환하기
//            val intent = Intent(this, SecondActivity::class.java)
//
//            startActivity(intent)
//        }


