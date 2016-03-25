package teamone.tanfieldrailway;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class TimelineFragment extends Fragment implements FragmentTitle{

    private String title = "Timeline";


    //http://www.tanfield-railway.co.uk/
    TimelineEvent event1600 = new TimelineEvent("???", "Worlds First?", "When was the world's first railway laid? No one is quite confident on a precise date but simple rails have been used to guide vehicles for centuries,4 " +
            "definitely as far back as the middle ages and possibly even in ancient Greece. What is certain is that in the early 1600s a Nottinghamshire businessman called Huntingdon Beaumont came to Northumberland and laid rails from collieries near Blyth to a shipping point on the coast.\n" +
            "\n" +
            "His waggonway used horses hauling wooden waggons on wooden wheels on wooden rails, however this was not a financial success. It did though provide the spark of progression from primitive wooden rails over short distances, to a railway network which would change Britain and the world forever.", R.drawable.slideshow1);

    TimelineEvent event1650 = new TimelineEvent("1600", "Tyneside Roads", "From the mid 1600 onwards waggonways and the Tyneside coal industry became linked so closely that they were known throughout the rest of Britain as 'Tyneside Roads'. A network of lines linked collieries on both sides of the Tyne to the river." + "\n" +
            "It is no coincidence that the North East was the area where waggonways took greatest hold, because canal building was impossible due to deep valleys and steep hills. What set the rail systems of Tyneside apart from all others was its use of the flanged wheel - a key element of the modern railway as we know it.", R.drawable.slideshow1);

    TimelineEvent event1725 = new TimelineEvent("1725", "A Revelation", "When the Tanfield Railway - or waggonway as it was known at the time - was built in 1725, it was a revelation. Its massive engineering was unlike anything else in its era, " +
            "or even since the Roman Empire. It was a triumph of engineering over nature, a clear signal that a new industrial age was upon the world, and that railways would play a massive part.", R.drawable.slideshow1);

    TimelineEvent event1960 = new TimelineEvent("1960", "Preservation", "By the late 1960s it was becoming apparent that steam was soon to be a thing of the past on our railways. Not just on high profile main line passenger trains, but even on the varied colliery and industrial railways that were such a major part of the North East landscape.\n" +
            "\n" +
            "The region that gave the practical and efficient railway to the world was in danger of allowing that legacy to disappear and be forgotten. Railway museums and preservation schemes were appearing elsewhere in the country, but nothing north of Yorkshire was on the horizon.\n" +
            "\n" +
            "A small group of like-minded people agreed that something ought to be done to redress the balance. They began looking for a location that could form the base for a collection of locomotives that were built and worked locally.", R.drawable.slideshow1);

    TimelineEvent event1970 = new TimelineEvent("1970", "???", "In 1970 the National Coal Board's Marley Hill engine shed was closed. The nearby Beamish Museum stepped in to use the building as a store. But, our group of like-minded enthusiasts managed to agree with Beamish that they could work on the railway items housed there as well as bringing some locomotives that they had acquired on to site too.\n" +
            "\n" +
            "The first locomotive to steam at Marley Hill in preservation was Malleable No.5 from the Beamish collection and the group also worked on the repairs to the museum's North Eastern Railway 'C' Class locomotive. Within a couple of years, Beamish had their own railway centred around a recreated colliery and the rebuilt Rowley station. Their stock was moved from Marley Hill to their current site.\n" +
            "\n" +
            "A new connecting curve was built from the shed yard at Marley Hill and onto the Tanfield branch itself. In 1977 the first passenger trains were run on half of a mile of track between Marley Hill and Bowes Bridge. By 1982 track had reached Sunniside, a mile from Marley Hill.\n" +
            "\n" +
            "Attention then turned South to the track bed from Terrace Junction to Andrews House, Causey and the site of the former East Tanfield colliery. After rebuilding the partially demolished Gibraltar Bridge at Andrews House, track laying continued through a newly built station and on towards Causey.", R.drawable.slideshow1);

    TimelineEvent event1992 = new TimelineEvent("1992", "???", "The railway between Andrews House and Causey was opened in 1992 and the following year the first trains made it to East Tanfield, although a platform there was not opened to the public until 1997. Throughout this period locomotives, carriages and waggons were restored, buildings erected at stations and workshops built at Marley Hill to form a complete operational railway.\n" +
            "\n" +
            "Since 1997 development of more buildings at Marley Hill to store the now extensive collection has taken precedence as well as the growing task of maintaining the operational railway. Construction of a new station building at East Tanfield was begun in 2012 and completion is hoped for by the end of 2014. Plans exist for further development at both East Tanfield and Marley Hill and in the future there could be potential to extend the railway along the former Bowes Railway track bed, West to Byermoor.\n" +
            "\n" +
            "All of this has only been made possible thanks to the dedication of the hundreds of volunteers who have given up their time and energy over the years, and of course our visitors, who by paying to ride our trains ensure that the railway will survive for future generations.", R.drawable.slideshow1);

    ArrayList<TimelineEvent> events = new ArrayList<>();

    RelativeLayout timeline;
    ScrollView info;

    TextView infoDate;
    TextView infoDescription;
    TextView infoTitle;

    ImageView infoBack;
    ImageView infoImage;

    RelativeLayout dateOne;
    RelativeLayout dateTwo;
    RelativeLayout dateThree;
    RelativeLayout dateFour;
    RelativeLayout dateFive;
    RelativeLayout dateSix;

    RelativeLayout innerDateOne;
    RelativeLayout innerDateTwo;
    RelativeLayout innerDateThree;
    RelativeLayout innerDateFour;
    RelativeLayout innerDateFive;
    RelativeLayout innerDateSix;

    TextView textDateOne;
    TextView textDateTwo;
    TextView textDateThree;
    TextView textDateFour;
    TextView textDateFive;
    TextView textDateSix;

    RelativeLayout currentlySelectedDate;
    RelativeLayout currentlySelectedDateBox;


    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        events.add(event1600);
        events.add(event1650);
        events.add(event1725);
        events.add(event1960);
        events.add(event1970);
        events.add(event1992);

        timeline = (RelativeLayout) view.findViewById(R.id.timeline);
        info = (ScrollView) view.findViewById(R.id.timeline_info);
        info.setVisibility(View.GONE);

        infoDate = (TextView) view.findViewById(R.id.timeline_info_date);
        infoDescription = (TextView) view.findViewById(R.id.timeline_info_description);
        infoTitle = (TextView) view.findViewById(R.id.timeline_info_title);

        infoImage = (ImageView) view.findViewById(R.id.timeline_info_image);
        infoBack = (ImageView) view.findViewById(R.id.timeline_info_back_to_button);


        dateOne = (RelativeLayout) view.findViewById(R.id.timeline_date_one);
        dateTwo = (RelativeLayout) view.findViewById(R.id.timeline_date_two);
        dateThree = (RelativeLayout) view.findViewById(R.id.timeline_date_three);
        dateFour = (RelativeLayout) view.findViewById(R.id.timeline_date_four);
        dateFive = (RelativeLayout) view.findViewById(R.id.timeline_date_five);
        dateSix = (RelativeLayout) view.findViewById(R.id.timeline_date_six);

        innerDateOne = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_one);
        innerDateTwo = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_two);
        innerDateThree = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_three);
        innerDateFour = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_four);
        innerDateFive = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_five);
        innerDateSix = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_six);

        textDateOne = (TextView) view.findViewById(R.id.timeline_date_one_text);
        textDateTwo = (TextView) view.findViewById(R.id.timeline_date_two_text);
        textDateThree = (TextView) view.findViewById(R.id.timeline_date_three_text);
        textDateFour = (TextView) view.findViewById(R.id.timeline_date_four_text);
        textDateFive = (TextView) view.findViewById(R.id.timeline_date_five_text);
        textDateSix = (TextView) view.findViewById(R.id.timeline_date_six_text);

        textDateOne.setText(events.get(0).getDate());
        textDateTwo.setText(events.get(1).getDate());
        textDateThree.setText(events.get(2).getDate());
        textDateFour.setText(events.get(3).getDate());
        textDateFive.setText(events.get(4).getDate());
        textDateSix.setText(events.get(5).getDate());

        currentlySelectedDateBox = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_one);
        currentlySelectedDate = (RelativeLayout) view.findViewById(R.id.timeline_date_inner_box_one);

        dateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateColours(dateOne, innerDateOne);
                setInfo(0);
                currentlySelectedDate = dateOne;
                currentlySelectedDateBox = innerDateOne;
            }
        });

        dateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo(1);
                setDateColours(dateTwo, innerDateTwo);
                currentlySelectedDate = dateTwo;
                currentlySelectedDateBox = innerDateTwo;
            }
        });

        dateThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo(2);
                setDateColours(dateThree, innerDateThree);
                currentlySelectedDate = dateThree;
                currentlySelectedDateBox = innerDateThree;
            }
        });

        dateFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo(3);
                setDateColours(dateFour, innerDateFour);
                currentlySelectedDate = dateFour;
                currentlySelectedDateBox = innerDateFour;
            }
        });

        dateFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo(4);
                setDateColours(dateFive, innerDateFive);
                currentlySelectedDate = dateFive;
                currentlySelectedDateBox = innerDateFive;
            }
        });

        dateSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfo(5);
                setDateColours(dateSix, innerDateSix);
                currentlySelectedDate = dateSix;
                currentlySelectedDateBox = innerDateSix;
            }
        });

        infoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setVisibility(View.GONE);
                timeline.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    public void setDateColours(RelativeLayout selected, RelativeLayout selectedBox) {
        currentlySelectedDate.setBackgroundColor(Color.parseColor("#777777"));
        currentlySelectedDateBox.setBackgroundColor(Color.parseColor("#222222"));
        selected.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        selectedBox.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void setInfo(int index) {
        timeline.setVisibility(View.GONE);
        info.setVisibility(View.VISIBLE);
        infoDate.setText(events.get(index).getDate());
        infoDescription.setText(events.get(index).getDescription());
        infoImage.setImageResource(events.get(index).getImage());
        infoTitle.setText(events.get(index).getTitle());
    }


    @Override
    public String getTitle() {
        return title;
    }
}
