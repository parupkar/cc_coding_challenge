package com.cc.codingchallenge.data.models


data class MarketData(
    val result: List<ResponseResult>
)
data class ResponseResult(
    val fullExchangeName: String,
    val shortname: String,
    val quoteType: String,
    val symbol: String,
    val index: String,
    val score: Int,
    val typeDisp: String,
    val longname: String,
    val exchDisp: String,
    val isYahooFinance: Boolean
)