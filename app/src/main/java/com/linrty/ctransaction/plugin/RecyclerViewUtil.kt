package com.linrty.ctransaction.plugin

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.utils.BRV
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.linrty.ctransaction.R
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel

object RecyclerViewUtil {
    public fun bindingIndexWorkList(rv: RecyclerView, modelId: Int, data: MutableList<Any>,activity: Activity,context: Context){
        BRV.modelId = modelId
        rv.linear().setup {
            addType<IndexWorkListItemModel>(R.layout.item_index_work_list)
            R.id.textView.onLongClick {
                BottomSheetUtil.showSimpleBottomSheetList(true, true, "This is Title!!!",
                    3, false, false,activity,context)
            }
        }.models = data
    }
}