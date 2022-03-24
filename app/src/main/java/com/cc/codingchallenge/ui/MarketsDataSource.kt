package com.cc.codingchallenge.ui

import android.util.Log
import androidx.paging.PagingSource
import com.cc.codingchallenge.data.MyApi
import com.cc.codingchallenge.data.models.MarketsResponse
import com.cc.codingchallenge.data.models.ResponseResult

class MarketsDataSource(private val api: MyApi) : PagingSource<Int, ResponseResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseResult> {

        return try {
            val nextPageNumber = params.key ?: 1
            val response: MarketsResponse = api.getMarketsData()
            Log.d("TAG", "message")

            LoadResult.Page(
                data = response.marketSummaryAndSparkResponse.result,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 20) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}