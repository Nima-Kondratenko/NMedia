package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = mutableListOf(
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях uD83DuDC47",
            published = "18 сентября в 10:12",
            likes = 20,
            likeByMe = false,
            shareCount = 0
        ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 10,
            likeByMe = false,
            shareCount = 0
        )
    )

    private val data = MutableLiveData<List<Post>>(posts)

    override fun get(): LiveData<List<Post>> {
        return data
    }

    override fun likeById(id: Long) {
        val postIndex = posts.indexOfFirst { it.id == id }
        if (postIndex != -1) {
            val post = posts[postIndex]
            val newLikes = if (post.likeByMe) post.likes - 1 else post.likes + 1
            posts[postIndex] = post.copy(likes = newLikes, likeByMe = !post.likeByMe)
            data.value = posts
        }
    }

    override fun share(postId: Long) {
        val postIndex = posts.indexOfFirst { it.id == postId }
        if (postIndex != -1) {
            val post = posts[postIndex]
            posts[postIndex] = post.copy(shareCount = post.shareCount + 1)
            data.value = posts
        }
    }
}
