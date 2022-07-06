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
		fun get(url : String = Constancts.Gist_URL, api: String): String{
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token " + Constancts.Gist_Token)
				.uri(URI.create(url + api))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body()
		}

		fun gist(): String{
			return get(api = Constancts.Gist_ListGists)
		}

		fun checkToken(token: String) : Boolean{
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token " + token)
				.uri(URI.create(Constancts.Gist_URL + Constancts.Gist_ListGists))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString())
			return response.statusCode() == 200
		}

	}
}