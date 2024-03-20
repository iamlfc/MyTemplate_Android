package com.leif.main.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.leif.common.model.CategorySecondItem
import com.leif.framework.adapter.BaseBindViewHolder
import com.leif.framework.adapter.BaseRecyclerViewAdapter
import com.leif.framework.utils.ViewUtils
import com.leif.framework.utils.dpToPx
import com.leif.main.databinding.LayoutCategorySecondItemBinding

/**
 * @author mingyan.su
 * @date   2023/3/19 22:45
 * @desc   分类二级Adapter
 */
class CategorySecondItemAdapter : BaseRecyclerViewAdapter<CategorySecondItem, LayoutCategorySecondItemBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutCategorySecondItemBinding {
        return LayoutCategorySecondItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutCategorySecondItemBinding>,
        item: CategorySecondItem?,
        position: Int
    ) {
        holder.binding?.apply {
            tvTitle.text = item?.title
            ViewUtils.setClipViewCornerRadius(tvTitle, dpToPx(8))
        }

    }
}