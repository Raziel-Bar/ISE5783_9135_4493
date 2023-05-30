package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Image Writer Test unit
 */
public class ImageWriterTest {

    private static final int size = 800;
    private static final int ny = 500;
    Color temp  = new Color(255, 0, 0);
    Color temp2 = new Color(255, 255, 0);
    /**
     * Test method for ImageWriter
     * Test for creating a simple image with pixel
     */
    @Test
    public void testWriteToImage() {
        ImageWriter image = new ImageWriter("imageTestNew", size, ny);
        // The nested loop colors each pixel
        for (int i = 0; i < size; i++)
            for (int j = 0; j < ny; j++)
                if (i % 50 == 0 && i != 0 || j % 50 == 0 && j != 0)
                    image.writePixel(i, j,temp);
                else
                    image.writePixel(i, j, temp2);

        image.writeToImage();
    }


}
