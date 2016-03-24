package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;

/**
 * Created by Joshua on 16/03/2016.
 */
public enum Carriage implements Row{
    FOUR_CMPT_THIRD_BRAKE("4 Cmpy Third Brake", "MSLR", "Gorton", "1885", "4", "888", "TR No. 8", "Marley Hill Carriage Shed", R.drawable.no8, R.drawable.no8),
    FIVE_CMPT_COMPOSITE("5 cmpt composite","GNR","Doncaster","1888","4","6032","TR No. 5","Marley Hill Carriage Shed", R.drawable.no5, R.drawable.no5),
    FIVE_CMPT_THIRD("5 cmpt third","GNR","Doncaster","1885","4","1441","TR No. 6","Marley Hill Carriage Shed", R.drawable.slideshow1, R.drawable.slideshow1),
    FIVE_CMPT_THIRD_TWO("5 cmpt third","NER","York","1878","4","256","TR No. 9","Marley Hill 5 Road Shed", R.drawable.five_cmpt_third_two, R.drawable.five_cmpt_third_two),
    BRAKE_VAN_NO7("Lambton Railway, Brake Van No. 7","Lambton Hetton and\nJoicey Collieries","LHJC Philadelphia workshops","1946","4","7", "Unknown","Marley Hill 5 Road Shed", R.drawable.lbv, R.drawable.lbv, R.string.LambtonRailwayDescription),
    OFFICERS_SALOON("Officers' saloon", "NER","York","1875","4","876","TR No. 7","Marley Hill Carriage Shed", R.drawable.slideshow1, R.drawable.slideshow1),
    SALOON_BRAKE("Saloon brake","LNER","Pickering","1945","4","Unknown","TR No. 3","Marley Hill Carriage Shed",R.drawable.slideshow1, R.drawable.slideshow1),
    VERANDAH_SALOON("Verandah saloon","BR","Swindon","1956","4","NULL","TR No. 2","Marley Hill Carriage Shed", R.drawable.no2, R.drawable.no2),
    VERANDAH_SALOON_2("Verandah saloon","GWR","Swindon","1911","4","28899","TR No. 1","Marley Hill Carriage Shed", R.drawable.no1, R.drawable.no1);

    //carriageName	company	builder	period	wheels	companyNumber	tanfieldNumber	location	imageURL
    private String carriageName;
    private String company;
    private String builder;
    private String period;
    private String wheels;
    private String companyNumber;
    private String tanfieldNumber;
    private String location;
    private int description = -1;
    private int image;
    private int biggerImage;

    Carriage(String carriageName, String company, String builder, String period, String wheels, String companyNumber, String tanfieldNumber, String location, int image, int biggerImage) {
        this.carriageName = carriageName;
        this.company = company;
        this.builder = builder;
        this.period = period;
        this.wheels = wheels;
        this.companyNumber = companyNumber;
        this.tanfieldNumber = tanfieldNumber;
        this.location = location;
        this.image = image;
        this.biggerImage = biggerImage;
    }

    Carriage(String carriageName, String company, String builder, String period, String wheels, String companyNumber, String tanfieldNumber, String location, int image, int biggerImage, int description) {
        this.carriageName = carriageName;
        this.company = company;
        this.builder = builder;
        this.period = period;
        this.wheels = wheels;
        this.companyNumber = companyNumber;
        this.tanfieldNumber = tanfieldNumber;
        this.location = location;
        this.image = image;
        this.biggerImage = biggerImage;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return location;
    }

    public int getIntDesc(){
        return description;
    }

    @Override
    public int getId() {
        return this.ordinal();
    }

    @Override
    public int getPictureID() {
        return image;
    }

    @Override
    public String getTitle() {
        return carriageName;
    }

    @Override
    public Drawable getPicture() {
        return null;
    }

    public int getBiggerImage(){
        return biggerImage;
    }

    public String getCompany() {
        return company;
    }

    public String getBuilder() {
        return builder;
    }

    public String getPeriod() {
        return period;
    }

    public String getWheels() {
        return wheels;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getTanfieldNumber() {
        return tanfieldNumber;
    }

    public String getLocation() {
        return location;
    }



    @Override
    public String getColor() {
        return "#AB000000";
    }

    @Override
    public void pictureDownloaded() {

    }

    @Override
    public void setPictureDownloadedCallback(PictureDownloadedCallback callback) {

    }

}
