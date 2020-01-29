package com.ma.businessinventory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name_inventory") var nameInventory: String? = null,
    @ColumnInfo(name = "place") var place: String? = "",
    @ColumnInfo(name = "description") var description: String? = "",
    @ColumnInfo(name = "type") var type: String? = "",
    @ColumnInfo(name = "date_product") var dateProduct: String? = "",
    @ColumnInfo(name = "barcode") var barcode: String? = "",
    @ColumnInfo(name = "bought_no", defaultValue = "0.0") var boughtNo: Double? = 0.0,
    @ColumnInfo(name = "sold_no", defaultValue = "0.0") var soldNo: Double? = 0.0,
    @ColumnInfo(name = "unid_buy_price_us", defaultValue = "0") var unidBuyPriceUS: Int? = 0,
    @ColumnInfo(name = "unid_sell_price_us", defaultValue = "0") var unidSellPriceUS: Int? = 0,
    @ColumnInfo(name = "total_cost_us", defaultValue = "0") var totalCostUS: Double? = 0.0,
    @ColumnInfo(name = "total_received_us", defaultValue = "0") var totalReceivedUS: Int? = 0,
    @ColumnInfo(name = "total_profit_us", defaultValue = "0.0") var totalProfitUS: Int? = 0,
    @ColumnInfo(name = "photo") var photo: String? = ""
)
