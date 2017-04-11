package com.base.androidrawdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 普通提示Dialog.
     */
    public void normalTextDialog(View view) {
        // Dialog
        new NormalTextDialogFragment().show(getSupportFragmentManager(), "normal_text_dialog");
    }

    /**
     * 列表Dialog
     */
    public void listDialog(View view) {
        new ListDialogFragment().show(getSupportFragmentManager(), "list_dialog");
    }

    /**
     * 单选框Dialog
     */
    public void singleChoice(View view) {
        new SingleChoiceDialogFragment().show(getSupportFragmentManager(), "single_choice");
    }

    /**
     * 多选项Dialog.
     */
    public void multiChoice(View view) {
        new MultiChoiceDialogFragment().show(getSupportFragmentManager(), "multi_chioce");
    }

    /**
     * 自定义内容区
     */
    public void customContentView(View view) {
        new CustomContentDialogFragment().show(getSupportFragmentManager(), "custom_content");
    }

    /**
     * 自定义标题
     */
    public void customTitleView(View view) {
        new CustomTitleDialogFragment().show(getSupportFragmentManager(), "custom_title");
    }

    /**
     * 自定义标题Dialog
     */
    public static class CustomTitleDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater li = getActivity().getLayoutInflater();

            return builder
                    .setCustomTitle(li.inflate(R.layout.dialog_custom_title, null))
                    .setPositiveButton("OK" , null)
                    .setNegativeButton("Cancel", null)
                    .create();
        }
    }


    /**
     * 自定义Content
     */
    public static class CustomContentDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            return builder
                    .setView(R.layout.dialog_custom_content)
                    .setPositiveButton("OK" , null)
                    .setNegativeButton("Cancel", null)
                    .create();
        }
    }

    /**
     * 多选项Dialog
     */
    public static class MultiChoiceDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity())
                    .setMultiChoiceItems(R.array.color_arrays, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            // 获取列表
                            String[] colors = getResources().getStringArray(R.array.color_arrays);
                            // 显示
                            String txt = "点击了: " + colors[which] + " == " + isChecked;
                            Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Cancel", null)
                    .create();
        }
    }



    /**
     * 单选框Dialog
     */
    public static class SingleChoiceDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setSingleChoiceItems(R.array.color_arrays, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 获取列表
                            String[] colors = getResources().getStringArray(R.array.color_arrays);
                            // 显示
                            Toast.makeText(getActivity(), "选择了 " + colors[which], Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("OK",null)
                    .setNegativeButton("Cancel", null)
                    .create();
        }
    }



    /**
     * 列表Dialog
     */
    public static class ListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder
                    .setTitle("颜色选择")
                    .setItems(R.array.color_arrays, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 获取列表
                            String[] colors = getResources().getStringArray(R.array.color_arrays);
                            // 显示
                            Toast.makeText(getActivity(), "选择了 " + colors[which], Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
        }
    }


    /**
     * 普通提示Dialog.
     */
    public static class NormalTextDialogFragment extends DialogFragment {
        /**
         * 创建Dialog时调用
         */
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Log.d(TAG, "onCreateDialog: ");
            // 创建构造器
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // 设置参数
            builder.setMessage("普通提示Dialog信息")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "onClick: 确定 " + Thread.currentThread().getId());
                            Toast.makeText(getActivity(), "点击了确定", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "onClick: 取消 " + Thread.currentThread().getId());
                            Toast.makeText(getActivity(), "点击了取消", Toast.LENGTH_SHORT).show();
                        }
                    });
            // 创建对话框并返回.
            return builder.create();
        }
    }

}
