package ru.netology.nmedia.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel(
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
) : ViewModel() {

    val posts: LiveData<List<Post>> = repository.get()

    fun like(postId: Long) {
        repository.likeById(postId)
    }

    fun share(postId: Long) {
        repository.share(postId)
    }
}
