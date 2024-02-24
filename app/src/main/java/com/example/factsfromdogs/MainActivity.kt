package com.example.factsfromdogs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make API call
        callApi()

        findViewById<Button>(R.id.button).setOnClickListener{
            callApi()
        }
    }

    fun callApi(){
        val call = RetrofitClient.dogApiService.getRandomDogFact()
        call.enqueue(object : Callback<DogFactResponse> {
            override fun onResponse(call: Call<DogFactResponse>, response: Response<DogFactResponse>) {
                if (response.isSuccessful) {
                    val dogFact = response.body()
                    dogFact?.let {
                        findViewById<TextView>(R.id.fact).text = it.facts[0]
                    }
                }
            }

            override fun onFailure(call: Call<DogFactResponse>, t: Throwable) {
                // Handle failure
                findViewById<TextView>(R.id.fact).text = "Falha"
                t.printStackTrace()
            }
        })
    }
}