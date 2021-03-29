package ac.hurley.module_daily.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_daily.model.TypeMultiModel
import ac.hurley.module_daily.service.DailyService
import androidx.lifecycle.LiveData

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:03 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class DailyViewModel : BaseViewModel() {

    /**
     * 根据请求 API 返回的数据命名
     */
    private val BANNER_TYPE = "banner2"
    private val TEXT_HEADER_TYPE = "textHeader"
    private var mNextPageUrl: String? = null

    /**
     * 获取日报轮播图
     */
    fun getDailyBanner(): LiveData<TypeMultiModel> = liveDataEx {
        val dailyModel = DailyService.getDailyBanner()
        mNextPageUrl = dailyModel.nextPageUrl
        val list = dailyModel.issueList[0].itemList
        list.removeAll {
            it.type == BANNER_TYPE
        }
        val typeMultiModel = TypeMultiModel(type = TypeMultiModel.Type.TYPE_BANNER, items = list)
        typeMultiModel
    }

    /**
     * 获取日报列表
     */
    fun getDailyList(): LiveData<List<TypeMultiModel>> = liveDataEx {
        if (mNextPageUrl == null) {
            mutableListOf()
        } else {
            val dailyModel = DailyService.getDailyList(mNextPageUrl!!)
            mNextPageUrl = dailyModel.nextPageUrl
            val list = dailyModel.issueList[0].itemList
            list.removeAll {
                it.type == BANNER_TYPE
            }
            val typeMultiModel = mutableListOf<TypeMultiModel>()
            list.forEach {
                // 如果是文字 Header 类型，就显示发表时间
                if (it.type == TEXT_HEADER_TYPE) {
                    typeMultiModel.add(
                        TypeMultiModel(
                            type = TypeMultiModel.Type.TYPE_TITLE,
                            item = it
                        )
                    )
                } else {
                    // 如果是图片类型，就显示图片
                    typeMultiModel.add(
                        TypeMultiModel(
                            type = TypeMultiModel.Type.TYPE_IMAGE, item = it
                        )
                    )
                }
            }
            typeMultiModel
        }
    }
}