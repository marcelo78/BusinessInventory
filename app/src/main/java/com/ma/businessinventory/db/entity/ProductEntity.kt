package com.ma.businessinventory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name_inventory") var nameInventory: String?,
    @ColumnInfo(name = "place") var place: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "barcode") var barcode: String?,
    @ColumnInfo(name = "bought_no") var boughtNo: Double?,
    @ColumnInfo(name = "sold_no") var soldNo: Double?,
    @ColumnInfo(name = "unid_buy_price_us") var unidBuyPriceUS: Int?,
    @ColumnInfo(name = "unid_sell_price_us") var unidSellPriceUS: Int?,
    @ColumnInfo(name = "total_cost_us") var totalCostUS: Double?,
    @ColumnInfo(name = "total_received_us") var totalReceivedUS: Int?,
    @ColumnInfo(name = "total_profit_us") var totalProfitUS: Int?,
    @ColumnInfo(name = "photo") var photo: String?
)
