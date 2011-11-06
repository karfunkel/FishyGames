package fishygames.ui

import griffon.builder.gfx.GfxBuilder
import griffon.builder.gfx.GfxContext
import griffon.builder.gfx.GfxNode
import griffon.builder.gfx.swing.GfxCanvas

import java.awt.Composite
import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImageOp
import javax.swing.JComponent

class GfxBuilderUIHelper {
    @Override
    void paint(GfxNode node, Graphics2D g, JComponent c, BufferedImageOp op = null, AffineTransform transform = null, Composite composite = null) {
        if(op)
            node.filter = op
        def oldTransform = g.transform
        def oldComposite = g.composite
        if (composite)
            g.composite = composite
        if (transform)
            g.transform = transform
        GfxCanvas canvas = new GfxCanvas(node: node, opaque: false, size: c.size)
        canvas.paintComponent(g)
        if (transform)
            g.transform = oldTransform
        if (composite)
            g.composite = oldComposite
    }
}
