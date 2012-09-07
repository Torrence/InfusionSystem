package cn.superion.infusion.internet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import cn.superion.infusion.model.Nurse;
import cn.superion.infusion.util.PropertyUtil;

public class UserValidationImpl implements IUserValidation{
	
	private String propertyUrl = PropertyUtil.getNetConfigProperties().getProperty("url");
	
	public static HttpClient httpClient = new DefaultHttpClient();
	
	public boolean sendPostRequest(String userCode, String password, String unitsCode,
			String unitsName, String roleCode){
		boolean pan = false;
		
		try{
			String url = propertyUrl + "login.do";
			
			HttpPost request = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			
			params.add(new BasicNameValuePair("userCode", userCode));
			params.add(new BasicNameValuePair("pwd", password));
			params.add(new BasicNameValuePair("unitsCode", unitsCode));
			params.add(new BasicNameValuePair("unitsName", unitsName));
			params.add(new BasicNameValuePair("roleCode", roleCode));
			
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			UserValidationImpl.httpClient = new DefaultHttpClient();
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				pan = true;
				// 解析JSON数据
				String str = EntityUtils.toString(response.getEntity());
				
				JSONObject rer = new JSONObject(str);
				
				JSONObject re = rer.getJSONObject("reObject");
				
				boolean ja = re.getBoolean("success");
				
				pan = ja;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pan;
	}

	public boolean userLogout() {
		boolean pan = false;
		try{
			String url = propertyUrl + "logout.do";
			HttpPost request = new HttpPost(url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				pan = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pan;
	}
	
	@SuppressLint("UseValueOf")
	public Nurse findRoleAndUnits(String userCode) {
		Nurse nurse = new Nurse();
		nurse.setUserCode(userCode);
		
		List<String> roles = new ArrayList<String>();
		List<String> roleCodes = new ArrayList<String>();
		List<String> unitCodes = new ArrayList<String>();
		List<String> units = new ArrayList<String>();
		
		try{
			
			
			
			Log.i("propertyUrl", propertyUrl);
			
			String url = propertyUrl + "findRoleAndUnits.do";
			
			Log.i("url",url);
			
			HttpPost request = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("userCode", userCode));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = UserValidationImpl.httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				// 解析JSON数据
				String str = EntityUtils.toString(response.getEntity());
				
				JSONObject rer = new JSONObject(str);
				
				JSONObject re = rer.getJSONObject("reObject");
				
				boolean success = re.getBoolean("success");
				
				if(!success){
					return null;
				}
				
				JSONArray ja = re.getJSONArray("data");
				JSONObject jobj = (JSONObject) ja.getJSONObject(0);
				
				JSONArray userRoles = jobj.getJSONArray("userRoles");
				for(int i = 0; i < userRoles.length(); i++){
					JSONObject userRole = (JSONObject) userRoles.getJSONObject(i);
					roles.add(userRole.getString("roleName"));
					roleCodes.add(userRole.getString("roleCode"));
				}
				
				JSONArray userUnits = jobj.getJSONArray("userUnits");
				for(int i = 0; i < userUnits.length(); i++){
					JSONObject userUnit = (JSONObject) userUnits.getJSONObject(i);
					units.add(userUnit.getString("unitsName"));
					unitCodes.add(userUnit.getString("unitsCode"));
				}
				
				if(roles.size()>0 && units.size()>0 && unitCodes.size()>0){
					nurse.setRoleName(roles.get(0));
					nurse.setUnitCode(unitCodes.get(0));
					nurse.setUnitName(units.get(0));
					nurse.setRoleCode(roleCodes.get(0));
				}
			}
		}catch(Exception e) {
			return null;
		}
		return nurse;
	}

	

}
