package com.dicoding.capstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstone.data.local.model.ResultModel
import com.dicoding.capstone.databinding.ItemResultBinding

class ResultAdapter(private val results: List<ResultModel>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    class ResultViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results[position]
        with(holder.binding) {
            tvNama.text = result.name
            tvStatus.text = result.status
            Glide.with(root.context)
                .load(result.photoUrl)
                .into(ivPhoto)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
}

