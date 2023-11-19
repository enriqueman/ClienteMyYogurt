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



    //    val preferences= requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
   //     if(preferences.getBoolean("isCart",false))
   //         findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        println("pppppppppppppppppppppppppppppppppppppppppppppppppp")
        getCategories()
        getSliderImages()

        getProducts()
        return binding.root

    }

    private fun getSliderImages() {
        println(1111)
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImage)
            }
    }

    private fun getProducts() {
        println(122222222222222)
        val list= ArrayList<AddYogurtModel>()
        Firebase.firestore.collection("noticias")
            .get().addOnSuccessListener {
                list.clear()

                for (doc in it.documents){
                    val data =AddYogurtModel(
                        newsId = doc.id,
                        newsTitle = doc["newsTitle"] as String?,
                        newsBody = doc["newsBody"]as String?,
                        CoverImg = doc["CoverImg"]as String?,
                        yogurtCategoria = doc["yogurtCategoria"]as String?,
                        newsVera = doc["newsVera"]as String?,
                        newsDate = doc["newsDate"]as String?,
                        newslink = doc["newslink"]as String?,
                        newsAutor = doc["newsAutor"]as String?,
                        yogurtImages = doc["yogurtImages"] as ArrayList<String>
                    )

                    list.add(data!!)
                    println("documento$doc")
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