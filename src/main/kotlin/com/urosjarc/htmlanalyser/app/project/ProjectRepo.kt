package com.urosjarc.htmlanalyser.app.project

import com.urosjarc.htmlanalyser.shared.Repository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ProjectRepo(val fileName: String) : Repository<Project>(){
	init {
		this.load()
	}

	override fun load() {
		val file = File(this.fileName)
		if (!file.exists()) return
		this.set(Json.decodeFromString(file.readText()))
	}

	override fun save() {
		val file = File(this.fileName)
		if (!file.exists()) file.createNewFile()
		file.writeText(Json.encodeToString(this.data))
	}

	override fun save(t: Project){
		super.save(t)
		this.chose(t)
	}
}
