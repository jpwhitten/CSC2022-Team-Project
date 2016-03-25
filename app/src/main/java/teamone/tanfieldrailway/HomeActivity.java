package teamone.tanfieldrailway;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ObjectInputStream;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FacebookPostFragment.OnListFragmentInteractionListener {

	Activity thisActivity = this;

	private Boolean isHistoryCollapsed = true;
	private Boolean isEventsCollapsed = true;
	private Boolean isKidsCollapsed = true;

	RelativeLayout navHome;
	RelativeLayout navHistory;
	RelativeLayout navEvents;
	RelativeLayout navMap;
	RelativeLayout navLiveJourney;
	RelativeLayout navKids;
	RelativeLayout navWalkingRoutes;
	RelativeLayout navDirections;

	ImageView scanButton;


	LinearLayout submenuHistory;
	LinearLayout submenuEvents;
	LinearLayout submenuKids;

	int NAV_MARGIN_HOME = 450;
	int NAV_MARGIN = 250;

	ImageView historyIndicator;
	ImageView eventsIndicator;
	ImageView kidsIndicator;

	AnimationDrawable historyIndicatorAnimation;
	AnimationDrawable eventsIndicatorAnimation;
	AnimationDrawable kidsIndicatorAnimation;

	DrawerLayout drawer;
	TreasureHuntManager treasureHuntManager;
	ViewPager mViewPager;

	ObjectInputStream is;

	private static ListViewFragment specialEventsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);

		final HomeFragment fragment = new HomeFragment();
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("");

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		positionNavDrawerUnderLogo();

		scanButton = (ImageView) findViewById(R.id.scanButton);

		scanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(thisActivity, ScanActivity.class);
				if(treasureHuntManager != null){
					i.putExtra("teamone.tanfieldrailway.TreasureHuntManager", treasureHuntManager);
				}
				startActivity(i);

			}
		});

		navHome = (RelativeLayout) findViewById(R.id.nav_home);
		navHistory = (RelativeLayout) findViewById(R.id.nav_history);
		navEvents = (RelativeLayout) findViewById(R.id.nav_events);
		navMap = (RelativeLayout) findViewById(R.id.nav_map);
		navLiveJourney = (RelativeLayout) findViewById(R.id.nav_live_journey);
		navKids = (RelativeLayout) findViewById(R.id.nav_kids);
		navWalkingRoutes = (RelativeLayout) findViewById(R.id.nav_walking_routes);
		navDirections = (RelativeLayout) findViewById(R.id.nav_directions);

		submenuHistory = (LinearLayout) findViewById(R.id.nav_history_submenu);
		submenuEvents = (LinearLayout) findViewById(R.id.nav_events_submenu);
		submenuKids = (LinearLayout) findViewById(R.id.nav_kids_submenu);

		navHome.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

		historyIndicator = (ImageView) findViewById(R.id.history_indicator);
		eventsIndicator = (ImageView) findViewById(R.id.events_indicator);
		kidsIndicator = (ImageView) findViewById(R.id.kids_indicator);

		historyIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);
		eventsIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);
		kidsIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);


		try {
			FacebookPost.getPosts(getApplicationContext(), new Response.Listener<List<FacebookPost>>() {
				@Override
				public void onResponse(List<FacebookPost> response) {
					//TODO: Add option to attempt reload of Facebook feed on exception
					Log.i("FB", "onResponse");
					if (response != null) {
						FacebookPost.ITEMS = response;
						FacebookPostFragment fb = new FacebookPostFragment();
						FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
						transaction.replace(R.id.fragment_container, fb);
						transaction.commit();
					}

				}
			});
		} catch (VolleyError volleyError) {
			volleyError.printStackTrace();
		}

		Bundle extras = getIntent().getExtras();
			if (extras != null && extras.containsKey("teamone.tanfieldrailway.TreasureHuntManager")) {
				//Log.println(100, "", "Treasure Hunt Manager Created");
				treasureHuntManager = extras.getParcelable("teamone.tanfieldrailway.TreasureHuntManager");
				if (treasureHuntManager.treasureFound) {

					setMenuColors();
					selectMenuItem(navKids, false);
					selectMenuItem(submenuKids, true);
					ScrollView sv = (ScrollView) findViewById(R.id.nav_menu);
					FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sv.getLayoutParams();
					layoutParams.topMargin = NAV_MARGIN;
					sv.setLayoutParams(layoutParams);


					Bundle bundle = new Bundle();
					bundle.putParcelable("teamone.tanfieldrailway.TreasureHuntManager", treasureHuntManager);

					TreasureFragment treasureFragment = new TreasureFragment();
					treasureFragment.setArguments(bundle);
					fragmentTransaction.replace(R.id.fragment_container, treasureFragment);
					fragmentTransaction.addToBackStack(null);
					setTitle(treasureFragment.getTitle());
				}

			} else {
				treasureHuntManager = new TreasureHuntManager();
			}


		getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override
			public void onBackStackChanged() {

				Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
				if(f instanceof HomeFragment || f instanceof FacebookPostFragment){
					positionNavDrawerUnderLogo();
					setTitle("");
				}else{
					findViewById(R.id.logo).setVisibility(View.INVISIBLE);
				}
				if (f instanceof FragmentTitle) {
					FragmentTitle fragment = (FragmentTitle) f;// do something with f
					setTitle(fragment.getTitle());
				}

			}
		});

		try{
			Event.getEvents(getApplicationContext(), new Response.Listener<Event[]>() {
				@Override
				public void onResponse(final Event[] response) {
					//TODO: Add option to reload special events on error.
					//TODO: Add information explaining that the events could not be fetched on failure.
					if(response == null) return;
					specialEventsListView = new ListViewFragment();
					specialEventsListView.setListViewItems(response, new ListViewCallBack() {
						@Override
						public void itemClicked(int itemID) {
							ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
							viewPagerFragment.setEvents(response, itemID);
							android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
							fragmentTransaction.replace(R.id.fragment_container, viewPagerFragment);
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();
							setTitle(specialEventsListView.getTitle());
						}
					});
				}
			});

		}catch(VolleyError error){
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
		}

		fragmentTransaction.commit();

	}

	private void positionNavDrawerUnderLogo(){
		ScrollView sv = (ScrollView) findViewById(R.id.nav_menu);
		FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sv.getLayoutParams();
		layoutParams.topMargin = NAV_MARGIN_HOME;
		sv.setLayoutParams(layoutParams);

		findViewById(R.id.logo).setVisibility(View.VISIBLE);
	}
	public void onNavClick(View v) {

		Boolean shouldClose = true;
		Boolean isHome = false;

		int id = v.getId();

		final android.support.v4.app.FragmentTransaction fragmentTransaction =
				getSupportFragmentManager().beginTransaction();

		switch(id) {
			case R.id.nav_home:

				isHome = true;

				setMenuColors();
				selectMenuItem(v, false);

				positionNavDrawerUnderLogo();
				HomeFragment homeFragment = new HomeFragment();
				fragmentTransaction.replace(R.id.fragment_container, homeFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(homeFragment.getTitle());

				if(FacebookPost.ITEMS != null){
					FacebookPostFragment fb = new FacebookPostFragment();
					FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					transaction.replace(R.id.fragment_container, fb);
					transaction.commit();
				}

				break;

			case R.id.nav_history:

				shouldClose = false;

				if(isHistoryCollapsed){
					expand(submenuHistory);
					isHistoryCollapsed = false;
					historyIndicator.setBackgroundResource(R.drawable.nav_indicator_expand);
					historyIndicatorAnimation = (AnimationDrawable) historyIndicator.getBackground();
					historyIndicatorAnimation.start();


					collapseEvents();
					collapseKids();

				} else {
					collapseHistory();
				}
				break;

			case R.id.nav_timeline:

				setMenuColors();
				selectMenuItem(navHistory, false);
				selectMenuItem(submenuHistory, true);
				setTitle("Timeline");
				break;

			case R.id.nav_carriages:

				setMenuColors();
				selectMenuItem(navHistory, false);
				selectMenuItem(submenuHistory, true);

				ListViewFragment carriagesListView = new ListViewFragment();
				carriagesListView.setTitle("Carriages");
				carriagesListView.setListViewItems(Carriage.values(), new ListViewCallBack() {
					@Override
					public void itemClicked(int itemID) {
						CarriageFragment.carriage = Carriage.values()[itemID];

						CarriageFragment carriageFragment = new CarriageFragment();
						FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
						transaction.replace(R.id.fragment_container, carriageFragment);
						transaction.addToBackStack(null);
						transaction.commit();
						setTitle(CarriageFragment.carriage.getTitle());

					}
				});

				fragmentTransaction.replace(R.id.fragment_container, carriagesListView);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(carriagesListView.getTitle());
				break;

			case R.id.nav_trains:

				setMenuColors();
				selectMenuItem(navHistory, false);
				selectMenuItem(submenuHistory, true);
				setTitle("Trains");
				break;

			case R.id.nav_events:

				shouldClose = false;
				if(isEventsCollapsed){
					expand(submenuEvents);
					isEventsCollapsed = false;
					eventsIndicator.setBackgroundResource(R.drawable.nav_indicator_expand);
					eventsIndicatorAnimation = (AnimationDrawable) eventsIndicator.getBackground();
					eventsIndicatorAnimation.start();

					collapseHistory();
					collapseKids();

				} else {
					collapseEvents();
				}
				break;

			case R.id.nav_daily_events:

				setMenuColors();
				selectMenuItem(navEvents, false);
				selectMenuItem(submenuEvents, true);

				DailyEventsFragment dailyFragment = new DailyEventsFragment();
				setTitle(dailyFragment.getTitle());
				fragmentTransaction.replace(R.id.fragment_container, dailyFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case R.id.nav_special_events:
				if(specialEventsListView == null){
					break;
				}
				setMenuColors();
				selectMenuItem(navEvents, false);
				selectMenuItem(submenuEvents, true);
				setTitle(specialEventsListView.getTitle());




				fragmentTransaction.replace(R.id.fragment_container, specialEventsListView);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case R.id.nav_map:

				setMenuColors();
				selectMenuItem(v, false);
				setTitle("Map");
				break;

			case R.id.nav_live_journey:

				setMenuColors();
				selectMenuItem(v, false);
				setTitle("Live Journey");
				break;

			case R.id.nav_kids:

				shouldClose = false;
				if(isKidsCollapsed){
					expand(submenuKids);
					isKidsCollapsed = false;
					kidsIndicator.setBackgroundResource(R.drawable.nav_indicator_expand);
					kidsIndicatorAnimation = (AnimationDrawable) kidsIndicator.getBackground();
					kidsIndicatorAnimation.start();

					collapseHistory();
					collapseEvents();

				} else {
					collapseKids();
				}
				break;

			case R.id.nav_quiz:

				setMenuColors();
				selectMenuItem(navKids, false);
				selectMenuItem(submenuKids, true);

				QuizFragment quizFragment = new QuizFragment();
				fragmentTransaction.replace(R.id.fragment_container, quizFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(quizFragment.getTitle());
				break;

			case R.id.nav_treasure_hunt:

				setMenuColors();
				selectMenuItem(navKids, false);
				selectMenuItem(submenuKids, true);

				Bundle bundle = new Bundle();
				bundle.putParcelable("teamone.tanfieldrailway.TreasureHuntManager", treasureHuntManager);

				TreasureFragment treasureFragment = new TreasureFragment();
				treasureFragment.setArguments(bundle);
				fragmentTransaction.replace(R.id.fragment_container, treasureFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(treasureFragment.getTitle());
				break;

			case R.id.nav_walking_routes:

				setMenuColors();
				selectMenuItem(v, false);
				ListViewFragment listFragment = new ListViewFragment();

				final WalkingRouteFragment walkingRoutesFragment = new WalkingRouteFragment();
				listFragment.setTitle(walkingRoutesFragment.getTitle());
				listFragment.setListViewItems(WalkingRoutes.values(), new ListViewCallBack() {
					@Override
					public void itemClicked(int itemID) {

						walkingRoutesFragment.setWalkingRoute(WalkingRoutes.values()[itemID]);
						android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
						fragmentTransaction.replace(R.id.fragment_container, walkingRoutesFragment);
						fragmentTransaction.addToBackStack(null);
						fragmentTransaction.commit();
						setTitle(walkingRoutesFragment.getTitle());
					}
				});

				fragmentTransaction.replace(R.id.fragment_container, listFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(walkingRoutesFragment.getTitle());
				break;

			case R.id.nav_directions:

				setMenuColors();
				selectMenuItem(v, false);

				DirectionFragment directionFragment = new DirectionFragment();
				fragmentTransaction.replace(R.id.fragment_container, directionFragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				setTitle(directionFragment.getTitle());
				break;

			default: break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		if(shouldClose) {
			drawer.closeDrawer(GravityCompat.START);
			collapseHistory();
			collapseKids();
			collapseEvents();
			if(!isHome) {
				ScrollView sv = (ScrollView) findViewById(R.id.nav_menu);

				FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sv
						.getLayoutParams();

				layoutParams.topMargin = NAV_MARGIN;
				sv.setLayoutParams(layoutParams);

				findViewById(R.id.logo).setVisibility(View.INVISIBLE);
			}
		}



	}


	private void setMenuColors() {
		navHome.setBackgroundColor(Color.parseColor("#DD000000"));
		navHistory.setBackgroundColor(Color.parseColor("#DD000000"));
		navEvents.setBackgroundColor(Color.parseColor("#DD000000"));
		navMap.setBackgroundColor(Color.parseColor("#DD000000"));
		navLiveJourney.setBackgroundColor(Color.parseColor("#DD000000"));
		navKids.setBackgroundColor(Color.parseColor("#DD000000"));
		navWalkingRoutes.setBackgroundColor(Color.parseColor("#DD000000"));
		navDirections.setBackgroundColor(Color.parseColor("#DD000000"));
		submenuEvents.setBackgroundColor(Color.parseColor("#DD000000"));
		submenuHistory.setBackgroundColor(Color.parseColor("#DD000000"));
		submenuKids.setBackgroundColor(Color.parseColor("#DD000000"));
	}


	private void selectMenuItem(View menu, Boolean isSub) {

		if(isSub)
			menu.setBackgroundColor(getResources()
			.getColor(R.color.colorPrimaryDark));
		else
			menu.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
	}

	private void collapseHistory() {
		if(!isHistoryCollapsed) {
			collapse(submenuHistory);
			isHistoryCollapsed = true;
			historyIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);
			historyIndicatorAnimation = (AnimationDrawable) historyIndicator.getBackground();
			historyIndicatorAnimation.start();
		}

	}

	private void collapseEvents() {
		if(!isEventsCollapsed) {
			collapse(submenuEvents);
			isEventsCollapsed = true;
			eventsIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);
			eventsIndicatorAnimation = (AnimationDrawable) eventsIndicator.getBackground();
			eventsIndicatorAnimation.start();
		}
	}

	private void collapseKids() {
		if(!isKidsCollapsed) {
			collapse(submenuKids);
			isKidsCollapsed = true;
			kidsIndicator.setBackgroundResource(R.drawable.nav_indicator_collapse);
			kidsIndicatorAnimation = (AnimationDrawable) kidsIndicator.getBackground();
			kidsIndicatorAnimation.start();
		}
	}

	public static void expand(final View v) {
		v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		final int targetHeight = v.getMeasuredHeight();

		// Older versions of android (pre API 21) cancel animations for views with a height of 0.
		v.getLayoutParams().height = 1;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1
						? LinearLayout.LayoutParams.WRAP_CONTENT
						: (int)(targetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);

		int id = v.getId();
	}

	public static void collapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();

		Animation a = new Animation()
		{
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if(interpolatedTime == 1){
					v.setVisibility(View.GONE);
				}else{
					v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}


	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);

		return true;
	}


	@Override
	public void onListFragmentInteraction(FacebookPost item) {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement

		return super.onOptionsItemSelected(item);
	}



	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.


		return true;
	}
}
