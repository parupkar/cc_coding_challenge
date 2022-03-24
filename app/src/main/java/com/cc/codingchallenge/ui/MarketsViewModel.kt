package com.cc.codingchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.cc.codingchallenge.data.MyApi

class MarketsViewModel(
    private val api: MyApi
) : ViewModel() {
    val marketItems =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2), pagingSourceFactory = {
            MarketsDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
