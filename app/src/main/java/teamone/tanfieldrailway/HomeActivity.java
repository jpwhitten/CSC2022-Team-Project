package teamone.tanfieldrailway;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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


import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);

		HomeFragment fragment = new HomeFragment();
		android.support.v4.app.FragmentTransaction fragmentTransaction =
				getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("");

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		ScrollView sv = (ScrollView) findViewById(R.id.nav_menu);

		FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sv.getLayoutParams();

		layoutParams.topMargin = NAV_MARGIN_HOME;
		sv.setLayoutParams(layoutParams);

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

	}

	public void onNavClick(View v) {

		Boolean shouldClose = true;
		Boolean isHome = false;

		int id = v.getId();

		android.support.v4.app.FragmentTransaction fragmentTransaction =
				getSupportFragmentManager().beginTransaction();

		switch(id) {
			case R.id.nav_home:

				isHome = true;

				setMenuColors();
				selectMenuItem(v, false);

				ScrollView sv = (ScrollView) findViewById(R.id.nav_menu);

				FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sv
						.getLayoutParams();

				layoutParams.topMargin = NAV_MARGIN_HOME;
				sv.setLayoutParams(layoutParams);

				findViewById(R.id.logo).setVisibility(View.VISIBLE);
				HomeFragment homeFragment = new HomeFragment();
				fragmentTransaction.replace(R.id.fragment_container, homeFragment);
				fragmentTransaction.commit();
				setTitle("");
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
				setTitle("Carriages");
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
				setTitle("Daily Events");
				DailyEventsFragment dailyFragment = new DailyEventsFragment();
				fragmentTransaction.replace(R.id.fragment_container, dailyFragment);
				fragmentTransaction.commit();
				break;

			case R.id.nav_special_events:

				setMenuColors();
				selectMenuItem(navEvents, false);
				selectMenuItem(submenuEvents, true);
				setTitle("Special Events");
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
				fragmentTransaction.commit();
				setTitle("Quiz");
				break;

			case R.id.nav_treasure_hunt:

				setMenuColors();
				selectMenuItem(navKids, false);
				selectMenuItem(submenuKids, true);

				TreasureFragment treasureFragment = new TreasureFragment();
				fragmentTransaction.replace(R.id.fragment_container, treasureFragment);
				fragmentTransaction.commit();
				setTitle(treasureFragment.getTitle());
				break;

			case R.id.nav_walking_routes:

				setMenuColors();
				selectMenuItem(v, false);
				ListViewFragment listFragment = new ListViewFragment();


				listFragment.setListViewItems(WalkingRoutes.values(), new ListViewCallBack() {
					@Override
					public void itemClicked(int itemID) {
						WalkingRouteFragment walkingRoutesFragment = new WalkingRouteFragment();
						walkingRoutesFragment.setWalkingRoute(WalkingRoutes.values()[itemID]);
						android.support.v4.app.FragmentTransaction fragmentTransaction =
								getSupportFragmentManager().beginTransaction();
						fragmentTransaction.replace(R.id.fragment_container, walkingRoutesFragment);
						fragmentTransaction.commit();
						setTitle("Walking Routes");
					}
				});

				fragmentTransaction.replace(R.id.fragment_container, listFragment);
				fragmentTransaction.commit();
				setTitle("Walking Routes");
				break;

			case R.id.nav_directions:

				setMenuColors();
				selectMenuItem(v, false);

				DirectionFragment directionFragment = new DirectionFragment();
				fragmentTransaction.replace(R.id.fragment_container, directionFragment);
				fragmentTransaction.commit();
				setTitle("Directions");
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			startActivity(new Intent(HomeActivity.this, ScanActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		return true;
	}
}
