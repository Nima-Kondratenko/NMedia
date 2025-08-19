import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: LiveData<List<Post>> = repository.get()

    fun like(postId: Long) {
        repository.likeById(postId)
    }

    fun share(postId: Long) {
        repository.share(postId)
    }
}
