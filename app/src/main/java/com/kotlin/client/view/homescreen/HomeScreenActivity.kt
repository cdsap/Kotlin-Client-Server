package com.kotlin.client.view.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_trades.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import view.homescreen.HomeScreenPresenter
import javax.inject.Inject

class HomeScreenActivity : AppCompatActivity(), HomeScreenPresenter.ScreenView {

    companion object {
        const val BTC = 1L
        const val PERIOD = 100000L
    }

    private var jobId = 0
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
        swipe.setOnRefreshListener { getData() }
        presenter.initView(this)
        getData()
    }

    private fun getData() {
        swipe.isRefreshing = true
        launch(UI) {
            presenter.getData(BTC)
        }
    }

    override fun load(result: List<Trade>) {
        val adapter = TradesAdapter(result)
        recycler.adapter = adapter
        swipe.isRefreshing = false
    }
}
