package com.github.ai.autokpass.domain.arguments

import com.github.ai.autokpass.domain.arguments.Argument.AUTOTYPE
import com.github.ai.autokpass.domain.arguments.Argument.AUTOTYPE_DELAY
import com.github.ai.autokpass.domain.arguments.Argument.DELAY
import com.github.ai.autokpass.domain.arguments.Argument.FILE
import com.github.ai.autokpass.domain.arguments.Argument.INPUT
import com.github.ai.autokpass.domain.arguments.Argument.PROCESS_KEY_COMMAND
import com.github.ai.autokpass.domain.arguments.Argument.KEY_FILE
import com.github.ai.autokpass.model.RawArgs
import com.github.ai.autokpass.model.Result
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

class CommandLineArgumentExtractor(
    private val commandLineArguments: Array<String>
) : ArgumentExtractor {

    override fun extractArguments(): Result<RawArgs> {
        val parser = ArgParser("autokpass")

        val filePath by parser.option(
            ArgType.String,
            shortName = FILE.shortName,
            fullName = FILE.fullName,
            description = FILE.description
        )

        val keyPath by parser.option(
            ArgType.String,
            shortName = KEY_FILE.shortName,
            fullName = KEY_FILE.fullName,
            description = KEY_FILE.description
        )

        val delayInSeconds by parser.option(
            ArgType.String,
            shortName = DELAY.shortName,
            fullName = DELAY.fullName,
            description = DELAY.description
        )

        val autotypeDelayInMillis by parser.option(
            ArgType.String,
            shortName = AUTOTYPE_DELAY.shortName,
            fullName = AUTOTYPE_DELAY.fullName,
            description = DELAY.description
        )

        val inputReader by parser.option(
            ArgType.String,
            shortName = INPUT.shortName,
            fullName = INPUT.fullName,
            description = INPUT.description
        )

        val autotypeType by parser.option(
            ArgType.String,
            shortName = AUTOTYPE.shortName,
            fullName = AUTOTYPE.fullName,
            description = AUTOTYPE.description
        )

        val keyCommand by parser.option(
            ArgType.String,
            shortName = PROCESS_KEY_COMMAND.shortName,
            fullName = PROCESS_KEY_COMMAND.fullName,
            description = PROCESS_KEY_COMMAND.description
        )

        parser.parse(commandLineArguments)

        return Result.Success(
            RawArgs(
                filePath = filePath,
                keyPath = keyPath,
                delayInSeconds = delayInSeconds,
                autotypeDelayInMillis = autotypeDelayInMillis,
                inputType = inputReader,
                autotypeType = autotypeType,
                keyProcessingCommand = keyCommand
            )
        )
    }
}