package fishygames.ui

import javax.swing.plaf.PanelUI
import javax.swing.JComponent
import griffon.builder.gfx.GfxNode
import javax.swing.plaf.ComponentUI
import java.awt.Graphics
import griffon.builder.gfx.HSVGradientPaint
import javax.swing.plaf.basic.BasicPanelUI
import javax.swing.JPanel
import javax.swing.LookAndFeel

class FishyPanelUI extends BasicPanelUI {
    private static PanelUI componentUI = new FishyPanelUI()

    static ComponentUI createUI(JComponent c) {
        return componentUI
    }

    @Override
    void installDefaults(JPanel p) {
        super.installDefaults(p)
        LookAndFeel.installProperty(p, "opaque", false);
    }
}
