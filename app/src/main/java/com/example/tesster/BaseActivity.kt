package com.example.tesster

import android.app.Dialog
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    @JvmField
    protected val disposable = CompositeDisposable()

    protected val loadingDialog by lazy {
        Dialog(this).apply {
            setContentView(R.layout.loading)
            window?.setBackgroundDrawable(getDrawable(R.drawable.rounded_corners))
            window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            setCancelable(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        disposable.dispose()
        loadingDialog.dismiss()
        super.onDestroy()
    }
}