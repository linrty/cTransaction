package com.linrty.ctransaction.plugin;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.linrty.ctransaction.R;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

/**
  * @ClassName:      BottomSheetUtil
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/12
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/12
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class BottomSheetUtil {
     // ================================ 生成不同类型的BottomSheet
     public static void showSimpleBottomSheetList(boolean gravityCenter,
                                            boolean addCancelBtn,
                                            CharSequence title,
                                            int itemCount,
                                            boolean allowDragDismiss,
                                            boolean withMark,
                                            Activity activity,
                                            Context context) {
         QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(activity);
         builder.setGravityCenter(gravityCenter)
                 .setSkinManager(QMUISkinManager.defaultInstance(context))
                 .setTitle(title)
                 .setAddCancelBtn(addCancelBtn)
                 .setAllowDrag(allowDragDismiss)
                 .setNeedRightMark(withMark)
                 .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                     @Override
                     public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                         dialog.dismiss();
                         Toast.makeText(activity, "Item " + (position + 1), Toast.LENGTH_SHORT).show();
                     }
                 });
         if(withMark){
             builder.setCheckedIndex(40);
         }
         for (int i = 1; i <= itemCount; i++) {
             builder.addItem("Item " + i);
         }
         builder.build().show();
     }
}
