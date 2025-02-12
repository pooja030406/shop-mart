package com.cscorner.cmart

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.cmart.databinding.Element1Binding
import com.squareup.picasso.Picasso

class madap(val context: Context, val arr: MutableList<Product>): RecyclerView.Adapter<madap.viewholder>() {
    class viewholder(val binding: Element1Binding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = Element1Binding.inflate(android.view.LayoutInflater.from(context), parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return arr.size/2;
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tv9.text = arr.get(arr.size/2+position).title
        holder.binding.tv8.text = arr.get(position).title
        holder.binding.textView8.text = arr.get(position).price.toString()
        holder.binding.textView9.text = arr.get(arr.size/2+position).price.toString()
        Picasso.get().load(arr.get(position).thumbnail).into(holder.binding.iv9)
        Picasso.get().load(arr.get(arr.size/2+position).thumbnail).into(holder.binding.iv8)

    }

}
