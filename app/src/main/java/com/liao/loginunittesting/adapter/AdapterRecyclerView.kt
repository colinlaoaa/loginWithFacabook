package com.liao.loginunittesting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liao.loginunittesting.model.DataClass
import com.liao.loginunittesting.R
import kotlinx.android.synthetic.main.new_row.view.*

class AdapterRecyclerView(private var context: Context, private var list: List<DataClass>) :
    RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.new_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun refreshList(mList: List<DataClass>){
        list = mList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataClass: DataClass) {
            itemView.text_view.text = dataClass.id
            itemView.text_view_2.text = dataClass.name
            itemView.image_view.setImageResource(dataClass.image)
        }
    }
}