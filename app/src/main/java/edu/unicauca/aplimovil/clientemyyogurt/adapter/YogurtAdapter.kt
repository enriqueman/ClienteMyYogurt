package edu.unicauca.aplimovil.clientemyyogurt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import edu.unicauca.aplimovil.clientemyyogurt.activity.YogurtDetailsActivity
import edu.unicauca.aplimovil.clientemyyogurt.databinding.LayoutProductItemBinding
import edu.unicauca.aplimovil.clientemyyogurt.model.AddYogurtModel


class YogurtAdapter (val context:Context, val list : ArrayList<AddYogurtModel>)
    :RecyclerView.Adapter<YogurtAdapter.YogurtViewHolder>(){
    inner class YogurtViewHolder(val binding: LayoutProductItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YogurtViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return YogurtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YogurtViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context).load(data.CoverImg).into(holder.binding.imageView3)
        holder.binding.textView4.text = data.yogurtName
        holder.binding.textView5.text = data.yogurtDescripcion
        holder.binding.textView6.text = data.yogurtFruta

        holder.binding.button.text = data.yogurtPrecio

        holder.itemView.setOnClickListener(){
            val intent = Intent(context, YogurtDetailsActivity::class.java)
            intent.putExtra("id", list[position].yogurtId)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}