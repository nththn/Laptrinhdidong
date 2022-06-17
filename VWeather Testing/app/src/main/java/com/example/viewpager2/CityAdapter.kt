package com.example.viewpager2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.card_items_city.view.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.view.*

class CityAdapter  : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    var mode: Boolean = false
    private var cityList: ArrayList<City> = ArrayList()
    private var onClickItem: ((City) -> Unit)? = null
    private var onClickDeleteItem: ((City) -> Unit)? = null

    fun addItems(items: ArrayList<City>) {
//        this.cityList = items
//        notifyDataSetChanged()
        cityList.clear();
        cityList.addAll(items);
        this.notifyDataSetChanged();
    }

    fun setOnClickItem(callback: (City) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnClickDeleteitem(callback: (City) -> Unit) {
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityViewHolder(inflater.inflate(R.layout.card_items_city, parent, false))

    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.bindView(city)
        holder.itemView.setOnClickListener { onClickItem?.invoke(city) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(city) }

        if(mode == true){
            holder.itemView.item_city_linearLayout.setBackgroundResource(R.drawable.layout_city_item_light)
            holder.itemView.tv_city_name.setTextColor(Color.parseColor("#121212"))
            holder.itemView.item_city_linearLayout.elevation = 10F
        }
        else{
            holder.itemView.item_city_linearLayout.setBackgroundResource(R.drawable.layout_city_item)
            holder.itemView.tv_city_name.setTextColor(Color.parseColor("#dfdfdf"))

        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    class CityViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


        private var name = view.findViewById<TextView>(R.id.tv_city_name)

        var btnDelete = view.findViewById<ImageButton>(R.id.btn_delete)

        fun bindView(city: City) {
            name.text = city.name
        }
    }
    fun change_mode(){
        notifyDataSetChanged()
    }
}