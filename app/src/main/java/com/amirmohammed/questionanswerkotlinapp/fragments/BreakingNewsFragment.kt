package com.amirmohammed.questionanswerkotlinapp.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirmohammed.questionanswerkotlinapp.R
import com.amirmohammed.questionanswerkotlinapp.adapters.NewsAdapter
import com.amirmohammed.questionanswerkotlinapp.core.AppClass
import com.amirmohammed.questionanswerkotlinapp.databinding.FragmentBreakingNewsBinding
import com.amirmohammed.questionanswerkotlinapp.ui.NewsActivity
import com.amirmohammed.questionanswerkotlinapp.ui.NewsViewModel
import com.amirmohammed.questionanswerkotlinapp.util.Resource
import com.bumptech.glide.Glide

class BreakingNewsFragment : Fragment() {
    private val TAG = "BreakingNewsFragment"

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentBreakingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_breaking_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()



        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

//            val navController = Navigation.findNavController(requireView())
//            val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleNewsFragment(it)
//            navController.navigate(action)

            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleNewsFragment, bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data -> newsAdapter.differ.submitList(data.articles) }

                }

                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message -> Log.i(TAG, "onViewCreated: $message") }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }

            }

        })

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}