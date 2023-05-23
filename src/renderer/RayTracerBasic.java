package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

    /**
     * Constructor for the RayTracerBasic class.
     *
     * @param scene The scene to be rendered.
     * @throws IllegalArgumentException if the scene is null.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Implementation of the traceRay method.
     *
     * @param ray The ray to be traced.
     * @return The color of the traced ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersectionPoints = scene.geometries.findIntersections(ray);
        if (intersectionPoints == null)
            return scene.background;
        var closestPoint = ray.findClosestPoint(intersectionPoints);
        return calcColor(closestPoint);
    }

    /**
     * TODO
     * @param point
     * @return
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
