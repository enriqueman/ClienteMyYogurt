package edu.unicauca.aplimovil.clientemyyogurt.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {

            openLogin()

        }

        binding.button5.setOnClickListener {
            validateUser()
        }
    }

    private fun validateUser() {
        if (binding.Username.text!!.isEmpty() || binding.userNumber.text!!.isEmpty())
            Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show()
        else
            storeData()
    }

    private fun storeData() {
        var builder =AlertDialog.Builder(this)
            .setTitle("Cargando... ")
            .setMessage("Por favor espere")
            .setCancelable(false)
            .create()
        builder.show()
        val data = hashMapOf<String,Any>()
        data["name"]=binding.Username.text.toString()
        data["number"]=binding.userNumber.text.toString()

        Firebase.firestore.collection("users").document(binding.Username.text.toString())
            .set(data).addOnSuccessListener {
                Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT).show()
                openLogin()

            }
            .addOnFailureListener {
                Toast.makeText(this,"Algo salio mal",Toast.LENGTH_SHORT).show()

            }

    }

    private fun openLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()

    }
}