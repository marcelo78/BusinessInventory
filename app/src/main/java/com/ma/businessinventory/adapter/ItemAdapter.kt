package com.ma.businessinventory.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(private val items: List<ProductEntity>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    companion object {
        private val TAG = ItemAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "$position position")
        holder.tvId.text = "${items[position].id}"
        holder.tvName.text = items[position].nameInventory
        holder.tvPlace.text = items[position].place
        holder.tvDate.text = items[position].date
        var text = context.getString(
            R.string.list_item_text_cost_item,
            items[position].totalCostUS.toString()
        )
        holder.tvCost.text = text
        text = context.getString(
            R.string.list_item_text_rem_item,
            (items[position].boughtNo!! - items[position].soldNo!!),
            items[position].boughtNo
        )
        holder.tvRem.text = text
        val total: Double = if (items[position].boughtNo == items[position].soldNo) {
            items[position].totalReceivedUS!!.toDouble()
        } else {
            ((-items[position].totalProfitUS!!) + (-items[position].totalCostUS!!))
        }
        holder.tvRecv.text = context.getString(R.string.list_item_text_cost_item, total.toString())
        val color = if (total < 0) Color.RED else Color.GREEN
        holder.tvRecv.setTextColor(color)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId = view.lblId
        val tvName = view.lblName
        val tvPlace = view.lblPlace
        val tvDate = view.lblDate
        val tvCost = view.lblCost
        val tvRem = view.lblRem
        val tvRecv = view.lblRecv
    }

}