package ac.hurley.module_daily.model

import ac.hurley.module_provider.model.Issue

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 2:40 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日报的实体类的最外层（因为内部类通用，所以提取出来）
 * </pre>
 */

data class DailyModel(
    val issueList: List<Issue>,
    val dialog: Any,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
)