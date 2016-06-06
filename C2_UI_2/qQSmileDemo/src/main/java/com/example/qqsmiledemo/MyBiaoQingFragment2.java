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

public class MyBiaoQingFragment2 extends Fragment {
	private View biaoqing2View;
	private GridView gv_biaoqing2;
	private BiaoQingFragment2Adapter biaoQingFragment2Adapter;
	private OnArticleSelectedListener2 mListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		biaoqing2View = View.inflate(getActivity(), R.layout.biaoqing2, null);
		initView();
		return biaoqing2View;
	}
	private void initView() {
		gv_biaoqing2 = (GridView) biaoqing2View.findViewById(R.id.gv_biaoqing2);
		biaoQingFragment2Adapter = new BiaoQingFragment2Adapter(getActivity());
		gv_biaoqing2.setAdapter(biaoQingFragment2Adapter);
		
		
		gv_biaoqing2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mListener.onArticleSelected(1,imgs, position);
			}
			
		});
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnArticleSelectedListener2) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnArticleSelectedListener");
		}
	}
	private int[] imgs = { 
			R.drawable.emoji_20, R.drawable.emoji_21, R.drawable.emoji_22,  
			R.drawable.emoji_23, R.drawable.emoji_24, R.drawable.emoji_25,  
			R.drawable.emoji_26, R.drawable.emoji_27, R.drawable.emoji_28,  
			R.drawable.emoji_29, R.drawable.emoji_30, R.drawable.emoji_31,  
			R.drawable.emoji_32, R.drawable.emoji_33, R.drawable.emoji_34,  
			R.drawable.top_return_n,  
			
	}; 
	class BiaoQingFragment2Adapter extends BaseAdapter{
		private Context context;
		
		BiaoQingFragment2Adapter(Context context){
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
	
	public interface OnArticleSelectedListener2 {
		public void onArticleSelected(int pagePos,int[] imgs, int position);
	}
}
