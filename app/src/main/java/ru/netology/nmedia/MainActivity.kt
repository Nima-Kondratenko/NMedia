package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        published = "21 мая в 18:36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likeByMe = false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Установка обработчиков на элементы интерфейса
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = formatCount(post.likes)
            shareCount.text = formatCount(post.shares)
            like.setImageResource(R.drawable.ic_like_24)

            // Обработчик на кнопку Like
            like.setOnClickListener {
                // Точка останова
                post.likeByMe = !post.likeByMe
                if (post.likeByMe) {
                    post.likes += 1
                    like.setImageResource(R.drawable.ic_liked_24)
                } else {
                    post.likes -= 1
                    like.setImageResource(R.drawable.ic_like_24)
                }
                likeCount.text = formatCount(post.likes)
            }

            // Обработчик на текст поста
            content.setOnClickListener {
                // Точка останова
            }

            // Обработчик на аватар
            avatars.setOnClickListener {
                // Точка останова
            }

            // Обработчик на меню
            menu.setOnClickListener {
                // Точка останова
            }

            // Обработчик на корневой элемент
            binding.root.setOnClickListener {
                // Точка останова
            }

            share.setOnClickListener {
                post.shares += 1
                shareCount.text = formatCount(post.shares)
            }
        }
    }

    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10_000 -> String.format("%.1fK", count / 1000.0).replace(",", ".")
            count < 1_000_000 -> "${count / 1000}K"
            else -> String.format("%.1fM", count / 1_000_000.0).replace(",", ".")
        }
    }
}

