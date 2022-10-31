package com.github.ai.autokpass.domain.arguments

import com.github.ai.autokpass.model.RawArgs
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ArgumentExtractorTest {

    @Test
    fun `extractArguments should return all nulls`() {
        // arrange
        val expected = argsWith()

        // act
        val result = ArgumentExtractor().extractArguments(emptyArray())

        // assert
        result shouldBe expected
    }

    @Test
    fun `extractArguments should extract all arguments by full name`() {
        // arrange
        val expected = argsWith(
            filePath = FILE_PATH,
            keyPath = KEY_PATH,
            delayInSeconds = DELAY,
            autotypeDelayInMillis = AUTOTYPE_DELAY,
            inputType = INPUT_TYPE,
            autotypeExecutorType = AUTOTYPE_EXECUTOR_TYPE,
            keyProcessingCommand = COMMAND
        )
        val args = arrayOf(
            Argument.FILE.cliName, FILE_PATH,
            Argument.KEY_FILE.cliName, KEY_PATH,
            Argument.DELAY.cliName, DELAY,
            Argument.AUTOTYPE_DELAY.cliName, AUTOTYPE_DELAY,
            Argument.INPUT.cliName, INPUT_TYPE,
            Argument.AUTOTYPE.cliName, AUTOTYPE_EXECUTOR_TYPE,
            Argument.PROCESS_KEY_COMMAND.cliName, COMMAND
        )

        // act
        val result = ArgumentExtractor().extractArguments(args)

        // assert
        result shouldBe expected
    }

    @Test
    fun `extractArguments should extract all arguments by short name`() {
        // arrange
        val expected = argsWith(
            filePath = FILE_PATH,
            keyPath = KEY_PATH,
            delayInSeconds = DELAY,
            autotypeDelayInMillis = AUTOTYPE_DELAY,
            inputType = INPUT_TYPE,
            autotypeExecutorType = AUTOTYPE_EXECUTOR_TYPE,
            keyProcessingCommand = COMMAND
        )
        val args = arrayOf(
            Argument.FILE.cliShortName, FILE_PATH,
            Argument.KEY_FILE.cliShortName, KEY_PATH,
            Argument.DELAY.cliShortName, DELAY,
            Argument.AUTOTYPE_DELAY.cliShortName, AUTOTYPE_DELAY,
            Argument.INPUT.cliShortName, INPUT_TYPE,
            Argument.AUTOTYPE.cliShortName, AUTOTYPE_EXECUTOR_TYPE,
            Argument.PROCESS_KEY_COMMAND.cliShortName, COMMAND
        )

        // act
        val result = ArgumentExtractor().extractArguments(args)

        // assert
        result shouldBe expected
    }

    private fun argsWith(
        filePath: String? = null,
        keyPath: String? = null,
        delayInSeconds: String? = null,
        autotypeDelayInMillis: String? = null,
        inputType: String? = null,
        autotypeExecutorType: String? = null,
        keyProcessingCommand: String? = null
    ): RawArgs {
        return RawArgs(
            filePath = filePath,
            keyPath = keyPath,
            delayInSeconds = delayInSeconds,
            autotypeDelayInMillis = autotypeDelayInMillis,
            inputType = inputType,
            autotypeType = autotypeExecutorType,
            keyProcessingCommand = keyProcessingCommand
        )
    }

    companion object {
        private const val FILE_PATH = "/tmp/filePath"
        private const val KEY_PATH = "/tmp/keyPath"
        private const val DELAY = "123"
        private const val COMMAND = "gpg --decrypt"
        private const val AUTOTYPE_DELAY = "456"
        private const val INPUT_TYPE = "inputType"
        private const val AUTOTYPE_EXECUTOR_TYPE = "autotypeExecutorType"
    }
}