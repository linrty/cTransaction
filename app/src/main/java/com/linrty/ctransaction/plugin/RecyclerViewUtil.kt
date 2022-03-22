package com.linrty.ctransaction.plugin

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.PageRefreshLayout
import com.drake.brv.annotaion.AnimationType
import com.drake.brv.utils.*
import com.linrty.ctransaction.HeaderModel
import com.linrty.ctransaction.R
import com.linrty.ctransaction.fragment.index.fragment.model.IndexHomeItemModel
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel
import com.linrty.ctransaction.fragment.search.model.SearchResultItemModel
import com.linrty.ctransaction.fragment.swap.model.SwapUploadItemModel

object RecyclerViewUtil {

    public fun bindingIndexWorkList(rv: RecyclerView, data: MutableList<Any>,page: PageRefreshLayout){
        rv.linear().setup {
            addType<SwapUploadItemModel>(R.layout.item_index_work_list)
            addType<HeaderModel>(R.layout.item_index_home_classify)
            setAnimation(AnimationType.SLIDE_RIGHT)
        }.models = data
        rv.bindingAdapter.addHeader(HeaderModel())
    }

    public fun bindingSearchResultList(rv: RecyclerView,data: MutableList<Any>){
        Log.i("Linrty", "bindingSearchResultList: "+data[0].toString())
        rv.linear().setup {
            addType<SearchResultItemModel>(R.layout.item_search_result_list)
            /*onBind {
                findView<TextView>(R.id.searchResultItemTitle).text = getModel<SearchResultItemModel>().itemTitle
            }*/
        }.models = data
    }

    public fun bindingSwapUploadList(rv: RecyclerView,data: MutableList<Any>){
        rv.linear().setup {
            addType<SwapUploadItemModel>(R.layout.item_swap_upload_list)
        }.models = data
    }

    public fun bindingIndexHomeList(rv: RecyclerView,data: MutableList<Any>){
        rv.grid(1).setup {
            addType<IndexHomeItemModel>(R.layout.item_index_home_list_small)
            setAnimation(AnimationType.SLIDE_BOTTOM)
            onBind {
                val layoutParams = itemView.layoutParams
                layoutParams.width = layoutParams.width
                itemView.layoutParams = layoutParams
            }

        }.models = data
        rv.grid()
    }

    private fun getData(): MutableList<Any> {
        // 在Model中也可以绑定数据
        return mutableListOf<Any>().apply {
            for (i in 0..9) add(SearchResultItemModel().setItemTitle("54sda54f"))
        }
    }
}