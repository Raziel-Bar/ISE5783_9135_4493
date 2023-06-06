package primitives;

/**
 * The Material class represents a material in 3D space.
 * A material is a set of properties that determine how an object reflects light.
 */
public class Material {

    /**
     * The diffuse component of the material.
     */
    public Double3 kD = new Double3(0), kS = new Double3(0);

    /**
     * The shininess of the material.
     */
    public int nShininess = 0;

    /**
     * setters for kD
     *
     * @param kD the kD to set
     * @return the material
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setters for kS
     *
     * @param kS the kS to set
     * @return the material
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setters for nShininess
     *
     * @param nShininess the nShininess to set
     * @return the material
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
