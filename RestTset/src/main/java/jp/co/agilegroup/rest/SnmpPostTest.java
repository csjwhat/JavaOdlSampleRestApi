package jp.co.agilegroup.rest;

import javax.ws.rs.core.MediaType;

public class SnmpPostTest {

	public static void main(String[] args) {

		// 基本認証 ユーザ="admin" パスワード="admin"
		RestClient client = new RestClient("admin", "admin");
		String uri = "http://localhost:8181/restconf/operations/snmp:snmp-get"; // リクエスト先

		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("  \"input\": { ");
		json.append("    \"ip-address\": \"192.168.11.105\", ");
		json.append("    \"oid\": \".1.3.6.1.2.1.1.4.0\", ");
		json.append("    \"get-type\":\"GET\",");
		json.append("    \"community\":\"public\"");
		json.append("  }");
		json.append("}");

		String jsonString = json.toString();
		String res = client.post(uri, jsonString, String.class, MediaType.APPLICATION_JSON_TYPE);
		System.out.println(res);
	}

}
