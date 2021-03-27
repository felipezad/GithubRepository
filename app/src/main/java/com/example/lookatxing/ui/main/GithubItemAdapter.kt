package com.example.lookatxing.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.lookatxing.databinding.GithubItemListBinding
import com.example.lookatxing.domain.github.Github

class GithubItemAdapter(
    private val requestManager: RequestManager
) : ListAdapter<Github, GithubItemAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Github>() {
            override fun areItemsTheSame(oldItem: Github, newItem: Github): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Github, newItem: Github): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = GithubItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            requestManager = requestManager
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: GithubItemListBinding,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(gitItem: Github) {
            binding.githubName.text = gitItem.nameRepository
        }
    }

}