package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.List;
import java.util.LinkedList;

/**
 * Represents a scene in 3D space.
 */
public class Scene {

    /**
     * The name of the scene.
     */
    public final String name;

    /**
     * The background color of the scene.
     */
    public Color background = Color.BLACK;

    /**
     * The ambient light of the scene.
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * The geometries of the scene.
     */
    public Geometries geometries = new Geometries();

    /**
     * The lights of the scene.
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * constructor for Scene
     *
     * @param name name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * function sets background color
     *
     * @param background background color
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * function sets ambient light
     *
     * @param ambientLight ambient light
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * function sets lights
     *
     * @param lights lights
     * @return this
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * function sets geometries in the scene
     *
     * @param geometries geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}