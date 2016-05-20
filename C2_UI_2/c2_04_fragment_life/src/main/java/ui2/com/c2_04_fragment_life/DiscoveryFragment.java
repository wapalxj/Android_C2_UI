package ui2.com.c2_04_fragment_life;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment的生命周期
 * Created by vero on 2016/4/10.
 */
public class DiscoveryFragment extends Fragment {

    //依附在Activity上,这个方法调用下面个onAttach方法
    @Override
    public void onAttach(Context context) {
        Log.i("DiscoveryFragment","onAttach");
        super.onAttach(context);
    }
    //依附在Activity上,被上面的方法所取代了(意思是不要直接使用，在上面的onAttach中会调用此方法)
    @Override
    public void onAttach(Activity activity) {
        Log.i("DiscoveryFragment","onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("DiscoveryFragment","onCreate");
        super.onCreate(savedInstanceState);
    }


    //加载一个布局，显示fragment的内容
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("DiscoveryFragment","onCreateView");
        //将布局转化成一个view对象
        View view=inflater.inflate(R.layout.fragment_discovery,container,false);
        return view;
    }

    //在onCreateView方法中初始化的view，完全初始化了
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("DiscoveryFragment","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    //fragment页面可见
    @Override
    public void onStart() {
        Log.i("DiscoveryFragment","onStart");
        super.onStart();
    }

    //fragment获取焦点
    @Override
    public void onResume() {
        Log.i("DiscoveryFragment","onResume");
        super.onResume();
    }
    //fragment失去焦点
    @Override
    public void onPause() {
        Log.i("DiscoveryFragment","onPause");
        super.onPause();
    }
    //fragment界面不可见
    @Override
    public void onStop() {
        Log.i("DiscoveryFragment","onStop");
        super.onStop();
    }

    //在onCreateView方法中初始化的view，销毁了
    @Override
    public void onDestroyView() {
        Log.i("DiscoveryFragment","onDestroyView");
        super.onDestroyView();
    }

    //fragment销毁
    @Override
    public void onDestroy() {
        Log.i("DiscoveryFragment","onDestroy");
        super.onDestroy();
    }

    //取消依附在Activity上
    @Override
    public void onDetach() {
        Log.i("DiscoveryFragment","onDetach");
        super.onDetach();
    }
}
