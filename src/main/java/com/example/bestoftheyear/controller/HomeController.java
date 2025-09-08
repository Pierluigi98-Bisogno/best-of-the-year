package com.example.bestoftheyear.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bestoftheyear.model.Movie;
import com.example.bestoftheyear.model.Song;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		// Sostituisci "Il Tuo Nome" con il tuo nome reale
		model.addAttribute("name", "Pierluigi");
		return "index";
	}

	@GetMapping("/movies")
	public String movies(Model model) {
		List<Movie> movies = getBestMovies();
		model.addAttribute("movies", movies);
		
		// Aggiungo lista titoli separati da virgole
		String movieTitles = movies.stream()
				.map(Movie::getTitle)
				.reduce((title1, title2) -> title1 + ", " + title2)
				.orElse("");
		model.addAttribute("movieTitles", movieTitles);
		
		return "movies";
	}

	@GetMapping("/songs")
	public String songs(Model model) {
		List<Song> songs = getBestSongs();
		model.addAttribute("songs", songs);
		
		// Aggiungo lista titoli separati da virgole
		String songTitles = songs.stream()
				.map(Song::getTitle)
				.reduce((title1, title2) -> title1 + ", " + title2)
				.orElse("");
		model.addAttribute("songTitles", songTitles);
		
		return "songs";
	}

	// Metodi privati
	private List<Movie> getBestMovies() {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(1, "The Shawshank Redemption"));
		movies.add(new Movie(2, "The Godfather"));
		movies.add(new Movie(3, "The Dark Knight"));
		movies.add(new Movie(4, "Pulp Fiction"));
		movies.add(new Movie(5, "Forrest Gump"));
		return movies;
	}

	private List<Song> getBestSongs() {
		List<Song> songs = new ArrayList<>();
		songs.add(new Song(1, "Bohemian Rhapsody - Queen"));
		songs.add(new Song(2, "Stairway to Heaven - Led Zeppelin"));
		songs.add(new Song(3, "Hotel California - Eagles"));
		songs.add(new Song(4, "Imagine - John Lennon"));
		songs.add(new Song(5, "Sweet Child O' Mine - Guns N' Roses"));
		return songs;
	}
	
	@GetMapping("/movies/{id}")
	public String movieById(@PathVariable int id, Model model) {
		List<Movie> movies = getBestMovies();
		Movie movie = movies.stream()
				.filter(m -> m.getId() == id)
				.findFirst()
				.orElse(null);
		
		if (movie != null) {
			model.addAttribute("movie", movie);
			return "movie-detail";
		} else {
			model.addAttribute("error", "Film non trovato");
			return "error";
		}
	}
	
	@GetMapping("/songs/{id}")
	public String songById(@PathVariable int id, Model model) {
		List<Song> songs = getBestSongs();
		Song song = songs.stream()
				.filter(s -> s.getId() == id)
				.findFirst()
				.orElse(null);
		
		if (song != null) {
			model.addAttribute("song", song);
			return "song-detail";
		} else {
			model.addAttribute("error", "Canzone non trovata");
			return "error";
		}
	}
}