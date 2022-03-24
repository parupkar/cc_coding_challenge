package com.cc.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cc.codingchallenge.data.MyApi
import com.cc.codingchallenge.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.net.sip.SipErrorCode.TIME_OUT

import android.content.Intent
import android.net.sip.SipErrorCode
import android.os.Handler


class MainActivity : AppCompatActivity() {

    lateinit var marketsViewModel: MarketsViewModel
    lateinit var marketsAdapter: MarketsAdapter
    private lateinit var binding: ActivityMainBinding
    private val TIME_OUT = 8000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        title = "YH Finance"

        setupViewModel()
        setupList()
        setupView()

        //Handling Refresh Activity after every 8 seconds
        /*Handler().postDelayed(Runnable {
            finish();
            overridePendingTransition( 0, 0);
            startActivity(getIntent());
            overridePendingTransition( 0, 0);
        }, TIME_OUT.toLong())*/
    }

    private fun setupViewModel() {
        val factory = MarketsViewModelFactory(MyApi())
        marketsViewModel = ViewModelProvider(this, factory).get(MarketsViewModel::class.java)
    }

    private fun setupList() {
        marketsAdapter = MarketsAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marketsAdapter.withLoadStateHeaderAndFooter(
                header = MarketsLoadStateAdapter { marketsAdapter.retry() },
                footer = MarketsLoadStateAdapter { marketsAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun setupView() {
        lifecycleScope.launch {
            marketsViewModel.marketItems.collectLatest { pagedData ->
                marketsAdapter.submitData(pagedData)
            }
        }
    }
}