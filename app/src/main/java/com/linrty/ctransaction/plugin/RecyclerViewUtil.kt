package com.linrty.ctransaction.plugin

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.PageRefreshLayout
import com.drake.brv.annotaion.AnimationType
import com.drake.brv.utils.*
import com.drake.net.Get
import com.drake.net.utils.scopeNet
import com.linrty.ctransaction.HeaderModel
import com.linrty.ctransaction.R
import com.linrty.ctransaction.bean.Credit
import com.linrty.ctransaction.common.net.CommonResult
import com.linrty.ctransaction.fragment.index.fragment.model.IndexHomeItemModel
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel
import com.linrty.ctransaction.fragment.search.model.SearchResultItemModel
import com.linrty.ctransaction.fragment.swap.model.SwapUploadItemModel
import com.linrty.ctransaction.util.CodeUtil
import kotlin.math.log

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
        val layoutManager = GridLayoutManager(rv.context,2);
        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // 根据实际情况处理需要返回的值.对于小的item占用1个span size也就是分两列展示，对于大的就使用1列展示
                val model: IndexHomeItemModel = data[position] as IndexHomeItemModel
                return if (model.itemType == CodeUtil.CODE_INDEX_HOME_ITEM_SMALL) 1
                else 2
            }
        }
        layoutManager.spanSizeLookup = spanSizeLookup
        rv.layoutManager = layoutManager
        rv.setup {
            addType<IndexHomeItemModel>{
                // 根据model中的item type来选择不同的布局样式
                when (itemType) {
                    CodeUtil.CODE_INDEX_HOME_ITEM_SMALL -> {
                        R.layout.item_index_home_list_small
                    }
                    else -> {
                        R.layout.item_index_home_list_big
                    }
                }
            }
            setAnimation(AnimationType.SLIDE_BOTTOM)
        }.models = data
    }

    private fun getData(): MutableList<Any> {
        // 在Model中也可以绑定数据
        return mutableListOf<Any>().apply {
            for (i in 0..9) add(SearchResultItemModel().setItemTitle("54sda54f"))
        }
    }
}