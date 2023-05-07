package de.dasphiller.putilsfabric.gui

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeManager
import de.dasphiller.putilsfabric.challenge.active
import de.dasphiller.putilsfabric.color.color
import de.dasphiller.putilsfabric.timer.Timer
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.ItemStack.TooltipSection
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.silkmc.silk.core.item.itemStack
import net.silkmc.silk.core.item.setLore
import net.silkmc.silk.core.kotlin.ticks
import net.silkmc.silk.core.text.literal
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.igui.*
import net.silkmc.silk.igui.observable.toGuiList

fun challengeGui(): Gui {
    return igui(GuiType.NINE_BY_FIVE, literalText("Settings") {
        color = 0xf44336
    }, 2) {
        page(2, 2) {
            setDefaultPlaceHolder(this)
            changePageByKey((3 sl 4), itemStack(Items.DRAGON_HEAD) {
                setCustomName(literalText("Challenges") {
                    italic = false
                    color = 0x2986cc
                    setLore(listOf(literalText("Set the challenges that will get enabled when you start the timer") {
                        italic = false
                        color = 0x16537e
                    }))
                })
            }.guiIcon, 1)

            //Timer
            changePageByKey((3 sl 6), itemStack(Items.CLOCK) {
                setCustomName(literalText("Timer") {
                    italic = false
                    color = 0x16b818
                    setLore(listOf(literalText("Control the timer") {
                        italic = false
                        color = 0x16537e
                    }))
                })
            }.guiIcon, 3)
        }
        page(1, 1) {
            effectFrom = GuiPage.ChangeEffect.SLIDE_HORIZONTALLY
            effectTo = GuiPage.ChangeEffect.SLIDE_HORIZONTALLY
            setSettingsPlaceHolder(this)
            val compound = compound((4 sl 2) rectTo (2 sl 8), ChallengeManager.challenges.toGuiList(), iconGenerator = {
                generateChallengeItem(it)
            }, onClick = { event, challenge ->
                if (event.type == GuiActionType.PICKUP) {
                    challenge.active = !challenge.active
                    event.slot?.stack = generateChallengeItem(challenge)
                    event.player.playSound(
                        if (challenge.active) SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL.comp_349() else SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.comp_349(),
                        SoundCategory.MASTER,
                        1F,
                        1F
                    )
                }
            })
            compoundScrollForwards((1 sl 1), itemStack(Items.PAPER) {
                setCustomName(literalText("Nächste Seite") {
                    color = 0x2986cc
                    italic = false
                })
            }.guiIcon, compound, 20.ticks, 1)
            goBackLeft(this)
        }
        page(3, 3) {
            effectFrom = GuiPage.ChangeEffect.SLIDE_HORIZONTALLY
            effectTo = GuiPage.ChangeEffect.SLIDE_HORIZONTALLY
            setDefaultPlaceHolder(this)

            button((3 sl 3), itemStack(Items.LIME_DYE) {
                setCustomName(literalText("Start") {
                    color = color("limeyellow")
                    italic = false
                })
            }.guiIcon) {
                Timer.start()
            }

            button((3 sl 5), itemStack(Items.RED_DYE) {
                setCustomName(literalText("Stop") {
                    color = color("red")
                    italic = false
                })
            }.guiIcon) {
                Timer.stop()
            }

            button((3 sl 7), itemStack(Items.ORANGE_DYE) {
                setCustomName(literalText("Reset") {
                    color = color("orange")
                    italic = false
                })
            }.guiIcon) {
                Timer.reset()
            }
            goBackRight(this)
        }
    }
}

fun setDefaultPlaceHolder(builder: GuiBuilder.PageBuilder) {
    builder.placeholder(Slots.All, itemStack(Items.GRAY_STAINED_GLASS_PANE) {
        setCustomName(literalText(" ") {
            italic = false
        })
    }.guiIcon)
}

fun setSettingsPlaceHolder(builder: GuiBuilder.PageBuilder) {
    builder.placeholder(Slots.Border, itemStack(Items.GRAY_STAINED_GLASS_PANE) {
        setCustomName(literalText("") {
            italic = false
        })
    }.guiIcon)
}

fun generateChallengeItem(challenge: Challenge) = itemStack(challenge.icon) {
    setCustomName(literalText {
        text(challenge.name) {
            italic = false
            bold = challenge.active
            color = if (challenge.active) color("limeyellow") else color("red")
        }
        if (challenge.active) {
            addEnchantment(Enchantment.byRawId(1)!!, 1)
        }
        TooltipSection.values().forEach {
            addHideFlag(it)
        }
        setLore(listOf(challenge.devType.type, " ".literal, literalText {
            text(challenge.description) {
                italic = false
                color = color("blue")
            }
        }, " ".literal, literalText {
            text("Enabled: ${challenge.active}") {
                italic = false
                color = if (!challenge.active) color("darkred") else color("darkgreen")
            }
        }))
    })
}

fun goBackLeft(builder: GuiBuilder.PageBuilder) {
    builder.changePageByKey((1 sl 9), itemStack(Items.DARK_OAK_DOOR) {
        setCustomName(literalText("Go back") {
            italic = false
            color = 0xf44336
        })
    }.guiIcon, 2)
}

fun goBackRight(builder: GuiBuilder.PageBuilder) {
    builder.changePageByKey((1 sl 1), itemStack(Items.DARK_OAK_DOOR) {
        setCustomName(literalText("Go back") {
            italic = false
            color = 0xf44336
        })
    }.guiIcon, 2)
}