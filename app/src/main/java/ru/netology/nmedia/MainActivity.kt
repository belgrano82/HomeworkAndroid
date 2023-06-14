package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.formatNumber
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->

            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )


                likeCount.text = formatNumber(post.likes)
                shareCount.text = formatNumber(post.shares)
                viewsCount.text = formatNumber(post.views)

                root.setOnClickListener {
                    Log.d("stuff", "stuff")
                }

                avatar.setOnClickListener {
                    Log.d("stuff", "avatar")
                }


            }
        }
        binding.like.setOnClickListener {
            Log.d("stuff", "like")
            viewModel.like()
        }

        binding.share.setOnClickListener {
            Log.d("stuff", "share")
viewModel.share()
        }

        binding.views.setOnClickListener {
            Log.d("stuff", "views")
            viewModel.view()
        }
    }
}