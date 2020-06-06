package com.amsidh.mvc.repository;

import com.amsidh.mvc.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.util.Collections.emptyList;

@FeignClient(name = "albums-ws", fallback = AlbumServiceClientFallBack.class)
public interface AlbumServiceClient {

    @GetMapping(value = "/users/{userId}/albums")
    List<AlbumResponseModel> getAlbumsByUserId(@PathVariable(name = "userId", required = true) String userId);
}

@Component
class AlbumServiceClientFallBack implements AlbumServiceClient {

    @Override
    public List<AlbumResponseModel> getAlbumsByUserId(String userId) {
        return emptyList();
    }
}

