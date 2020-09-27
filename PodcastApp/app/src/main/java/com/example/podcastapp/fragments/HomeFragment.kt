package com.example.podcastapp.fragments

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.podcastapp.R
import com.example.podcastapp.activities.DetailsActivity
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.mvp.presenters.MainPresenter
import com.example.podcastapp.mvp.presenters.impls.MainPresenterImpl
import com.example.podcastapp.mvp.views.MainView
import com.example.podcastapp.views.viewpods.PlayerViewpod
import com.example.podcastapp.views.viewpods.PodcastListViewpod
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() , MainView{

    lateinit var mPodcastListViewpod: PodcastListViewpod
    lateinit var mPlayerViewpod: PlayerViewpod
    private lateinit var mPresenter : MainPresenter
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    lateinit var mContext : Context

    private var REQUEST_CODE = 100
    private var myDownloadId : Long = 0
    private var mDownloadedPodcast : PodcastVO = PodcastVO()
    private val br = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 1)
            if(id == myDownloadId){
                activity?.let {
                    Toast.makeText(it.applicationContext, "Download Complete", Toast.LENGTH_SHORT).show()
                    mPresenter.saveDownloadedPodcast(mDownloadedPodcast)
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpViewpod()
        initBroadCastReceiver()
        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun displayPlayer(podcast: PodcastVO) {
        mPlayerViewpod.displayPlayer(podcast)
    }

    override fun displayUpNextList(podcasts: List<PodcastVO>) {
        mPodcastListViewpod.displayUpNextList(podcasts)
    }

    override fun navigateToDetails(id: String) {
        startActivity(DetailsActivity.newIntent(mContext, id))
    }

    override fun downloadPodcast(podcast: PodcastVO) {
        mDownloadedPodcast = podcast
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE
            )
        } else {
            download(podcast.audio)
        }
    }

    private fun initBroadCastReceiver(){
        context?.registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun download(uri : String){
        val request = DownloadManager.Request(
            Uri.parse(uri)
        )
            .setTitle("Downloading Audio")
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS,"PodCastAudio.mp3")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
        val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        myDownloadId = dm.enqueue(request)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> if (grantResults.isNotEmpty() &&
                grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                download(mDownloadedPodcast.audio)
            }
        }
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setUpViewpod(){
        mPlayerViewpod = vpPlayer as PlayerViewpod

        mPodcastListViewpod = vpUpNextList as PodcastListViewpod
        mPodcastListViewpod.setDelegate(mPresenter)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }


    override fun onStart() {
        super.onStart()
        //mPlayerViewpod.initializePlayer()
    }


    override fun onStop() {
        mPlayerViewpod.releasePlayer()
        super.onStop()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}