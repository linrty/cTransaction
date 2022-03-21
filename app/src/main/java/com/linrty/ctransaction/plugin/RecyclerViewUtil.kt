package com.linrty.ctransaction.plugin

import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.PageRefreshLayout
import com.drake.brv.annotaion.AnimationType
import com.drake.brv.utils.BRV
import com.drake.brv.utils.bindingAdapter
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.linrty.ctransaction.HeaderModel
import com.linrty.ctransaction.R
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel
import com.linrty.ctransaction.fragment.search.model.SearchResultItemModel

object RecyclerViewUtil {

    public fun bindingIndexWorkList(rv: RecyclerView, modelId: Int, data: MutableList<Any>,page: PageRefreshLayout){
        BRV.modelId = modelId
        rv.linear().setup {
            addType<IndexWorkListItemModel>(R.layout.item_index_work_list)
            addType<HeaderModel>(R.layout.item_index_home_classify)
            setAnimation(AnimationType.SLIDE_RIGHT)
        }.models = data
        rv.bindingAdapter.addHeader(HeaderModel())
    }

    public fun bindingSearchResultList(rv: RecyclerView,modelId: Int,data: MutableList<Any>){
        BRV.modelId = modelId
        rv.linear().setup {
            addType<SearchResultItemModel>(R.layout.item_search_result_list)
        }.models = data
    }
}