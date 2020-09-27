package com.example.podcastapp.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.podcastapp.adapters.PodcastAdapter
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.PodcastDelegate
import kotlinx.android.synthetic.main.viewpod_player.view.*
import kotlinx.android.synthetic.main.viewpod_podcast_list.view.*

class PodcastListViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var mPodcastAdapter : PodcastAdapter
    lateinit var mDelegate : PodcastDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()

    }



    private fun setUpRecyclerView(){
        val upNextLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvUpNextList.layoutManager = upNextLayoutManager

        mPodcastAdapter = PodcastAdapter(mDelegate)
        rvUpNextList.adapter = mPodcastAdapter
    }

    fun displayUpNextList(podcasts : List<PodcastVO>){
        mPodcastAdapter.setNewData(podcasts)
    }

    fun setDelegate(delegate: PodcastDelegate){
        mDelegate = delegate

        setUpRecyclerView()
    }

}