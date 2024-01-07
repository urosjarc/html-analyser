package com.urosjarc.htmlanalyser.app.project

data class HtmlPart(
	val parent: HtmlPart?,
	val name: String,
	val attributes: Map<String, String>,
	val children: MutableList<HtmlPart> = mutableListOf()
) {
	fun depth(): Int {
		var count = 0
		var node: HtmlPart? = this
		while (node?.parent != null) {
			count++
			node = node.parent
		}
		return count
	}
}
