package griffon.builder.gfx

import com.jhlabs.image.PointFilter
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Transparency
import java.awt.color.ColorSpace
import java.awt.image.BufferedImage
import java.awt.image.ColorModel
import java.awt.image.ComponentColorModel
import java.awt.image.DataBuffer
import java.awt.Color
import java.awt.Rectangle
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp

class ClipMaskOp extends PointFilter {
    BufferedImage clip
    boolean scaling = false
    int xOffset = 0
    int yOffset = 0
    private BufferedImage mask
    private int oX = xOffset
    private int oY = xOffset

    ClipMaskOp(BufferedImage clip, boolean useAlpha = false) {
        this.clip = clip
        if (clip.type != BufferedImage.TYPE_BYTE_GRAY) {
            this.clip = new BufferedImage(clip.width, clip.height, BufferedImage.TYPE_BYTE_GRAY)
            if (useAlpha) {
                if (!clip.alphaRaster)
                    throw new IllegalArgumentException("When using 'useAlpha' the clip image needs an alpha-channel")
                Object alphaData = clip.alphaRaster.getSamples(0, 0, clip.width, clip.height, 0, (int[]) null)
                this.clip.getRaster().setSamples(0, 0, clip.width, clip.height, 0, alphaData)
            } else {
                Graphics2D g = this.clip.createGraphics()
                g.drawImage(clip, 0, 0, clip.width, clip.height, null)
                g.dispose()
            }
        }
    }

    @Override
    void setDimensions(int width, int height) {
        int oX = xOffset
        int oY = xOffset
        if (scaling) {
            oX = oY = 0
            mask = scale(clip, width, height)
        } else
            mask = clip
    }

    @Override
    int filterRGB(int x, int y, int rgb) {
        int alpha
        try {
            int m = mask.getRGB(x + oX, y + oY) & 0xffffff
            alpha = (m & 0xff)
        } catch (ArrayIndexOutOfBoundsException e) {
            alpha = 0x00
        }
        return (rgb & 0x00ffffff) | alpha << 24
    }

    BufferedImage scale(BufferedImage original, int width, int height) {
        int newWidth = (int) (original.width * width / original.width)
        int newHeight = (int) (original.height * height / original.height)
        BufferedImage resized = new BufferedImage(newWidth, newHeight, original.type)
        Graphics2D g = resized.createGraphics()
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
        g.drawImage(original, 0, 0, newWidth, newHeight, 0, 0, original.width, original.height, null)
        g.dispose()
        return resized
    }
}
