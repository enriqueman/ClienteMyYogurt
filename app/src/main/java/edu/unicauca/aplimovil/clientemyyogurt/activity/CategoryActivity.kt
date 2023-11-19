package edu.unicauca.aplimovil.clientemyyogurt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.adapter.CategoryProductAdapter
import edu.unicauca.aplimovil.clientemyyogurt.adapter.YogurtAdapter
import edu.unicauca.aplimovil.clientemyyogurt.model.AddYogurtModel

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        getProducts(intent.getStringExtra("cate"))
    }

    private fun getProducts(category: String?) {
        val list= ArrayList<AddYogurtModel>()
        Firebase.firestore.collection("noticias").whereEqualTo("yogurtCategoria", category)
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
                }
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter= CategoryProductAdapter(this,list)
            }
    }
}