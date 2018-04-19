package com.kotlin.client.homescreen

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.client.R
import com.kotlin.client.di.AppModule
import com.kotlin.client.di.DaggerInjector
import com.kotlin.client.job.SyncJob
import com.kotlin.client.presenter.HomeScreenPresenter
import com.kotlin.core.entities.Trade
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
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
        setContentView(R.layout.activity_main)
        initComponents()
        initJob()
    }

    private fun inject() {
        DaggerInjector.builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
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

    private fun initJob() {
        val builder = JobInfo.Builder(jobId++, ComponentName(this, SyncJob::class.java))
        builder.run {
            setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            setPeriodic(PERIOD)
        }
        (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).schedule(builder.build())
    }
}
