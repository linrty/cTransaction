package com.linrty.ctransaction.fragment.identification;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hyphenate.easeui.utils.EaseFileUtils;
import com.hyphenate.util.VersionUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIdentificationBinding;

import java.io.File;

/**
  * @ClassName:      IdentificationFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/31
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/30
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IdentificationFragment extends Fragment {

    FragmentIdentificationBinding binding;

    protected static final int REQUEST_CODE_SELECT_FILE = 12;

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         initData();
     }

     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_identification,container,false);
         initView();
         return binding.getRoot();
     }

     private void initData(){

     }

     private void initView(){
         binding.uploadButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectFileFromLocal();
             }
         });
     }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_CODE_SELECT_FILE) {
                onActivityResultForLocalFiles(data);
            }
        }
    }

    protected void selectFileFromLocal() {
        Intent intent = new Intent();
        if(VersionUtils.isTargetQ(getActivity())) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }else {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setAction(Intent.ACTION_GET_CONTENT);
            }else {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, REQUEST_CODE_SELECT_FILE);
    }

    protected void onActivityResultForLocalFiles(@Nullable Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String filePath = EaseFileUtils.getFilePath(requireActivity().getBaseContext(), uri);
                if(!TextUtils.isEmpty(filePath) && new File(filePath).exists()) {
                    //chatLayout.sendFileMessage(Uri.parse(filePath));
                }else {
                    EaseFileUtils.saveUriPermission(requireActivity().getBaseContext(), uri, data);
                    //chatLayout.sendFileMessage(uri);
                }
            }
        }
    }
 }
