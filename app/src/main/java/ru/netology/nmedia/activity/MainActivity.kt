package ru.netology.nmedia.activity

import PostViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "${this.hashCode()}")

        viewModel.posts.observe(this) { posts ->
            binding.container.removeAllViews()
            posts.forEach { post ->
                val cardPostBinding =
                    CardPostBinding.inflate(layoutInflater, binding.container, true)
                with(cardPostBinding) {
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likeCount.text = formatCount(post.likes)
                    shareCount.text = formatCount(post.shareCount)

                    like.setImageResource(if (post.likeByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)

                    like.setOnClickListener {
                        viewModel.like(post.id) // Убедитесь, что метод like() существует в ViewModel
                    }
                }
            }
        }

        setupListeners()
    }

    private fun setupListeners() {
    }

    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10_000 -> String.format("%.1fK", Math.floor(count / 1000.0 * 10) / 10)
                .replace(",", ".")

            count < 1_000_000 -> "${count / 1000}K"
            else -> String.format("%.1fM", Math.floor(count / 1_000_000.0 * 10) / 10)
                .replace(",", ".")
        }
    }
}
