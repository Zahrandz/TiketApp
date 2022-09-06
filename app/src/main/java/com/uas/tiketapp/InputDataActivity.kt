package com.uas.tiketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.uas.tiketapp.databinding.ActivityInputDataBinding
import com.google.firebase.auth.FirebaseAuth



private lateinit var auth: FirebaseAuth
private lateinit var binding: ActivityInputDataBinding

class InputDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)

        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        btnCheckout.setOnClickListener {
            val inputNama    = binding.inputNama.text.toString().trim()
            val inputTanggal = binding.inputTanggal.text.toString().trim()
            val inputTelepon = binding.inputTelepon.text.toString().trim()

            if(inputNama.isEmpty()){
                binding.inputNama.error = "Masukkan Nama Penumpang"
                binding.inputNama.requestFocus()
                return@setOnClickListener
            }
            if(inputTanggal.isEmpty()){
                binding.inputTanggal.error = "Masukkan Tanggal Keberangkatan"
                binding.inputTanggal.requestFocus()
                return@setOnClickListener
            }
            if(inputTelepon.isEmpty()){
                binding.inputTelepon.error = "Masukkan Nomor Telepon"
                binding.inputTelepon.requestFocus()
                return@setOnClickListener
            }

//            checkout(inputNama,inputTanggal,inputTelepon)
        }
        binding.toolbar.setOnClickListener {
            Intent(this@InputDataActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }
    }

}