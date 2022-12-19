package edu.unicauca.aplimovil.clientemyyogurt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import edu.unicauca.aplimovil.clientemyyogurt.databinding.LayoutCartItemBinding
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.YogurtModel

class CartAdapter(val context: Context, val list: List<YogurtModel>) :
RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    inner class CartViewHolder(val binding: LayoutCartItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = LayoutCartItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       Glide.with(context).load(list[position].yogurtImages).into(holder.binding.imageView4)
        holder.binding.textView7.text = list[position].yogurtName
        holder.binding.textView12.text = list[position].yogurtPrecio
    }

    override fun getItemCount(): Int {

        return list.size
    }
}