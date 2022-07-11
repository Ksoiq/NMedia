package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 0L,
            author = "Author",
            content = "Event",
            published = "01.01.1999"
        )

        binding.render(post)
        binding.activityPost.likeButton.setOnClickListener {
            post.likedByMe = !post.likedByMe
            binding.activityPost.likeButton.setImageResource(getLikedIconResId(post.likedByMe))
            post.likes = countLikeByMe(post.likedByMe, post.likes)
            binding.render(post)
        }

        binding.activityPost.shareButton.setOnClickListener {
            post.shares++
            binding.render(post)
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        activityPost.authorName.text = post.author
        activityPost.textField.text = post.content
        activityPost.date.text = post.published
        activityPost.likeCount.text = reductionNumbers(post.likes)
        activityPost.shareCount.text = reductionNumbers(post.shares)
        activityPost.likeButton.setImageResource(getLikedIconResId(post.likedByMe))
    }

    @DrawableRes
    private fun getLikedIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_liked_24 else R.drawable.ic_like_24


    private fun countLikeByMe(liked: Boolean, like: Int) =
        if (liked) like + 1 else like - 1

    private fun reductionNumbers(count: Int): String {
        return when (count) {
            in 0..999 -> count.toString()
            in 1000..1099 -> "${count / 1000}K"
            in 1100..9999 -> "${count / 1000}.${count / 100 % 10}K"
            in 10_000..999_999 -> "${count / 1000}K"
            in 1_000_000..1_099_999 -> "${count / 1_000_000}M"
            else -> "${(count / 10.pow(2)) / 10_000}.${((count / 1000) / 10.pow(2)) % 10}M"
        }
    }

    private fun Int.pow(x: Int): Int = (2..x).fold(this) { R, _ ->
        R * this
    }
}
