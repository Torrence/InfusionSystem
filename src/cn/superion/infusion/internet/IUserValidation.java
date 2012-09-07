package cn.superion.infusion.internet;

import cn.superion.infusion.model.Nurse;

public interface IUserValidation {
	
	public boolean sendPostRequest(String userName, String password, String unitCode,
			String unitName, String roleName);
	
	public boolean userLogout();
	
	public Nurse findRoleAndUnits(String userCode);
	
}
