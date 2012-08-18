package com.supersion.infusionsystem.model;

//autoId	String	��Һϵͳ��ʶ��
//labelId	String	ƿǩ��
//drugName	String	ҩƷ����
//dosage	double	����
//dosageUnits	String	������λ
//frequency	String	ִ��Ƶ��
//testSign	String	�Ƿ�Ƥ��ҩƷ
//testResult	String	Ƥ�Խ��
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
