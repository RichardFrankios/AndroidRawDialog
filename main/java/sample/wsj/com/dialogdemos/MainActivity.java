package sample.wsj.com.dialogdemos;

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
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void btnClickCallBack(View view) {
        switch (view.getId()){
            case R.id.btn_normal_dialog:
            {
                // 普通Dialog
                new NormalDialogFragment().show(getSupportFragmentManager(),"normal");
                break;
            }
            case R.id.btn_list_dialog:
            {
                new ListDialogFragment().show(getSupportFragmentManager(),"list");
                break;
            }
            case R.id.btn_list_single_dialog:
            {
                new ListSingleChoiceDialogFragment().show(getSupportFragmentManager(),"list_single");
                break;
            }
            case R.id.btn_list_multi_dialog:
            {
                new ListMultiChioceDialogFragment().show(getSupportFragmentManager(),"list_multi");
                break;
            }
            case R.id.btn_custom_dialog:
            {
                new CustomContentDialogFragment().show(getSupportFragmentManager(),"custom");
                break;
            }
            case R.id.btn_custom_title_dialog:
            {
                new CustomTitleDialogFragment().show(getSupportFragmentManager(),"custom_title");
                break;
            }

        }
    }

    /**
     * 普通Dialog
     */
    public static class NormalDialogFragment extends DialogFragment{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 创建Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("提示Dialog")
                    .setMessage("猜猜我是谁")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG,"点击了OK按钮");
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG,"用户点击了Cancel");
                        }
                    })
                    .setNeutralButton("其他", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG,"点击了其他按钮");
                        }
                    })
                    .create();
        }
    }

    /**
     * 列表 Dialog
     */
    public static class ListDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle("列表Dialog")
                    .setItems(R.array.langeage, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] langauge = getActivity().getResources().getStringArray(R.array.langeage);
                            Log.i(TAG,"点击了 : " + langauge[which]);
                        }
                    })
                    .setIcon(R.mipmap.ic_launcher)
                    .create();
        }
    }

    /**
     * 单选Dialog
     */
    public static class ListSingleChoiceDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            return builder
                    .setTitle("单选列表")
                    .setSingleChoiceItems(R.array.langeage, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] lans = getActivity().getResources().getStringArray(R.array.langeage);
                            Toast.makeText(getActivity(),"点击了 : " + lans[which],Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"点击了 OK: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"点击了OK按钮");
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"用户点击了Cancel: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"用户点击了Cancel");
                        }
                    })
                    .create();
        }
    }

    /**
     * 多选列表Dialog
     */
    public static class ListMultiChioceDialogFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            return builder
                    .setTitle("多选列表")
                    .setMultiChoiceItems(R.array.langeage, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            String[] lans = getActivity().getResources().getStringArray(R.array.langeage);
                            Toast.makeText(getActivity(),"点击了 : "+ lans[which] + "状态 : " + isChecked,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"点击了 OK: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"点击了OK按钮");
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"用户点击了Cancel: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"用户点击了Cancel");
                        }
                    })
                    .create();
        }
    }

    /**
     * 自定义Dialog
     */
    public static class CustomContentDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

//            return builder
//                    .setView(R.layout.custom_dialog_01)
//                    .create();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.custom_dialog_01,null);
            final EditText et_name = (EditText) view.findViewById(R.id.username);
            final EditText et_passwd = (EditText) view.findViewById(R.id.password);

            return builder
                    .setView(view)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"点击了 OK: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"username : " + et_name.getText().toString());
                            Log.i(TAG,"password : " + et_passwd.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"用户点击了Cancel: ",Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"用户点击了Cancel");
                        }
                    })
                    .create();
        }
    }

    /**
     * 自定义标题栏Dialog
     */
    public static class CustomTitleDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.custom_dialog_title,null);
            return builder
                    .setCustomTitle(view)
                    .setMessage("这是内容区")
                    .create();

        }
    }



}



















