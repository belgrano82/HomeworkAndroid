package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentViewPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class ViewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPostBinding.inflate(
            inflater, container, false
        )

        val post = Post(
            id = 0,
            content = "",
            author = "",
            likedByMe = false,
            likes = 0,
            shares = 0,
            views = 0,
            video = "",
            published = null
        )


        post.id = requireArguments().getLong("postId")

        val viewPost = viewModel.findById(post.id)


        if (viewPost != null) {
            binding.content.text = viewPost.content
            binding.author.text = viewPost.author
            binding.published.text = viewPost.published.toString()
            binding.like.text = viewPost.likes.toString()
            binding.share.text = viewPost.shares.toString()
            binding.like.isChecked = viewPost.likedByMe
            binding.viewsCount.text = viewPost.views.toString()
        }


        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.remove -> {
                            findNavController().navigateUp()
                            viewModel.removeById(post.id)

                            true
                        }
                        R.id.edit -> {
                            viewModel.edit(post)
                            viewModel.changeContent(binding.content.text.toString())
                            findNavController().navigate(
                                R.id.action_postViewFragment_to_newPostFragment
                            )
                            val result = binding.content.text
                            setFragmentResult("editText", bundleOf("textEdit" to result))

                            setFragmentResultListener("editText2") { editText2, bundle ->
                                val result2 = bundle.getString("textEdit2")
                                binding.content.text = result2
                            }

                            true
                        }
                        else -> false
                    }
                }
            }.show()
        }

        arguments?.textArg?.let(binding.author::setText)

        return binding.root
    }
}