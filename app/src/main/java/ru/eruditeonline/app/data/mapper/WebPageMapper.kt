package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.app.data.remote.model.base.ApiWebPage
import ru.eruditeonline.app.data.remote.model.base.ApiWebPageItem
import javax.inject.Inject

class WebPageMapper @Inject constructor() {
    fun fromApiToModel(api: ApiWebPage): WebPage {
        return WebPage(
            title = api.title.orEmpty(),
            content = api.content.orEmpty(),
        )
    }

    fun fromApiToModel(api: ApiWebPageItem): WebPageItem {
        return WebPageItem(
            path = api.path.orEmpty(),
            name = api.name.orEmpty()
        )
    }
}
