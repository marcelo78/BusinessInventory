package com.ma.businessinventory.tools

import java.text.DecimalFormat
import java.util.*

class NumberUtil {

    companion object {
        @Volatile
        private var instance: DecimalFormat? = null

        fun getInstance(): DecimalFormat? {

            if (instance == null) {
                instance = DecimalFormat("###,###,##0.00")
            }
            return instance
        }

    }
}
