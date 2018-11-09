package com.kotlin.client.view.pairscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.client.R

import com.kotlin.core.entities.PairSymbol
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pairs.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class PairScreenActivity : AppCompatActivity(), PairScreenPresenter.ScreenView {

    @Inject
    lateinit var presenter: PairScreenPresenter

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
        val adapter = PairSymbolAdapter(emptyList())
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        presenter.initView(this)
        getData()
    }

    private fun getData() {
        launch(UI) {
            presenter.getData()
        }
    }

    override fun load(pairs: List<PairSymbol>) {
        //    val adapter = PairSymbolAdapter(pairs)
        recycler.adapter = PairSymbolAdapter(pairs)
        (recycler.adapter as PairSymbolAdapter).notifyDataSetChanged()


    }
}