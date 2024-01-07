package com.urosjarc.htmlanalyser.app

import com.urosjarc.htmlanalyser.app.project.HtmlPartRepo
import com.urosjarc.htmlanalyser.app.logs.LogRepo
import com.urosjarc.htmlanalyser.app.logs.LogService
import com.urosjarc.htmlanalyser.app.project.HtmlRepo
import com.urosjarc.htmlanalyser.app.project.ProjectRepo
import kotlinx.datetime.Clock
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

object App {
	fun modul() = module {
		this.single<LogRepo> { LogRepo("log_${Clock.System.now()}.json") }
		this.single<HtmlPartRepo> { HtmlPartRepo() }
		this.single<HtmlRepo> { HtmlRepo() }
		this.single<ProjectRepo> { ProjectRepo("projects.json") }
		this.single { LogService(get()) }
	}

	fun init() {
		startKoin { this.modules(modul()) }
	}

	fun reset() = stopKoin()
}
