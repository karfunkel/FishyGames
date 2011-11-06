/*
 * This script is executed inside the UI thread, so be sure to  call 
 * long running code in another thread.
 *
 * You have the following options
 * - execOutside { // your code }
 * - execFuture { // your code }
 * - Thread.start { // your code }
 *
 * You have the following options to run code again inside the UI thread
 * - execAsync { // your code }
 * - execSync { // your code }
 */

import com.sun.java.swing.Painter
import fishygames.ui.FishyBorder
import fishygames.ui.FishyPainters
import groovy.swing.SwingBuilder
import java.awt.Color
import java.awt.Font
import java.awt.Insets
import javax.swing.UIManager

SwingBuilder.lookAndFeel('nimbus')

UIManager.defaults.RootPaneUI = "fishygames.ui.FishyRootPaneUI"
UIManager.defaults.PanelUI = "fishygames.ui.FishyPanelUI"

// Manche müssen in defaults eingegeben werden
//UIManager.defaults."text" = Color.WHITE
UIManager.lookAndFeelDefaults."Label.textForeground" = Color.WHITE
//UIManager.lookAndFeelDefaults."defaultFont" = new Font('Dialog', Font.BOLD, 16)
UIManager.lookAndFeelDefaults."Label.font" = new Font('Dialog', Font.BOLD, 16)

FishyPainters painters = new FishyPainters()
// http://jasperpotts.com/blogfiles/nimbusdefaults/nimbus.html
// weil [] in Nimbus schon gesetzt -> siehe com.sun.java.swing.plaf.nimbus.NimbusDefaults
def lafd = UIManager.lookAndFeelDefaults
lafd."TextField[Enabled].borderPainter" = painters.&paintTextFieldBorder.rcurry(false) as Painter
lafd."TextField[Enabled].backgroundPainter" = painters.&paintTextFieldBackground as Painter
lafd."TextField[Focused].borderPainter" = painters.&paintTextFieldBorder.rcurry(true) as Painter
lafd."TextField[Disabled].borderPainter" = painters.&paintTextFieldDisabledBackground as Painter
lafd."TextField[Disabled].backgroundPainter" = painters.&paintTextFieldDisabledBorder as Painter
lafd."TextField[Selected].backgroundPainter" = painters.&paintTextFieldBackground as Painter
lafd."TextField[Selected].borderPainter" = painters.&paintTextFieldBorder.rcurry(false) as Painter
lafd."TextField[Selected+Focused].borderPainter" = painters.&paintTextFieldBorder.rcurry(true) as Painter

lafd."ToolTip[Enabled].borderPainter" = painters.&paintToolTipBorder as Painter
lafd."ToolTip[Enabled].backgroundPaintedelegater" = painters.&paintToolTipBackground as Painter
lafd."ToolTip[Disabled].borderPainter" = painters.&paintToolTipDisabledBorder as Painter
lafd."ToolTip[Disabled].backgroundPainter" = painters.&paintToolTipDisabledBackground as Painter
lafd."ToolTip.contentMargins" = new Insets(2, 4, 2, 4)
lafd."ToolTip.font" = new Font('Dialog', Font.BOLD, 10)

lafd."List[Enabled].backgroundPainter" = painters.&paintListBackground as Painter
lafd."List[Disabled].backgroundPainter" = painters.&paintListDisabledBackground as Painter
lafd."List[Disabled+Selected].textBackground" = new Color(0x33888888, true)
lafd."List[Disabled].textForeground" = Color.BLACK
lafd."List[Selected].textBackground" = new Color(0x33000000, true)
lafd."List[Selected].textForeground" = Color.BLACK
lafd."List.dropLineColor" = new Color(0x33000000, true)
lafd."List.focusCellHighlightBorder" = new FishyBorder(1)
lafd."ScrollPane[Enabled].borderPainter" = painters.&paintScrollPaneBorder as Painter
lafd."ScrollPane[Disabled].borderPainter" = painters.&paintScrollPaneDisabledBorder as Painter

//Per Component:
//def component = <<create Component>>
//UIDefaults uiDefaults = new UIDefaults()
//uiDefaults.put(<<key>>,<<value>>)
//....
//component.putClientProperty("Nimbus.Overrides", uiDefaults)
//component.putClientProperty("Nimbus.Overrides.InheritDefaults", false)


