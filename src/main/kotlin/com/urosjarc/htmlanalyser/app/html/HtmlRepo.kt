package com.urosjarc.htmlanalyser.app.project

import com.urosjarc.htmlanalyser.shared.Repository
import org.koin.core.component.inject
import java.io.File

class HtmlRepo : Repository<Html>() {
	val projectRepo by this.inject<ProjectRepo>()

	init {

		this.projectRepo.onChose { project ->
			val htmls = File(project.path)
				.walk()
				.filter { it.isFile() && it.extension == "html" }
				.map { Html(file = it) }
				.toList()

			this.set(htmls)
		}

	}
}
