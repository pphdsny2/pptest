package ca.eatto.android.utils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SoftKeyboardManager {
	
	public static interface OnKeyboardToggledListener{
		void onToggled(boolean isShow);
	}
	
	private InputMethodManager mInputMM;
	private Rect mVisRect = new Rect();
	private Handler mLocalHandler = new Handler();
	private View mRoot;
	
	private boolean isShow = false; 
	private Runnable mAfterKeyboardShown = null;
	private Runnable mAfterKeyboardHidden = null;
	
	private OnKeyboardToggledListener mListener;
	private int mLastHeightDiff;
	
	public SoftKeyboardManager(Context context, final View root){
		mInputMM = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		mRoot = root;
		root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		@Override
		public void onGlobalLayout() {
		    //r will be populated with the coordinates of your view that area still visible.
		    root.getWindowVisibleDisplayFrame(mVisRect);
		    int heightDiff = root.getRootView().getHeight() - (mVisRect.bottom - mVisRect.top);
		    if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
		    	isShow = true;
		    	if(mAfterKeyboardShown!=null){
		    		mLocalHandler.post(mAfterKeyboardShown);
		    		mAfterKeyboardShown = null;
		    	}
		    	if(mListener!=null&&mLastHeightDiff<100){
		    		mListener.onToggled(true);
		    	}
		    }else{
		    	isShow = false;
		    	if(mAfterKeyboardHidden!=null){
		    		mLocalHandler.post(mAfterKeyboardHidden);
		    		mAfterKeyboardHidden = null;
		    	}
		    	if(mListener!=null&&mLastHeightDiff>100){
		    		mListener.onToggled(false);
		    	}
		    }
		    mLastHeightDiff = heightDiff;
		 }
		}); 
	}
	
	/**
	 * 返回当前键盘是否显示
	 * @return
	 */
	public boolean isKeyboardShown(){
		return isShow;
	}
	
	/**
	 * 屏蔽点击EditText后,弹出键盘
	 * @param et 相应的EditText
	 */
	public void ignoreShowKeyboard(EditText et){
		if (android.os.Build.VERSION.SDK_INT <= 10) {
            et.setInputType(InputType.TYPE_NULL);
        } else {
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(et, false);
            } catch (Exception e) {
                Log.e(SoftKeyboardManager.class.getSimpleName(), e.getMessage());
            }
        }
	}
	
	public void showKeyboard(View v){
		v.requestFocus();
		if(v instanceof EditText){
			((EditText)v).setSelection(((EditText) v).getText().length());
		}
		mInputMM.showSoftInput(v, 0);
	}
	
	/**
	 * 键盘显示后,运行runnable
	 * @param v
	 * @param runnable
	 */
	public void showKeyboard(View v, Runnable runnable){
		mAfterKeyboardShown = runnable;
		showKeyboard(v);
	}
	
	public void hideKeyboard(View v){
		mInputMM.hideSoftInputFromWindow(v.getWindowToken(), 0);
		View view = mRoot.findFocus();
		if(view != null){
			view.clearFocus();
		}
		mRoot.requestFocus();
	}
	
	/**
	 * 键盘隐藏后,运行runnable
	 * @param v
	 * @param runnable
	 */
	public void hideKeyboard(View v, Runnable runnable){
		mAfterKeyboardHidden = runnable;
		hideKeyboard(v);
	}
	
	public void setOnKeyboardToggledListener(OnKeyboardToggledListener listener){
		if(mListener!=listener)
			mListener = listener;
	}
	
	public InputMethodManager getInputMethodManager(){
		return mInputMM;
	}
}