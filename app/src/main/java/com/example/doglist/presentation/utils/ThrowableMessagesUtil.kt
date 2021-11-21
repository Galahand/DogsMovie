package com.example.doglist.presentation.utils

import androidx.annotation.StringRes
import com.example.doglist.R
import java.util.concurrent.TimeoutException

@StringRes
fun getNetworkThrowableMessage(throwable: Throwable): Int {
    return if (throwable is TimeoutException) {
        R.string.dogs_server_no_response_message
    } else {
        R.string.dogs_generic_error
    }
}