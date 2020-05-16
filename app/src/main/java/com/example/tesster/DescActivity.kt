package com.example.tesster

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tesster.extension.toolbar
import com.example.tesster.model.DescModel
import org.jetbrains.anko.*

class DescActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val desc = intent.getSerializableExtra("desc") as DescModel
        setContentView(scrollView {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            verticalLayout {
                setSupportActionBar(toolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                    title = desc.name
                    setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                })
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                textView {
                    text = desc.desc
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(dip(16), dip(16), dip(16), dip(16))
                }.lparams(matchParent, 0, 1f)
                textView {
                    text = desc.a_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.b_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.c_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.d_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.e_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.f_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.g_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.h_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.i_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.j_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.k_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.l_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
                textView {
                    text = desc.m_desc
                    setTypeface(null,Typeface.BOLD)
                    textSize = 17f
                    textColor = Color.BLACK
                    setPadding(45,20,45,0)
                }
            }.lparams(matchParent, wrapContent)
        })
    }
}