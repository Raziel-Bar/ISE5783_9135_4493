package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class
 */
public class AmbientLight extends Light {

    /**
     * default constructor
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constructor build the ambient light intensity
     * calls the super constructor
     * @param iA the intensity of the ambient light
     * @param kA the attenuation factor
     */
    @SuppressWarnings("unused")
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA)); // kA is the intensity of the ambient light
    }

    /**
     * constructor build the ambient light intensity
     * calls the super constructor
     * @param iA the intensity of the ambient light
     * @param kA the attenuation factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

}
