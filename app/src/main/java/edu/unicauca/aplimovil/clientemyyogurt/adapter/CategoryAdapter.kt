package edu.unicauca.aplimovil.clientemyyogurt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.databinding.LayoutCategoryItemBinding
import edu.unicauca.aplimovil.clientemyyogurt.model.CategoryModel


class CategoryAdapter(var contex: Context,var list: ArrayList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = LayoutCategoryItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(contex).inflate(R.layout.layout_category_item,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
    holder.binding.textView.text = list[position].cate
    Glide.with(contex).load(list[position].img).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}