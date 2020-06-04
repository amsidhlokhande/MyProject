package com.amsidh.mvc.repository;

import com.amsidh.mvc.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "albums-ws")
public interface AlbumServiceClient {

    @GetMapping(value = "/users/{userId}/albums")
    public List<AlbumResponseModel> getAlbumsByUserId(@PathVariable(name = "userId", required = true) String userId);

}
