package com.urosjarc.htmlanalyser.app.project

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class HtmlService {
	fun parseFile(html: Html): HtmlPart {
		val rootDoc: Document = Jsoup.parse(html.file)
		val rootHtmlPart: HtmlPart = this.parseHtmlPart(element = rootDoc.root())
		val queue = mutableListOf(Pair(first = rootDoc.root(), second = rootHtmlPart))
		while (queue.isNotEmpty()) {
			val (doc, htmlPart) = queue.removeFirst()
			doc.children().forEach {
				val childHtmlPart = this.parseHtmlPart(element = it)
				htmlPart.children.add(childHtmlPart)
				queue.add(Pair(first = it, second = childHtmlPart))
			}
		}

		return rootHtmlPart
	}

	private fun parseHtmlPart(element: Element): HtmlPart {
		val attributes: Map<String, String> = element.attributes().map { Pair(first = it.key, second = it.value) }.toMap()
		return HtmlPart(parent = null, name = element.tagName(), attributes = attributes)
	}
}
