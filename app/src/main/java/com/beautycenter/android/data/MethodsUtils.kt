package com.beautycenter.android.data

import android.content.Context
import android.widget.Toast

class MethodsUtils {
    companion object {

        fun showMessage(context: Context, msg: String) {
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}