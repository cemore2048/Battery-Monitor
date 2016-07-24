package com.demoji.rafa.demoji;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafa on 7/24/16.
 */
public class ImageDetailActivity extends Activity {

    public static final String EXTRA_ALBUM_ART_RESID = "EXTRA_ALBUM_ART_RESID";

    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.fab)
    ImageButton fab;
    @BindView(R.id.title_panel)
    ViewGroup titlePanel;
    @BindView(R.id.track_panel) ViewGroup trackPanel;
    @BindView(R.id.detail_container) ViewGroup detailContainer;

    private TransitionManager mTransitionManager;
    private Scene mExpandedScene;
    private Scene mCollapsedScene;
    private Scene mCurrentScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity_detail);
        ButterKnife.bind(this);
        populate();
        setupTransitions();
    }

    private Transition createTransition() {
        TransitionSet set = new TransitionSet();
        set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);

        Transition tFab = new Scale();
        tFab.setDuration(150);
        tFab.addTarget(fab);

        Transition tTitle = new Fold();
        tTitle.setDuration(150);
        tTitle.addTarget(titlePanel);

        Transition tTrack = new Fold();
        tTrack.setDuration(150);
        tTrack.addTarget(trackPanel);

        set.addTransition(tTrack);
        set.addTransition(tTitle);
        set.addTransition(tFab);

        return set;
    }

    @OnClick(R.id.pic)
    public void onAlbumArtClick(View view) {
        Transition transition = createTransition();
        TransitionManager.beginDelayedTransition(detailContainer, transition);
        fab.setVisibility(View.INVISIBLE);
        titlePanel.setVisibility(View.INVISIBLE);
        trackPanel.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.track_panel)
    public void onTrackPanelClicked(View view) {
        if (mCurrentScene == mExpandedScene) {
            mCurrentScene = mCollapsedScene;
        }
        else {
            mCurrentScene = mExpandedScene;
        }
        mTransitionManager.transitionTo(mCurrentScene);
    }

    private void setupTransitions() {
        mTransitionManager = new TransitionManager();
        ViewGroup transitionRoot = detailContainer;

        // Expanded scene
        mExpandedScene = Scene.getSceneForLayout(transitionRoot,
                R.layout.image_activity_detail_expanded, this);

        mExpandedScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                ButterKnife.bind(ImageDetailActivity.this);
                populate();
                mCurrentScene = mExpandedScene;
            }
        });

        TransitionSet expandTransitionSet = new TransitionSet();
        expandTransitionSet.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(200);
        expandTransitionSet.addTransition(changeBounds);

        Fade fadeLyrics = new Fade();
        fadeLyrics.addTarget(R.id.lyrics);
        fadeLyrics.setDuration(150);
        expandTransitionSet.addTransition(fadeLyrics);

        // Collapsed scene
        mCollapsedScene = Scene.getSceneForLayout(transitionRoot,
                R.layout.image_activity_detail, this);

        mCollapsedScene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                ButterKnife.bind(ImageDetailActivity.this);
                populate();
                mCurrentScene = mCollapsedScene;
            }
        });

        TransitionSet collapseTransitionSet = new TransitionSet();
        collapseTransitionSet.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);

        Fade fadeOutLyrics = new Fade();
        fadeOutLyrics.addTarget(R.id.lyrics);
        fadeOutLyrics.setDuration(150);
        collapseTransitionSet.addTransition(fadeOutLyrics);

        ChangeBounds resetBounds = new ChangeBounds();
        resetBounds.setDuration(200);
        collapseTransitionSet.addTransition(resetBounds);

        mTransitionManager.setTransition(mExpandedScene, mCollapsedScene, collapseTransitionSet);
        mTransitionManager.setTransition(mCollapsedScene, mExpandedScene, expandTransitionSet);
        mCollapsedScene.enter();

//        postponeEnterTransition();
    }

    private void populate() {
        int albumArtResId = getIntent().getIntExtra(EXTRA_ALBUM_ART_RESID, android.R.drawable.ic_search_category_default);
        pic.setImageResource(albumArtResId);

        Bitmap albumBitmap = getReducedBitmap(albumArtResId);
        colorizeFromImage(albumBitmap);
    }

    private Bitmap getReducedBitmap(int albumArtResId) {
        // reduce image size in memory to avoid memory errors
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 8;
        return BitmapFactory.decodeResource(getResources(), albumArtResId, options);
    }

    private void colorizeFromImage(Bitmap image) {
        Palette palette = Palette.from(image).generate();

        // set panel colors
        int defaultPanelColor = 0xFF808080;
        int defaultFabColor = 0xFFEEEEEE;
        titlePanel.setBackgroundColor(palette.getDarkVibrantColor(defaultPanelColor));
        trackPanel.setBackgroundColor(palette.getLightMutedColor(defaultPanelColor));

        // set fab colors
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_pressed}
        };

        int[] colors = new int[]{
                palette.getVibrantColor(defaultFabColor),
                palette.getLightVibrantColor(defaultFabColor)
        };
//        fab.setBackgroundTintList(new ColorStateList(states, colors));
    }
}
