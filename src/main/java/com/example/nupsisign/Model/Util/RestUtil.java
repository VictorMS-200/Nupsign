package com.example.nupsisign.Model.Util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtil {

        private RestUtil() {
        }

        public static URI getUri(String id) {
            return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
        }
}
