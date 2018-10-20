package view.pairscreen

import com.kotlin.core.usecases.GetPairs
import javax.inject.Inject

class PairScreenPresenter @Inject constructor(private val getPairs: GetPairs) {

    fun init() {
        getPairs.get()
    }

}
