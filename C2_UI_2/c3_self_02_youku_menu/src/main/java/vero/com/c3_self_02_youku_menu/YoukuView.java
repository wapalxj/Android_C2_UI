package vero.com.c3_self_02_youku_menu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 封装成一个view
 * Created by vero on 2016/6/3.
 */
public class YoukuView extends RelativeLayout implements View.OnClickListener {
    private ImageView l1_iv_home;
    private ImageView l2_iv_menu;
    private boolean isLevel1Display=true;
    private boolean isLevel2Display=true;
    private boolean isLevel3Display=true;
    private RelativeLayout mRlLevel1;
    private RelativeLayout mRlLevel2;
    private RelativeLayout mRlLevel3;

    private int mAnimCount;//记录动画播放数量

    public YoukuView(Context context) {
        this(context,null);
    }

    public YoukuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //xml绑定
        View.inflate(context,R.layout.youku,this);//this:父容器就是自己
        setFocusableInTouchMode(true);//当前view可以接收到Activity可以接收的任何时间(不加这行点击硬件menu不起作用)
        initView();
    }

    private void initView(){
        mRlLevel1= (RelativeLayout) findViewById(R.id.rl_level1);
        mRlLevel2= (RelativeLayout) findViewById(R.id.rl_level2);
        mRlLevel3= (RelativeLayout) findViewById(R.id.rl_level3);
        l1_iv_home= (ImageView) findViewById(R.id.l1_iv_home);
        l1_iv_home.setOnClickListener(this);
        l2_iv_menu= (ImageView) findViewById(R.id.l2_iv_menu);
        l2_iv_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l1_iv_home:
                clickL1Home();
                break;
            case R.id.l2_iv_menu:
                clickL2Menu();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    //二级menu菜单键
    private void clickL2Menu() {
        //检测是否有动画正在播放
        if (checkAnim()){
            return;
        }
        //3级菜单都显示，3级菜单隐藏
        if (isLevel3Display){
            hiddenLevel(mRlLevel3,0);//不延时
            isLevel3Display=false;
            return;
        }
        //3级菜单隐藏,3级菜单都显示
        if (!isLevel3Display){
            displayLevel(mRlLevel3,0);//不延时
            isLevel3Display=true;
            return;
        }
    }

    //一级home菜单键
    private void clickL1Home(){
        //检测是否有动画正在播放
        if (checkAnim()){
            return;
        }
        //2,3级菜单都显示，则2,3级菜单都隐藏
        if (isLevel2Display && isLevel3Display){
            hiddenLevel(mRlLevel2,100);//延时
            hiddenLevel(mRlLevel3,0);//不延时
            isLevel2Display=false;
            isLevel3Display=false;
            return;
        }
        //2,3级菜单都隐藏，则2级菜单打开
        if (!isLevel2Display && !isLevel3Display){
            displayLevel(mRlLevel2,0);
            isLevel2Display=true;
            return;
        }
        //2级菜单打开，3级菜单隐藏，则2级菜单隐藏
        if (isLevel2Display && !isLevel3Display){
            hiddenLevel(mRlLevel2,0);
            isLevel2Display=false;
            return;
        }
    }

    private void hiddenLevel(RelativeLayout menu,long startDelay){
//        menu.setVisibility(View.GONE);

        //解决控件隐藏后出现的还能点击BUG
        int count=menu.getChildCount();
        for (int i=0;i<count;i++){
            menu.getChildAt(i).setEnabled(false);
        }

        Animation anim=new RotateAnimation(0,-180,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,1f);
        anim.setDuration(400);
        anim.setFillAfter(true);//保留动画完成后的状态
        anim.setStartOffset(startDelay);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始播放
                mAnimCount++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束播放
                mAnimCount--;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menu.startAnimation(anim);
    }
    private void displayLevel(RelativeLayout menu,long startDelay) {
//        menu.setVisibility(View.VISIBLE);
        int count=menu.getChildCount();
        for (int i=0;i<count;i++){
            menu.getChildAt(i).setEnabled(true);
        }
        Animation anim = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1f);
        anim.setDuration(400);
        anim.setFillAfter(true);//保留动画完成后的状态
        anim.setStartOffset(startDelay);
        menu.startAnimation(anim);
    }

    /**
     * 防止用户使劲不停的点击而产生卡顿
     */
    private boolean checkAnim(){
        //如果当前有动画在执行，则不执行新的动画
        return mAnimCount>0;
    }


    /**
     * 硬件菜单的点击
     */
    private void clickHardwareMenu() {
        //如果3个菜单全部可见，则全部隐藏
        if (isLevel2Display && isLevel3Display && isLevel1Display){
            hiddenLevel(mRlLevel1,0);
            hiddenLevel(mRlLevel2,0);
            hiddenLevel(mRlLevel3,0);

            isLevel1Display=false;
            isLevel2Display=false;
            isLevel3Display=false;
            return;
        }
        //如果3个菜单全部隐藏，则全部可见
        if (!isLevel2Display && !isLevel3Display && !isLevel1Display){
            displayLevel(mRlLevel1,0);
            displayLevel(mRlLevel2,0);
            displayLevel(mRlLevel3,0);

            isLevel1Display=true;
            isLevel2Display=true;
            isLevel3Display=true;
            return;
        }
        //如果3级菜隐藏，则全部隐藏
        if (isLevel2Display && !isLevel3Display && isLevel1Display){
            hiddenLevel(mRlLevel1,0);
            hiddenLevel(mRlLevel2,0);

            isLevel1Display=false;
            isLevel2Display=false;
            return;
        }
        //只有1级可见，则全部隐藏
        if (!isLevel2Display && !isLevel3Display && isLevel1Display){
            hiddenLevel(mRlLevel1,0);

            isLevel1Display=false;
            return;
        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_MENU){
            Log.i("onKeyUp","点击了硬件menu");
            clickHardwareMenu();
            //消费点击事件
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
