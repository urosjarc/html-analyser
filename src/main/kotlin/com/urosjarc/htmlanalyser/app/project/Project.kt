package com.urosjarc.htmlanalyser.app.project

import kotlinx.serialization.Serializable

@Serializable
data class Project(
	val name: String,
	val path: String
)
