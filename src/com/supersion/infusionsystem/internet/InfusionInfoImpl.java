package com.supersion.infusionsystem.internet;

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

import com.supersion.infusionsystem.model.Infusion;

public class InfusionInfoImpl implements IInfusionInfo {
	
	private static final String URL_HEADER = "http://192.168.1.52:8080/SupCtis/spring/pda/";

	public List<Infusion> findInfusionDetail(String labelId) {
		
		List<Infusion> infusions = new ArrayList<Infusion>();
		
		try{
			String url = URL_HEADER + "findInfusionDetail.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
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
					tmp.setDosageUnit(jobj.getString("dosageUnit"));
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
			String url = URL_HEADER + "inject/puncture.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
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
			String url = URL_HEADER + "inject/turnBottle.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
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
			String url = URL_HEADER + "inject/pull.do";
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair> ();
			params.add(new BasicNameValuePair("autoId", autoId));
			params.add(new BasicNameValuePair("labelId", labelId));
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				flag = true;				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
