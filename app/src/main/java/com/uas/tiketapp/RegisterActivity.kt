package com.uas.tiketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.uas.tiketapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: ActivityRegisterBinding
private lateinit var auth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backLogin = findViewById<Button>(R.id.btnBackLogin)
        val signUp = findViewById<Button>(R.id.btnSignUp)
        auth = FirebaseAuth.getInstance()

        signUp.setOnClickListener{
            val email = binding.email.text.toString().trim()
            val pass = binding.Password.text.toString().trim()

            if (email.isEmpty()){
                binding.email.error = "Email harus diisi"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Email tidak valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (pass.isEmpty() || pass.length<6){
                binding.Password.error = "Password harus lebih dari 6 karakter"
                binding.Password.requestFocus()
                return@setOnClickListener
            }

            registerUser(email,pass)
        }

        backLogin.setOnClickListener{
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }
    }

    private fun registerUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@RegisterActivity, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@RegisterActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}