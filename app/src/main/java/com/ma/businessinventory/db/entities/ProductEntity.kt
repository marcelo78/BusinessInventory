package com.ma.businessinventory.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name_inventory") var nameInventory: String? = null,
    @ColumnInfo(name = "place") var place: String? = "",
    @ColumnInfo(name = "description") var description: String? = "",
    @ColumnInfo(name = "type") var type: String? = "",
    @ColumnInfo(name = "date_product") var dateProduct: String? = "",
    @ColumnInfo(name = "barcode") var barcode: String? = "",
    @ColumnInfo(name = "bought_no") var boughtNo: Double? = 0.0,
    @ColumnInfo(name = "sold_no") var soldNo: Double? = 0.0,
    @ColumnInfo(name = "unid_buy_price_us") var unidBuyPriceUS: Int? = 0,
    @ColumnInfo(name = "unid_sell_price_us") var unidSellPriceUS: Int? = 0,
    @ColumnInfo(name = "total_cost_us") var totalCostUS: Double? = 0.0,
    @ColumnInfo(name = "total_received_us") var totalReceivedUS: Int? = 0,
    @ColumnInfo(name = "total_profit_us") var totalProfitUS: Int? = 0,
    @ColumnInfo(name = "photo") var photo: String? = ""
)
