package com.leif.main.repository

import com.leif.common.model.ArticleList
import com.leif.common.model.Banner
import com.leif.common.model.ProjectSubList
import com.leif.network.manager.ApiManager
import com.leif.common.model.ProjectTabItem
import com.leif.network.repository.BaseRepository
import com.leif.room.entity.VideoInfo
import com.leif.room.manager.VideoCacheManager

/**
 * @author mingyan.su
 * @date   2023/2/27 18:58
 * @desc   首页请求仓库
 */
class HomeRepository : BaseRepository() {
    /**
     * 首页Banner
     */
    suspend fun getHomeBanner(): MutableList<Banner>? {
        return requestResponse {
            ApiManager.api.getHomeBanner()
        }
    }

    /**
     * 首页列表
     * @param page 页码
     * @param pageSize 每页数量
     */
    suspend fun getHomeInfoList(page: Int): ArticleList? {
        return requestResponse {
            ApiManager.api.getHomeList(page, 20)
        }
    }

    /**
     * 项目tab
     */
    suspend fun getProjectTab(): MutableList<ProjectTabItem>? {
        return requestResponse {
            ApiManager.api.getProjectTab()
        }
    }

    /**
     * 项目列表
     * @param page
     * @param cid
     */
    suspend fun getProjectList(page: Int, cid: Int): ProjectSubList? {
        return requestResponse {
            ApiManager.api.getProjectList(page, cid)
        }
    }

    /**
     * 获取视频列表数据
     */
    suspend fun getVideoListCache(): MutableList<VideoInfo>? {
        return VideoCacheManager.getVideoList()
    }
}