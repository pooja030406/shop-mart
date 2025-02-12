package com.cscorner.cmart

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.cmart.databinding.Element1Binding
import com.squareup.picasso.Picasso

class myadapter(val context: MainActivity2, val arr: List<Product>): RecyclerView.Adapter<myadapter.viewholder>() {
    class viewholder(val binding: Element1Binding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = Element1Binding.inflate(context.layoutInflater, parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return arr.size/2;
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

            holder.binding.textView9.text = arr.get(arr.size/2+position).title
            holder.binding.textView8.text = arr.get(position).title


            Picasso.get().load(arr.get(position).thumbnail).into(holder.binding.imageView9)
            Picasso.get().load(arr.get(arr.size/2+position).thumbnail).into(holder.binding.imageView10)

    }

}
