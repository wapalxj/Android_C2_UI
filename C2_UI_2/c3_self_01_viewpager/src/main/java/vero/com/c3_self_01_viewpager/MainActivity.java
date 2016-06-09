package vero.com.c3_self_01_viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mPager;
    private List<ImageView> list_images;
    private LinearLayout mPointContainer;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager= (ViewPager) findViewById(R.id.pager);
        mPointContainer= (LinearLayout) findViewById(R.id.point_container);
        mTitle= (TextView) findViewById(R.id.tv_title);

        int []imgs={R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5};
        final String []titles={"1","2","3","4","5"};
        mTitle.setText(titles[0]);

        list_images =new ArrayList<>();
        for (int i = 0; i< imgs.length; i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(imgs[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            list_images.add(iv);

            View point =new View(this);
            point.setBackgroundResource(R.drawable.point);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            if (i!=0){
                params.leftMargin=20;
            }else {
                point.setBackgroundResource(R.drawable.point_selector);
            }
            mPointContainer.addView(point,params);
        }

        mPager.setAdapter(new MyAdapter());

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //page滚动时的回调
                // position：当前选中的项
                // positionOffset：滑动百分比：滑动距离/pager宽度
                //positionOffsetPixels：偏移的位置
            }

            @Override
            public void onPageSelected(int position) {
                //选中:滑到某项时回调
                position=position % list_images.size();
                Log.i("OnPageChangeListener","onPageSelected"+position);
                int count=mPointContainer.getChildCount();
                for (int i=0;i<count;i++){
                    View view=mPointContainer.getChildAt(i);
                    view.setBackgroundResource(position==i?R.drawable.point_selector:R.drawable.point);
                }
                mTitle.setText(titles[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态改变回调
                //ViewPager#SCROLL_STATE_IDLE==0:闲置状态
                //ViewPager#SCROLL_STATE_DRAGGING==1：拖动状态
                //ViewPager#SCROLL_STATE_SETTLING==2：固定状态
                //拖动0--->释放1--->2
                Log.i("StateChanged","State"+state);
            }
        });

        int middle=Integer.MAX_VALUE/2;
        int extra=middle % list_images.size();
        int item=middle-extra;//相当于选中第1个
        mPager.setCurrentItem(item);

    }


    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            //返回pager页面的数量
            if (list_images !=null){
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        /**
         *标记方法：用来记录缓存标记
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //view:显示的view
            //object:标记:instantiateItem返回值
            return view==object;
        }

        /**
         * 初始化item:类似于baseAdapter的getView()
         * @param container---->即mPager
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //position:要加载的位置
            position=position % list_images.size();
            ImageView iv= list_images.get(position);
            //添加要显示的view:
            mPager.addView(iv);

            //记录缓存标记：通过return:
            return iv;
        }

        /**
         * 销毁item
         * @param container---->即mPager
         * @param position
         * @param object---->需要移除的iv
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //object:标记
            position=position % list_images.size();
            ImageView iv= list_images.get(position);
            mPager.removeView(iv);
        }
    }


}
