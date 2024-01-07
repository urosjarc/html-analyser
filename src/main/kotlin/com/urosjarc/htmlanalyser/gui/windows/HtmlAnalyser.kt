package com.urosjarc.htmlanalyser.gui.windows

import com.urosjarc.htmlanalyser.gui.widgets.projects.ProjectList
import javafx.fxml.FXML
import javafx.scene.control.Tab
import org.koin.core.component.KoinComponent

abstract class HtmlAnalyserUi : KoinComponent {
	@FXML
	lateinit var projectListController: ProjectList

	@FXML
	lateinit var mainSchemasT: Tab

	@FXML
	lateinit var mainTablesT: Tab

	@FXML
	lateinit var tablesT: Tab

	@FXML
	lateinit var columnsT: Tab

	@FXML
	lateinit var connectionsT: Tab
}

class HtmlAnalyser : HtmlAnalyserUi() {

	@FXML
	fun initialize() {
	}

}
