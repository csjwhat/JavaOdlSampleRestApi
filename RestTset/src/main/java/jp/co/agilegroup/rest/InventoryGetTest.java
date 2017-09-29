package jp.co.agilegroup.rest;

import javax.ws.rs.core.MediaType;

public class InventoryGetTest {

	public static void main(String[] args) {

		RestClient client = new RestClient("admin", "admin");

		// Switchの情報＋すべてのNoddeの情報
		String uri = "http://192.168.10.100:8181/restconf/operational/opendaylight-inventory:nodes/node/openflow:1/";

		// 1つのNoddeの情報
		String uri2 = "http://192.168.10.100:8181/restconf/operational/opendaylight-inventory:nodes/node/openflow:1/node-connector/openflow:1:3/";

		String res = client.get(uri,MediaType.APPLICATION_JSON_TYPE);
		String res2 = client.get(uri2,MediaType.APPLICATION_JSON_TYPE);

		System.out.println(res);
		System.out.println(res2);

	}
}