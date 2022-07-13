package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData(
        List(1000) { index ->
            Post(
                id = index + 1L,
                author = "Netology",
                content = "Event $index",
                published = "01.01.1999"
            )
        }
    )

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                likedByMe = !it.likedByMe,
                likes = countLikeByMe(it.likedByMe, it.likes)
            )
        }
    }

    override fun share(postId: Long) {

        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                shares = it.shares + 1
            )
        }
    }

    private fun countLikeByMe(liked: Boolean, like: Int) =
        if (liked) like - 1 else like + 1

}