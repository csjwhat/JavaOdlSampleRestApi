package jp.co.agilegroup.rest;

public class DeleteFromFlowDeleteTest {

	public static void main(String[] args) {

		RestClient client = new RestClient("admin", "admin");

		// 登録したフローを全部クリアする
		String uri = "http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/flow-node-inventory:table/0/"; // リクエスト先

		client.delete(uri);
		System.out.println("OK");

	}
}