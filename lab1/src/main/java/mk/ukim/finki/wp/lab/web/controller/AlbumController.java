package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlbumController {
    private final AlbumService albumService;
    private final SongService songService;

    public AlbumController(SongService songService, AlbumService albumService){
        this.albumService=albumService;
        this.songService=songService;
    }

    @GetMapping("/albums")
    public String getAlbumsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("albumList",albumService.albumList());
        return "albumSongs";
    }

    @PostMapping("/albums/add")
    public String saveAlbum(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String genre,
            @RequestParam String releaseYear,
            Model model){

        Album album=albumService.addAlbum(id,name, genre, releaseYear);
        model.addAttribute("albumList",albumService.albumList());
        return  "albumList";
    }

    @PostMapping("/albums/edit/{albumId}")
    public String editSong(@PathVariable Long albumId,
                           @RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           Model model){
        Album album = albumService.modifyAlbum(albumId,id,name, genre, releaseYear);
        model.addAttribute("albumList",albumService.albumList());
        return "albumList";
    }

    @GetMapping("/albums/delete/{id}")
    public String delteAlbum(@PathVariable Long id, Model model){
        albumService.deleteAlbumById(id);
        model.addAttribute("albumList",albumService.albumList());
        return "albumList";
    }
    @GetMapping("/albums/edit-form/{id}")
    public String editForm(Model model,@PathVariable Long id){
        model.addAttribute("albumList",albumService.findAll());
        Album album=albumService.findById(id);
        model.addAttribute("album",album);
        return "add-album";
    }
}

