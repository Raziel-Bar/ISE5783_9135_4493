package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class for ray tracing.
 */
public abstract class RayTracerBase {

    /**
     * The scene to be rendered.
     */
    protected final Scene scene;


    /**
     * Constructor for the RayTracerBase class.
     *
     * @param scene The scene to be rendered.
     */
    public RayTracerBase(Scene scene) {
        if (scene == null)
            throw new IllegalArgumentException("Scene cannot be null.");
        this.scene = scene;
    }

    /**
     * Abstract method for tracing a ray and returning the color.
     *
     * @param ray The ray to be traced.
     * @return The color of the traced ray.
     */
    public abstract Color traceRay(Ray ray);

}
