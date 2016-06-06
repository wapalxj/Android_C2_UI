package com.example.qqsmiledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqsmiledemo.MyBiaoQingFragment1.OnArticleSelectedListener;
import com.example.qqsmiledemo.MyBiaoQingFragment2.OnArticleSelectedListener2;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnArticleSelectedListener, OnArticleSelectedListener2 {

	//切换是否显示表情栏的控件
	private ImageView iv_show;
	//输入、发送表情框控件
	private TextView tv_biaoqingkuang,tv_send;
	//表情框控件
	private LinearLayout ll_biaoqing;
	//viewpager切换时，高亮显示控件
	private RelativeLayout rl_indicator;
	private ImageView iv_indicator1;
	private ImageView iv_indicator2;
	//viewpager
	private ViewPager biaoqing_viewpager;
	//adapter
	private BiaoQingAdapter biaoQingAdapter;

	// 默认表情区域不显示（隐藏状态）
	private boolean isBiaoqingShow = false;
	//目前viewpager所在页面的position
	private int currPos;
	private SpannableStringBuilder spb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {
		iv_show = (ImageView) findViewById(R.id.iv_show);
		tv_biaoqingkuang = (TextView) findViewById(R.id.tv_biaoqingkuang);
		tv_send = (TextView) findViewById(R.id.tv_send);

		ll_biaoqing = (LinearLayout) findViewById(R.id.ll_biaoqing);
		iv_indicator1 = (ImageView) findViewById(R.id.iv_indicator1);
		iv_indicator2 = (ImageView) findViewById(R.id.iv_indicator2);
		rl_indicator = (RelativeLayout) findViewById(R.id.rl_indicator);
		biaoqing_viewpager = (ViewPager) findViewById(R.id.biaoqing_viewpager);
		biaoQingAdapter = new BiaoQingAdapter(getSupportFragmentManager());
		biaoqing_viewpager.setAdapter(biaoQingAdapter);

		// 设置页面改变监听
		biaoqing_viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					if (currPos == 1) {

						iv_indicator1
								.setBackgroundResource(R.drawable.flag_online);
						iv_indicator2
								.setBackgroundResource(R.drawable.flag_offline);
					}
					break;
				case 1:
					if (currPos == 0) {
						iv_indicator2
								.setBackgroundResource(R.drawable.flag_online);
						iv_indicator1
								.setBackgroundResource(R.drawable.flag_offline);
					}
					break;
				default:
					break;
				}

				currPos = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		iv_show.setOnClickListener(this);
		tv_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_show:
			if (!isBiaoqingShow) {
				ll_biaoqing.setVisibility(View.VISIBLE);
				rl_indicator.setVisibility(View.VISIBLE);
				isBiaoqingShow = true;

			} else {
				ll_biaoqing.setVisibility(View.GONE);
				rl_indicator.setVisibility(View.GONE);
				isBiaoqingShow = false;
			}

			break;

		case R.id.tv_send:
			Toast.makeText(MainActivity.this, "聊天界面功能未实现", 0).show();
			break;
		default:
			break;
		}
	}

	//实现接口方法
	@Override
	public void onArticleSelected(int pagePos, int[] imgs, int position) {
		if (pagePos == 0) {
			if (position < imgs.length - 1) {

				spb = new SpannableStringBuilder();
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						imgs[position]);
				bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
				// imageSpan用来让图片代替字体的
				ImageSpan imageSpan = new ImageSpan(this, bitmap);
				// 用来设置EditText多种样式的类
				SpannableString spanString = new SpannableString("表情");
				// 指定位置替换字体
				spanString.setSpan(imageSpan, 0, spanString.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				spb.append(spanString);
				tv_biaoqingkuang.append(spb);
			} else {
				Toast.makeText(MainActivity.this, "回退删除表情未实现，用系统自带的吧", 0)
						.show();
			}

		} else if (pagePos == 1) {
			if (position < imgs.length - 1) {

				spb = new SpannableStringBuilder();
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						imgs[position]);
				bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, true);
				ImageSpan imageSpan = new ImageSpan(this, bitmap);

				SpannableString spanString = new SpannableString("表情");
				spanString.setSpan(imageSpan, 0, spanString.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				spb.append(spanString);
				tv_biaoqingkuang.append(spb);
			} else {
				Toast.makeText(MainActivity.this, "回退删除表情未实现，用系统自带的吧", 0)
						.show();

			}

		}

	}

}
