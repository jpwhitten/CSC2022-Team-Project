package teamone.tanfieldrailway;

/**
 * Created by Joshua on 08/02/2016.
 * JW: Added Image and color parameters along with corresponding getters
 */
public enum WalkingRoutes{
    ANDREWS_HOUSE_TO_SUNNISIDE("Andrews House to Sunniside", "This walk is designed to integrate with existing railway services and\n" +
            "is paced at “dog-walking speed” ie, really slowly!", 1.0, 50, "Car Park at Andrews House Station", "Sunniside Station", "", " suitable for sturdy buggies and powered wheelchairs (this\n" +
            "track undulates)", "#DD000000", R.drawable.slideshow5);


    WalkingRoutes(String routeName, String description, double distance, double duration, String startingLocation, String endLocation, String mapData, String terrain, String color, int imageID) {
        this.description = description;
        this.distance = distance;
        this.duration = duration;
        this.endLocation = endLocation;
        this.mapData = mapData;
        this.routeName = routeName;
        this.startingLocation = startingLocation;
        this.terrain = terrain;
        this.image = imageID;
        this.color = color;
    }

    private String routeName;
    private String description;

    private String startingLocation;
    private String endLocation;

    private double distance;
    private double duration;

    private String terrain;

    private String mapData;

    private String color;

    private int image;


        public String getDescription() {
            return description;
        }

        public double getDistance() {
            return distance;
        }

        public double getDuration() {
            return duration;
        }

        public String getEndLocation() {
            return endLocation;
        }

        public String getMapData() {
            return mapData;
        }

        public String getRouteName() {
            return routeName;
        }

        public String getStartingLocation() {
            return startingLocation;
        }

        public String getTerrain() {
            return terrain;
        }

    public int getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }
}
