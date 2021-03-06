package de.thathalas.protonet.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetUserDevice;

public class Devices {

	private ProtonetWrapper protonet;
	
	public Devices(ProtonetWrapper protonet) {
		this.protonet = protonet;
	}
	
	public ProtonetUserDevice create(String deviceId, String platform) {
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("device_id", deviceId));
		data.add(new BasicNameValuePair("platform", platform));
		return ProtonetUserDevice.createUserDevice(protonet.post("/api/v1/users/"+ protonet.getUserId() +"/devices", data));
	}
	
	public boolean destroy(int userId, int deviceId) {
		String response = protonet.delete("/api/v1/users/" + userId + "/devices/" + deviceId);
		return response == "{}";
	}
	
	public boolean update_active(int userId, int deviceId) {
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		String response = protonet.patch("/api/v1/users/" + userId + "/devices/" + deviceId + "/update_active", list);
		return response == "{}";
	}
}
