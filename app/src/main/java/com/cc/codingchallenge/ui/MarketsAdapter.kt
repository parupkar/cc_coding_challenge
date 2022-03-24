package com.cc.codingchallenge.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cc.codingchallenge.data.models.ResponseResult
import com.cc.codingchallenge.databinding.ItemMarketBinding

class MarketsAdapter :
    PagingDataAdapter<ResponseResult, MarketsAdapter.MarketsViewHolder>(MarketsComparator) {

    lateinit var mContext: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketsViewHolder {        mContext = parent.context

        return MarketsViewHolder(
            ItemMarketBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MarketsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
        holder.itemView.setOnClickListener {
                    val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("symbol", item?.symbol)
            mContext.startActivity(intent)
            Log.d("Selected:", item?.symbol.toString())
        }
    }

    inner class MarketsViewHolder(private val binding: ItemMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassenger(item: ResponseResult) = with(binding) {
            titleTV.text = item.fullExchangeName
            dataTV.text = item.toString()
        }
    }

    object MarketsComparator : DiffUtil.ItemCallback<ResponseResult>() {
        override fun areItemsTheSame(oldItem: ResponseResult, newItem: ResponseResult): Boolean {
            return oldItem.fullExchangeName == newItem.fullExchangeName
        }

        override fun areContentsTheSame(oldItem: ResponseResult, newItem: ResponseResult): Boolean {
            return oldItem == newItem
        }
    }
}