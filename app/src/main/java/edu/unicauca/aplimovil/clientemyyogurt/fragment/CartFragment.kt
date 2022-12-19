package edu.unicauca.aplimovil.clientemyyogurt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.adapter.CartAdapter
import edu.unicauca.aplimovil.clientemyyogurt.databinding.FragmentCartBinding
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.AppDatabase

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
        }

        return binding.root
    }


}