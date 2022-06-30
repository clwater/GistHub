package utils

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * Create by clwater on 2022/6/30.
 */
class Requests {
	companion object{
		fun get(){
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token " + Constancts.Gist_Token)
				.uri(URI.create(Constancts.Gist_API + Constancts.Gist_ListGists))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			println(response.body())
		}
	}
}