package net.androidsquad.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.androidsquad.movieapp.adapters.MovieRecyclerView;
import net.androidsquad.movieapp.adapters.OnMovieListener;
import net.androidsquad.movieapp.models.MovieModel;
import net.androidsquad.movieapp.request.Servicey;
import net.androidsquad.movieapp.response.MovieResponse;
import net.androidsquad.movieapp.response.MovieSearchResponse;
import net.androidsquad.movieapp.utils.Credentials;
import net.androidsquad.movieapp.utils.MovieApi;
import net.androidsquad.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    // RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;


    // ViewModel
    private MovieListViewModel movieListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);



        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);



        ConfigureRecyclerView();
        ObserveAnyChange();
        searchMovieApi("fast", 1);



    }


    // Observing any data change
    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null){
                    for (MovieModel movieModel: movieModels){
                        // Get the data in log
                        movieRecyclerAdapter.setmMovies(movieModels);

                    }
                }


            }
        });




    }


    // 4- Calling method in Main Activity
    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query, pageNumber);
    }



    // 5- Intializing recyclerView & adding data to it
    private void ConfigureRecyclerView(){
        // LIve Data can't be passed via the constructor
        movieRecyclerAdapter = new MovieRecyclerView( this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMovieClick(int position) {

        Toast.makeText(this, "The Position "  +position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCategoryClick(String category) {

    }






}

