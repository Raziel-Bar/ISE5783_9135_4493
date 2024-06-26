package renderer;

import lighting.*;
import primitives.*;
import static primitives.Util.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.LinkedList;
import java.util.List;

/**
 * The RayTracerBasic class represents a basic implementation of a ray tracer.
 * It extends the RayTracerBase class.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * The initial level of recursion.
     */
    private static final double INITIAL_K = 1.0;

    /**
     * The maximum level of recursion.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * The minimum level of recursion.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Finds the closest intersection point of the given ray with the scene.
     *
     * @param ray The ray to find the closest intersection point with.
     * @return The closest intersection point of the given ray with the scene.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the color of the given point.
     *
     * @param point The point to calculate the color in.
     * @param ray   The ray that intersects the point.
     * @return The color of the given point.
     */
    public Color calcColor(GeoPoint point, Ray ray) {
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color of the given point.
     *
     * @param point The point to calculate the color in.
     * @param ray   The ray that intersects the point.
     * @param level The level of recursion.
     * @param k     The k vector.
     * @return The color of the given point.
     */
    public Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = point.geometry.getEmission();
        color = color.add(calcLocalEffects(point, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));
    }

    /**
     * Calculates the local effects of the given point.
     *
     * @param gp    The point to calculate the local effects in.
     * @param ray   The ray that intersects the point.
     * @param level The level of recursion.
     * @param k     The k vector.
     * @return The color of the given point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point, ray), level, material.kR, kkr);

        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.geometry.getNormal(ray.getP0()), gp.point, ray), level, material.kT, kkt));

        return color;
    }

    /**
     * Calculates the global effects of the given point.
     *
     * @param ray   The ray that intersects the point.
     * @param level The level of recursion.
     * @param kx    The kx vector.
     * @param kkx   The kkx vector.
     * @return The color of the given point.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }

    /**
     * Construct the reflected ray
     *
     * @param n     normal
     * @param point the point of the geometry that the ray hit
     * @param inRay the ray that hit the geometry
     * @return reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        Vector r = v.subtract(n.scale(Util.alignZero(2 * (n.dotProduct(v)))));
        return new Ray(r.normalize(), point, n);
    }

    /**
     * Construct the refracted ray
     *
     * @param n     normal
     * @param point the point of the geometry that the ray hit
     * @param inRay the ray that hit the geometry
     * @return refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray(inRay.getDir(), point, n);
    }


    /**
     * Calculate the effects of lights
     *
     * @param intersection the intersection point
     * @param ray          the ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if sign(nl) == sing(nv)
                //if (unshaded(lightSource, l, n, intersection))
                Double3 ktr = transparency(lightSource, l, n, intersection);
                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Checks if there is no shade between a point and a light source
     * enhanced method: implemented soft shadows using super sampling
     *
     * @param ls       the light source
     * @param l        the vector from the point to the light source
     * @param n        the normal vector
     * @param geoPoint the point
     * @return the averaged ktr of all sample rays
     */
    private Double3 transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Double3 ktr = Double3.ZERO;
        int count = 0;

        List<Point> targetAreaPoints = ls.getGridPoints(l);
        // in case the light is directional, we simply add a point in the opposite direction, so we will do 1 check
        // as we used to do (Directional light is NOT affected by super sampling)
        if (targetAreaPoints == null) {
            targetAreaPoints = new LinkedList<>();
            targetAreaPoints.add(geoPoint.point.add(l.scale(-1)));
        }

        for (Point p : targetAreaPoints) {
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(p.subtract(geoPoint.point).normalize(), geoPoint.point, n));
            if (intersections == null) {
                ktr = ktr.add(Double3.ONE);
                count++;
            } else {
                double lightDistance = ls.getDistance(geoPoint.point);
                for (GeoPoint geop : intersections) {
                    if (Util.alignZero(geop.point.distance(geoPoint.point) - lightDistance) <= 0) {
                        ktr = ktr.add(geop.geometry.getMaterial().kT);
                        count++;
                    } else {
                        ktr = ktr.add(Double3.ONE);
                        count++;
                    }
                }
            }
        }
        return ktr.scale(1.0 / count).lowerThan(MIN_CALC_COLOR_K) ? Double3.ZERO : ktr.scale(1.0 / count);
    }

    /**
     * Calculates diffusive light
     *
     * @param kd             the diffusive coefficient
     * @param l              the light vector
     * @param n              the normal vector
     * @param lightIntensity the light intensity
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Util.alignZero(l.dotProduct(n));
        return lightIntensity.scale(kd.scale(ln < 0 ? -ln : ln));
    }

    /**
     * Calculate specular light
     *
     * @param ks             the specular coefficient
     * @param l              the light vector
     * @param n              the normal vector
     * @param v              the view vector
     * @param nShininess     the shininess factor
     * @param lightIntensity the light intensity
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = -Util.alignZero(v.dotProduct(r));
        return vr <= 0 ? Color.BLACK : lightIntensity.scale(ks.scale(Math.pow(vr, nShininess)));
    }

    /**
     * Checks if the point is shaded
     *
     * @param light the light source
     * @param l     the light vector
     * @param n     the normal vector
     * @param gp    the intersection point
     * @return true if the point is shaded, false otherwise
     */
    @SuppressWarnings("unused")
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
            if (alignZero(geop.point.distance(gp.point) - lightDistance) <= 0)
                if (gp.geometry.getMaterial().kT.equals(Double3.ZERO))
                    return false;
        }
        return true; //in case all intersections are in between lightDistance and gp.
    }

}
