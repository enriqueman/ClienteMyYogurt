package edu.unicauca.aplimovil.clientemyyogurt.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.activity.AddressActivity
import edu.unicauca.aplimovil.clientemyyogurt.activity.YogurtDetailsActivity
import edu.unicauca.aplimovil.clientemyyogurt.adapter.CartAdapter
import edu.unicauca.aplimovil.clientemyyogurt.databinding.FragmentCartBinding
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.AppDatabase
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.YogurtModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)
        val preferences= requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isCart", false)
        editor.apply()

        val dao = AppDatabase.getInstance(requireContext()).yogurtDao()
        dao.getAllYogurt().observe(requireActivity()){
            binding.cartRecycler.adapter= CartAdapter(requireContext(),it)
            totalcost(it)
        }

        return binding.root
    }

    private fun totalcost(data: List<YogurtModel>?) {
        var total= 0
        for(item in data!!){
            total+=item.yogurtPrecio!!.toInt()
        }
        binding.textView13.text="Numero de productos: ${data.size}"
        binding.textView14.text="Costo total: $total"

        binding.checkout.setOnClickListener {
            val intent = Intent(context, AddressActivity::class.java)
            intent.putExtra("totalcost", total)
            startActivity(intent)
        }

    }


}