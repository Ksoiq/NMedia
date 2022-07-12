package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData(
        Post(
            id = 0L,
            author = "Author",
            content = "Event",
            published = "01.01.1999"
        )
    )

    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "Data value should not be null"
        }
        val likedPost = currentPost.copy(
            likedByMe = !currentPost.likedByMe,
            likes = countLikeByMe(!currentPost.likedByMe, currentPost.likes)
        )
        data.value = likedPost
    }

    override fun share() {
        val currentPost = checkNotNull(data.value) {
            "Data value should not be null"
        }
        val sharedPost = currentPost.copy(
            shares = currentPost.shares + 1
        )
        data.value = sharedPost
    }


    private fun countLikeByMe(liked: Boolean, like: Int) =
        if (liked) like + 1 else like - 1

}