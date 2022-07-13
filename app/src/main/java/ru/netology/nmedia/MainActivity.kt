package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.data.impl.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(
            viewModel::onLikeClicked,
            viewModel::onShareClick
        )
        binding.postsRecyclerView.adapter = adapter

        viewModel.data.observe(this) { posts ->
             adapter.submitList(posts)
        }

    }


//    private fun ActivityMainBinding.render(posts: List<Post>) {
//        for (post in posts) {
//            PostBinding.inflate(
//                layoutInflater, root, true
//            ).render(post)
//        }
//    }

//    private fun PostBinding.render(post: Post) {
//        authorName.text = post.author
//        textField.text = post.content
//        date.text = post.published
//        likeCount.text = Counter.displayFormatting(post.likes)
//        shareCount.text = Counter.displayFormatting(post.shares)
//        likeButton.setImageResource(getLikedIconResId(post.likedByMe))
//        likeButton.setOnClickListener { viewModel.onLikeClicked(post) }
//        shareButton.setOnClickListener { viewModel.onShareClick(post) }
//    }

//    @DrawableRes
//    private fun getLikedIconResId(liked: Boolean) =
//        if (liked) R.drawable.ic_liked_24 else R.drawable.ic_like_24

}
