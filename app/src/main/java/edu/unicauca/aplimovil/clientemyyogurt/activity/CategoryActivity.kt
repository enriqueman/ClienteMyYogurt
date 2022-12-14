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
        Firebase.firestore.collection("yogures").whereEqualTo("yogurtCategoria", category)
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(AddYogurtModel::class.java)
                    list.add(data!!)
                }
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter= CategoryProductAdapter(this,list)
            }
    }
}