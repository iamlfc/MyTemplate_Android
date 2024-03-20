package com.leif.main.ui.home

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.leif.common.constant.KEY_VIDEO_PLAY_LIST
import com.leif.common.constant.VIDEO_ACTIVITY_PLAYER
import com.leif.framework.decoration.StaggeredItemDecoration
import com.leif.framework.base.BaseMvvmFragment
import com.leif.framework.ext.gone
import com.leif.framework.ext.visible
import com.leif.framework.toast.TipsToast
import com.leif.framework.utils.dpToPx
import com.leif.main.databinding.FragmentHomeVideoBinding
import com.leif.main.ui.home.adapter.HomeVideoItemAdapter
import com.leif.main.ui.home.viewmodel.HomeViewModel
import com.leif.room.entity.VideoInfo
import com.tbruyelle.rxpermissions3.RxPermissions
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

/**
 * @author mingyan.su
 * @date   2023/3/5 20:11
 * @desc   首页视频列表
 */
class HomeVideoFragment : BaseMvvmFragment<FragmentHomeVideoBinding>() {
    lateinit var videoAdapter: HomeVideoItemAdapter
    private val mViewModel: HomeViewModel by viewModel()

    override fun initView(view: View, savedInstanceState: Bundle?) {

        val spanCount = 2
        val manager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        videoAdapter = HomeVideoItemAdapter(requireContext())
        mBinding?.recyclerView?.apply {
            layoutManager = manager
            addItemDecoration(StaggeredItemDecoration(dpToPx(10)))
            adapter = videoAdapter
        }

        videoAdapter.onItemClickListener = { view: View, position: Int ->
            RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).subscribe { granted ->
                if (granted) {
                    ARouter.getInstance().build(VIDEO_ACTIVITY_PLAYER)
                            .withParcelableArrayList(KEY_VIDEO_PLAY_LIST, videoAdapter.getData() as ArrayList<VideoInfo>)
                            .navigation()
                } else {
                    TipsToast.showTips(com.leif.common.R.string.default_agree_permission)
                }
            }
        }
    }

    override fun initData() {
        showLoading()
        mViewModel.getVideoList(requireContext().assets).observe(this) {
            dismissLoading()
            if (it.isNullOrEmpty()) {
                mBinding?.viewEmptyData?.visible()
            } else {
                mBinding?.viewEmptyData?.gone()
                videoAdapter.setData(it)
            }
        }
    }
}