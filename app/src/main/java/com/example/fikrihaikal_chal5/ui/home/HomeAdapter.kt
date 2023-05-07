package com.example.fikrihaikal_chal5.ui.home

import ResultsItem
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.example.fikrihaikal_chal5.R
import com.example.fikrihaikal_chal5.databinding.ItemListBinding


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(
            oldItem: ResultsItem,
            newItem: ResultsItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResultsItem,
            newItem: ResultsItem
        ): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder =
        ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem) {
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            binding.name.text = item.title
            Glide.with(itemView).load(imageUrl + item.backdropPath).into(binding.photo)
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(
                        R.id.action_homeFragment2_to_detailFragment2,
                        Bundle().apply { putInt("MOVIE_ID", item.id) })
            }
        }
    }
}