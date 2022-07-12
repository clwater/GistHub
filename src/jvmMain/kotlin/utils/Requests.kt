package utils

import Constancts
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import enity.Gist
import model.Gists
import viewmodel.FilesViewer
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

		fun getGist(url : String): FilesViewer {
			val filesViewer = FilesViewer("", emptyList())
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token " + Constancts.Gist_Token)
				.uri(URI.create(url))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			val moshi = Moshi.Builder()
				.addLast(KotlinJsonAdapterFactory())
				.build()

			val adapter = moshi.adapter<Gists>(Gists::class.java)
			val gists = adapter.fromJson(response.body())
			filesViewer.spaceName = getSpaceName(gists!!.files)
			filesViewer.files = getSpaceFiles(gists.files)
			return filesViewer
		}

		fun getGistWithIndex(index: Int): String{
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token " + Constancts.Gist_Token)
				.uri(URI.create(Constancts.Gist_URL + Constancts.Gist_ListGists + "?per_page=${Constancts.Gist_Per_Page}&page=$index"))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body()
		}

		fun checkToken(token: String) : Boolean{
			val client = HttpClient.newBuilder().build();
			val request = HttpRequest.newBuilder()
				.header("Authorization", "token $token")
				.uri(URI.create(Constancts.Gist_URL + Constancts.Gist_ListGists))
				.build();

			val response = client.send(request, HttpResponse.BodyHandlers.ofString())
			return response.statusCode() == 200
		}
		fun updateAllGis(): MutableList<Gist> {
			var index = 1
			val list = mutableListOf<Gist>()
//			Constancts.Gists.clear()
			while (true) {
//				println("index: $index")
				val listOfGistsType = Types.newParameterizedType(List::class.java, Gists::class.java)
				val moshi = Moshi.Builder()
					.addLast(KotlinJsonAdapterFactory())
					.build()

				val adapter = moshi.adapter<List<Gists>>(listOfGistsType)
				val gists = adapter.fromJson(Requests.getGistWithIndex(index))
				if (gists!!.isEmpty()){
					break
				}

				gists.forEach {
					val gist = Gist()
					gist.isFix = checkIsFix(it.files)
					gist.spaceName = getSpaceName(it.files)
					gist.url = it.url
//					Constancts.Gists.add(gist)
					list.add(gist)
				}
				index++
			}
			return list

//			Constancts.Gists.forEach {
//				println(it)
//			}


		}

	}
}