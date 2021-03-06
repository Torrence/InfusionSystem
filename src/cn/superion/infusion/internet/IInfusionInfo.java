package cn.superion.infusion.internet;

import java.util.List;

import cn.superion.infusion.model.Infusion;
import cn.superion.infusion.model.Patient;

public interface IInfusionInfo {
	public List<Infusion> findInfusionDetail(String labelCode);
	
	public boolean puncture(String autoId, String labelId);
	
	public boolean turnBottle(String autoId, String labelId);
	
	public boolean pull(String autoId, String labelId);
	
	public Patient findRegisterInfo(String patientId);
}