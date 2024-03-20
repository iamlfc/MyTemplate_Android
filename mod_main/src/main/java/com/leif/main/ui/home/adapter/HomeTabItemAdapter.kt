package com.leif.main.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.leif.common.model.ProjectSubInfo
import com.leif.framework.adapter.BaseBindViewHolder
import com.leif.framework.adapter.BaseRecyclerViewAdapter
import com.leif.framework.ext.onClick
import com.leif.framework.utils.ViewUtils
import com.leif.framework.utils.dpToPx
import com.leif.glide.setUrl
import com.leif.main.databinding.LayoutHomeTabItemBinding
import com.leif.main.ui.ImagePreviewActivity

/**
 * @author mingyan.su
 * @date   2023/3/13 17:53
 * @desc   首页列表信息
 */
class HomeTabItemAdapter : BaseRecyclerViewAdapter<ProjectSubInfo, LayoutHomeTabItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutHomeTabItemBinding {
        return LayoutHomeTabItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutHomeTabItemBinding>,
        item: ProjectSubInfo?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            ivMainIcon.setUrl(item.envelopePic)
            tvTitle.text = item.title
            tvSubTitle.text = item.desc
            tvAuthorName.text = item.author
            tvTime.text = item.niceDate
            ivMainIcon.onClick {
                ImagePreviewActivity.start(it.context, item.envelopePic)
            }
            ViewUtils.setClipViewCornerRadius(holder.itemView, dpToPx(8))
        }
    }
}