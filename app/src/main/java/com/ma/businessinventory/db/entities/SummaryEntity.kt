package com.ma.businessinventory.db.entities

import androidx.room.ColumnInfo

data class SummaryEntity(
    @ColumnInfo(name = "data1") val data1: Double,
    @ColumnInfo(name = "data2") val data2: Double,
    @ColumnInfo(name = "data3") val data3: Double,
    @ColumnInfo(name = "data4") val data4: Double,
    @ColumnInfo(name = "data5") val data5: Double,
    @ColumnInfo(name = "data6") val data6: Double
)