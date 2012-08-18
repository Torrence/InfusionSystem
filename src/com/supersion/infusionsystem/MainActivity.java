package com.supersion.infusionsystem;

import com.supersion.infusionsystem.internet.IUserValidation;
import com.supersion.infusionsystem.internet.UserValidationImpl;
import com.supersion.infusionsystem.model.Nurse;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
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
        
        ImageButton confirm = (ImageButton) this.findViewById(R.id.loginConfirm);
        
        nameText = (EditText) this.findViewById(R.id.name);
        passwordText = (EditText) this.findViewById(R.id.password);
        
        nameText.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String userCode = nameText.getText().toString();
					IUserValidation userVal = new UserValidationImpl();
					nurse = userVal.findRoleAndUnits(userCode);
					
					Log.i("userCode", nurse.getUserCode());
					Log.i("roleCode", nurse.getRoleCode());
					Log.i("roleName", nurse.getRoleName());
					Log.i("unitCode",nurse.getUnitCode());
					Log.i("unitName", nurse.getUnitName());
				}
			}
        	
        });
        
        confirm.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
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
        });
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
				//String role = roleSpinner.getSelectedItem().toString();
				
				//nurse.setRoleName(role);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        	
        });
        
        departmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//String department = departmentSpinner.getSelectedItem().toString();
				//nurse.setUnitName(department);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        	
        });
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
