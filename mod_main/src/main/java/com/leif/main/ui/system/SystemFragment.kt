package com.leif.main.ui.system

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.leif.framework.base.BaseMvvmFragment
import com.leif.framework.decoration.NormalItemDecoration
import com.leif.framework.ext.toJson
import com.leif.framework.ext.visible
import com.leif.framework.utils.dpToPx
import com.leif.main.databinding.FragmentSystemBinding
import com.leif.main.ui.system.adapter.SystemAdapter
import com.leif.main.ui.system.viewmodel.SystemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author mingyan.su
 * @date   2023/3/3 8:18
 * @desc   体系
 */
class SystemFragment : BaseMvvmFragment<FragmentSystemBinding>() {
    private lateinit var mAdapter: SystemAdapter
    private val mViewModel: SystemViewModel by viewModel()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = SystemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(NormalItemDecoration().apply {
                setBounds(left = dpToPx(8), top = dpToPx(10), right = dpToPx(8), bottom = dpToPx(10))
                setLastBottom(true)
            })
        }
        mAdapter.onItemClickListener = { view: View, position: Int ->
            val item = mAdapter.getItem(position)
            ArticleTabActivity.startIntent(requireContext(), item?.toJson(true))
        }
    }

    override fun initData() {
        showLoading()
        mViewModel.getSystemList().observe(this) {
            mAdapter.setData(it)
            dismissLoading()
        }
        mViewModel.errorListLiveData.observe(this) {
            //空数据视图
            mBinding?.viewEmptyData?.visible()
        }
    }
}