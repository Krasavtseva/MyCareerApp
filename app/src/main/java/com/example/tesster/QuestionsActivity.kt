package com.example.tesster

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.tesster.model.TestModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_questions.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class QuestionsActivity : BaseActivity() {

    private lateinit var test: TestModel

    private var score = 0

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val json = assets.open(intent.getStringExtra("filename")!!)
            .bufferedReader()
            .use { it.readText() }
        val gson = GsonBuilder()
            .setLenient()
            .create()
        test = gson.fromJson(json, TestModel::class.java)
        next_btn.setOnClickListener {
            val index = getCheckIndex()
            if (index >= 0) {
                val question = test.questions[position]
                val answer = question.answers[index]
                score += answer.points
                position++
                if (position < test.questions.size) {
                    nextQuestion()
                } else {
                    val result = test.results.firstOrNull { score in it.min..it.max }
                    startActivity<ResultActivity>(
                        "result" to result?.text.toString()
                    )
                    finish()
                }
            } else {
                toast("Выберите вариант ответа")
            }
        }
        nextQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() {
        val question = test.questions[position]
        tv_question.text = question.text
        tv_position.text = "${position + 1}/${test.questions.size}"
        button1.text = question.answers.getOrNull(0)?.text
        button2.text = question.answers.getOrNull(1)?.text
        button3.text = question.answers.getOrNull(2)?.text
        button4.text = question.answers.getOrNull(3)?.text
        button1.isChecked = false
        button2.isChecked = false
        button3.isChecked = false
        button4.isChecked = false
        button1.visibility = if (question.answers.size > 0) View.VISIBLE else View.GONE
        button2.visibility = if (question.answers.size > 1) View.VISIBLE else View.GONE
        button3.visibility = if (question.answers.size > 2) View.VISIBLE else View.GONE
        button4.visibility = if (question.answers.size > 3) View.VISIBLE else View.GONE
    }

    private fun getCheckIndex() = when {
        button1.isChecked -> 0
        button2.isChecked -> 1
        button3.isChecked -> 2
        button4.isChecked -> 3
        else -> -1
    }
}