
import java.util.ArrayList;

/**
 Represent a basic solar system. A star can have a name and (optionally)
 a luminosity (expressed in units of our sun == 1.0). SolarSystems may optionally
 have planets, represented by {@link Planet}.

 @version 1.0
*/
public class SolarSystem {

    //format for String representation of a solar system
    private static final String SOLAR_STR = "Star %s has planets:";

    private final ArrayList<Planet> planetList;
    private final String systemName;
    private final double luminosity;

    /*
    Keep track of furthest/closest - updated as planets added so not final
     */
    private Planet furthest = null;
    private Planet closest = null;

    /**
    Construct a SolarSystem with only a name. Note that this is NOT possible
     to later amend a SolarSystem by adding a luminosity - that must be done
     initially. Luminosity will be set to zero.
     @param systemName the name of the SolarSystem
    */
    public SolarSystem(String systemName) {
        this.systemName = systemName;
        planetList = new ArrayList<>();
        luminosity = 0.0;
    }

    /**
    Construct a SolarSystem with a name and luminosity.
     @param systemName the name of the SolarSystem
     @param luminosity the luminosity of the star in units of The Sun == 1.0
     */
    public SolarSystem(String systemName, double luminosity) {
        this.luminosity = luminosity > 0 ? luminosity : 0;
        this.systemName = systemName;
        planetList = new ArrayList<>();
    }

    /**
    Add a new planet to the SolarSystem with name and distance only, allowing
     the orbital period to be calculated. Note that
     it is NOT possible to later add a mass, radius or link to the parent system
     - that must be done initially. The mass and radius will be set to zero; and
     the parent system to null
     @param name the planet's name
     @param distance the planet's distance from the star in AU (Earth = 1.0)
    */
    public void addPlanet(String name, double distance) {
        Planet newPlanet = new Planet(name, distance);
        planetList.add(newPlanet);

        if (furthest == null || distance > furthest.getDistance()) {
            furthest = newPlanet;
        } //for the first planet added, both conditions can be true
        if (closest == null || distance < closest.getDistance()) {
            closest = newPlanet;
        }
    }

    /**
     Add a new planet to the SolarSystem with name, distance, mass, radius
     allowing orbital period and surface gravity to be calculated. The planet will
     also have a reference to the parent SolarSystem  allowing its
     potential habitability to be calculated.
     @param name the planet's name
     @param distance the planet's distance from the star in AU (Earth = 1.0)
     @param mass the planet's mass in Earth units (Earth = 1.0)
     @param radius the planet's radius in Earth units (Earth = 1.0)
     */
    public void addPlanet(String name, double mass, double radius, double distance) {
        Planet newPlanet = new Planet(name, mass, radius, distance, this);
        planetList.add(newPlanet);
    }

    /**
     Returns the solar system name
     @return the name of the SolarSystem
     */
    public String getName() {
        return systemName;
    }

    /**
     Returns the star's luminosity
     @return the luminosity of the SolarSystem
     */
    public double getLuminosity() {
        return luminosity;
    }

    /**
     Return the {@link Planet} furthest from the star or null if there
     are no planets
     @return the furthest {@link Planet} or null
    */
    public Planet furthest() {
        return furthest;
    }

    /**
     Return the {@link Planet} closest to the star or null if there
     are no planets
     @return the closest {@link Planet} or null
     */
    public Planet closest() {
        return closest;
    }

    /**
     Return a planet by name - returns the first one found
     if there is more than one, and null if there are none
     @param name the name of the {@link Planet}
     @return the first {@link Planet} of that name found or null
     */
    public Planet getPlanetByName(String name) {
        for (Planet p: planetList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }


    /**
     Returns the string representation of this Solar System.
    */
    @Override
    public String toString() {
        String solarSystemString =
                String.format(SOLAR_STR, systemName) + "\n";
        for (Planet planet : planetList) {
            solarSystemString += planet + "\n";
        }
        return solarSystemString;
    }

    /**
     Return the ith {@link Planet} in the order they were added, or null
     if there is no ith Planet or i is negative
     @param i the index of the requested Planet
     @return the ith Planet or null
    */
    public Planet getPlanet(int i) {
        if (i > planetList.size() || i < 0) {
            return null;
        } else {
            return planetList.get(i);
        }
    }

    /**
     Return the number of {@link Planet} objects in the SolarSystem
     @return the number of Planets
     */
    public int planetCount() {
        return planetList.size();
    }
}
