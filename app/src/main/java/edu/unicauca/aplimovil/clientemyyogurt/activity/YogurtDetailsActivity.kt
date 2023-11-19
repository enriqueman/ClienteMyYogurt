package edu.unicauca.aplimovil.clientemyyogurt.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.clientemyyogurt.MainActivity
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.databinding.ActivityYogurtDetailsBinding
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.AppDatabase
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.YogurtDao
import edu.unicauca.aplimovil.clientemyyogurt.roomdb.YogurtModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YogurtDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityYogurtDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYogurtDetailsBinding.inflate(layoutInflater)

        getYogurtDetails(intent.getStringExtra("id"))

        setContentView(binding.root)
    }

    private fun getYogurtDetails(newsId: String?) {
        Firebase.firestore.collection("noticias")
            .document(newsId!!).get().addOnSuccessListener {
                val list = it.get("yogurtImages") as ArrayList<String>
                val name = it.getString("newsTitle")
                val yogurtPrecio = it.getString(("newsDate"))
                binding.textView8Title.text = it.getString("newsTitle")
                binding.textViewCuerpo.text = it.getString("newsBody")
                binding.textViewFecha.text = it.getString("newsDate")
                binding.textViewfuente.text = it.getString("newsAutor")
                binding.textViewEstado.text = it.getString("newsVera")



                val slideList = ArrayList<SlideModel>()

                for(data in list){
                    slideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
                }

                cartAction(newsId, name, list[0],yogurtPrecio  )

                binding.imageSlider.setImageList(slideList)

            }.addOnFailureListener(){
                Toast.makeText(this,"Algo salió mal", Toast.LENGTH_SHORT)
            }

    }

    private fun cartAction(yogId: String, name: String?, yogurtPrecio: String?, coverImg: String?) {
        val yogurtDao = AppDatabase.getInstance(this).yogurtDao()
        if(yogurtDao.isExit(yogId)!=null){
            binding.textViewInicio.text = "Inicio"
        }else{
            binding.textViewInicio.text = "Inicio"
        }

        binding.textViewInicio.setOnClickListener {
            if(yogurtDao.isExit(yogId)!=null){
                openCart()

            }else{

                addToCart(yogurtDao,yogId,name,yogurtPrecio,coverImg)
            }
        }

    }

    private fun addToCart(
    yogurtDao: YogurtDao,
    yogId: String,
    name: String?,
    yogurtPrecio: String?,
    coverImg: String?
    ) {
        val data = YogurtModel(yogId,name,yogurtPrecio,coverImg)
        lifecycleScope.launch(Dispatchers.IO){
            yogurtDao.insertYogurt(data)
            binding.textViewInicio.text = "Go to cart"

        }

    }

    private fun openCart() {
    val preferences= this.getSharedPreferences("info", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isCart", true)
        editor.apply()

        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }
}