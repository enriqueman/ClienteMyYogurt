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

    private fun getYogurtDetails(yogId: String?) {
        Firebase.firestore.collection("yogures")
            .document(yogId!!).get().addOnSuccessListener {
                val list = it.get("yogurtImages") as ArrayList<String>
                val name = it.getString("yogurtName")
                val yogurtPrecio = it.getString(("yogurtPrecio"))
                binding.textView8.text = it.getString("yogurtName")
                binding.textView9.text = it.getString("yogurtPrecio")
                binding.textView10.text = it.getString("yogurtDescripcion")

                val slideList = ArrayList<SlideModel>()

                for(data in list){
                    slideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
                }

                cartAction(yogId, name, yogurtPrecio,  it.getString("CoverImg") )

                binding.imageSlider.setImageList(slideList)

            }.addOnFailureListener(){
                Toast.makeText(this,"Algo salió mal", Toast.LENGTH_SHORT)
            }

    }

    private fun cartAction(proId: String, name: String?, yogurtPrecio: String?, coverImg: String?) {
        val yogurtDao = AppDatabase.getInstance(this).yogurtDao()
        if(yogurtDao.isExit(proId)!=null){
            binding.textView11.text = "Go to cart"
        }else{
            binding.textView11.text = "Add to cart"
        }

        binding.textView11.setOnClickListener {
            if(yogurtDao.isExit(proId)!=null){
                openCart()

            }else{

                addToCart(yogurtDao,proId,name,yogurtPrecio,coverImg)
            }
        }

    }

    private fun addToCart(
    yogurtDao: YogurtDao,
    proId: String,
    name: String?,
    yogurtPrecio: String?,
    coverImg: String?
    ) {
        val data = YogurtModel(proId,name,yogurtPrecio,coverImg)
        lifecycleScope.launch(Dispatchers.IO){
            yogurtDao.insertYogurt(data)
            binding.textView11.text = "Go to cart"

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