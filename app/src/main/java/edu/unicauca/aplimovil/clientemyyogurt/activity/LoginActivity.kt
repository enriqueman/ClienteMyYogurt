package edu.unicauca.aplimovil.clientemyyogurt.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.unicauca.aplimovil.clientemyyogurt.R
import edu.unicauca.aplimovil.clientemyyogurt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }
        
        binding.button4.setOnClickListener { 
            if (binding.userNumber.text!!.isEmpty())
                Toast.makeText(this,"Por favor ingrese un numero",Toast.LENGTH_SHORT).show()
            else
                sendOtp(binding.userNumber.text.toString())

        }
    }

    private fun sendOtp(toString: String) {

    }
}