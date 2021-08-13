package com.amirmohammed.questionanswerkotlinapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirmohammed.questionanswerkotlinapp.R
import com.amirmohammed.questionanswerkotlinapp.adapters.NewsAdapter
import com.amirmohammed.questionanswerkotlinapp.databinding.FragmentSearchNewsBinding
import com.amirmohammed.questionanswerkotlinapp.ui.NewsActivity
import com.amirmohammed.questionanswerkotlinapp.ui.NewsViewModel
import com.amirmohammed.questionanswerkotlinapp.util.Constants
import com.amirmohammed.questionanswerkotlinapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment() {
    private val TAG = "SearchNewsFragment"
    lateinit var binding: FragmentSearchNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_news, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
//        binding.etSearch.addTextChangedListener{ editable ->
//            job?.cancel()
//            job = MainScope().launch {
//                delay(Constants.SEARCH_NEWS_TIME_DELAY)
//                editable?.let{
//                    if (editable.toString().isNotEmpty()){
//                        viewModel.searchNews(editable.toString())
//                    }
//                }
//            }
//        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                job?.cancel()
                job = MainScope().launch {
                    delay(Constants.SEARCH_NEWS_TIME_DELAY)
                    if (s.toString().isNotEmpty()) {
                        viewModel.searchNews(s.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleNewsFragment,
                bundle
            )
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data ->
                        newsAdapter.differ.submitList(data.articles)
                    }
                    Log.i(TAG, "onViewCreated: success")

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
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}