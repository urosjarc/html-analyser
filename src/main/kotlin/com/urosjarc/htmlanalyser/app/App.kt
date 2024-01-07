package com.urosjarc.htmlanalyser.app

import com.urosjarc.htmlanalyser.app.project.ProjectRepo
import com.urosjarc.htmlanalyser.app.logs.LogRepo
import com.urosjarc.htmlanalyser.app.logs.LogService
import kotlinx.datetime.Clock
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

object App {
	fun modul() = module {
		this.single<LogRepo> { LogRepo("log_${Clock.System.now()}.json") }
		this.single<ProjectRepo> { ProjectRepo() }
		this.single { LogService(get()) }
	}

	fun init() {
		startKoin { this.modules(modul()) }
	}

	fun reset() = stopKoin()
}
