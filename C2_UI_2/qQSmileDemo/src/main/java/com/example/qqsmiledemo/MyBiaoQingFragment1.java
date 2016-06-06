package com.example.qqsmiledemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyBiaoQingFragment1 extends Fragment {
	private View biaoqing1View;
	private GridView gv_biaoqing1;
	private BiaoQingFragment1Adapter biaoQingFragment1Adapter;

	private OnArticleSelectedListener mListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		biaoqing1View = View.inflate(getActivity(), R.layout.biaoqing1, null);
		initView();
		return biaoqing1View;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			//强转换
			mListener = (OnArticleSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnArticleSelectedListener");
		}
	}

	private void initView() {
		gv_biaoqing1 = (GridView) biaoqing1View.findViewById(R.id.gv_biaoqing1);
		biaoQingFragment1Adapter = new BiaoQingFragment1Adapter(getActivity());
		gv_biaoqing1.setAdapter(biaoQingFragment1Adapter);

		gv_biaoqing1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 每个条目的点击监听事件

				mListener.onArticleSelected(0,imgs, position);
			}

		});
	}

	private int[] imgs = { R.drawable.emoji_0, R.drawable.emoji_1,
			R.drawable.emoji_2, R.drawable.emoji_3, R.drawable.emoji_4,
			R.drawable.emoji_5, R.drawable.emoji_6, R.drawable.emoji_7,
			R.drawable.emoji_8, R.drawable.emoji_9, R.drawable.emoji_10,
			R.drawable.emoji_11, R.drawable.emoji_12, R.drawable.emoji_13,
			R.drawable.emoji_14, R.drawable.emoji_15, R.drawable.emoji_16,
			R.drawable.emoji_17, R.drawable.emoji_18, R.drawable.emoji_19,
			R.drawable.top_return_n,

	};

	class BiaoQingFragment1Adapter extends BaseAdapter {
		private Context context;

		BiaoQingFragment1Adapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imgs[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(context, R.layout.picture_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			imageView.setBackgroundResource(imgs[position]);
			return view;
		}

	}

	/**
	 * 回调接口--每个表情被选中调用接口
	 *
	 */
	public interface OnArticleSelectedListener {
		public void onArticleSelected(int pagePos,int[] imgs, int position);
	}
}
