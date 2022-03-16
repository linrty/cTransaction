package com.linrty.ctransaction.plugin

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.PageRefreshLayout
import com.drake.brv.utils.BRV
import com.drake.brv.utils.bindingAdapter
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.linrty.ctransaction.HeaderModel
import com.linrty.ctransaction.R
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel

object RecyclerViewUtil {
    public fun bindingIndexWorkList(rv: RecyclerView, modelId: Int, data: MutableList<Any>,page: PageRefreshLayout){
        BRV.modelId = modelId
        rv.linear().setup {
            addType<IndexWorkListItemModel>(R.layout.item_index_work_list)
            addType<HeaderModel>(R.layout.item_index_home_classify)
        }.models = data
        rv.bindingAdapter.addHeader(HeaderModel())
        /*page.onRefresh {
            postDelayed({ // 模拟网络请求2秒后成功, 创建假的数据集

            }, 2)
        }*/
    }
}