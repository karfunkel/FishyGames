package fishygames.ui

import griffon.builder.gfx.HSVGradientPaint
import java.awt.geom.Area
import javax.swing.border.Border
import java.awt.*
import javax.swing.plaf.UIResource
import java.awt.geom.Rectangle2D

class FishyBorder implements Border, UIResource {
    int thickness

    FishyBorder(int thickness = 5) {
        this.thickness = thickness
    }

    void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = g
        def oldPaint = g2.paint
        def oldStroke = g2.stroke
        def paint = new HSVGradientPaint(x, y, Color.RED, x + thickness * 15, y + thickness * 15, Color.RED, true, true)
        def stroke = new BasicStroke(thickness)
        double offset = thickness / 2
        Rectangle2D outer = new Rectangle2D.Double(x + offset, y + offset, width - thickness - 0.5f, height - thickness - 0.5f)
        g2.paint = paint
        g2.stroke = stroke
        g2.draw(outer)
        g2.paint = oldPaint
        g2.stroke = oldStroke
    }

    Insets getBorderInsets(Component c) {
        new Insets(thickness, thickness, thickness, thickness)
    }

    boolean isBorderOpaque() {
        return true
    }
}

