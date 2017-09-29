package jp.co.agilegroup.rest;

import javax.ws.rs.core.MediaType;

public class InsertToFlowPutTest {

	public static void main(String[] args) {

		RestClient client = new RestClient("admin", "admin");

		String uri = "http://192.168.10.100:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0/flow/testFlow3"; // リクエスト先

		StringBuilder json = new StringBuilder();
		json.append("{\"flow\":");
		json.append("          [{\"id\":\"testFlow3\",");
		json.append("         \"match\":{\"in-port\":\"3\"},");
		json.append("  \"instructions\":{\"instruction\":");
		json.append("                             [{\"apply-actions\":{");
		json.append("                                           \"action\":[{\"order\":\"0\",\"drop-action\":{}}]");
		json.append("                                               },\"order\":\"0\"");
		json.append("                             }]");
		json.append("                 },");
		json.append("   \"priority\":\"11\",");
		json.append("   \"table_id\":\"0\"}");
		json.append("]}");

		String jsonString = json.toString();
		String res = client.put(uri, jsonString, String.class, MediaType.APPLICATION_JSON_TYPE);
		System.out.println(res);

	}
}