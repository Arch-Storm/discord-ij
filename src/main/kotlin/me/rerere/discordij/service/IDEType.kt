package me.rerere.discordij.service

import com.intellij.openapi.application.ex.ApplicationInfoEx
import me.rerere.discordij.DiscordIJ

enum class IDEType(
    val title: String,
    val icon: String
) {
    IDEA_ULTIMATE("IntelliJ IDEA Ultimate", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/ideau.png"),
    IDEA_COMMUNITY("IntelliJ IDEA Community", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/ideac.png"),
    WEBSTORM("WebStorm", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/webstorm.png"),
    PYCHARM_COMMUNITY("PyCharm Community", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/pycharmc.png"),
    PYCHARM_PROFESSIONAL("PyCharm Professional", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/pycharmp.png"),
    CLION("CLion", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/clion.png"),
    GOLAND("GoLand", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/goland.png"),
    RIDER("Rider", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/rider.png"),
    PHPSTORM("PhpStorm", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/phpstorm.png"),
    ANDROID_STUDIO("Android Studio", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/studio.png"),
    RUSTROVER("RustRover", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/rustrover.png"),
    WRITERSIDE("WriterSide","https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/writerside.png"),
    AQUA("Aqua", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/aqua.png"),
    DATAGRIP("DataGrip", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/datagrip.png"),
    DATASPELL("DataSpell", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/dataspell.png"),
    GATEWAY("Gateway", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/gateway.png"),
    MPS("MPS", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/mps.png"),
    RUBYMINE("RubyMine", "https://raw.githubusercontent.com/Arch-Storm/icons/master/ide/rubymine.png"),
    JETBRAINS("JetBrains", "jetbrains"), // FALLBACK
}

val currentIDEType by lazy {
    val info = ApplicationInfoEx.getInstanceEx()
    when(info.build.productCode) {
        "IC" -> IDEType.IDEA_COMMUNITY
        "IU" -> IDEType.IDEA_ULTIMATE
        "WS" -> IDEType.WEBSTORM
        "PC" -> IDEType.PYCHARM_COMMUNITY
        "PY" -> IDEType.PYCHARM_PROFESSIONAL
        "CL" -> IDEType.CLION
        "GO" -> IDEType.GOLAND
        "RD" -> IDEType.RIDER
        "PS" -> IDEType.PHPSTORM
        "AI" -> IDEType.ANDROID_STUDIO
        "RR" -> IDEType.RUSTROVER
        "WRS" -> IDEType.WRITERSIDE
        "QA" -> IDEType.AQUA
        "DB" -> IDEType.DATAGRIP
        "DS" -> IDEType.DATASPELL
        "GW" -> IDEType.GATEWAY
        "MPS" -> IDEType.MPS
        "RM" -> IDEType.RUBYMINE
        else -> IDEType.JETBRAINS.also {
            DiscordIJ.logger.warn("Unknown IDE type: ${info.build.productCode}")
        }
    }
}