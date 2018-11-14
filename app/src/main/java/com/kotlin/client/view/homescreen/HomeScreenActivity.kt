package com.kotlin.client.view.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_trades.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenActivity : AppCompatActivity(), HomeScreenPresenter.ScreenView {

    @Inject
    lateinit var presenter: HomeScreenPresenter

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
        recycler.layoutManager = LinearLayoutManager(this)
        swipe.setOnRefreshListener { getData(getIntent().getLongExtra("PAIR", 1L)) }
        presenter.initView(this)
        getData(getIntent().getLongExtra("PAIR", 1L))
    }

    private fun getData(longExtra: Long) {
        swipe.isRefreshing = true
     //   CoroutineScope..uiScope.launch { /
        // presenter.getData(longExtra)
       // }
    }

    override fun load(result: List<Trade>) {
        val adapter = TradesAdapter(result)
        recycler.adapter = adapter
        swipe.isRefreshing = false
    }
}
