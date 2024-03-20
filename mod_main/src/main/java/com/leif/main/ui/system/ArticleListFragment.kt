package com.leif.main.ui.system

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.leif.common.constant.KEY_ID
import com.leif.common.provider.LoginServiceProvider
import com.leif.common.provider.MainServiceProvider
import com.leif.framework.base.BaseMvvmFragment
import com.leif.framework.decoration.NormalItemDecoration
import com.leif.framework.toast.TipsToast
import com.leif.framework.utils.dpToPx
import com.leif.main.databinding.FragmentArticleListBinding
import com.leif.main.ui.system.adapter.ArticleAdapter
import com.leif.main.ui.system.viewmodel.ArticleListViewModel
import com.leif.network.error.ERROR
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author mingyan.su
 * @date   2023/3/21 18:24
 * @desc   普通文件列表
 */
class ArticleListFragment : BaseMvvmFragment<FragmentArticleListBinding>(),
    OnRefreshListener, OnLoadMoreListener {
    private var page = 0
    private var cId = 0
    private lateinit var mAdapter: ArticleAdapter
    private val mViewModel: ArticleListViewModel by viewModel()

    companion object {
        fun newInstance(id: Int): ArticleListFragment {
            val args = Bundle()
            args.putInt(KEY_ID, id)
            val fragment = ArticleListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.refreshLayout?.apply {
            setEnableRefresh(true)
            setEnableLoadMore(true)
            setOnRefreshListener(this@ArticleListFragment)
            setOnLoadMoreListener(this@ArticleListFragment)
            autoRefresh()
        }
        mAdapter = ArticleAdapter()
        val dp12 = dpToPx(12)
        mBinding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            addItemDecoration(NormalItemDecoration().apply {
                setBounds(left = dp12, top = dp12, right = dp12, bottom = dp12)
                setLastBottom(true)
            })
        }
        mAdapter.onItemClickListener = { _: View, position: Int ->
            val item = mAdapter.getItem(position)
            if (item != null && !item.link.isNullOrEmpty()) {
                MainServiceProvider.toArticleDetail(
                    context = requireContext(),
                    url = item.link!!,
                    title = item.title ?: ""
                )
            }
        }
        mAdapter.onItemCollectListener = { _: View, position: Int ->
            if (LoginServiceProvider.isLogin()) {
                setCollectView(position)
            } else {
                LoginServiceProvider.login(requireContext())
            }
        }
    }

    override fun initData() {
        cId = arguments?.getInt(KEY_ID, 0) ?: 0
        mViewModel.articleListLiveData.observe(this) {

            if (page == 0) {
                mAdapter.setData(it)
                if (it.isNullOrEmpty()) {
                    //空视图

                }
                mBinding?.refreshLayout?.finishRefresh()
            } else {
                mAdapter.addAll(it)
                mBinding?.refreshLayout?.finishLoadMore()
            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        getArticleList()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
        getArticleList()
    }

    /**
     * 获取文章列表数据
     */
    private fun getArticleList() {
        mViewModel.getArticleList(page, cId)
    }

    /**
     * 收藏和取消收藏
     * @param position
     */
    private fun setCollectView(position: Int) {
        val data = mAdapter.getItem(position)
        data?.let { item ->
            showLoading()
            val collect = item.collect ?: false
            mViewModel.collectArticle(item.id, collect).observe(this) {
                dismissLoading()
                it?.let {
                    val tipsRes =
                        if (collect) com.leif.common.R.string.collect_cancel else com.leif.common.R.string.collect_success
                    TipsToast.showSuccessTips(tipsRes)
                    item.collect = !collect
                    mAdapter.updateItem(position, item)
                }

                if (it == ERROR.UNLOGIN.code){
                    LoginServiceProvider.login(requireContext())
                }
            }
        }
    }

}