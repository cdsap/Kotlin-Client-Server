package com.kotlin.client.view.tradescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_trades.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TradesActivity : AppCompatActivity(),
        TradesScreenPresenter.ScreenView, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job = SupervisorJob()

    @Inject
    lateinit var presenter: TradesScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(R.layout.activity_trades)
        initComponents()
    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun initComponents() {
        val adapter = TradesAdapter(emptyList())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        swipe.setOnRefreshListener { getData(getIntent().getLongExtra("PAIR", 1L)) }
        presenter.initView(this)
        getData(getIntent().getLongExtra("PAIR", 1L))
    }

    private fun getData(longExtra: Long) = launch {
        swipe.isRefreshing = true
        presenter.getData(longExtra)
    }

    override fun load(result: List<Trade>) {
        recycler.adapter = TradesAdapter(result)
        swipe.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
