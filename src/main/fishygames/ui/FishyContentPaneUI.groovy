package fishygames.ui

import griffon.builder.gfx.GfxNode
import java.awt.Graphics
import javax.swing.JComponent
import javax.swing.plaf.ComponentUI
import javax.swing.plaf.PanelUI
import java.awt.Rectangle
import java.awt.Image
import javax.swing.ImageIcon
import java.awt.Graphics2D
import java.awt.RenderingHints
import fishygames.ImageService
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class FishyContentPaneUI extends PanelUI {
    private static PanelUI componentUI = new FishyContentPaneUI()

    static ComponentUI createUI(JComponent c) {
        return componentUI
    }

    @Override
    void paint(Graphics g, JComponent c) {
        Graphics2D g2 = g
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
        BufferedImage bg = ImageIO.read(ImageService.classLoader.getResource('Background.png'))
        BufferedImage header = ImageIO.read(ImageService.classLoader.getResource('Header.png'))
        BufferedImage bar = ImageIO.read(ImageService.classLoader.getResource('Bar.png'))
        // Background über den ganzen Bildschirm skalieren
        g2.drawImage(bg, 0, 0, c.width, c.height, c)
        // Bar ab y=81 horizontal skalieren
        g2.drawImage(bar, 0, 81, c.width, bar.height, c)
        // Header ab y=7 zentriert zeichnen, aber mindestens links 228px Platz lassen
        g2.drawImage(header, Math.max(228, (int) ((c.width - header.width) / 2)), 7, c)
    }
}
