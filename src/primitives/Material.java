package primitives;

/**
 * The Material class represents a material in 3D space.
 * A material is a set of properties that determine how an object reflects light.
 */
public class Material {

    /**
     * The diffuse component of the material.
     */
    public Double3 kD = new Double3(0);

    /**
     * The specular component of the material.
     */
    public Double3 kS = new Double3(0);

    /**
     * The reflection component of the material.
     */
    public Double3 kR = new Double3(0);

    /**
     * The refraction component of the material.
     */
    public Double3 kT = new Double3(0);

    /**
     * The shininess of the material.
     */
    public int nShininess = 0;

    /**
     * setter for kD
     *
     * @param kD the kD to set
     * @return the material
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kD
     *
     * @param kD the kD to set
     * @return the material
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for kS
     *
     * @param kS the kS to set
     * @return the material
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for kS
     *
     * @param kS the kS to set
     * @return the material
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter for kT
     *
     * @param kT the kR to set
     * @return the material
     */
    public Material setkT(Double3 kT){
        this.kT = kT;
        return this;
    }

    /**
     * setter for kT
     *
     * @param kT the kT to set
     * @return the material
     */
    public Material setkT(double kT){
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter for kR
     *
     * @param kR the kR to set
     * @return the material
     */
    public Material setKR(Double3 kR){
        this.kR = kR;
        return this;
    }

    /**
     * setter for kR
     *
     * @param kR the kR to set
     * @return the material
     */
    public Material setKR(double kR){
        this.kR = new Double3(kR);
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
