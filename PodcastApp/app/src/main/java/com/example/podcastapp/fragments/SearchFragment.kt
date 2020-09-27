package com.example.podcastapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podcastapp.R
import com.example.podcastapp.adapters.CategoryAdapter
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.mvp.presenters.SearchPresenter
import com.example.podcastapp.mvp.presenters.impls.SearchPresenterImpl
import com.example.podcastapp.mvp.views.SearchView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_search.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), SearchView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter : SearchPresenter
    private lateinit var categoryAdapter : CategoryAdapter
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(SearchPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun displayCategories(categories: List<GenresVO>) {
        categoryAdapter.setNewData(categories)

        tvSearch.text = categories.first().name
    }

    private fun setUpRecyclerView(){
        val categoriesLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.layoutManager = categoriesLayoutManager

        categoryAdapter = CategoryAdapter()
        rvCategories.adapter = categoryAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}