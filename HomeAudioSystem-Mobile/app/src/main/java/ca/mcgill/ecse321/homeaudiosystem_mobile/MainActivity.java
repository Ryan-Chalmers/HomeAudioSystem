package ca.mcgill.ecse321.homeaudiosystem_mobile;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.HomeAudioSystem.model.Genre;
import ca.mcgill.ecse321.HomeAudioSystem.controller.HomeAudioSystemController;
    import ca.mcgill.ecse321.HomeAudioSystem.controller.InvalidInputException;
import ca.mcgill.ecse321.HomeAudioSystem.model.Album;
import ca.mcgill.ecse321.HomeAudioSystem.model.Artist;
import ca.mcgill.ecse321.HomeAudioSystem.model.HAS;
import ca.mcgill.ecse321.HomeAudioSystem.model.Location;
import ca.mcgill.ecse321.HomeAudioSystem.model.Playlist;
import ca.mcgill.ecse321.HomeAudioSystem.model.Song;
import ca.mcgill.ecse321.HomeAudioSystem.persistence.PersistenceHomeAudioSystem;

    public class MainActivity extends AppCompatActivity {

        private HashMap<Integer, Album> albums;
        private HashMap<Integer, Artist> artists;
        private HashMap<Integer, Song> songs;
        private HashMap<Integer, Playlist> playlists;
        private HashMap<Integer, Location> locations;
        private String error = null;
        private String locationInfo = null;
        private String HASStatusHeader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Context context = this;
        //set the xml file name and location
        PersistenceHomeAudioSystem.setFileName(context.getFilesDir().getAbsolutePath() +
                "homeaudiosystem.xml");
        // load model
        PersistenceHomeAudioSystem.loadHomeAudioSystemModel();

        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void refreshData(){
        // Erase all the text fields
        TextView albumTitle = (TextView) findViewById(R.id.newalbumtitle_name);
        albumTitle.setText("");
        TextView artistName = (TextView) findViewById(R.id.newartistname_name);
        artistName.setText("");
        TextView songTitle = (TextView) findViewById(R.id.newsongtitle_name);
        songTitle.setText("");
        TextView songDuration = (TextView) findViewById(R.id.newsongduration_name);
        songDuration.setText("");
        TextView playlistName = (TextView) findViewById(R.id.newplaylistname_name);
        playlistName.setText("");
        TextView locationName = (TextView) findViewById(R.id.newlocationname_name);
        locationName.setText("");

        HAS has = HAS.getInstance();

        // Initialize the data in the album spinner
        Spinner albumSpinner = (Spinner) findViewById(R.id.albumspinner);
        ArrayAdapter<CharSequence> albumAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        albumAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.albums = new HashMap<Integer, Album>();

        int i = 0;
        for(Iterator<Album> albums = has.getAlbums().iterator();
            albums.hasNext(); i++){
            Album p = albums.next();
            if(p.getSongs().isEmpty())
                albumAdapter.add(p.getTitle() + " (Empty!)");
            else
                albumAdapter.add(p.getTitle());
            this.albums.put(i, p);
        }
        albumSpinner.setAdapter(albumAdapter);


        // Initialize the data in the artist spinner
        Spinner artistSpinner = (Spinner) findViewById(R.id.artistspinner);
        ArrayAdapter<CharSequence> artistAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        artistAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.artists = new HashMap<Integer, Artist>();

        i = 0;
        for(Iterator<Artist> artists = has.getArtists().iterator();
            artists.hasNext(); i++){
            Artist p = artists.next();
            if(p.getSongs().isEmpty())
                artistAdapter.add(p.getName() + " (Empty!)");
            else
                artistAdapter.add(p.getName());
            this.artists.put(i, p);
        }
        artistSpinner.setAdapter(artistAdapter);

        // Initialize the data in the song spinner
        Spinner songSpinner = (Spinner) findViewById(R.id.songspinner);
        ArrayAdapter<CharSequence> songAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        songAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.songs = new HashMap<Integer, Song>();

        i = 0;
        for(Iterator<Song> songs = has.getSongs().iterator();
            songs.hasNext(); i++){
            Song p = songs.next();
            songAdapter.add(p.getTitle());
            this.songs.put(i, p);
        }
        songSpinner.setAdapter(songAdapter);

        // Initialize the data in the playlist spinner
        Spinner playlistSpinner = (Spinner) findViewById(R.id.playlistspinner);
        ArrayAdapter<CharSequence> playlistAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        playlistAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.playlists = new HashMap<Integer, Playlist>();

        i = 0;
        for(Iterator<Playlist> playlists = has.getPlaylists().iterator();
            playlists.hasNext(); i++){
            Playlist p = playlists.next();
            if(p.getSongs().isEmpty())
                playlistAdapter.add(p.getName() + " (Empty!)");
            else
                playlistAdapter.add(p.getName());
            this.playlists.put(i, p);
        }
        playlistSpinner.setAdapter(playlistAdapter);


// Initialize the data in the location spinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        ArrayAdapter<CharSequence> locationAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.locations = new HashMap<Integer, Location>();

        i = 0;
        for(Iterator<Location> locations = has.getLocations().iterator();
            locations.hasNext(); i++){
            Location p = locations.next();
            if (p.getSong() != null)
                locationAdapter.add(p.getName() + " (songAssigned)");
            else if (p.getAlbum() != null)
                locationAdapter.add(p.getName() + " (AlbumAssigned!)");
            else if (p.getPlaylist() != null)
                locationAdapter.add(p.getName() + " (PlaylistAssigned!)");
            else
                locationAdapter.add(p.getName() + "(Empty!) ");
            this.locations.put(i, p);
        }
        locationSpinner.setAdapter(locationAdapter);


        // Initialize the data in the genre album spinner
        Spinner genreSpinner = (Spinner) findViewById(R.id.genrespinner);
        genreSpinner.setAdapter(new ArrayAdapter<Genre>(this, android.R.layout.simple_list_item_1, Genre.values()));

        // show errors if any
        TextView errorDisplay = (TextView)findViewById(R.id.errortodisplay);
        errorDisplay.setTextColor(Color.RED);
        errorDisplay.setText(error);
        // HAS

        displayHASStatus();
    }

    public void displayHASStatus() {
        HAS has = HAS.getInstance();
        HomeAudioSystemController pc = new HomeAudioSystemController();
        //display song info
        HASStatusHeader = "HAS STATUS:\n" + "No music is assigned to the locations: ";
        locationInfo = "";
        int numberOfEmptyLocations =0;
        for (int i=0; i<has.getLocations().size();i++){
            if (has.getLocation(i).getIsPlaying()) {
                if (has.getLocation(i).getSong() != null) {
                    locationInfo += "Playing song " + has.getLocation(i).getSong().getTitle()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
                else if (has.getLocation(i).getAlbum() != null) {
                    locationInfo += "Playing album " + has.getLocation(i).getAlbum().getTitle()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
                else if (has.getLocation(i).getPlaylist() != null) {
                    locationInfo += "Playing playlist " + has.getLocation(i).getPlaylist().getName()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
            }
            else{
                if (has.getLocation(i).getSong() != null) {
                    locationInfo += "Paused song " + has.getLocation(i).getSong().getTitle()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
                else if (has.getLocation(i).getAlbum() != null) {
                    locationInfo += "Paused album " + has.getLocation(i).getAlbum().getTitle()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
                else if (has.getLocation(i).getPlaylist() != null) {
                    locationInfo += "Paused playlist " + has.getLocation(i).getPlaylist().getName()+
                            " at the " + has.getLocation(i).getName() +  " | Volume: " +
                            has.getLocation(i).getVolume()+ " | Total time: " +
                            pc.convertSecondsToTime(has.getLocation(i).getTime()) + "\n";
                }
                else {
                    HASStatusHeader += has.getLocation(i).getName() + ", ";
                    numberOfEmptyLocations++;
                }
            }
        }

        if(numberOfEmptyLocations!=0)
            HASStatusHeader += "\n";
        else
            HASStatusHeader = "HAS STATUS:\n";

        TextView locationDisplay = (TextView)findViewById(R.id.locationinfotodisplay);
        locationDisplay.setTextColor(Color.GREEN);
        locationDisplay.setText(locationInfo);

        TextView hasstatusheader = (TextView)findViewById(R.id.HASstatusHeaderdisplay);
        hasstatusheader.setTextColor(Color.BLUE);
        hasstatusheader.setText(HASStatusHeader);
    }

    //Method to add albums
    public void addAlbum(View v) {
        TextView tvAlbumTitle = (TextView) findViewById(R.id.newalbumtitle_name);

        //extract value of genrespinner
        Spinner genreSpinner=(Spinner) findViewById(R.id.genrespinner);

        //Extract date and convert to appropriate Date format
        TextView tvDate = (TextView) findViewById(R.id.newalbum_date);
        String strDate = tvDate.getText().toString();
        // format of strDate is dd-mm-yyyy, need to convert it to yyyy-mm-dd to create Date Object
        String strDateFormatted = strDate.substring(6) + "-" + strDate.substring(3, 5) + "-" +
                strDate.substring(0,2);
        Date date = java.sql.Date.valueOf(strDateFormatted);

        HomeAudioSystemController pc = new HomeAudioSystemController();
        error = null;
        //calling create event method
        try {
            pc.addAlbum(tvAlbumTitle.getText().toString(), genreSpinner.getSelectedItem().toString(), date);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }

        refreshData();
        }


    //Method to add artists
    public void addArtist(View v) {
        TextView tvArtistName = (TextView) findViewById(R.id.newartistname_name);

        HomeAudioSystemController pc = new HomeAudioSystemController();
        error = null;
        //calling create artist method
        try {
            pc.addArtist(tvArtistName.getText().toString());
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }

        refreshData();
    }

    //Method to add songs
    public void addSong(View v) {
        TextView tvSongTitle = (TextView) findViewById(R.id.newsongtitle_name);
        TextView tvSongDuration = (TextView) findViewById(R.id.newsongduration_name);

        //extract value of albumspinner
        Spinner albumSpinner=(Spinner) findViewById(R.id.albumspinner);
        int albumSelected = albumSpinner.getSelectedItemPosition();

        //extract value of artistspinner
        Spinner artistSpinner = (Spinner) findViewById(R.id.artistspinner);
        int artistSelected = artistSpinner.getSelectedItemPosition();

        HAS has = HAS.getInstance();
        error = "";
//        if (albumSelected < 0)
//            error = error + "Album needs to be selected to add a new song! ";
//        if (artistSelected < 0)
//            error = error + "Artist needs to be selected to add a new song! ";
        error = error.trim();
        //calling create song method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                if(albumSelected<0){
                    if (artistSelected<0){
                        pc.addSong(tvSongTitle.getText().toString(), tvSongDuration.getText().toString(),
                                null, null);
                    }
                    else{
                        pc.addSong(tvSongTitle.getText().toString(), tvSongDuration.getText().toString(),
                                null, has.getArtist(artistSelected));
                    }
                }
                else if (artistSelected<0){
                    pc.addSong(tvSongTitle.getText().toString(), tvSongDuration.getText().toString(),
                            has.getAlbum(albumSelected), null);
                }
                else {
                    pc.addSong(tvSongTitle.getText().toString(), tvSongDuration.getText().toString(),
                            albums.get(albumSelected), artists.get(artistSelected));
                }
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    //Method to add playlists
    public void addPlaylist(View v) {
        TextView tvPlaylistName = (TextView) findViewById(R.id.newplaylistname_name);

        HomeAudioSystemController pc = new HomeAudioSystemController();
        error = null;
        //calling create playlist method
        try {
            pc.addPlaylist(tvPlaylistName.getText().toString());
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        refreshData();
    }

    public void addLocation(View v) {
        TextView tvLocationName = (TextView) findViewById(R.id.newlocationname_name);

        HomeAudioSystemController pc = new HomeAudioSystemController();
        error = null;
        //calling create playlist method
        try {
            pc.addLocation(tvLocationName.getText().toString(), 50);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        refreshData();
    }

    public void addSongToPlaylist(View w){
        //extract value of songpinner
        Spinner songSpinner=(Spinner) findViewById(R.id.songspinner);
        int songSelected = songSpinner.getSelectedItemPosition();

        //extract value of playlistspinner
        Spinner playlistSpinner = (Spinner) findViewById(R.id.playlistspinner);
        int playlistSelected = playlistSpinner.getSelectedItemPosition();

        error = "";
        if (songSelected < 0)
            error = "Song needs to be selected to add a song to a playlist! ";
        if (playlistSelected < 0)
            error = error + "playlist needs to be selected to song to a playlist! ";
        error = error.trim();
        //calling create song method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.addSongToPlaylist(songs.get(songSelected), playlists.get(playlistSelected));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void assignSongToLocation(View w){
        //extract value of songpinner
        Spinner songSpinner=(Spinner) findViewById(R.id.songspinner);
        int songSelected = songSpinner.getSelectedItemPosition();

        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        error = "";
        if (songSelected < 0)
            error = "Song needs to be selected to assign a song to a location! ";
        if (locationSelected < 0)
            error = error + "Location needs to be selected to assign a song to a location! ";
        error = error.trim();
        //calling assignSongToLocation method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.assignSongToLocation(songs.get(songSelected), locations.get(locationSelected));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void assignAlbumToLocation(View w){
        //extract value of albumpinner
        Spinner albumSpinner=(Spinner) findViewById(R.id.albumspinner);
        int albumSelected = albumSpinner.getSelectedItemPosition();

        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        error = "";
        if (albumSelected < 0)
            error = "Album needs to be selected to assign an album to a location! ";
        if (locationSelected < 0)
            error = error + "Location needs to be selected to assign an album to a location! ";
        error = error.trim();
        //calling assignAlbumToLocation method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.assignAlbumToLocation(albums.get(albumSelected), locations.get(locationSelected));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void assignPlaylistToLocation(View w){
        //extract value of playlistpinner
        Spinner playlistSpinner=(Spinner) findViewById(R.id.playlistspinner);
        int playlistSelected = playlistSpinner.getSelectedItemPosition();

        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        error = "";
        if (playlistSelected < 0)
            error = "Playlist needs to be selected to assign a playlist to a location! ";
        if (locationSelected < 0)
            error = error + "Location needs to be selected to assign a playlist to a location! ";
        error = error.trim();
        //calling assignPlaylistToLocation method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.assignPlaylistToLocation(playlists.get(playlistSelected), locations.get(locationSelected));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void playAllLocations(View w){
        HAS has = HAS.getInstance();
        HomeAudioSystemController pc = new HomeAudioSystemController();

        error = "";
        if (has.getLocations().isEmpty())
            error = error + "Locations are not created in HAS! ";
        error = error.trim();

        if (error.length() == 0) {
            pc.playPauseAll(true);
        }
        refreshData();
    }

    public void pauseAll (View w){
        HAS has = HAS.getInstance();
        HomeAudioSystemController pc = new HomeAudioSystemController();

        error = "";
        if (has.getLocations().isEmpty())
            error = error + "Locations are not created in HAS! ";
        error = error.trim();

        if (error.length() == 0) {
            pc.playPauseAll(false);
        }
        refreshData();
    }

    public void playPauseLocation(View w){
        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();
        HAS has = HAS.getInstance();

        error = "";
        if (locationSelected < 0)
            error = error + "Location needs to be selected to be paused! ";
        else if(has.getLocation(locationSelected).getSong()==null &&
                has.getLocation(locationSelected).getAlbum()==null &&
                has.getLocation(locationSelected).getPlaylist()==null)
            error = error + "the selected location doesn't have any music assigned!";
        error = error.trim();
        //calling assignPlaylistToLocation method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            if(has.getLocation(locationSelected).getIsPlaying()) {
                try {
                    pc.playPause(locations.get(locationSelected), false);
                } catch (InvalidInputException e) {
                    error = e.getMessage();
                }
            }
            else{
                try {
                    pc.playPause(locations.get(locationSelected), true);
                } catch (InvalidInputException e) {
                    error = e.getMessage();
                }
            }
        }
        refreshData();
    }

    public void changeVolumeOfLocation(View w){
        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        SeekBar seekBar =(SeekBar) findViewById(R.id.seekBar1);
        int value = seekBar.getProgress();

        error = "";
        if (locationSelected < 0)
            error = "Location needs to be selected to change its volume! ";
        error = error.trim();

        //calling assignAlbumToLocation method
        if (error.length() == 0) {
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.changeVolumeLocation(locations.get(locationSelected), value, 0);
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void muteUnmuteLocation(View w){
        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        error = "";
        if (locationSelected < 0)
            error = "Location needs to be selected to be muted! ";
        error = error.trim();

        //calling assignAlbumToLocation method
        if (error.length() == 0) {
            HAS has = HAS.getInstance();
            HomeAudioSystemController pc = new HomeAudioSystemController();
            int volume = has.getLocation(locationSelected).getVolume();
            int volumeBefore = has.getLocation(locationSelected).getBeforeMuted();
            try {
                if(volume==0)
                    pc.changeVolumeLocation(locations.get(locationSelected),volumeBefore,volume);
                else
                    pc.changeVolumeLocation(locations.get(locationSelected), 0,volume);
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void clearLocation (View w){
        //extract value of locationspinner
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationspinner);
        int locationSelected = locationSpinner.getSelectedItemPosition();

        error = "";
        if (locationSelected < 0)
            error = "Location cannot be empty! ";
        error = error.trim();

        if (error.length() == 0) {
            HAS has = HAS.getInstance();
            HomeAudioSystemController pc = new HomeAudioSystemController();
            try {
                pc.clearLocation(locations.get(locationSelected));
            } catch (InvalidInputException e) {
                error = e.getMessage();
            }
        }
        refreshData();
    }

    public void clearAllLocations (View w){
        HAS has = HAS.getInstance();
        HomeAudioSystemController hasc = new HomeAudioSystemController();
        error = "";
        if (has.getLocations().isEmpty())
            error = error + "No Location is in HAS! ";
        error = error.trim();

        if (error.length() == 0) {
            hasc.clearAllLocation();
        }
        refreshData();
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager() , "datePicker");
    }

    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if(comps.length ==3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month - 1);
        rtn.putInt("year", year);

        return rtn;
    }

    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m+1, y));
    }

}
