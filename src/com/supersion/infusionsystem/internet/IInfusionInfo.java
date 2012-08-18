package com.supersion.infusionsystem.internet;

import java.util.List;

import com.supersion.infusionsystem.model.Infusion;

public interface IInfusionInfo {
	public List<Infusion> findInfusionDetail(String labelCode);
	
	public boolean puncture(String autoId, String labelId);
	
	public boolean turnBottle(String autoId, String labelId);
	
	public boolean pull(String autoId, String labelId);
}