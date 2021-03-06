package com.linrty.ctransaction.plugin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.navigation.NavController
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
import com.linrty.ctransaction.bean.Identification
import com.linrty.ctransaction.bean.Order
import com.linrty.ctransaction.bean.Work
import com.linrty.ctransaction.common.net.CommonResult
import com.linrty.ctransaction.fragment.index.fragment.model.IndexHomeItemModel
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel
import com.linrty.ctransaction.fragment.search.model.SearchResultItemModel
import com.linrty.ctransaction.fragment.swap.model.SwapUploadItemModel
import com.linrty.ctransaction.fragment.work.CommentModel
import com.linrty.ctransaction.util.CodeUtil
import com.xuexiang.xui.widget.button.roundbutton.RoundButton
import kotlin.math.log

object RecyclerViewUtil {


    public fun bindingIdentificationList(rv: RecyclerView,data: MutableList<Any>){
        rv.linear().divider(R.drawable.item_divider).setup {
            addType<Identification>(R.layout.item_identification_process)
            onBind {
                val state = (data[modelPosition] as Identification).state
                var button:RoundButton = findView<RoundButton>(R.id.confirmIdentificationItemButton)
                if (state == 1){

                }
            }
        }.models = data
    }

    public fun bindingCollectList(rv: RecyclerView, data: MutableList<Any>, navController: NavController){
        rv.linear().setup {
            addType<IndexWorkListItemModel>(R.layout.item_index_work_list)
            onClick(R.id.indexWorkItem){
                val bundle:Bundle = Bundle()
                bundle.putLong("work_id", (data[modelPosition] as IndexWorkListItemModel).workId)
                navController.navigate(R.id.action_myCollectFragment_to_workInfoFragment,bundle)
            }
        }.models =data
    }

    public fun bindingSellHistoryList(rv: RecyclerView, data: MutableList<Any>, navController: NavController){
        rv.linear().divider(R.drawable.item_divider).setup {
            addType<Order>(R.layout.item_buy_history)
            R.id.buyHistoryGotoWork.onClick {
                // ?????????????????????????????????????????????
                val bundle:Bundle = Bundle()
                bundle.putLong("work_id", (data[modelPosition] as Order).workId)
                navController.navigate(R.id.action_sellHistoryFragment_to_workInfoFragment,bundle)
            }
        }.models = data
    }

    public fun bindingBuyHistoryList(rv: RecyclerView, data: MutableList<Any>, navController: NavController){
        rv.linear().divider(R.drawable.item_divider).setup {
            addType<Order>(R.layout.item_buy_history)
            R.id.buyHistoryGotoWork.onClick {
                // ?????????????????????????????????????????????
                val bundle:Bundle = Bundle()
                bundle.putLong("work_id", (data[modelPosition] as Order).workId)
                navController.navigate(R.id.action_buyHistoryFragment_to_workInfoFragment,bundle)
            }
        }.models = data
    }

    public fun bindingCommentList(rv: RecyclerView, data: MutableList<Any>, navController: NavController){
        rv.linear().setup {
            addType<CommentModel>(R.layout.item_comment)
            R.id.ivAvatar.onClick {
                // ???????????????????????????????????????
            }
            R.id.tvUserName.onClick {
                // ????????????????????????????????????????????????
            }
            R.id.tvUp.onClick {
                // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????+1????????????????????????????????????????????????????????????
            }
            R.id.ivDown.onClick {
                // ??????????????????????????????????????????????????????????????????
            }
        }.models = data
    }

    public fun bindingIndexWorkList(rv: RecyclerView, data: MutableList<Any>,page: PageRefreshLayout,navController: NavController){
        rv.linear().setup {
            addType<SwapUploadItemModel>(R.layout.item_index_work_list)
            addType<HeaderModel>(R.layout.item_index_home_classify)
            setAnimation(AnimationType.SLIDE_RIGHT)
            onClick(R.id.indexWorkItem){
                navController.navigate(R.id.action_indexFragment_to_workInfoFragment)
            }
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
                // ??????????????????????????????????????????.????????????item??????1???span size????????????????????????????????????????????????1?????????
                val model: IndexHomeItemModel = data[position] as IndexHomeItemModel
                return if (model.itemType == CodeUtil.CODE_INDEX_HOME_ITEM_SMALL) 1
                else 2
            }
        }
        layoutManager.spanSizeLookup = spanSizeLookup
        rv.layoutManager = layoutManager
        rv.setup {
            addType<IndexHomeItemModel>{
                // ??????model??????item type??????????????????????????????
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
        // ???Model????????????????????????
        return mutableListOf<Any>().apply {
            for (i in 0..9) add(SearchResultItemModel().setItemTitle("54sda54f"))
        }
    }
}