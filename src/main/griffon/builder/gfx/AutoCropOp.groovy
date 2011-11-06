package griffon.builder.gfx

import com.jhlabs.image.CropFilter
import java.awt.image.BufferedImage
import java.awt.image.WritableRaster

class AutoCropOp extends CropFilter {
    @Override
    BufferedImage filter(BufferedImage src, BufferedImage dst) {
        int type = src.type
        WritableRaster alphaRaster = src.alphaRaster

        int x1 = src.width
        int x2 = 0
        int y1 = src.height
        int y2 = 0

        int[] inPixels = new int[src.width]
        for (int y = 0; y < src.height; y++) {
            alphaRaster.getDataElements(0, y, src.width, 1, inPixels)
            for (int x = 0; x < src.width; x++) {
                if (inPixels[x] >> 24 != 0x00) {
                    x1 = Math.min(x1, x)
                    x2 = Math.max(x2, x)
                    y1 = Math.min(y1, y)
                    y2 = Math.max(y2, y)
                }
            }
        }
        x = x1
        y = y1
        width = x2 - x1 + 1
        height = y2 - y1 + 1
        return super.filter(src, dst)
    }

}
