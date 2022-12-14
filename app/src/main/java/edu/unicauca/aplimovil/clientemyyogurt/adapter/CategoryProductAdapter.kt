package edu.unicauca.aplimovil.clientemyyogurt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import edu.unicauca.aplimovil.clientemyyogurt.databinding.ItemCategoryProductLayoutBinding
import edu.unicauca.aplimovil.clientemyyogurt.databinding.LayoutProductItemBinding
import edu.unicauca.aplimovil.clientemyyogurt.model.AddYogurtModel

class CategoryProductAdapter (val context: Context, val list : ArrayList<AddYogurtModel>)
    : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>(){

    inner class CategoryProductViewHolder(val binding: ItemCategoryProductLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val binding = ItemCategoryProductLayoutBinding.inflate(LayoutInflater.from(context),parent, false)
        return CategoryProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        Glide.with(context).load(list[position].yogurtImages[0]).into(holder.binding.imageView2)

        holder.binding.textView2.text = list[position].yogurtName
        holder.binding.textView3.text = list[position].yogurtDescripcion

    }

    override fun getItemCount(): Int {
        return list.size
    }
}