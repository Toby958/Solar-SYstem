import java.util.Objects;

/**
<p>A Planet object represents basic information about a Planet. Typically Planet objects will be related to
 a (parent) {@link SolarSystem} object though this isn't required. Planets come in two forms:</p>
 <p><strong>Basic:</strong> A basic Planet has only a name and a distance from the star, expressed in
 AU (Earth = 1.0). This allows it's orbital period in years to be computed:
 <span style="font-family: monospace;">Sqrt(distance^3).</span></p>
 <p><strong>Enhanced:</strong> An enhanced Planet additionally has a mass, a radius and (optionally
 if it's related to a {@link SolarSystem})
 a reference to it's parent system. This allows it's surface gravity to be computed:
 <span style="font-family: monospace;">mass/radius^2</span>. If the reference to the parent {@link SolarSystem}
 is present, then habitability can be calculated:
 <span style="font-family: monospace;">{@link #MIN_DIST_FACTOR} * lumfactor &le;= distance &le;=
 {@link #MAX_DIST_FACTOR} * lumfactor</span>, where lumfactor is the square root of the parent system's luminosity,
 and <span style="font-family: monospace;">{@link #MIN_MASS} &le;= mass &le;= {@link #MAX_MASS}</span> where
 MIN_DIST_FACTOR, MAX_DIST_FACTOR, MIN_MASS and MAX_MASS are public constants in Planet.</p>
 */
public class Planet {

    //String constants present in output
    private static final String PLANET_FORMAT
            = "%s  is %sAU from its star, and orbits in %s years";

    private static final String PLANET_FORMAT_EXT
            = "%s has a mass of %s Earths with a surface gravity of %s"
            + "g, is %sAU from its star, and orbits in %s years: "
            + "could be habitable? %s";
    private static final String POS = "yes";
    private static final String NEG = "no";

    /**
     Measure of habitability based on assumption that planets are habitable if at least 0.75AU from our sun.
     Actual habitable distances is corrected by multiplying by the square root of the parent SolarSystem's luminosity
     */
    public static final double MIN_DIST_FACTOR = 0.75;
    /**
     Measure of habitability based on assumption that planets are habitable if at most 2.0AU from our sun.
     Actual habitable distances is corrected by multiplying by the square root of the parent SolarSystem's luminosity
     */
    public static final double MAX_DIST_FACTOR = 2.0;
    /**
     Measure of habitability based on assumption that planets are habitable if their mass is at least 0.6 Earths,
     allowing them to retain an atmosphere
     */
    public static final double MIN_MASS = 0.6;
    /**
     Measure of habitability based on assumption that planets are habitable if their mass is at most 7.0 Earths,
     allowing them to have an atmosphere that is not so dense that the surface temperature is very high
     */
    public static final double MAX_MASS = 7.0;

    /**
     The number of decimal places that are used when representing a Planet as a string - not that data is stored,
     computed and returned by getters in full precision.
     */
    public static final int NUM_PLACES = 3;
    private static final double ROUNDING_FACTOR = Math.pow(10, NUM_PLACES);

    //orbital period is computed on demand
    private final String name;
    private final double distance;
    private final double radius;
    private final double mass;
    private final SolarSystem parentSystem;

    /**
     Create a Planet object given name and distance in AU (Earth = 1.0).
     Distance must be >= 0 (and will be set to zero if negative) - mass
     and distance are explicitly set to zero and the parent system to null.
     Note that it is not possible to change a 'basic' planet to an 'enhanced'
     planet after it is created.
     @param name the Planet's name
     @param distance the Planet's distance from its star in AU (Earth = 1.0)
     */
    public Planet(String name, double distance) {
        this.name = name;
        this.distance = distance > 0 ? distance : 0;
        this.radius = 0;
        this.mass = 0;
        this.parentSystem = null;
    }

    /**
     Create a Planet object given name, distance, mass, radius (all in units of Earth = 1.0)
     as well as a link to its parent {@link SolarSystem} (which may potentially be null).
     Distance, mass and radius must be >= 0 (and will be set to zero if negative)
     @param name the Planet's name
     @param mass the Planet's mass (Earth = 1.0)
     @param radius the Planet's radius (Earth = 1.0)
     @param distance the Planet's distance from its star in AU (Earth = 1.0)
     @param system the Planet's parent SolarSystem
     */
    public Planet(String name, double mass, double radius,
                  double distance, SolarSystem system) {
        this.name = name;
        this.mass = mass > 0 ? mass : 0;
        this.distance = distance > 0 ? distance : 0;
        this.radius = radius > 0 ? radius : 0;
        parentSystem = system;
    }

    /**
     Return the Planet's name
     @return the Planet's name
     */
    public String getName() {
        return name;
    }

    /**
     Return the Planet's distance in AU - this is not rounded
     @return the Planet's distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     Return the Planet's radius in units of Earth = 1.0 - this is not rounded
     @return the Planet's radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     Return the Planet's orbital period in years- this is not rounded
     @return the Planet's orbital period
     */
    public double getOrbitalPeriod() {
        return Math.sqrt(distance * distance * distance);
    }

    /**
     Return the Planet's mass in units of Earth = 1.0 - this is not rounded
     @return the Planet's mass
     */
    public double getMass() {
        return mass;
    }

    /**
     Return the Planet's surface Gravity in G (Earth = 1.0) - this is not rounded
     @return the Planet's surface gravity
     */
    public double getSurfaceGravity() {
        return mass / (radius * radius);
    }

    /**
     Return the Planet's potential habitability - this is calculated from
     the luminosity of the parent {@link SolarSystem} and the planet's mass - see
     {@link Planet} for the formula.
     @return the Planet's potential habitability
     */
    public boolean couldBeHabitable() {
        if (parentSystem == null) {
            return false;
        }
        double lumFactor = Math.sqrt(parentSystem.getLuminosity());
        boolean distanceOk = distance >= MIN_DIST_FACTOR * lumFactor
                && distance <= MAX_DIST_FACTOR * lumFactor;
        boolean massOk = mass >= MIN_MASS && mass <= MAX_MASS;
        return massOk && distanceOk;
    }

    /**
     Planets are equal if they have the same mass, distance, radius and name - for
     'basic' planets the mass and radius will always be zero.
     @param planet the Planet being checked for equality
     @return true if the Planet being checked for equality has the same name, radius, mass and distance
     */
    @Override
    public boolean equals(Object planet) {
        if (planet == null) {
            return false;
        }

        if (this == planet) {
            return true;
        }

        if (getClass() != planet.getClass()) {
            return false;
        }

        Planet p = (Planet) planet;
        return p.getName().equals(name)
                && p.getDistance() == distance
                && p.getMass() == mass
                && p.getRadius() == radius;
    }

    /**
     hashcode computed from name, mass, distance and radius to preserve the 'contract'
     if planets P &amp; Q are equal, they have the same hashcode
     @return the computed hashcode
    */
    @Override
    public int hashCode() {
        return Objects.hash(name, distance, mass, radius);
    }

    /**
     Generate a formatted string representing a Planet, with numerical data rounded to {@link #NUM_PLACES} dp
     The format of the string is differs between 'basic' and 'enhanced' planets, with enhanced ones
     containing more information
     @return the Planet represented as a string
     */
    @Override
    public String toString() {

        double period = getOrbitalPeriod();

        if (parentSystem == null) {
            return String.format(PLANET_FORMAT, name,
                    round(distance), round(period));
        } else {
            String habitable = couldBeHabitable() ? POS : NEG;
            double gravity = getSurfaceGravity();
            return String.format(PLANET_FORMAT_EXT, name,
                    round(mass), round(gravity), round(distance),
                    round(period), habitable);
        }

    }

    /*
    Method to round to a specific number of decimal places
     */
    private double round(double val) {
        return Math.round(val * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }
}
