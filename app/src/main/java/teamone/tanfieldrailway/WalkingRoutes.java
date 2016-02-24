package teamone.tanfieldrailway;

/**
 * Created by Joshua on 08/02/2016.
 * JW: Added Image and color parameters along with corresponding getters
 */
public enum WalkingRoutes implements Row{
    //https://www.google.co.uk/maps/dir/54.92077,-1.674837/54.912775,-1.677614/54.915279,-1.677529/54.918343,-1.676568/54.909907,+-1.675449/@54.9151866,-1.6850345,15z/data=!3m1!4b1!4m10!4m9!1m0!1m0!1m0!1m0!1m3!2m2!1d-1.675449!2d54.909907!3e2
    //https://maps.google.co.uk/maps?dirflg=w&saddr=54.909907,-1.675449&daddr=54.920770,-1.674837 to:54.912775,-1.677614 to: 54.915279,-1.677529 to:54.918343,-1.676568
    ANDREWS_HOUSE_TO_SUNNISIDE("Andrews House to Sunniside", "This walk is designed to integrate with existing railway services and\n" +
            "is paced at “dog-walking speed” ie, really slowly!", 1.0, 50, "Car Park at Andrews House Station", "Sunniside Station", "https://www.google.co.uk/maps/dir/54.909907,+-1.675449/54.918343,-1.676568/54.915279,-1.677529/54.912775,-1.677614/54.92077,-1.674837/@54.9151866,-1.6850345,15z/data=!3m1!4b1!4m10!4m9!1m3!2m2!1d-1.675449!2d54.909907!1m0!1m0!1m0!1m0!3e2", " suitable for sturdy buggies and powered wheelchairs (this\n" +
            "track undulates)", "#DD000000", R.drawable.slideshow5),
    ANDREWS_HOUSE_TO_CAUSEY_ARCH("Andrews House to Causey Arch", "This walk is designed to integrate with existing railway services\n" +
            "and is paced at “dog-walking speed” ie, really slowly!", 1.5, 30, "Car Park at Andrews House Station", "Causey Station", "https://www.google.co.uk/maps/dir/54.909907,+-1.675449/54.904855,+-1.683923/54.901117,+-1.683258/54.900701,+-1.683969/54.898033,+-1.687533/54.897459,+-1.686932/@54.9027443,-1.6851075,1888m/data=!3m1!1e3!4m26!4m25!1m3!2m2!1d-1.675449!2d54.909907!1m3!2m2!1d-1.683923!2d54.904855!1m3!2m2!1d-1.683258!2d54.901117!1m3!2m2!1d-1.683969!2d54.900701!1m3!2m2!1d-1.687533!2d54.898033!1m3!2m2!1d-1.686932!2d54.897459!3e2", " this walk is not suitable for pushchairs and the less\n" +
            "mobile. Sturdy footwear and suitable clothing is recommended.\n" +
            "The valley bottom can be soggy. ", "#DD000000", R.drawable.slideshow5),

    CAUSEY_ARCH_TO_EAST_TANFIELD_AND_RETURN("Causey Arch to East Tanfield & Return", "This walk is designed to integrate with existing railway services\n" +
            "and is paced at “dog-walking speed” ie, really slowly! ", 2 , 45, "Causey Arch Station", "Causey Arch Station", "https://www.google.co.uk/maps/dir/54.897422,-1.687372/54.897416,-1.687136/54.897403,-1.688971/54.890703,-1.696706/54.890814,-1.696288/54.891338,-1.695022/54.896947,-1.687372/@54.8932615,-1.6937424,936m/data=!3m1!1e3!4m2!4m1!3e2", "This walk is not suitable for pushchairs and the less mobile. Sturdy footwear and suitable clothing is recommended", "#DD000000", R.drawable.slideshow5)


    ;
//https://maps.google.co.uk/maps?dirflg=w&saddr=54.909907,-1.675449&daddr=54.920770,-1.674837 to:54.912775,-1.677614 to: 54.915279,-1.677529 to:54.918343,-1.676568



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

    @Override
    public int getId() {
        return this.ordinal();
    }

    @Override
    public int getPictureID() {
        return getImage();
    }

    @Override
    public String getTitle() {
        return getRouteName();
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
