package com.github.ai.autokpass.domain.autotype

import com.github.ai.autokpass.domain.process.ProcessExecutor
import com.github.ai.autokpass.model.AutotypeSequence
import com.github.ai.autokpass.model.AutotypeSequenceItem

class XdotoolAutotypeExecutor(
    private val processExecutor: ProcessExecutor
) : AutotypeExecutor {

    override fun execute(sequence: AutotypeSequence) {
        sequence.items.forEach { item ->
            when (item) {
                is AutotypeSequenceItem.Enter -> {
                    processExecutor.execute("xdotool key enter")
                }
                is AutotypeSequenceItem.Tab -> {
                    processExecutor.execute("xdotool key Tab")
                }
                is AutotypeSequenceItem.Text -> {
                    processExecutor.execute("xdotool type ${item.text}")
                }
                is AutotypeSequenceItem.Delay -> {
                    Thread.sleep(item.millis)
                }
            }
        }
    }
}