package com.cc.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.cc.codingchallenge.data.MyApi
import com.cc.codingchallenge.data.models.StocksResponse
import com.cc.codingchallenge.databinding.ActivityDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        title = "YH Stock"


        setupList()

    }


    private fun setupList() {
        GlobalScope.launch(Dispatchers.Default) {
            var symbol:String = intent.getStringExtra("symbol").toString()
            val regex = Regex("[^A-Za-z0-9]")
            symbol = regex.replace(symbol, "")
            try {

                val response: StocksResponse = MyApi().getStocksData(symbol)

                runOnUiThread {
                    binding.detailTV.apply {
                            text = response.defaultKeyStatistics.sharesOutstanding.toString()
                    }
                }
            }catch (e: Exception) {
                Log.d("Error", e.message.toString())
                runOnUiThread {
                    binding.detailTV.apply {
                        text = e.message.toString()
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}