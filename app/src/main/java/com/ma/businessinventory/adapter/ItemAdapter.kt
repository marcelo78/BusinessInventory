package com.ma.businessinventory.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import com.ma.businessinventory.ui.main.MainActivity
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*

class ItemAdapter(private val items: MutableList<ProductEntity>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(), Filterable {

    private val onClickListener: View.OnClickListener
    private var itemsFull: MutableList<ProductEntity> = mutableListOf()

    init {
        onClickListener = View.OnClickListener { view ->
            Log.d(TAG, "Id: ${view.tag}")
            val idItem: Int = view.tag.toString().toInt()
            (context as MainActivity).openAddItemActivity(idItem)
        }
        itemsFull.addAll(items)
    }

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
        Log.d(TAG, "ID: ${items[position].id}")
        holder.tvName.text = items[position].nameInventory
        holder.tvPlace.text = items[position].place
        holder.tvDate.text = items[position].dateProduct
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
        with(holder.cardView) {
            tag = items[position].id
            setOnClickListener(onClickListener)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.lblName
        val tvPlace = view.lblPlace
        val tvDate = view.lblDate
        val tvCost = view.lblCost
        val tvRem = view.lblRem
        val tvRecv = view.lblRecv
        val cardView = view.cardView
    }

    override fun getFilter(): Filter {
        return filterItems
    }

    private var filterItems = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var filteredList: MutableList<ProductEntity> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredList = itemsFull
            } else {
                val filterPatterns = constraint.toString().toLowerCase(Locale.getDefault()).trim()

                for (item: ProductEntity in itemsFull) {
                    if (item.nameInventory?.toLowerCase(Locale.getDefault())!!.contains(
                            filterPatterns
                        )
                    ) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results

        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            items.clear()
            items.addAll(results?.values as MutableList<ProductEntity>)
            notifyDataSetChanged()
        }


    }
}
