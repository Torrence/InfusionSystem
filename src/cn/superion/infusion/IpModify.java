package cn.superion.infusion;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class IpModify extends Activity {
	
	private EditText ipText;
	private ImageButton cancel;
	private ImageButton confirm;

    @SuppressLint("CommitPrefEdits")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_modify);
        
        ipText = (EditText) this.findViewById(R.id.ip);
        cancel = (ImageButton) this.findViewById(R.id.ipCancel);
        confirm = (ImageButton) this.findViewById(R.id.ipConfirm);
        
        confirm.setOnClickListener(new OnClickListener(){
			@SuppressLint("ShowToast")
			public void onClick(View v) {
				
				String ipAddress = ipText.getText().toString();
				if( ipAddress== "" || ipAddress.isEmpty()){
					Toast.makeText(IpModify.this, "IP地址为空，请重新输入", Toast.LENGTH_LONG).show();
				}else{
					
					Log.i("ip", ipAddress);
					boolean flag = saveIpConfig(ipAddress);
					
					if(flag){
						Toast.makeText(IpModify.this, "IP地址已更改", Toast.LENGTH_LONG).show();
						
					}
					Intent intent = new Intent();
					intent.setClass(IpModify.this, MainActivity.class);
					startActivity(intent);
				}
			}
        });
        
        cancel.setOnClickListener(new OnClickListener(){
			@SuppressLint("ShowToast")
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(IpModify.this, MainActivity.class);
				startActivity(intent);
			}
        });
        
        
        
//        SharedPreferences sharedPreferences = getSharedPreferences("netconfig", Activity.MODE_PRIVATE);
//        String ip = sharedPreferences.getString("ip", "");
//        Toast.makeText(this, "IP: " + ip, Toast.LENGTH_LONG).show();
    }
    
    public boolean saveIpConfig(String ipAddress){
    	
    	SharedPreferences mySharedPreferences = getSharedPreferences("netconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("ip", ipAddress);
        editor.commit();
        
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
