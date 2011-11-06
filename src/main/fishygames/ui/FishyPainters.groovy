package fishygames.ui

import com.jhlabs.image.CompoundFilter
import com.jhlabs.image.GrayscaleFilter
import com.jhlabs.image.HSBAdjustFilter
import griffon.builder.gfx.GfxNode
import griffon.builder.gfx.GfxBuilder
import griffon.builder.gfx.HSVGradientPaint
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D
import java.awt.event.FocusListener
import java.beans.PropertyChangeListener
import javax.swing.JComponent

class FishyPainters {
    GfxBuilder gfx = new GfxBuilder(true)

    private GfxBuilderUIHelper helper = new GfxBuilderUIHelper()

    protected GfxNode createBorderNode(JComponent c, int width, int height, int thickness) {
        def b = [
                x: thickness,
                y: thickness,
                w: width - 2 * thickness,
                h: height - 2 * thickness
        ]
        return gfx.group(bw: 0) {
            antialias true
            def offset = thickness / 2
            rect(x: offset, y: offset, w: width - thickness - 0.5, h: height - thickness - 0.5, bc: new HSVGradientPaint(0, 0, color('red'), thickness * 15, thickness * 15, color('red'), true, true), bw: thickness)
        }
    }

    protected GfxNode createBackgroundNode(JComponent c, int width, int height, int thickness) {
        def b = [
                x: thickness,
                y: thickness,
                w: width - 2 * thickness,
                h: height - 2 * thickness
        ]
        return gfx.group(bw: 0) {
            antialias true
            rect(x: b.x, y: b.y, w: b.w, h: b.h, fill: color('white'), bw: 0)
            rect(x: b.x, y: b.y, w: b.w, h: b.h, fill: new HSVGradientPaint(b.x, b.y, color(0x33ff0000), b.w, b.h, color(0x33ff0000), false), bw: 0)
        }
    }

    void paintTextFieldBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBackgroundNode(c, width, height, 2), g, c)
    }

    void paintTextFieldBorder(Graphics2D g, JComponent c, int width, int height, boolean focused) {
        if (focused)
            helper.paint(createBorderNode(c, width, height, 2), g, c, new HSBAdjustFilter(sFactor: -0.5f))
        else
            helper.paint(createBorderNode(c, width, height, 2), g, c)
    }

    void paintTextFieldDisabledBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 2), g, c, new GrayscaleFilter())
    }

    void paintTextFieldDisabledBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 2), g, c, new GrayscaleFilter())
    }

    void paintToolTipBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBackgroundNode(c, width, height, 1), g, c)
    }

    void paintToolTipBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 1), g, c)
    }

    void paintToolTipDisabledBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBackgroundNode(c, width, height, 1), g, c, new GrayscaleFilter())
    }

    void paintToolTipDisabledBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 1), g, c, new GrayscaleFilter())
    }

    void paintListBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBackgroundNode(c, width, height, 1), g, c)
    }

    void paintListDisabledBackground(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBackgroundNode(c, width, height, 1), g, c, new GrayscaleFilter())
    }

    void paintScrollPaneBorder(Graphics2D g, JComponent c, int width, int height) {
        Component view = c.viewport.view
        FocusListener fListener = c.getClientProperty('__SCROLLPANE_FOCUS_LISTENER')
        if (!fListener) {
            fListener = [focusGained: { e -> c.repaint() }, focusLost: { e -> c.repaint() }] as FocusListener
            c.putClientProperty('__SCROLLPANE_FOCUS_LISTENER', fListener)
            view.addFocusListener(fListener)
        }
        PropertyChangeListener eListener = c.getClientProperty('__SCROLLPANE_ENABLED_LISTENER')
        if (!eListener) {
            eListener = { e -> c.repaint() } as PropertyChangeListener
            c.putClientProperty('__SCROLLPANE_ENABLED_LISTENER', eListener)
            view.addPropertyChangeListener('enabled', eListener)
        }
        boolean focused = view.hasFocus()
        boolean enabled = view.isEnabled()
        if (focused) {
            if (enabled)
                helper.paint(createBorderNode(c, width, height, 2), g, c, new HSBAdjustFilter(sFactor: -0.5f))
            else
                helper.paint(createBorderNode(c, width, height, 2), g, c, new CompoundFilter(new HSBAdjustFilter(sFactor: -0.5f), new GrayscaleFilter()))
        } else {
            if (enabled)
                helper.paint(createBorderNode(c, width, height, 2), g, c)
            else
                helper.paint(createBorderNode(c, width, height, 2), g, c, new GrayscaleFilter())
        }
    }

    void paintScrollPaneDisabledBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 2), g, c, new GrayscaleFilter())
    }

    void paintListCellRendererBackground(Graphics2D g, JComponent c, int width, int height) {
        def old = g.paint
        g.paint = new Color(0x33000000, true)
        g.fillRect(0, 0, width, height)
        g.paint = old
    }

    void paintListCellRendererBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 1), g, c)
    }

    void paintListCellRendererDisabledBackground(Graphics2D g, JComponent c, int width, int height) {
        def old = g.paint
        g.paint = new Color(0x33888888, true)
        g.fillRect(0, 0, width, height)
        g.paint = old
    }

    void paintListCellRendererDisabledBorder(Graphics2D g, JComponent c, int width, int height) {
        helper.paint(createBorderNode(c, width, height, 1), g, c, new GrayscaleFilter())
    }
}
