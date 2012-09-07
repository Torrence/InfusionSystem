package cn.superion.infusion;

import cn.superion.infusion.R;
import cn.superion.infusion.internet.IUserValidation;
import cn.superion.infusion.internet.UserValidationImpl;
import cn.superion.infusion.model.Nurse;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	private Spinner roleSpinner;
	private Spinner departmentSpinner;
	private EditText nameText;
	private EditText passwordText;
	private ImageButton confirm;
	private ImageButton cancel;
	
	
	private Nurse nurse = new Nurse();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        //初始化下拉框
        this.initSpinners();
        
        this.confirm = (ImageButton) this.findViewById(R.id.loginConfirm);
        this.cancel = (ImageButton) this.findViewById(R.id.loginCancel);
        this.nameText = (EditText) this.findViewById(R.id.name);
        this.passwordText = (EditText) this.findViewById(R.id.password);
        
        this.initListener();
    }
    
    public void initListener(){
    	nameText.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String userCode = nameText.getText().toString();
					IUserValidation userVal = new UserValidationImpl();
					nurse = userVal.findRoleAndUnits(userCode);
					
					if(nurse == null){
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("错误提示")
						.setMessage("当前用户不存在，请重新输入")
						.setNegativeButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						})
						.setIcon(R.drawable.error)
						.show();
						return;
					}
					
					Log.i("userCode", nurse.getUserCode());
					Log.i("roleCode", nurse.getRoleCode());
					Log.i("roleName", nurse.getRoleName());
					Log.i("unitCode",nurse.getUnitCode());
					Log.i("unitName", nurse.getUnitName());
				}
			}
        });
    	
    	cancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
			}
    	});
        
        confirm.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				processLogin();
			}
        });
        
        
		OnKeyListener onKey = new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					processLogin();
				}
				return false;
			}
		};
		confirm.setOnKeyListener(onKey);
		passwordText.setOnKeyListener(onKey);
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					MainActivity.this);
			dialog.setTitle("确认退出？")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setMessage("确认退出？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									android.os.Process.killProcess(android.os.Process.myPid());
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).create().show();
			return true;
		}else{		
			return super.onKeyDown(keyCode, event);
		}
	}
 
 
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}    
    public void initSpinners(){
    	// setting spinner
        roleSpinner = (Spinner) this.findViewById(R.id.role_spinner);
        departmentSpinner = (Spinner) this.findViewById(R.id.department_spinner);
        
        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        roleSpinner.setAdapter(roleAdapter);
        departmentSpinner.setAdapter(departmentAdapter);
        
        roleSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String role = roleSpinner.getSelectedItem().toString();
				
				nurse.setRoleName(role);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        	
        });
        
        departmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String department = departmentSpinner.getSelectedItem().toString();
				nurse.setUnitName(department);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        	
        });
    }
    
    public void processLogin(){
    	String password = passwordText.getText().toString();
		nurse.setPwd(password);
		
		Log.d("confirm", "name: " + nurse.getUserCode() + " password:" + nurse.getPwd() + 
			    " role:" + nurse.getRoleCode() + " unitName:" + nurse.getUnitName() + 
				" unitCode:" + nurse.getUnitCode());
		IUserValidation userVal = new UserValidationImpl();
		boolean flag = userVal.sendPostRequest(nurse.getUserCode(), nurse.getPwd(),
				nurse.getUnitCode(), nurse.getUnitName(), nurse.getRoleCode());
		
		if(flag){
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Transfusion.class);
			startActivity(intent);
		}else{
			new AlertDialog.Builder(MainActivity.this)
			.setTitle("错误提示")
			.setMessage("用户名与密码不匹配，请重新输入")
			.setNegativeButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			})
			.setIcon(R.drawable.error)
			.show();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
}
