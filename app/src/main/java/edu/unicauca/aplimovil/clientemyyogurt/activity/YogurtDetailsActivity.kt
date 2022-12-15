package edu.unicauca.aplimovil.clientemyyogurt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.databinding.ActivityYogurtDetailsBinding

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
                binding.textView8.text = it.getString("yogurtName")
                binding.textView9.text = it.getString("yogurtPrecio")
                binding.textView10.text = it.getString("yogurtDescripcion")

                val slideList = ArrayList<SlideModel>()

                for(data in list){
                    slideList.add(SlideModel(data, ScaleTypes.CENTER_CROP))
                }

                binding.imageSlider.setImageList(slideList)

            }.addOnFailureListener(){
                Toast.makeText(this,"Algo salió mal", Toast.LENGTH_SHORT)
            }

    }
}