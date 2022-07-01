package utils

import java.io.File
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class ConfigFile {
	companion object{

		private fun getFile(): File {
			val filePath = Paths.get("").absolutePathString() + "\\config\\gisthub.config"
			val file = File(filePath)
			if (!file.isFile) {
				file.createNewFile()
			}
			return file
		}

		fun getToken(): String {
			return getFile().readText()
		}

		fun saveToken(token: String) {
			getFile().writeText(token)
		}
	}
}