package renderer;
import lighting.*;
import geometries.Plane;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import static java.awt.Color.*;
import java.util.List;

/**
 * The RayTracerBasic class represents a basic implementation of a ray tracer.
 * It extends the RayTracerBase class.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Head of rays movement const
     */
    private static final double DELTA = 0.1;

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
        var intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionPoints == null)
            return scene.background;
        var closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(closestPoint, ray);
    }


    /**
     * Calculates the color of the given point.
     *
     * @param point The point to calculate the color in.
     * @return The color of the given point.
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
    }

    /**
     * Calculate the effects of lights
     *
     * @param intersection the intersection point
     * @param ray         the ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                if (unshaded(lightSource, l, n, intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


    /**
     * Calculates diffusive light
     * @param kd the diffusive coefficient
     * @param l the light vector
     * @param n the normal vector
     * @param lightIntensity the light intensity
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Util.alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }

    /**
     * Calculate specular light
     * @param ks the specular coefficient
     * @param l the light vector
     * @param n the normal vector
     * @param v the view vector
     * @param nShininess the shininess factor
     * @param lightIntensity the light intensity
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = Util.alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }

    /**
     * Checks if the point is shaded
     * @param l the light vector
     * @param n the normal vector
     * @param gp the intersection point
     * @return true if the point is shaded, false otherwise
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;

        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint geop : intersections) {
            if (Util.alignZero(geop.point.distance(gp.point) - lightDistance) <= 0)
                return false;
        }
        return true; //in case all intersections are in between lightDistance and gp.
    }

}
