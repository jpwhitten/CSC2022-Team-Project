package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;

/**
 * Created by Joshua on 16/03/2016.
 */
public enum Carriage implements Row{
    FOUR_CMPT_THIRD_BRAKE("4 Cmpy Third Brake", "MSLR", "Gorton", "1885", "4", "888", "TR No. 8", "Marley Hill Carriage Shed", R.drawable.no8),
    FIVE_CMPT_COMPOSITE("5 cmpt composite","GNR","Doncaster","1888","4","6032","TR No. 5","Marley Hill Carriage Shed", R.drawable.no5),
    FIVE_CMPT_THIRD("5 cmpt third","GNR","Doncaster","1885","4","1441","TR No. 6","Marley Hill Carriage Shed", R.drawable.slideshow1),
    FIVE_CMPT_THIRD_TWO("5 cmpt third","NER","York","1878","4","256","TR No. 9","Marley Hill 5 Road Shed", R.drawable.five_cmpt_third_two),
    BRAKE_VAN_NO7("Lambton Railway, Brake Van No. 7","Lambton Hetton and Joicey Collieries","LHJC Philadelphia workshops","1946","4","7", "Unknown","Marley Hill 5 Road Shed", R.drawable.lbv),
    OFFICERS_SALOON("Officers' saloon", "NER","York","1875","4","876","TR No. 7","Marley Hill Carriage Shed", R.drawable.slideshow1),
    SALOON_BRAKE("Saloon brake","LNER","Pickering","1945","4","Unknown","TR No. 3","Marley Hill Carriage Shed",R.drawable.slideshow1),
    VERANDAH_SALOON("Verandah saloon","BR","Swindon","1956","4","NULL","TR No. 2","Marley Hill Carriage Shed", R.drawable.no2),
    VERANDAH_SALOON_2("Verandah saloon","GWR","Swindon","1911","4","28899","TR No. 1","Marley Hill Carriage Shed", R.drawable.no1);

    //carriageName	company	builder	period	wheels	companyNumber	tanfieldNumber	location	imageURL
    private String carriageName;
    private String company;
    private String builder;
    private String period;
    private String wheels;
    private String companyNumber;
    private String tanfieldNumber;
    private String location;
    private int image;

    Carriage(String carriageName, String company, String builder, String period, String wheels, String companyNumber, String tanfieldNumber, String location, int image) {
        this.carriageName = carriageName;
        this.company = company;
        this.builder = builder;
        this.period = period;
        this.wheels = wheels;
        this.companyNumber = companyNumber;
        this.tanfieldNumber = tanfieldNumber;
        this.location = location;
        this.image = image;
    }

    @Override
    public String getDescription() {
        return location;
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

    @Override
    public String getColor() {
        return "#AB000000";
    }
}
