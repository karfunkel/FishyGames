package fishygames.ui

import griffon.builder.gfx.GfxNode
import griffon.builder.gfx.HSVGradientPaint
import java.awt.Graphics
import javax.swing.JComponent
import javax.swing.plaf.ComponentUI
import javax.swing.plaf.ListUI
import javax.swing.plaf.basic.BasicListUI

class FishyListUI extends BasicListUI {
    private GfxBuilderUIHelper helper = new GfxBuilderUIHelper()
    private static ListUI componentUI

    @Override
    static ComponentUI createUI(JComponent c) {
        if (!componentUI)
            componentUI = new FishyListUI()
        return componentUI
    }

    @Override
    void paint(Graphics g, JComponent c) {
        helper.paint(createNode(c), g, c)
        super.paint(g, c)
    }

    protected GfxNode createNode(JComponent c) {
        def bounds = [
                x: c.insets.left,
                y: c.insets.top,
                w: c.width - 1 - c.insets.left - c.insets.right,
                h: c.height - 1 - c.insets.top - c.insets.bottom
        ]
        return GfxBuilderUIHelper.gfx.group(bw: 0) {
            antialias true
            background(color('white'))
            rect(
                    x: bounds.x,
                    y: bounds.y,
                    w: bounds.w,
                    h: bounds.h,
                    fill: new HSVGradientPaint(0, 0, color(0x33ff0000), c.width - 1, c.height - 1, color(0x33ff0000), false),
                    bw: 0
            )
        }
    }
}
