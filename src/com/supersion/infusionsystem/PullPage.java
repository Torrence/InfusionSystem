package com.supersion.infusionsystem;

import java.util.ArrayList;

import com.superion.infusionsystem.util.TableAdapter;
import com.superion.infusionsystem.util.TableAdapter.TableCell;
import com.superion.infusionsystem.util.TableAdapter.TableRow;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class PullPage  extends Activity {
	
	private ListView lv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull);
		lv = (ListView) this.findViewById(R.id.ListView01);  
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
