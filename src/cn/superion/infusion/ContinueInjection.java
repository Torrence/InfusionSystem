package cn.superion.infusion;

import java.util.ArrayList;
import java.util.List;

import cn.superion.infusion.internet.IInfusionInfo;
import cn.superion.infusion.internet.InfusionInfoImpl;
import cn.superion.infusion.model.Infusion;
import cn.superion.infusion.model.Patient;
import cn.superion.infusion.util.TableAdapter;
import cn.superion.infusion.util.TableAdapter.TableCell;
import cn.superion.infusion.util.TableAdapter.TableRow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ContinueInjection extends Activity {
	
private ListView lv;
	
	private EditText serialText;
	private EditText identifierText;
	
	private TextView name;
	private TextView gender;
	private TextView age;
	private TextView seatNum;
	private TextView diagnose;
	
	private static final int SERIAL = 0;
	private static final int DRUG_NAME = 1;
	private static final int DOSAGE = 2;
	private static final int UNIT = 3;
	private static final int FREQUENCY = 4;
	private static final int TEST_SIGN = 5;
	private static final int TEST_RESULT = 6;
	
	private List<Infusion> infusions = new ArrayList<Infusion>();
	
	ArrayList<TableRow> table = new ArrayList<TableRow>();
	TableCell[] titles = new TableCell[7];// 每行7个单元
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.continue_injection_page);
		
		/*
		 * find views by their corresponding ids.
		 */
		this.initViews();
		
		/*
		 * initialize the table with four rows sample data.
		 */
		this.initListView();
		
		serialText.setOnFocusChangeListener(new OnFocusChangeListener(){

			@SuppressLint("UseValueOf")
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String labelCode = serialText.getText().toString();
					
					//labelCode = "11208230141";
					
					IInfusionInfo infusion = new InfusionInfoImpl();
					infusions = infusion.findInfusionDetail(labelCode);
					
					Log.i("infusion info sum", new Integer(infusions.size()).toString());
					
					int count = 1;
					for(Infusion theInfusion:infusions){
						Log.i("testResult", theInfusion.getTestResult());
						Log.i("autoId", theInfusion.getAutoId());
						Log.i("drugName", theInfusion.getDrugName());
						Log.i("dosage", new Double(theInfusion.getDosage()).toString());
						Log.i("testSign", theInfusion.getTestSign());
						//Log.i("dosageUnits", theInfusion.getDosageUnit());
						Log.i("frequency", theInfusion.getFrequency());
						Log.i("labelId", theInfusion.getLabelId());
						
						TableCell[] cells = new TableCell[7];// 每行7个单元  

						cells[SERIAL] = new TableCell(new Integer(count).toString(), titles[SERIAL].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
						
						cells[DRUG_NAME] = new TableCell(theInfusion.getDrugName(), titles[DRUG_NAME].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
						
						cells[UNIT] = new TableCell("", titles[UNIT].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
				        
						cells[DOSAGE] = new TableCell(theInfusion.getDosage(), titles[DOSAGE].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
						
						cells[FREQUENCY] = new TableCell(theInfusion.getFrequency(), titles[FREQUENCY].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
				        
						String testSign;
						if(theInfusion.getTestSign() == "0"){
							testSign = "否";
						}else{
							testSign = "是";
						}
						cells[TEST_SIGN] = new TableCell(testSign, titles[TEST_SIGN].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
						
						String testResult;
						if(theInfusion.getTestSign() == "0"){
							testResult = "阴性";
						}else{
							testResult = "阳性";
						}
						cells[TEST_RESULT] = new TableCell(testResult, titles[TEST_RESULT].width,
								LayoutParams.FILL_PARENT, TableCell.STRING);
						
				        table.remove(count);
				        TableRow tableRow = new TableRow(cells);
				        table.add(count, tableRow);
				        count++;
					}
					
					TableAdapter tableAdapter = new TableAdapter(ContinueInjection.this, table);  
			        lv.setAdapter(tableAdapter);
					
				}
			}
        });
		
		identifierText.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String patientId = "3210" + identifierText.getText().toString();
					String labelCode = serialText.getText().toString();
					//patientId = "32102001910578";
					IInfusionInfo infusion = new InfusionInfoImpl();
					Patient patient = infusion.findRegisterInfo(patientId);
					
					if(patient == null){
						new AlertDialog.Builder(ContinueInjection.this)
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
					
					Log.i("patient Id", patient.getPatientId());
					Log.i("paient name", patient.getPatientName());
					Log.i("age", patient.getAge());
					Log.i("sex", patient.getSex());
					
					name.setText(patient.getPatientName());
					gender.setText(patient.getSex());
					age.setText(patient.getAge());
					seatNum.setText(patient.getSeatNo());
					diagnose.setText(patient.getDiseaseName());
					
					if(infusions.size() == 0){
						return;
					}
					
					infusion.turnBottle(infusions.get(0).getAutoId(), labelCode);
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
            cells[i] = new TableCell("",  
                                    titles[i].width,   
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);  
        }
        cells[1] = new TableCell("",  
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