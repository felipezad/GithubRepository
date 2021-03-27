package com.example.lookatxing.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lookatxing.R
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
            binding.githubDescription.text = gitItem.description
            binding.githubOwner.text = gitItem.owner

            if (gitItem.fork) {
                with(binding.itemContainer) {
                    setBackgroundColor(resources.getColor(R.color.teal_200, null))
                }
            }

            binding.itemContainer.setOnLongClickListener { view ->
                explicitIntentForBrowser(view.context, gitItem.nameRepository, gitItem.owner)
                true
            }

            requestManager
                .load(gitItem.ownerAvatarURL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.githubProfilePicture)
                .clearOnDetach()
        }

        private fun explicitIntentForBrowser(
            context: Context,
            githubRepo: String,
            ownerLogin: String
        ) {
            val githubWeb: Uri = Uri.parse("http://github.com/$ownerLogin/$githubRepo")
            val intent = Intent(Intent.ACTION_VIEW, githubWeb)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }
}