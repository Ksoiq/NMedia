package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            binding.render(post)
        }


        binding.activityPost.likeButton.setOnClickListener {
            viewModel.onLikeClicked()
        }

        binding.activityPost.shareButton.setOnClickListener{
            viewModel.onShareClick()
        }

    }


    private fun ActivityMainBinding.render(post: Post) {
        activityPost.authorName.text = post.author
        activityPost.textField.text = post.content
        activityPost.date.text = post.published
        activityPost.likeCount.text = Counter.displayFormatting(post.likes)
        activityPost.shareCount.text = Counter.displayFormatting(post.shares)
        activityPost.likeButton.setImageResource(getLikedIconResId(post.likedByMe))
    }

    @DrawableRes
    private fun getLikedIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_liked_24 else R.drawable.ic_like_24

}
