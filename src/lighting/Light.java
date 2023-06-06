package lighting;
import primitives.Color;

/**
 * The Light class represents a light (not light source yet but only the abstract idea of light) in 3D space.
 */
abstract class Light {
    /**
     * The light's color's intensity.
     */
    private Color intensity;

    /**
     * Constructor for the Light class.
     *
     * @param intensity The color of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
