package ac.hurley.module_player.util

import ac.hurley.module_provider.model.Data
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

/**
 * <pre>
 *      @author hurley
 *      date    : 4/8/21 1:56 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 历史记录管理类
 * </pre>
 */

object WatchHistoryManager {

    private val mmkv = MMKV.defaultMMKV()
    private val WATCH_HISTORY = "video_history"

    /**
     * 添加观看历史记录
     */
    fun addWatchHistoryRecord(data: Data) {
        val json = mmkv.decodeString(
            WATCH_HISTORY
        )
        if (json.isNullOrEmpty()) {
            val videoList = mutableListOf<Data>()
            videoList.add(data)

            mmkv.encode(
                WATCH_HISTORY, GsonUtils.toJson(videoList))
        } else {
            val videoList: MutableList<Data> =
                GsonUtils.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)

            if (!videoList.any {
                    it.id == data.id
                }) {
                videoList.add(data)
            }
            mmkv.encode(
                WATCH_HISTORY, GsonUtils.toJson(videoList))
        }
    }

    /**
     * 移除观看历史记录
     */
    fun removeWatchHistoryRecord(data: Data) {
        val json = mmkv.decodeString(
            WATCH_HISTORY
        )
        val videoList: MutableList<Data> =
            GsonUtils.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        videoList.remove(data)
        mmkv.encode(
            WATCH_HISTORY, GsonUtils.toJson(videoList))
    }

    /**
     * 获取观看历史记录列表
     */
    fun getWatchHistoryList(): MutableList<Data> {
        val json = mmkv.decodeString(
            WATCH_HISTORY
        )
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            GsonUtils.fromJson(json, object : TypeToken<MutableList<Data>>() {}.type)
        }
    }

}