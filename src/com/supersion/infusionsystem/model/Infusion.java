package com.supersion.infusionsystem.model;

//autoId	String	输液系统标识号
//labelId	String	瓶签号
//drugName	String	药品名称
//dosage	double	剂量
//dosageUnits	String	剂量单位
//frequency	String	执行频率
//testSign	String	是否皮试药品
//testResult	String	皮试结果
public class Infusion {
	private String labelId;
	private String autoId;
	private String drugName;
	private double dosage;
	private String dosageUnit;
	private String frequency;
	private String testSign;
	private String testResult;
	
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public double getDosage() {
		return dosage;
	}
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}
	public String getDosageUnit() {
		return dosageUnit;
	}
	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getTestSign() {
		return testSign;
	}
	public void setTestSign(String testSign) {
		this.testSign = testSign;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}
