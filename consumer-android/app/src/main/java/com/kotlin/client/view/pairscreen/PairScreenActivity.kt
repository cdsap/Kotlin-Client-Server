package com.kotlin.client.view.pairscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.client.view.homescreen.TradesActivity

import com.kotlin.core.entities.PairSymbol
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pairs.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PairScreenActivity : AppCompatActivity(), PairScreenPresenter.ScreenView,
        PairListListener, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job = SupervisorJob()

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
        val adapter = PairSymbolAdapter(emptyList(), this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        presenter.initView(this)
        swipe.setOnRefreshListener {
            swipe.isRefreshing = true
            refresh()
        }
    }

    private fun getData() = launch {
        presenter.getData()
    }

    private fun refresh() = launch {
        presenter.refresh()
    }

    override fun load(pairs: List<PairSymbol>) {
        swipe.isRefreshing = false
        recycler.adapter = PairSymbolAdapter(pairs, this)
        (recycler.adapter as PairSymbolAdapter).notifyDataSetChanged()
    }

    override fun onPairClicked(id: Long) {
        startActivity(Intent(this, TradesActivity::class.java).apply {
            putExtra("PAIR", id)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}