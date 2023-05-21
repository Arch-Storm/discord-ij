package me.rerere.discordij.listener

import com.intellij.analysis.problemsView.Problem
import com.intellij.analysis.problemsView.ProblemsCollector
import com.intellij.analysis.problemsView.ProblemsListener
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.vcs.changes.shelf.ShelveChangesManager.PostStartupActivity
import com.intellij.openapi.vfs.VirtualFile
import me.rerere.discordij.DiscordIJ
import me.rerere.discordij.service.TimeService

class PostStartListener: ProjectActivity {
    override suspend fun execute(project: Project) {
        service<TimeService>().onProjectOpened(project)
    }
}

class ProjectListener : PostStartupActivity(), ProjectManagerListener {
    override fun projectClosing(project: Project) {
        service<TimeService>().onProjectClosed(project)
    }
}

class FileListener: FileEditorManagerListener {
    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        val service = service<TimeService>()
        service.onFileOpened(source.project, file)
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        val service = service<TimeService>()
        service.onFileClosed(source.project, file)
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        event.newFile?.let {
            val service = service<TimeService>()
            service.onFileChanged(event.manager.project, it)
        }
    }
}

class FileProblemListener: ProblemsListener {
    override fun problemAppeared(problem: Problem) {
        val service = service<TimeService>()
        service.render(problem.provider.project)
    }

    override fun problemDisappeared(problem: Problem) {
        val service = service<TimeService>()
        service.render(problem.provider.project)
    }

    override fun problemUpdated(problem: Problem) {
        val service = service<TimeService>()
        service.render(problem.provider.project)
    }
}