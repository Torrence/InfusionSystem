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
        TableCell[] titles = new TableCell[7];// ÿ��7����Ԫ  
        int width = this.getWindowManager().getDefaultDisplay().getWidth()/titles.length;  
        // �������  
        
        titles[0] = new TableCell("���",   
                width + 8 * 0,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[1] = new TableCell("ҩƷ����",   
                width + 200 * 1,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[2] = new TableCell("һ�μ���",   
                width + 8 * 2,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[3] = new TableCell("��λ",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[4] = new TableCell("ִ��Ƶ��",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[5] = new TableCell("Ƥ��",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        titles[6] = new TableCell("���",   
                width + 8 * 3,  
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        
        table.add(new TableRow(titles));
        
        // ÿ�е�����  
        TableCell[] cells = new TableCell[7];// ÿ��7����Ԫ  
        for (int i = 0; i < cells.length; i++) {  
            cells[i] = new TableCell("No." + String.valueOf(i),  
                                    titles[i].width,   
                                    LayoutParams.FILL_PARENT,   
                                    TableCell.STRING);  
        }
        cells[1] = new TableCell("0.9%Ũ�ȵ��Ȼ�����Һ�Լ�",  
        		titles[1].width,   
                LayoutParams.FILL_PARENT,   
                TableCell.STRING);
        
        
        // �ѱ�������ӵ����  
        for (int i = 0; i < 4; i++){
        	TableRow tableRow = new TableRow(cells);
        	
            table.add(tableRow);
            
        }
        TableAdapter tableAdapter = new TableAdapter(this, table);  
        lv.setAdapter(tableAdapter);  
	}
}
