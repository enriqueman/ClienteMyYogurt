package edu.unicauca.aplimovil.clientemyyogurt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.adapter.CategoryAdapter
import edu.unicauca.aplimovil.clientemyyogurt.adapter.YogurtAdapter
import edu.unicauca.aplimovil.clientemyyogurt.databinding.FragmentHomeBinding
import edu.unicauca.aplimovil.clientemyyogurt.model.AddYogurtModel
import edu.unicauca.aplimovil.clientemyyogurt.model.CategoryModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)



        val preferences= requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        if(preferences.getBoolean("isCart",false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        getCategories()
        getSliderImages()

        getProducts()
        return binding.root

    }

    private fun getSliderImages() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImage)
            }
    }

    private fun getProducts() {
        val list= ArrayList<AddYogurtModel>()
        Firebase.firestore.collection("yogures")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(AddYogurtModel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.adapter= YogurtAdapter(requireContext(),list)
            }
    }

    private fun getCategories() {
        val list= ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter= CategoryAdapter(requireContext(),list)
            }
    }

}