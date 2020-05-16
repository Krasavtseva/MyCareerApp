package com.example.tesster

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        total.text = intent.getStringExtra("result")
        done_btn.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }
    }
}