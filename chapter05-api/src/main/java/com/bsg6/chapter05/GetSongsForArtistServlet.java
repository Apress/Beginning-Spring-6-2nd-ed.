package com.bsg6.chapter05;

import com.bsg6.chapter03.MusicService;
import com.bsg6.chapter03.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/songs")
public class GetSongsForArtistServlet extends HttpServlet {
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ApplicationContext context = (ApplicationContext) req
                .getServletContext()
                .getAttribute("context");
        MusicService service = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();

        String artist = req.getParameter("artist");

        if (artist == null ) {
            log("Missing data in request: requires artist parameter");
            resp.setStatus(500);
        } else {
            List<Song> data=service.getSongsForArtist(artist);

            resp.setStatus(200);
            resp.getWriter().println(
                    mapper.writeValueAsString(data)
            );
        }
    }
}
