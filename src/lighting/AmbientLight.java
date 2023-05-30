package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class
 */
public class AmbientLight {

    private final Color intensity;

    /**
     * default constructor
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

 /**
     * constructor build the ambient light intensity
     * @param iA the intensity of the ambient light
     * @param kA the attenuation factor
     */
    @SuppressWarnings("unused")
    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA); // kA is the intensity of the ambient light
    }

    /**
     * @param iA the intensity of the ambient light
     * @param kA the attenuation factor
     *           constructor build the ambient light intensity
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     *
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }

}
