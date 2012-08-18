package com.supersion.infusionsystem.internet;

import com.supersion.infusionsystem.model.Nurse;
import com.supersion.infusionsystem.model.Patient;

public interface IUserValidation {
	
	public boolean sendPostRequest(String userName, String password, String unitCode,
			String unitName, String roleName);
	
	public boolean userLogout();
	
	public Nurse findRoleAndUnits(String userCode);
	
	public Patient findRegisterInfo(String patientId);

}
