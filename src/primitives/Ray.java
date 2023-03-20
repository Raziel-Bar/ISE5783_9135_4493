package primitives;

import java.util.Objects;

public class Ray {
    final private Point p0;
    final private Vector dir;

    public Ray(Vector v, Point p){
        p0 = p;
        dir = v.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "p0= " + p0 +
                ", dir= " + dir;
    }
}
