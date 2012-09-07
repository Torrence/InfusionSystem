package cn.superion.infusion;

import cn.superion.infusion.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class Transfusion extends TabActivity implements OnCheckedChangeListener{
	
	private TabHost mHost;
	private RadioGroup radioderGroup;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set the UI presented in a full-screen way.
		Window window = this.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.bottom_tab);
		
		mHost=this.getTabHost();
        
        mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
        		.setContent(new Intent(this, Injection.class)));
        
        mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
    			.setContent(new Intent(this, ContinueInjection.class)));
        
        mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
    			.setContent(new Intent(this, PullPage.class)));
        
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
		radioderGroup.setOnCheckedChangeListener(this);
	}
	
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radio_button0:
			mHost.setCurrentTabByTag("ONE");
			break;
		case R.id.radio_button1:
			mHost.setCurrentTabByTag("TWO");
			break;
		case R.id.radio_button2:
			mHost.setCurrentTabByTag("THREE");
			break;
		}
	}

}
