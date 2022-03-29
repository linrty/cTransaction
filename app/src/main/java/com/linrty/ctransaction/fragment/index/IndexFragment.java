package com.linrty.ctransaction.fragment.index;

import static com.blankj.utilcode.util.BarUtils.setStatusBarLightMode;
import static com.linrty.ctransaction.util.CodeUtil.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.adapter.ViewPageFragmentAdapter;
import com.linrty.ctransaction.databinding.FragmentIndexBinding;
import com.linrty.ctransaction.fragment.index.fragment.IndexHomeFragment;
import com.linrty.ctransaction.fragment.index.fragment.IndexMessageFragment;
import com.linrty.ctransaction.fragment.index.fragment.IndexUserFragment;
import com.linrty.ctransaction.fragment.index.fragment.IndexWorkFragment;
import com.xuexiang.xui.utils.StatusBarUtils;

import java.util.ArrayList;
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
    FragmentIndexBinding binding;

      /**
       * 获取index页面的ViewModel，也就是主页面存储数据的地方
       */
    IndexViewModel indexViewModel;

      /**
       * 记录是否是点击事件
       */
    Boolean isClick;

      /**
       * 是否是第一次加载Fragment
       */
    Boolean isFirstLoad;

    public IndexFragment(){
        // Required empty public constructor
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        isFirstLoad = true;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 与指定的视图进行绑定
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_index,container,false);
        initView();
        // 除了第一次加载视图会改true为false,其他每次加载都是false
        isFirstLoad = false;
        return binding.getRoot();
    }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);
      }

      /**
       * 初始化页面数据,有些类必须放在此处初始化防止二次加载,丢失数据
       */
    private void initData(){
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        indexFragments = new ArrayList<>();
        // 添加子Fragment的顺序不能乱，必须按照该顺序，不然ViewPager2就不好确认哪个页面和对应的index
        indexFragments.add(new IndexHomeFragment());
        indexFragments.add(new IndexWorkFragment());
        indexFragments.add(new IndexMessageFragment());
        indexFragments.add(new IndexUserFragment());
        // 初始化指定的展示页面,不能放在init内,因为每次重新加载视图都会执行init函数,就会导致重新初始化展示页面
        currentFragment = CODE_FRAGMENT_INDEX_HOME;
        // 因为初始化进入的是首页，首页没有Top bar 所以系统状态栏的背景是Light色系的所以系统字体需要设置为暗色系才能看得更清楚，true为暗色字体，false为白色字体
        BarUtils.setStatusBarLightMode(getActivity(),true);
    }


     /**
      * @brief 初始化数据
      * @param
      * @return
      */
    @SuppressLint("ClickableViewAccessibility")
    private void initView(){
        LogUtils.i("渲染Index页面");
        // 设置该fragment的dataBinding需要绑定的数据实例
        binding.setData(indexViewModel);
        // 设置该fragment的dataBinding的生命周期
        binding.setLifecycleOwner(requireActivity());
        // 初始化适配器，隶属于本Fragment
        indexFragmentAdapter = new ViewPageFragmentAdapter(this,indexFragments);
        // 设置VIewPager预加载数量，如果不设置的话每次重新启动APP进入首页时切换页面都会卡一下
        binding.indexViewPager.setOffscreenPageLimit(4);
        // 设置ViewPage对应的适配器
        binding.indexViewPager.setAdapter(indexFragmentAdapter);
        // 关闭左右滑动手势来切换页面
        binding.indexViewPager.setUserInputEnabled(false);
        // 设置刚加载首页时，默认展示的子页面，并将Tabbar的动画做相应的修改
        binding.indexViewPager.setCurrentItem(CODE_FRAGMENT_INDEX_HOME-CODE_FRAGMENT_INDEX-1);
        // 如果是第一次加载进页面,那就需要设定首页为最初的展示页面,其他情况应按照进入其他fragment时所在在页面退回相应的页面
        if(isFirstLoad) {
            binding.indexTabbarHome.indexTabbarHomeLottie.setFrame(48);
        }else{
            switch (currentFragment){
                case CODE_FRAGMENT_INDEX_HOME:
                    binding.indexTabbarHome.indexTabbarHomeLottie.setFrame(48);
                    break;
                case CODE_FRAGMENT_INDEX_MESSAGE:
                    binding.indexTabbarMessage.indexTabbarMessageLottie.setFrame(48);
                    break;
                case CODE_FRAGMENT_INDEX_USER:
                    binding.indexTabbarUser.indexTabbarUserLottie.setFrame(48);
                    break;
                case CODE_FRAGMENT_INDEX_WORK:
                    binding.indexTabbarWork.indexTabbarWorkLottie.setFrame(48);
                    break;
                default:
                    break;
            }
        }
        // 设置底部导航栏的触摸事件,先找到对应的include组件id，然后进入对应的dataBinding，利用dataBinding进入include内的组件控制
        binding.indexTabbarHome.tabbarHomeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LogUtils.i("触发首页onTouch");
                BarUtils.setStatusBarLightMode(getActivity(),true);
                changeLottieState(binding.indexTabbarHome.indexTabbarHomeLottie,motionEvent,CODE_FRAGMENT_INDEX_HOME,view);
                return true;
            }
        });

        binding.indexTabbarMessage.tabbarMessageLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LogUtils.i("触发消息onTouch");
                BarUtils.setStatusBarLightMode(getActivity(),true);
                changeLottieState(binding.indexTabbarMessage.indexTabbarMessageLottie,motionEvent,CODE_FRAGMENT_INDEX_MESSAGE,view);
                return true;
            }
        });

        binding.indexTabbarUser.tabbarUserLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LogUtils.i("触发用户onTouch");
                BarUtils.setStatusBarLightMode(getActivity(),false);
                changeLottieState(binding.indexTabbarUser.indexTabbarUserLottie,motionEvent,CODE_FRAGMENT_INDEX_USER,view);
                return true;
            }
        });

        binding.indexTabbarWork.tabbarWorkLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LogUtils.i("触发作品onTouch");
                BarUtils.setStatusBarLightMode(getActivity(),true);
                changeLottieState(binding.indexTabbarWork.indexTabbarWorkLottie,motionEvent,CODE_FRAGMENT_INDEX_WORK,view);
                return true;
            }
        });
    }

     /**
      * @brief 每个tabbar的触摸事件处理
      * @param
      * @return
      */

    public void changeLottieState(LottieAnimationView lottieAnimationView ,MotionEvent motionEvent,Integer selectFragmentCode,View v){
        ViewPager2 viewPager2 = binding.indexViewPager;
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 当刚刚按下这个Tabbar时先将该Lottie设置到尾部帧处
                isClick = true;
                lottieAnimationView.setFrame(48);
                break;
            case MotionEvent.ACTION_UP:
                if (isClick){
                    // 将底部导航栏原来的坐标进行还原回未选中的状态
                    switch (currentFragment){
                        // 除了Home页面没有Top bar，也就是系统状态栏为白色，其他的页面系统状态栏都是暗色背景。所以需要设置系统状态栏的字体颜色是白色系的
                        case CODE_FRAGMENT_INDEX_HOME:
                            binding.indexTabbarHome.indexTabbarHomeLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_MESSAGE:
                            binding.indexTabbarMessage.indexTabbarMessageLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_USER:
                            binding.indexTabbarUser.indexTabbarUserLottie.setFrame(0);
                            break;
                        case CODE_FRAGMENT_INDEX_WORK:
                            binding.indexTabbarWork.indexTabbarWorkLottie.setFrame(0);
                            break;
                        default:
                            break;
                    }
                    // 将ViewPage2内的Fragment切换至对应的Fragment，Fragment的item码通过FRAGMENTCODE减去INDEXCODE，因为将Fragment放入List时是按这个顺序排放的
                    viewPager2.setCurrentItem(selectFragmentCode-CODE_FRAGMENT_INDEX-1);
                    // 播放点击动画,先取消所有的动画
                    binding.indexTabbarHome.indexTabbarHomeLottie.cancelAnimation();
                    binding.indexTabbarWork.indexTabbarWorkLottie.cancelAnimation();
                    binding.indexTabbarUser.indexTabbarUserLottie.cancelAnimation();
                    binding.indexTabbarMessage.indexTabbarMessageLottie.cancelAnimation();
                    lottieAnimationView.setMinFrame(9);
                    lottieAnimationView.playAnimation();
                    currentFragment = selectFragmentCode;
                }
                // 将是否点击变量恢复之前的状态
                isClick = false;
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                // 当触摸事件在抬起时离开了这个Layout或者被其他事件中断，就代表取消这个点击事件
                isClick = false;
                // 代表当前选择的icon不是之前已经选中的
                if (!selectFragmentCode.equals(currentFragment)){
                    lottieAnimationView.setFrame(0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算出View的长宽
                int len = v.getRight() - v.getLeft();
                int height = v.getBottom() - v.getTop();
                // 判断move之后触摸点是否还在View内,如果在View之外代表取消了这次点击
                if(!(motionEvent.getX()>=0 && motionEvent.getX()<=len && motionEvent.getY()>=0 && motionEvent.getY()<=height)){
                    isClick = false;
                    if (!selectFragmentCode.equals(currentFragment)){
                        lottieAnimationView.setFrame(0);
                    }
                }
                break;
            default:
                break;
        }
    }
}
