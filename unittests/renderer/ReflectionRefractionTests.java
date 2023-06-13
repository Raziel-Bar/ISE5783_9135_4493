package renderer;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;
import static java.awt.Color.ORANGE;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setkT(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKL(0.0004).setKQ(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKR(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKR(new Double3(0.5, 0, 0.4))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKL(0.00001).setKQ(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setkT(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKL(4E-5).setKQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

    @Test
    public void sevenDragonBalls() {

        Camera camera = new Camera(new Point(0, -13, 20), new Vector(0, 1, -.5), new Vector(0, .5, 1))
                .setVPSize(70, 70).setVPDistance(50);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add(
                //floor
                new Plane(new Point(0, 0, -2), new Vector(0, 0, 1))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.8).setKS(0.2).setKR(0.1)),

                //wall
                new Plane(new Point(0, 30, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKR(1)),

                new Sphere(new Point(0, 6, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(0, 12, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(0, 18, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.8).setKS(0.2).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(-6, 9, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(-6, 15, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(6, 9, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60).setkT(0.5)),
                new Sphere(new Point(6, 15, 0), 2)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60).setkT(0.5))

        );

        ImageWriter imageWriter = new ImageWriter("SevenDragonBalls", 800, 800);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage();
        camera.writeToImage();
    }

}


