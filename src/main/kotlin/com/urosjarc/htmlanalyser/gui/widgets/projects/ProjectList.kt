package com.urosjarc.htmlanalyser.gui.widgets.projects

import com.urosjarc.htmlanalyser.app.project.Project
import com.urosjarc.htmlanalyser.app.project.ProjectRepo
import com.urosjarc.htmlanalyser.shared.startThread
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.stage.DirectoryChooser
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

abstract class ProjectListUi : KoinComponent {

	@FXML
	lateinit var nameTF: TextField

	@FXML
	lateinit var urlTF: TextField

	@FXML
	lateinit var analyseB: Button

	@FXML
	lateinit var projectLV: ListView<Project>
}

class ProjectList : ProjectListUi() {
	val projectRepo by this.inject<ProjectRepo>()

	@FXML
	fun initialize() {
		this.projectLV.items.setAll(this.projectRepo.data)
		this.projectRepo.onData { this.projectLV.items.setAll(this.projectRepo.data) }
		this.urlTF.setOnMouseClicked { this.findUrl() }
		this.analyseB.setOnAction { this.analyse() }
		this.projectLV.setOnMouseClicked { this.select(it) }
	}

	private fun findUrl() {
		val chooser = DirectoryChooser()
		val file: File = chooser.showDialog(null)
		this.urlTF.text = file.absolutePath
	}

	fun select(mouseEvent: MouseEvent) {
		if (mouseEvent.clickCount == 2) {
			this.analyse()
			return
		}
		var project: Project = this.projectLV.selectionModel.selectedItem ?: return
		project = this.projectRepo.find(project) ?: project
		this.nameTF.text = project.name
		this.urlTF.text = project.url
	}

	private fun analyse() = startThread {
		val project = Project(
			name = nameTF.text,
			url = urlTF.text,
		)
		projectRepo.save(project)
		projectRepo.chose(project)
	}
}
