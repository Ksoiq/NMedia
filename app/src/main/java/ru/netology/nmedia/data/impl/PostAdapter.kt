package ru.netology.nmedia.data.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Counter
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding

typealias OnPostLikeClicked = (Post) -> Unit
typealias OnPostShareClicked = (Post) -> Unit

internal class PostAdapter(
    private val onLikeClicked: OnPostLikeClicked,
    private val onShareClicked: OnPostShareClicked
) : ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: PostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            binding.likeButton.setOnClickListener { onLikeClicked(post) }
            binding.shareButton.setOnClickListener { onShareClicked(post) }
        }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                textField.text = post.content
                date.text = post.published
                likeCount.text = Counter.displayFormatting(post.likes)
                shareCount.text = Counter.displayFormatting(post.shares)
                likeButton.setImageResource(getLikedIconResId(post.likedByMe))
            }
        }

        @DrawableRes
        private fun getLikedIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_liked_24 else R.drawable.ic_like_24

    }

    private object DiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem

    }

}