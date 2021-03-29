package ac.hurley.module_hot.model

/**
 * <pre>
 *      @author hurley
 *      date    : 3/29/21 4:59 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门页面的 Tab 实体类
 * </pre>
 */

data class TabInfo(
    val tabInfo: TabList
)

data class TabList(
    val defaultIdx: Int,
    val tabList: List<Tab>
)

data class Tab(
    val adTrack: Any,
    val apiUrl: String,
    val id: Int,
    val name: String,
    val nameType: Int,
    val tabType: Int
)