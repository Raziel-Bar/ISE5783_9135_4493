package primitives;

public class BoundingBox {
    /**
     * The minimum point of the BoundingBox.
     */
    private Point minPoint;

    /**
     * The maximum point of the BoundingBox.
     */
    private Point maxPoint;

    /**
     * Constructs an BoundingBox (AABB kind) with the given minimum and maximum points.
     *
     * @param minPoint The minimum point of the BoundingBox.
     * @param maxPoint The maximum point of the BoundingBox.
     */
    public BoundingBox(Point minPoint, Point maxPoint) {
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    /**
     * Checks if a ray intersects with the BoundingBox.
     *
     * @param ray The ray to check for intersection.
     * @return True if the ray intersects with the BoundingBox, false otherwise.
     */
    public boolean intersectsWith(Ray ray) {
        double tMin = Double.NEGATIVE_INFINITY;
        double tMax = Double.POSITIVE_INFINITY;

        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        // Check intersection with X slabs
        double originX = p0.getX();
        double dirX = dir.getX();
        double invDirX = 1.0 / dirX;
        double t1 = (minPoint.getX() - originX) * invDirX;
        double t2 = (maxPoint.getX() - originX) * invDirX;
        if (invDirX < 0) {
            double temp = t1;
            t1 = t2;
            t2 = temp;
        }
        tMin = (t1 > tMin) ? t1 : tMin;
        tMax = (t2 < tMax) ? t2 : tMax;
        if (tMin > tMax) return false;

        // Check intersection with Y slabs
        double originY = p0.getY();
        double dirY = dir.getY();
        double invDirY = 1.0 / dirY;
        t1 = (minPoint.getY() - originY) * invDirY;
        t2 = (maxPoint.getY() - originY) * invDirY;
        if (invDirY < 0) {
            double temp = t1;
            t1 = t2;
            t2 = temp;
        }
        tMin = (t1 > tMin) ? t1 : tMin;
        tMax = (t2 < tMax) ? t2 : tMax;
        if (tMin > tMax) return false;

        // Check intersection with Z slabs
        double originZ = p0.getZ();
        double dirZ = dir.getZ();
        double invDirZ = 1.0 / dirZ;
        t1 = (minPoint.getZ() - originZ) * invDirZ;
        t2 = (maxPoint.getZ() - originZ) * invDirZ;
        if (invDirZ < 0) {
            double temp = t1;
            t1 = t2;
            t2 = temp;
        }
        tMin = (t1 > tMin) ? t1 : tMin;
        tMax = (t2 < tMax) ? t2 : tMax;
        return tMin <= tMax;
    }

    /**
     * Expands the BoundingBox to include the bounds of another BoundingBox.
     *
     * @param other The BoundingBox to include in the expansion.
     */
    public void expand(BoundingBox other) {
        // Expand the BoundingBox by including the bounds of another BoundingBox
        Point otherMin = other.getMinPoint();
        Point otherMax = other.getMaxPoint();

        double newMinX = (minPoint.getX() < otherMin.getX()) ? minPoint.getX() : otherMin.getX();
        double newMinY = (minPoint.getY() < otherMin.getY()) ? minPoint.getY() : otherMin.getY();
        double newMinZ = (minPoint.getZ() < otherMin.getZ()) ? minPoint.getZ() : otherMin.getZ();

        double newMaxX = (maxPoint.getX() > otherMax.getX()) ? maxPoint.getX() : otherMax.getX();
        double newMaxY = (maxPoint.getY() > otherMax.getY()) ? maxPoint.getY() : otherMax.getY();
        double newMaxZ = (maxPoint.getZ() > otherMax.getZ()) ? maxPoint.getZ() : otherMax.getZ();


        minPoint = new Point(newMinX, newMinY, newMinZ);
        maxPoint = new Point(newMaxX, newMaxY, newMaxZ);
    }

    /**
     * Checks if the BoundingBox contains a point.
     *
     * @param point The point to check for containment.
     * @return True if the BoundingBox contains the point, false otherwise.
     */
    public boolean contains(Point point) {
        // Check if the BoundingBox contains the given point
        double pointX = point.getX();
        double pointY = point.getY();
        double pointZ = point.getZ();

        return pointX >= minPoint.getX() && pointX <= maxPoint.getX() &&
                pointY >= minPoint.getY() && pointY <= maxPoint.getY() &&
                pointZ >= minPoint.getZ() && pointZ <= maxPoint.getZ();
    }

    /**
     * Gets the minimum point of the BoundingBox.
     *
     * @return The minimum point of the BoundingBox.
     */
    public Point getMinPoint() {
        return minPoint;
    }

    /**
     * Gets the maximum point of the BoundingBox.
     *
     * @return The maximum point of the BoundingBox.
     */
    public Point getMaxPoint() {
        return maxPoint;
    }

    /**
     * calculates the middle X-axis of the BoundingBox.
     *
     * @return The middle X-axis of the BoundingBox.
     */
    public double getMidPointX() {
        return (minPoint.getX() + maxPoint.getX()) / 2;
    }
}
