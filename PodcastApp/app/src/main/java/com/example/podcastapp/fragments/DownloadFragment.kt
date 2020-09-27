package com.example.podcastapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podcastapp.R
import com.example.podcastapp.activities.DetailsActivity
import com.example.podcastapp.adapters.DownloadedPodcastAdapter
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.mvp.presenters.DownloadPresenter
import com.example.podcastapp.mvp.presenters.MainPresenter
import com.example.podcastapp.mvp.presenters.impls.DownloadPresenterImpl
import com.example.podcastapp.mvp.views.DownloadView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_download.*
import kotlinx.android.synthetic.main.viewpod_empty.*
import kotlinx.android.synthetic.main.viewpod_podcast_list.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DownloadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DownloadFragment : Fragment() , DownloadView{

    private lateinit var mContext : Context
    private lateinit var mPresenter : DownloadPresenter
    private lateinit var mAdapter : DownloadedPodcastAdapter
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(this)
        setUpListeners()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(DownloadPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        mAdapter = DownloadedPodcastAdapter(mPresenter)
        rvDownloadedShows.adapter = mAdapter

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvDownloadedShows.layoutManager = layoutManager
    }

    private fun setUpListeners(){
        btnFindNew.setOnClickListener {
            mPresenter.onTapFindNew()
        }
        btnReload.setOnClickListener {
            mPresenter.onTapReload()
        }

        btnBack.setOnClickListener {
            mPresenter.onTapFindNew()
        }
    }

    override fun displayEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    override fun displayDownloadedShows(podcasts: List<PodcastVO>) {
        vpEmpty.visibility = View.GONE
        rvDownloadedShows.visibility = View.VISIBLE

        mAdapter.setNewData(podcasts)
    }

    override fun navigateToDetails(id: String) {
        startActivity(DetailsActivity.newIntent(mContext, id))
    }


    override fun navigateToUpNext() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.flContainer, HomeFragment())
            ?.commit()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}