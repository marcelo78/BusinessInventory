package com.ma.businessinventory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name_inventory") val nameInventory: String?,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "barcode") val barcode: String?,
    @ColumnInfo(name = "bought_no") val boughtNo: Double?,
    @ColumnInfo(name = "sold_no") val soldNo: Double?,
    @ColumnInfo(name = "unid_buy_price_us") val unidBuyPriceUS: Int?,
    @ColumnInfo(name = "unid_sell_price_us") val unidSellPriceUS: Int?,
    @ColumnInfo(name = "total_cost_us") val totalCostUS: Double?,
    @ColumnInfo(name = "total_received_us") val totalReceivedUS: Int?,
    @ColumnInfo(name = "total_profit_us") val totalProfitUS: Int?,
    @ColumnInfo(name = "photo") val photo: String?
)
