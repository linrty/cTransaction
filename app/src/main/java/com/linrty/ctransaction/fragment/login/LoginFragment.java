package com.linrty.ctransaction.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ToastUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentLoginBinding;
import com.linrty.ctransaction.util.PrivacyUtils;
import com.linrty.ctransaction.util.SettingSPUtils;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ViewUtils;

/**
  * @ClassName:      LoginFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/4/5
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/5
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    LoginViewModel model;

    private CountDownButtonHelper mCountDownHelper;



     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         initData();
     }

     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
         initView();
         return binding.getRoot();
     }

     private void initData(){
         model = new ViewModelProvider(this).get(LoginViewModel.class);
     }

     private void initView(){
         mCountDownHelper = new CountDownButtonHelper(binding.btnGetVerifyCode, 60);
         //隐私政策弹窗
         SettingSPUtils spUtils = SettingSPUtils.getInstance();
         if (!spUtils.isAgreePrivacy()) {
             PrivacyUtils.showPrivacyDialog(getContext(), (dialog, which) -> {
                 dialog.dismiss();
                 spUtils.setIsAgreePrivacy(true);
                 // 应用市场不让默认勾选
//                ViewUtils.setChecked(cbProtocol, true);
             });
         }
         binding.cbProtocol.setOnCheckedChangeListener((buttonView, isChecked) -> {
             spUtils.setIsAgreePrivacy(isChecked);
             ViewUtils.setEnabled(binding.btnLogin, isChecked);
         });
         ViewUtils.setEnabled(binding.btnLogin, spUtils.isAgreePrivacy());
         ViewUtils.setChecked(binding.cbProtocol, spUtils.isAgreePrivacy());

         binding.btnGetVerifyCode.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (binding.etPhoneNumber.validate()) {
                     getVerifyCode(binding.etPhoneNumber.getEditValue());
                 }
             }
         });

         binding.btnLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (binding.etPhoneNumber.validate()) {
                     if (binding.etVerifyCode.validate()) {
                         loginByVerifyCode(binding.etPhoneNumber.getEditValue(), binding.etVerifyCode.getEditValue());
                     }
                 }
             }
         });

         binding.tvForgetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ToastUtils.showShort("忘记密码");
             }
         });

         binding.tvOtherLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ToastUtils.showShort("其他登录方式");
             }
         });

         binding.tvUserProtocol.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // 前往用户协议页面
                 ToastUtils.showShort("用户协议");
             }
         });

         binding.tvPrivacyProtocol.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // 前往隐私政策页面
                 ToastUtils.showShort("隐私政策");
             }
         });
     }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNumber) {
        // XToastUtils.warning("只是演示，验证码请随便输");
        mCountDownHelper.start();
    }

    /**
     * 根据验证码登录
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     */
    private void loginByVerifyCode(String phoneNumber, String verifyCode) {
        /*String token = RandomUtils.getRandomNumbersAndLetters(16);
        if (TokenUtils.handleLoginSuccess(token)) {
            popToBack();
            ActivityUtils.startActivity(MainActivity.class);
        }*/
    }

    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
 }
