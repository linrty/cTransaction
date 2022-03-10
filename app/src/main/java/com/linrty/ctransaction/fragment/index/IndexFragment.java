package com.linrty.ctransaction.fragment.index;

import static com.linrty.ctransaction.util.CodeUtil.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.adapter.ViewPageFragmentAdapter;
import com.linrty.ctransaction.databinding.FragmentIndexBinding;

import java.util.List;

  /**
   * @ClassName:      IndexFragment
   * @Description:    Index总页面的逻辑
   * @Author:         Linrty
   * @CreateDate:     2022/3/9
   * @UpdateUser:     updater
   * @UpdateDate:     2022/3/9
   * @UpdateRemark:   更新内容
   * @Version:        1.0
   */
public class IndexFragment extends Fragment {

    /**
     * IndexFragment内有四个子Fragment分别为user、work、message、home
     */
    List<Fragment> indexFragments;

    /**
     * ViewPager是基于RecyclerView实现的，所以需要对应的Adapter
     */
    ViewPageFragmentAdapter indexFragmentAdapter;

    /**
     * 记录当前所在的Fragment，防止Navigation使用popBackStack方法后，导致ViewPager内的Fragment与底部导航栏不匹配
     */
    Integer currentFragment;

      /**
       * 与视图UI控件进行绑定
       */
    FragmentIndexBinding fragmentIndexBinding;

      /**
       * 获取index页面的ViewModel，也就是主页面存储数据的地方
       */
    IndexViewModel indexViewModel;

    Boolean isClick;



    public IndexFragment(){
        // Required empty public constructor
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 与指定的视图进行绑定
        fragmentIndexBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_index,container,false);
        init();
        return fragmentIndexBinding.getRoot();
    }


     /**
      * @brief 初始化数据
      * @param
      * @return
      */
    private void init(){
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        // 设置该fragment的dataBinding需要绑定的数据实例
        fragmentIndexBinding.setIndexData(indexViewModel);
        // 设置该fragment的dataBinding的生命周期
        fragmentIndexBinding.setLifecycleOwner(requireActivity());

        // 设置底部导航栏的点击事件,先找到对应的include组件id，然后进入对应的dataBinding，利用dataBinding进入include内的组件控制
        fragmentIndexBinding.indexTabbarHome.tabbarHomeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                }
                return false;
            }
        });

        fragmentIndexBinding.indexTabbarMessage.tabbarMessageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fragmentIndexBinding.indexTabbarUser.tabbarUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fragmentIndexBinding.indexTabbarWork.tabbarWorkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

     /**
      * @brief 每个tabbar的触摸事件处理
      * @param
      * @return
      */

    public void changeLottieState(LottieAnimationView lottieAnimationView ,MotionEvent motionEvent,Integer selectFragmentCode){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 当刚刚按下这个Tabbar时先将该Lottie设置到尾部帧处
                isClick = true;
                lottieAnimationView.setFrame(48);
                break;
            case MotionEvent.ACTION_UP:
                if (isClick){
                    lottieAnimationView.setFrame(9);
                    // 将底部导航栏原来的坐标进行还原回未选中的状态
                    switch (currentFragment){
                        case CODE_FRAGMENT_INDEX_HOME:
                            fragmentIndexBinding.indexTabbarHome.indexTabbarHomeLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_MESSAGE:
                            fragmentIndexBinding.indexTabbarMessage.indexTabbarMessageLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_USER:
                            fragmentIndexBinding.indexTabbarUser.indexTabbarUserLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_WORK:
                            fragmentIndexBinding.indexTabbarWork.indexTabbarWorkLottie.setFrame(0);
                            break;
                    }
                    switch (selectFragmentCode){
                        case CODE_FRAGMENT_INDEX_MESSAGE:

                            break;
                        case CODE_FRAGMENT_INDEX_HOME:
                            break;
                        case CODE_FRAGMENT_INDEX_USER:
                            break;
                        case CODE_FRAGMENT_INDEX_WORK:
                            break;
                    }
                    // 播放点击动画
                    lottieAnimationView.playAnimation();
                }
                // 将是否点击变量恢复之前的状态
                isClick = false;
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                // 当触摸事件在抬起时离开了这个Layout或者被其他事件中断，就代表取消这个点击事件
                isClick = false;
                break;
        }
    }
}
