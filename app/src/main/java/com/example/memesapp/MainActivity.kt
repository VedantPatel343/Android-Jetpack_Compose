package com.example.memesapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.memesapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    // https://meme-api.com/gimme
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.nextMemeBtn.setOnClickListener {
            getData()
        }

    }

    private fun getData() {

        val progressDialog = ProgressDialog(this)
        progressDialog.show()

        RetrofitInstance.apiInterface.getMeme().enqueue(object : Callback<MemeModel?> {
            override fun onResponse(call: Call<MemeModel?>, response: Response<MemeModel?>) {

            Glide.with(this@MainActivity).load(response.body()?.url).into(binding.imageView)
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<MemeModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong.", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        })

    }
}