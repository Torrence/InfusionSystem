package com.supersion.infusionsystem;

import java.util.ArrayList;  
import java.util.List;

import com.superion.infusionsystem.util.TableAdapter;
import com.superion.infusionsystem.util.TableAdapter.TableCell;
import com.superion.infusionsystem.util.TableAdapter.TableRow;  
import com.supersion.infusionsystem.internet.IInfusionInfo;
import com.supersion.infusionsystem.internet.IUserValidation;
import com.supersion.infusionsystem.internet.InfusionInfoImpl;
import com.supersion.infusionsystem.internet.UserValidationImpl;
import com.supersion.infusionsystem.model.Infusion;
import com.supersion.infusionsystem.model.Patient;

import android.app.Activity;  
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ListView;  
import android.widget.LinearLayout.LayoutParams;  
import android.widget.TextView;

public class Injection extends Activity {
	
	private ListView lv;
	
	private EditText serialText;
	private EditText identifierText;
	
	private TextView name;
	private TextView gender;
	private TextView age;
	private TextView seatNum;
	private TextView diagnose;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_page);
		
		this.initViews();
		this.initListView();
		
		serialText.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String labelCode = serialText.getText().toString();
					
					IInfusionInfo infusion = new InfusionInfoImpl();
					List<Infusion> infusions = infusion.findInfusionDetail(labelCode);
					
					for(Infusion theInfusion:infusions){
						Log.i("testResult", theInfusion.getTestResult());
						Log.i("autoId", theInfusion.getAutoId());
						Log.i("drugName", theInfusion.getDrugName());
						Log.i("dosage", new Double(theInfusion.getDosage()).toString());
						Log.i("testSign", theInfusion.getTestSign());
						Log.i("dosageUnits", theInfusion.getDosageUnit());
						Log.i("frequency", theInfusion.getFrequency());
						Log.i("labelId", theInfusion.getLabelId());
					}
				}
			}
        });
		
		identifierText.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String patientId = identifierText.getText().toString();
					
					IUserValidation userValidation = new UserValidationImpl();
					Patient patient = userValidation.findRegisterInfo(patientId);
					
					Log.i("patient Id", patient.getPatientId());
					Log.i("paient name", patient.getPatientName());
					Log.i("age", patient.getAge());
					Log.i("sex", patient.getSex());
					
				}
			}
        });
		
    }  
	
	/**
	 * find views by their corresponding ids.
	 */
	public void initViews(){
		this.lv = (ListView) this.findViewById(R.id.ListView01);
		
		this.serialText = (EditText) this.findViewById(R.id.serial);
		this.identifierText = (EditText) this.findViewById(R.id.identifier);
		
		this.name = (TextView) this.findViewById(R.id.name);
		this.gender= (TextView) this.findViewById(R.id.gender);
		this.age = (TextView) this.findViewById(R.id.age);
		this.seatNum = (TextView) this.findViewById(R.id.seat);
		this.diagnose = (TextView) this.findViewById(R.id.diagnose);
	}
	
	
	/**
	 * initialize the table with four rows sample data.
	 */
	public void initListView(){
        ArrayList<TableRow> table = new ArrayList<TableRow>();  
        TableCell[] titles = new TableCell[7];// 每行7个单元  
        int width = this.getWindowManager().getDefaultDisplay().getWidth()/titles.length;  
        // 定义标题  
        
        titles[0] = new TableCell("序号",   
                width + 8 * 0,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[1] = new TableCell("药品名称",   
                width + 200 * 1,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[2] = new TableCell("一次剂量",   
                width + 8 * 2,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[3] = new TableCell("单位",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[4] = new TableCell("执行频率",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[5] = new TableCell("皮试",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[6] = new TableCell("结果",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        
        table.add(new TableRow(titles));
        
        // 每行的数据  
        TableCell[] cells = new TableCell[7];// 每行7个单元  
        for (int i = 0; i < cells.length; i++) {  
            cells[i] = new TableCell("No." + String.valueOf(i),  
                                    titles[i].width,   
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);  
        }
        cells[1] = new TableCell("0.9%浓度的氯化钠溶液试剂",  
        		titles[1].width,   
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        
        
        // 把表格的行添加到表格  
        for (int i = 0; i < 4; i++){
        	TableRow tableRow = new TableRow(cells);
        	
            table.add(tableRow);
            
        }
        TableAdapter tableAdapter = new TableAdapter(this, table);  
        lv.setAdapter(tableAdapter);
	}
		
}
