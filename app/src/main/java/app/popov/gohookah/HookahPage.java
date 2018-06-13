package app.popov.gohookah;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Fragment;

import com.google.android.gms.maps.SupportMapFragment;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.popov.gohookah.adapters.ViewPagerAdapter;
import app.popov.gohookah.fragments.MapViewFragment;
import app.popov.gohookah.fragments.RatingAndContactsFragment;
import app.popov.gohookah.fragments.ReviewsFragment;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.MyCallback;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;
import app.popov.gohookah.logic.storage.Storage;


public class HookahPage extends AppCompatActivity {
    static TextView textView;
    static ImageView imageView;
    static Context context;
    static FirebaseHookah thisHookah;
    static TextView phone;
    static CollapsingToolbarLayout toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppCompatImageButton addPhoto;
    SetImageCallback callback;
    Snackbar downloadingSnackbar;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (savedInstanceState != null) {
            finish();
        } else {
            position = intent.getIntExtra("position", 0);
        }
        setContentView(R.layout.hookahpagewithcoordinator);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id.hookahPageCoordinator);
        context = getApplicationContext();
        imageView = (ImageView) findViewById(R.id.main_backdrop);
        thisHookah = Firebase.getHookahsList().get(position);
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        scrollView.setFillViewport(true);
        FloatingActionButton phoneButton = (FloatingActionButton) findViewById(R.id.phoneFloatButton);
        if (thisHookah.getPhoneNumber().equals("")) {
            phoneButton.setVisibility(View.INVISIBLE);
        }
        phoneButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    callHookah();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1337);
                }
            } else {
                callHookah();
            }
        });
        phoneButton.setOnLongClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) super.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", thisHookah.getInstagram());
            clipboard.setPrimaryClip(clip);
            makeCopiedSnack(v);
            return true;
        });

        addPhoto = (AppCompatImageButton) findViewById(R.id.addPhotoButton);
        addPhoto.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        });
        callback = new SetImageCallback();
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    if (thisHookah.getImages().size() != 0) {
        Storage.getImage(thisHookah, imageView);
    }
    downloadingSnackbar = Snackbar.make(coordinatorLayout, "Выполняется загрузка...", Snackbar.LENGTH_LONG);
    toolbar.setTitle(thisHookah.getClubName());

        if (thisHookah.getImages().size() != 0){
            addPhoto.setVisibility(View.INVISIBLE);
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("id", thisHookah.getId());
        RatingAndContactsFragment ratingAndContactsFragment = new RatingAndContactsFragment();
        ratingAndContactsFragment.setArguments(bundle);
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        reviewsFragment.setArguments(bundle);
        MapViewFragment mapViewFragment = new MapViewFragment();
        mapViewFragment.setArguments(bundle);
        adapter.addFragment(ratingAndContactsFragment, "Информация");
        adapter.addFragment(reviewsFragment, "Отзывы");
        adapter.addFragment(mapViewFragment, "На карте");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        downloadingSnackbar.show();
                        addPhoto.setVisibility(View.INVISIBLE);
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        byte[] data = IOUtils.toByteArray(imageStream);
                        Firebase.uploadImage(thisHookah.getId(), data, callback);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
        }
    }


    public void setImage() {
        File imagesDirectory = new File(context.getFilesDir() + "/HookahsImages/");
        if (thisHookah.getImages().size() != 0) {
            System.out.println(imagesDirectory + "/" + thisHookah.getImages().get(0));
            imageView.setImageDrawable(Drawable.createFromPath(imagesDirectory + "/" + thisHookah.getImages().get(0)));
            addPhoto.setVisibility(View.INVISIBLE);
        }
        setHeader(thisHookah.getClubName());
    }

    public static void setHeader(String header) {
        toolbar.setTitle(header);
    }

    private void callHookah() {
        Uri uri = Uri.parse("tel:" + thisHookah.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intent);
    }

    private void makeCopiedSnack(View view) {
        Snackbar.make(view, "Скопировано в буфер обмена", Snackbar.LENGTH_SHORT).show();
    }

    public class SetImageCallback implements MyCallback{
        @Override
        public void call(){
            downloadingSnackbar.setText("Загрузка завершена").show();
            thisHookah = Firebase.getHookahs().get(thisHookah.getId());
          Storage.getImage(thisHookah, imageView);
        }
    }

}
