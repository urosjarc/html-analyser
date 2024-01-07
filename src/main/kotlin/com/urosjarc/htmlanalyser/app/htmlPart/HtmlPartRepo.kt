package com.urosjarc.htmlanalyser.app.project

import com.urosjarc.htmlanalyser.shared.Repository
import org.jsoup.Jsoup
import org.koin.core.component.inject

class HtmlPartRepo : Repository<HtmlPart>() {
	val htmlRepo by this.inject<HtmlRepo>()
	val htmlService by this.inject<HtmlService>()

	init {

		this.htmlRepo.onChose {
			val htmlPart = this.htmlService.parseFile(html=it)
		}

	}
}
