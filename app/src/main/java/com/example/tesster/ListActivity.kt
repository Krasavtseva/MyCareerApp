package com.example.tesster

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.recyclical.ViewHolder
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.tesster.extension.recyclerView
import com.example.tesster.extension.toolbar
import com.example.tesster.model.DescModel
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import kotlinx.android.synthetic.main.set_item.view.*
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout

class ListViewHolder(itemView: View) : ViewHolder(itemView) {
    val text: TextView = itemView.textview
}

class ListActivity : BaseActivity() {

    private val dataSource = emptyDataSourceTyped<DescModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = intent.getStringExtra("path")!!
        setContentView(verticalLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            setSupportActionBar(toolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                title = intent.getStringExtra("title")
                setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            })
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            recyclerView {
                setup {
                    withLayoutManager(LinearLayoutManager(context))
                    withDataSource(dataSource)
                    withItem<DescModel, ListViewHolder>(R.layout.set_item) {
                        onBind(::ListViewHolder) { _, item ->
                            text.text = item.name
                        }
                        onClick { index ->
                            val desc = dataSource[index]
                            if (path == "Tests") {
                                startActivity<QuestionsActivity>(
                                    "title" to desc.name,
                                    "filename" to desc.desc
                                )
                            } else {
                                startActivity<DescActivity>("desc" to desc)
                            }
                        }
                    }
                }
            }.lparams(matchParent, 0, 1f)
        })
        loadingDialog.show()
        disposable.add(RxFirebaseDatabase.observeSingleValueEvent(
            FirebaseDatabase.getInstance().reference.child(intent.getStringExtra("path")!!),
            DataSnapshotMapper.listOf(DescModel::class.java)
        )
            .subscribe({ data ->
                dataSource.set(data)
                loadingDialog.dismiss()
            }) { error ->
                toast(error.message.toString())
                loadingDialog.dismiss()
            })
    }
}