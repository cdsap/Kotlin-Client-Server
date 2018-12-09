package com.kotlin.server.repository.api.patch

import okhttp3.Call
import okhttp3.Request

class CallFactoryWrapper : Call.Factory {
    override fun newCall(request: Request?): Call = CallWrapper(request!!)
}