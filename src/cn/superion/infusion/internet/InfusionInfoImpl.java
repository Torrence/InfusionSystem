package cn.superion.infusion.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import cn.superion.infusion.model.Infusion;
import cn.superion.infusion.model.Patient;
import cn.superion.infusion.util.PropertyUtil;

public class InfusionInfoImpl implements IInfusionInfo {
	
	
	private List<Infusion> infusions = new ArrayList<Infusion>();
	
	private String propertyUrl = PropertyUtil.getNetConfigProperties().getProperty("url") + "inject/";

	public List<Infusion> findInfusionDetail(String labelId) {
		try{
			String url = propertyUrl + "findInfusionDetail.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				// ½âÎöJSONÊý¾Ý
				String str = EntityUtils.toString(response.getEntity());
				JSONObject rer = new JSONObject(str);
				JSONObject re = rer.getJSONObject("reObject");
				JSONArray ja = re.getJSONArray("data");
				
				for(int i=0; i<ja.length(); i++){
					JSONObject jobj = ja.getJSONObject(i);
					
					Infusion tmp = new Infusion();
					tmp.setAutoId(jobj.getString("autoId"));
					tmp.setDosage(jobj.getDouble("dosage"));
					//tmp.setDosageUnit(jobj.getString("units"));
					tmp.setDrugName(jobj.getString("drugName"));
					tmp.setFrequency(jobj.getString("frequency"));
					tmp.setLabelId(jobj.getString("labelId"));
					tmp.setTestResult(jobj.getString("testResult"));
					tmp.setTestSign(jobj.getString("testSign"));
					
					infusions.add(tmp);
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return infusions;
	}

	public boolean puncture(String autoId, String labelId) {
		boolean flag = false;
		try{
			String url = propertyUrl + "puncture.do";
			HttpPost request = new HttpPost(url);
			
			Log.i("autoId", autoId);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				flag = true;				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean turnBottle(String autoId, String labelId) {
		boolean flag = false;
		
		try{
			String url = propertyUrl + "turnBottle.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				flag = true;				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean pull(String autoId, String labelId) {
		boolean flag = false;
		
		try{
			String url = propertyUrl + "pull.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				flag = true;				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Patient findRegisterInfo(String patientId) {

		Patient patient = new Patient();
		
		try{
			String url = propertyUrl + "findRegisterInfo.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			
			params.add(new BasicNameValuePair("patientId", patientId));
			
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				String str = EntityUtils.toString(response.getEntity());
				
				JSONObject rer = new JSONObject(str);
				
				
				
				JSONObject re = rer.getJSONObject("reObject");
				
				boolean success = re.getBoolean("success");
				
				if(!success){
					return null;
				}
				
				JSONArray ja = re.getJSONArray("data");
				
				if(ja == null){
					return null;
				}
				if(ja.equals(null)){
					return null;
				}
				if(ja.isNull(0)){
					return null;
				}
				
				
				JSONObject jobj = ja.getJSONObject(0);
				
				patient.setPatientId(patientId);
				patient.setPatientName(jobj.getString("patientName"));
				patient.setAge(jobj.getString("age"));
				patient.setSex(jobj.getString("sex"));
				patient.setSeatNo("001");
				patient.setDiseaseName(jobj.getString("diseaseName"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

}
