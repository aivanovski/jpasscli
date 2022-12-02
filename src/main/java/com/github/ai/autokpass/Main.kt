package com.github.ai.autokpass

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.github.ai.autokpass.di.GlobalInjector.get
import com.github.ai.autokpass.di.KoinModule
import com.github.ai.autokpass.domain.ErrorInteractor
import com.github.ai.autokpass.domain.MainInteractor
import com.github.ai.autokpass.presentation.ui.root.RootComponent
import com.github.ai.autokpass.presentation.ui.root.RootScreen
import org.koin.core.context.startKoin

@OptIn(ExperimentalDecomposeApi::class)
fun main(args: Array<String>) {
    startKoin {
        modules(KoinModule.appModule)
    }

    val interactor: MainInteractor = get()
    val errorInteractor: ErrorInteractor = get()

    val initResult = interactor.initApp(args)
    if (errorInteractor.processFailed(initResult)) {
        return
    }

    val arguments = initResult.getDataOrThrow()
    val lifecycle = LifecycleRegistry()
    val rootComponent = RootComponent(
        componentContext = DefaultComponentContext(lifecycle),
        startScreen = interactor.determineStartScreen(arguments),
        appArguments = arguments,
    )

    application {
        val windowState by rootComponent.viewModel.windowState.collectAsState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = {
                exitApplication()
            },
            title = "Autokpass",
            state = windowState,
            alwaysOnTop = true,
            undecorated = false,
            resizable = true,
        ) {
            RootScreen(rootComponent = rootComponent)
        }
    }
}