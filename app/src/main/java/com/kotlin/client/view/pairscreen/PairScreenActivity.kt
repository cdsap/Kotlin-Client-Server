package com.kotlin.client.view.pairscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.client.view.homescreen.HomeScreenActivity

import com.kotlin.core.entities.PairSymbol
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pairs.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class PairScreenActivity : AppCompatActivity(), PairScreenPresenter.ScreenView,
        PairListListener {

    @Inject
    lateinit var presenter: PairScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(R.layout.activity_trades)
        initComponents()
        getData()
    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun initComponents() {
        val adapter = PairSymbolAdapter(emptyList(),this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        presenter.initView(this)
        recycler.setOnClickListener {
        }
    }

    private fun getData() {
        launch(UI) {
            presenter.getData()
        }
    }

    override fun load(pairs: List<PairSymbol>) {
        recycler.adapter = PairSymbolAdapter(pairs, this)
        (recycler.adapter as PairSymbolAdapter).notifyDataSetChanged()
    }

    override fun onPairClicked(id: Long) {
        startActivity(Intent(this, HomeScreenActivity::class.java).apply {
            putExtra("PAIR", id)
        })
    }

}